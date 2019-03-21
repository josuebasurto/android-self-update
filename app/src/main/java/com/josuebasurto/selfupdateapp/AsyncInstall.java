package com.josuebasurto.selfupdateapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.widget.Toast;

public class AsyncInstall extends AsyncTask {

    Context context;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Debug("PreExcecute");
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);

        Download();

        String apkPath = "file:///path/to/your.apk";

        if(Install(apkPath)){
            Toast.makeText(context, context.getString(R.string.msg_install_finished), Toast.LENGTH_LONG).show();
            Debug(context.getString(R.string.msg_install_finished));
        } else{
            Toast.makeText(context, context.getString(R.string.msg_install_unsuccesful), Toast.LENGTH_LONG).show();
            Debug(context.getString(R.string.msg_install_unsuccesful));
        }
        Debug("PostExcecute");
    }

    private void Download() {

    }

    private boolean Install(String apkPath) {

    }

    @Override
    protected Object doInBackground(Object[] objects) {
        context = (Context) objects[0];
        Debug("Background");
        return null;
    }

    private void Debug(String message) {
        LogHelper.Debug(this.getClass().getName(), message);
    }
}
