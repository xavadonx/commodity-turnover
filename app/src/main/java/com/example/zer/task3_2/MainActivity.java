package com.example.zer.task3_2;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private boolean isOpen = true;

    private Button startStop;
    private ArrayList<Product> productArrayList;
    private ProductAdapter adapter;
    private ListView productList;

    Handler handler = new Handler();
    Runnable r;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initProductList();

        startStop = (Button) findViewById(R.id.start_stop);
        productList = (ListView) findViewById(R.id.product_list);
        adapter = new ProductAdapter(this, productArrayList);
        productList.setAdapter(adapter);

        startStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isOpen) {
                    isOpen = false;
                    startStop.setText("Start");
                    handler.removeCallbacks(r);
                    Toast.makeText(MainActivity.this, "Торговля остановлена", Toast.LENGTH_LONG).show();
                } else {
                    isOpen = true;
                    startStop.setText("Stop");
                    handler.postDelayed(r, 500);
                    Toast.makeText(MainActivity.this, "Торговля запущена", Toast.LENGTH_LONG).show();
                }
            }
        });

        r = new Runnable() {
            @Override
            public void run() {
                if (buyRandomProducts()) {
                    handler.postDelayed(r, 500);
                } else {
//                    handler.removeCallbacks(r);
                    startStop.performClick();
                }
            }
        };

        handler.postDelayed(r, 500);
    }

    @Override
    protected void onPause() {
        super.onPause();
        handler.removeCallbacks(r);
    }

    private boolean buyRandomProducts() {
        int randomProducts = (int) (Math.random() * productArrayList.size());

        for (int i = 0; i < randomProducts; i++) {
            int index = (int) (Math.random() * productArrayList.size());
            Product p = productArrayList.get(index);
            Integer count = p.getCount() - (int) (Math.random() * 10);

            if (count < 0) {
                Toast.makeText(MainActivity.this,
                        "Товар " + p.getName() + " закончился, торговля остановлена",
                        Toast.LENGTH_LONG).show();
                return false;
            }

            p.setCount(count);
            productArrayList.set(index, p);
        }
        adapter.notifyDataSetChanged();
        return true;
    }

    private void initProductList() {
        productArrayList = new ArrayList<>();
        productArrayList.add(new Product("test1", 100));
        productArrayList.add(new Product("test2", 100));
        productArrayList.add(new Product("test3", 100));
        productArrayList.add(new Product("test4", 100));
        productArrayList.add(new Product("test5", 100));
    }
}
