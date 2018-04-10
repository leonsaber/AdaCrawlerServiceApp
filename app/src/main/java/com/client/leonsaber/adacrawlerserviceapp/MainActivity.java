package com.client.leonsaber.adacrawlerserviceapp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.client.leonsaber.adacrawlerserviceapp.controller.HttpRequestController;
import com.client.leonsaber.adacrawlerserviceapp.entity.ProductInfo;
import com.client.leonsaber.adacrawlerserviceapp.service.JsonHandler;
import com.client.leonsaber.adacrawlerserviceapp.view.ProductAdapter;


import java.util.ArrayList;

import java.util.List;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private List<ProductInfo> productData = null;
    private Context context;
    private ProductAdapter productAdapter = null;
    private ListView list_productInfo;
    private String jsonStr = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        //Remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //set content view AFTER ABOVE sequence (to avoid crash)
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        context = MainActivity.this;
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        list_productInfo = (ListView)findViewById(R.id.product_info_list);

        String url = "http://ec2-35-165-121-98.us-west-2.compute.amazonaws.com:8080/v1/product/all";
        listUpdate(url);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.product_all) {

            String url = "http://ec2-35-165-121-98.us-west-2.compute.amazonaws.com:8080/v1/product/all";
            listUpdate(url);
        } else if (id == R.id.product_top_10) {

            String url = "http://ec2-35-165-121-98.us-west-2.compute.amazonaws.com:8080/v1/product/top10";
            listUpdate(url);

        } else if (id == R.id.last_one) {

            String url = "http://ec2-35-165-121-98.us-west-2.compute.amazonaws.com:8080/v1/product/lastOne";
            listUpdate(url);

        } else if (id == R.id.out_of_stack) {

            String url = "http://ec2-35-165-121-98.us-west-2.compute.amazonaws.com:8080/v1/product/outOfStock";
            listUpdate(url);

        } else if (id == R.id.price_L_H) {

            String url = "http://ec2-35-165-121-98.us-west-2.compute.amazonaws.com:8080/v1/product/priceAsc";
            listUpdate(url);

        } else if (id == R.id.recommend_by_price) {
            final EditText inputData = new EditText(this);

            new AlertDialog.Builder(this).setTitle("Your Price")
                    .setView(inputData)
                    .setPositiveButton("Search", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            String input = (String)inputData.getText().toString();
                            String url = "http://ec2-35-165-121-98.us-west-2.compute.amazonaws.com:8080/v1/product/priceRecommend="+input;
                            Pattern pattern = Pattern.compile("[0-9]*");
                            if (input.equals("") || !pattern.matcher(input).matches()) {
                                Toast.makeText(getApplicationContext(), "Input Invalid", Toast.LENGTH_LONG).show();
                            }
                            else {
                                listUpdate(url);
                            }
                        }
                    })
                    .setNegativeButton("Cancel", null)
                    .show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void listUpdate(final String url) {
        new Thread() {
            public void run() {
                HttpRequestController httpRequestController = new HttpRequestController();
                try {
                    jsonStr = httpRequestController.connect(url);
                } catch (Exception e) {
                    System.out.print(e);
                }
                JsonHandler jsonHandler = new JsonHandler();
                productData = jsonHandler.fromJson(jsonStr);
                productAdapter = new ProductAdapter((ArrayList<ProductInfo>)productData, context);
            }
        }.start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        list_productInfo.setAdapter(productAdapter);

        list_productInfo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectURL = productData.get(position).productURL;
                Intent intent = new Intent();
                intent.setData(Uri.parse(selectURL));
                intent.setAction(Intent.ACTION_VIEW);
                startActivity(intent);
            }
        });
    }

}
