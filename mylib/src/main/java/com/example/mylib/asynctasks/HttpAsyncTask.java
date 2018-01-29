package com.example.mylib.asynctasks;

import android.os.AsyncTask;
import android.util.Log;

/**
 * Created by sergio on 29/1/18.
 */

public class HttpAsyncTask extends AsyncTask<String,Integer,Integer> {

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Log.v("HttpAsyncTask", "DEFINIMOS VARIABLES PREVIAS");
    }

    @Override
    protected Integer doInBackground(String... strings) {
        this.publishProgress(10);
        //Log.v("HttpAsyncTask", "FASE 1" + strings[0]);
        this.publishProgress(20);
        //Log.v("HttpAsyncTask", "FASE 2" + strings[0]);
        this.publishProgress(60);
        //Log.v("HttpAsyncTask", "FASE 3" + strings[0]);
        this.publishProgress(100);
        //Log.v("HttpAsyncTask", "FASE 4" + strings[0]);
        return 100;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        Log.v("HttpAsyncTask", "PORCENTAJE PROGRESO: "+values[0]);
    }

    @Override
    protected void onPostExecute(Integer in) {
        super.onPostExecute(in);
        Log.v("HttpAsyncTask", "SE ACABÓ LA TAREA"+in);
    }
}
