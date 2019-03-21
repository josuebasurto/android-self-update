package com.josuebasurto.selfupdateapp;

import android.content.Context;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import android.util.Log;

import com.google.firebase.remoteconfig.FirebaseRemoteConfig;

public class UpdateHelper {
    public static String KEY_UPDATE_ENABLE = "isUpdate";
    public static String KEY_UPDATE_VERSION = "version";
    public static String KEY_UPDATE_URL = "updateURL";
    private OnUpdateCheckListener onUpdateCheckListener;
    private Context context;

    /**
     *
     * @param context
     * @param onUpdateCheckListener
     */
    public UpdateHelper(Context context, OnUpdateCheckListener onUpdateCheckListener) {
        this.onUpdateCheckListener = onUpdateCheckListener;
        this.context = context;
    }

    public static Builder with(Context context) {
        return new Builder(context);
    }

    public void Check() {
        FirebaseRemoteConfig remoteConfig = FirebaseRemoteConfig.getInstance();
        boolean update = remoteConfig.getBoolean(KEY_UPDATE_ENABLE);

        Log.d(this.getClass().getName(), "Update: " + (update ? "Yes " : "No"));

        if (update) {

            String currentVersion = remoteConfig.getString(KEY_UPDATE_VERSION);
            String appVersion = getAppVersion(context);
            String updateURL = remoteConfig.getString(KEY_UPDATE_URL);

            Log.d(this.getClass().getName(), "Current: " + currentVersion);
            Log.d(this.getClass().getName(), "App: " + appVersion);

            if (!TextUtils.equals(currentVersion, appVersion) && onUpdateCheckListener != null) {

                Log.d(this.getClass().getName(), "Do Update over Url: " + updateURL);
                onUpdateCheckListener.onUpdateCheckListener(updateURL);
            }
        }

    }

    private String getAppVersion(Context context) {
        String result = "";
        try {
            result = context.getPackageManager().getPackageInfo(context.getPackageName(), 0)
                    .versionName
                    .replaceAll("[a-zA-Z]|-", "");
            Log.d("", "App version detected: " + result);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Interface Listener
     */
    public interface OnUpdateCheckListener {
        void onUpdateCheckListener(String urlApp);
    }

    /**
     * Static Builder
     */
    public static class Builder {

        private Context context;
        private OnUpdateCheckListener onUpdateCheckListener;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder onUpdateCheck(OnUpdateCheckListener onUpdateCheckListener) {
            this.onUpdateCheckListener = onUpdateCheckListener;
            return this;
        }

        public UpdateHelper build() {
            return new UpdateHelper(context, onUpdateCheckListener);
        }

        public UpdateHelper check() {
            UpdateHelper updateHelper = build();
            updateHelper.Check();
            return updateHelper;
        }

    }


}

