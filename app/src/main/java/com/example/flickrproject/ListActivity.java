package com.example.flickrproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuPopupHelper;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Vector;

public class ListActivity extends AppCompatActivity {
    ListView ListImage;
    ImageView imageView;
    private static final String url = "https://www.flickr.com/services/feeds/photos_public.gne?tags=trees&format=json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        ListImage = findViewById(R.id.list);
        imageView = findViewById(R.id.imageS);
        MyAdapter adapt = new MyAdapter(ListImage.getContext()); // there i link my adapteur with mys Listview
        ListImage.setAdapter(adapt);
        AsyncFlickrJSONDataForList task = new AsyncFlickrJSONDataForList(ListActivity.this,adapt);// there i call the asynchrone task witch get the list of url from Flickr server
        task.execute(url); // i execute the url




    }
}



