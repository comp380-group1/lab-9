package com.example.lab_9;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

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
    	new DownloadImageTask().execute("http://www.ecs.csun.edu/ecs/pics/spectra2011/wang_george.jpg");
    }
    
    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        /** The system calls this to perform work in a worker thread and
          * delivers it the parameters given to AsyncTask.execute() */

        protected Bitmap doInBackground(String... urls) {
    		
        	try {
				Bitmap b = BitmapFactory.decodeStream((InputStream)new URL(urls[0]).getContent());
                return b;

			} catch (Exception e) {
				// TODO Auto-generated catch block
				Log.e("lab-9", "Error Downloading File" + e.getMessage());
			}
			return null;
        }
        
        /** The system calls this to perform work in the UI thread and delivers
          * the result from doInBackground() */
        protected void onPostExecute(Bitmap result) {
            ImageView mImageView = (ImageView) findViewById(R.id.imageView1);
            mImageView.setImageBitmap(result);
        }
    }
}

