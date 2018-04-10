package com.client.leonsaber.adacrawlerserviceapp.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.client.leonsaber.adacrawlerserviceapp.R;
import com.client.leonsaber.adacrawlerserviceapp.entity.ProductInfo;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ProductAdapter extends BaseAdapter{
    private List<ProductInfo> productInfoList;
    private Context context;


    public ProductAdapter(ArrayList<ProductInfo> productInfoList, Context context) {
        this.productInfoList = productInfoList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return productInfoList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = LayoutInflater.from(context).inflate(R.layout.list_child,parent,false);
        TextView product_ID = (TextView) convertView.findViewById(R.id.product_ID);
        TextView product_price = (TextView) convertView.findViewById(R.id.product_price);
        TextView product_name = (TextView) convertView.findViewById(R.id.product_name);
        TextView product_status = (TextView) convertView.findViewById(R.id.product_status);
        TextView product_qty = (TextView) convertView.findViewById(R.id.product_qty);
        TextView product_url = (TextView) convertView.findViewById(R.id.product_url);


        product_ID.setText("ID: " + productInfoList.get(position).getProductID());
        product_price.setText("$ " + productInfoList.get(position).getProductPrice());
        product_name.setText(productInfoList.get(position).getProductName());
        product_status.setText(productInfoList.get(position).getProductStatus());
        int qty = Integer.parseInt(productInfoList.get(position).getProductQty());
        if (qty >= 100) product_qty.setText("QTY: 100+");
        else product_qty.setText("QTY: " + productInfoList.get(position).getProductQty());
        product_url.setText(productInfoList.get(position).getProductURL());

        return convertView;
    }
}
