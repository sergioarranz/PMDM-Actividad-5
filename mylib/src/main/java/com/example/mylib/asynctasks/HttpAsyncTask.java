package com.example.mylib.asynctasks;

import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by sergio on 29/1/18.
 */

public class HttpAsyncTask extends AsyncTask<String,Integer,String> {

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Log.v("HttpAsyncTask", "DEFINIMOS VARIABLES PREVIAS");
    }

    @Override
    protected String doInBackground(String... urls) {
        int count;
        String pathfin=null;
        try {
            String root = Environment.getExternalStorageDirectory().toString();

            Log.v("HttpAsyncTask", "DESCARGANDO");
            this.publishProgress(10);
            URL url = new URL(urls[0]);

            URLConnection conection = url.openConnection();
            conection.connect();
            // getting file length
            int lenghtOfFile = conection.getContentLength();

            // input stream to read file - with 8k buffer
            InputStream input = new BufferedInputStream(url.openStream(), 8192);

            // Output stream to write file
            this.publishProgress(20);

            pathfin=root+"/downloadedfile.jpg";
            OutputStream output = new FileOutputStream(pathfin);
            byte data[] = new byte[1024];

            int contador=30;
            long total = 0;
            this.publishProgress(30);

            while ((count = input.read(data)) != -1) {
                total += count;
                contador+=5;
                this.publishProgress(contador);

                // writing data to file
                output.write(data, 0, count);

            }
            this.publishProgress(100);
            // flushing output
            output.flush();

            // closing streams
            output.close();
            input.close();

        } catch (Exception e) {
            Log.e("Error: ", e.getMessage());
        }
        return pathfin;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        Log.v("HttpAsyncTask", "PORCENTAJE PROGRESO: "+values[0]);
    }

    @Override
    protected void onPostExecute(String in) {
        super.onPostExecute(in);
        Log.v("HttpAsyncTask", "SE ACABÓ LA TAREA"+in);
    }
}
