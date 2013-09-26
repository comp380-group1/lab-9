package com.example.lab_9;

import java.io.InputStream;
import java.net.URL;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
        
    public void button1_onClick(View view){
    	//Launch asynchronous download task, passing it the URL of the image to download.
    	new DownloadImageAsyncTask().execute("http://www.ecs.csun.edu/ecs/pics/spectra2011/wang_george.jpg");
    }    
    
    private class DownloadImageAsyncTask extends AsyncTask<String, Void, Bitmap> {
    	//To be done by worker thread
        protected Bitmap doInBackground(String... urls) {
    		
        	try {
				Bitmap bitmap = BitmapFactory.decodeStream((InputStream)new URL(urls[0]).getContent());
                return bitmap;

			} catch (Exception e) {
				Log.e("lab-9", "Error Downloading File: " + e.getMessage());
			}
			return null;
        }
        
        //Update UI
        protected void onPostExecute(Bitmap result) {
            ImageView imageView1 = (ImageView) findViewById(R.id.imageView1);
            imageView1.setImageBitmap(result);
        }
    }
}

