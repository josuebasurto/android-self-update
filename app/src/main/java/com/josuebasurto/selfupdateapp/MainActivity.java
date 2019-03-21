package com.josuebasurto.selfupdateapp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements UpdateHelper.OnUpdateCheckListener {

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    @Override
    public void onUpdateCheckListener(final String urlApp) {
        //Create alert dialog
        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setTitle("New version available")
                .setMessage("Please update to new version to continue.")
                .setPositiveButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d("onUpdateCheckListener","OK");
                        Toast.makeText(context, "" + urlApp, Toast.LENGTH_LONG)
                                .show();
                        AsyncInstall installer = new AsyncInstall();
                        installer.execute(context);
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d("onUpdateCheckListener","NO");

                        dialog.dismiss();
                    }
                })
                .create();
        alertDialog.show();

    }

    /**
     * Initializes Objetcs
     */
    private void init() {
        context = MainActivity.this;

        UpdateHelper.with(this)
                .onUpdateCheck(this)
                .check();
    }


}
