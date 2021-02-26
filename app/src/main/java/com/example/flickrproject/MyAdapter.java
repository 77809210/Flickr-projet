package com.example.flickrproject;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;

import java.util.ArrayList;
import java.util.Vector;

public class MyAdapter extends BaseAdapter { //this classnis a bridge between UI component and data source that helps us to fill data in IU component.
    private Context context = null;
    static Vector vector = new Vector<String>(); // this vector store url from that i get from JSONObject
    ArrayList<Bitmap> myList = new ArrayList<Bitmap>();
    LayoutInflater inflter;
    public MyAdapter(Context context)
    {
        this.context = context;
        inflter = (LayoutInflater.from(context));
    }

     // returns the number of objects present in our list
    @Override
    public int getCount() {
        return myList.size();
    }
    // return an element of our list according to its position
    @Override
    public Bitmap getItem(int position)
    {
        return myList.get(position);
    }
    //returns the id of an element of our list according to its position
    @Override
    public long getItemId(int position)
    {
        return myList.indexOf(getItem(position));
    }

    public void add(Bitmap image) {
        myList.add(image);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
           // convertView = LayoutInflater.from(context_).inflate(R.layout.texviewlayout, parent, false);
            convertView = LayoutInflater.from(context).inflate(R.layout.bitmaplayout, parent, false);
        }
         //TextView tv = (TextView) convertView.findViewById(R.id.textView2) ;
         //tv.setText(vector.get(position).toString());
        ImageView iv = (ImageView)convertView.findViewById(R.id.imageS);
        RequestQueue queue = MySingleton.getInstance(parent.getContext()).getRequestQueue();
        Response.Listener<Bitmap> rep_listener = response ->
        {
            iv.setImageBitmap(response);
        };
        Response.ErrorListener errorListener = reponse ->
        {
            Log.i("Error", "impossible to download");
        };
        ImageRequest ir = new ImageRequest(vector.get(position).toString(),rep_listener,2000,2000,ImageView.ScaleType.CENTER,Bitmap.Config.RGB_565,errorListener);
        queue.add(ir);
        return convertView;

    }
    public void  dd(String url) //this function adds received url in my vector
    {
        vector.add(url);
    }
}
