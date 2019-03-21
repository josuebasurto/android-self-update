package com.josuebasurto.selfupdateapp;

import android.app.Application;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;

import java.util.HashMap;
import java.util.Map;

public class app extends Application {
    @Override
    public void onCreate(){
        super.onCreate();
        final FirebaseRemoteConfig remoteConfig = FirebaseRemoteConfig.getInstance();

        Map<String, Object> defautValue = new HashMap<>();

        defautValue.put(UpdateHelper.KEY_UPDATE_ENABLE,false);
        defautValue.put(UpdateHelper.KEY_UPDATE_VERSION,"1.0");
        defautValue.put(UpdateHelper.KEY_UPDATE_URL,"");

        remoteConfig.setDefaults(defautValue);

        remoteConfig.fetch(5).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Log.d(app.this.getClass().getName(),"Fetched.");
                    remoteConfig.activateFetched();
                }
            }
        });

    }
}
