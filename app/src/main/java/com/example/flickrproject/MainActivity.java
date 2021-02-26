package com.example.flickrproject;

import android.app.DownloadManager;
import android.app.VoiceInteractor;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.service.voice.VoiceInteractionSession;
import android.util.Log;
import android.view.View;
import android.view.textclassifier.TextLanguage;
import android.view.textclassifier.TextLinks;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.android.volley.RequestQueue;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Queue;


public class MainActivity extends AppCompatActivity {
    ImageView imageview;
    Button btnDownl;
    Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageview = findViewById(R.id.imageShow);// i link variable imageView to my ImageView UI
        btnDownl = findViewById(R.id.btnDownl); // i link variable variable btnDownl  tu my Button IU
        btnDownl.setOnClickListener(new GetImageOnClickListener()); // when we click on button btnDownl the class GetImageOnClickListener() is called
        next = findViewById(R.id.btnList);

        next.setOnClickListener(new View.OnClickListener() {// when We click to this button using the event that listens a redirection is made to the listActivity
            @Override
            public void onClick(View v) {
                switchActivities();
            }
        });
    }
    private void switchActivities()// this function allows the start of ListActivity
    {
        Intent switchActivittIntent = new Intent(this,ListActivity.class);
        startActivity(switchActivittIntent);
    }

    public class GetImageOnClickListener implements  View.OnClickListener{

        @Override
        public void onClick(View v) {
            new AsynBitmapDownload().execute("https://live.staticflickr.com/4833/45154748454_c7316e460d_m.jpg");
        }
    }

    public class AsynBitmapDownload extends AsyncTask<String, Void, Bitmap> //this class downloads an image given its input URL
    {
        HttpURLConnection httpURLConnection;
        @Override
        protected Bitmap doInBackground(String... strings) {
            URL url = null;
            try {
                url = new URL(strings[0]);
                httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = new BufferedInputStream(httpURLConnection.getInputStream());
                Bitmap temp = BitmapFactory.decodeStream(inputStream);
                return temp;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally
            {
             httpURLConnection.disconnect();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            if(bitmap != null) {
                imageview.setImageBitmap(bitmap);
                Toast.makeText(getApplicationContext(), "Download Successfull", Toast.LENGTH_SHORT);
            }else {
                Toast.makeText(getApplicationContext(), "Download failed", Toast.LENGTH_SHORT);
            }
        }
    }

}