package com.homen.mobilemanager.file.util;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;

import java.io.File;

/**
 * Created by linhonghong on 2015/12/30.
 */
public class ExternalStorage {

    private Context mContext;

    public ExternalStorage(Context context) {
        mContext = context;
    }

    /**
     * @see android.content.Context#getExternalFilesDir(String)
     */
    public File getExternalFilesDir(String type) {
        File result = null;
        try {
            if (isExternalStorageAvailable()) {
                result = mContext.getExternalFilesDir(type);
            }
            if (result == null) {
                //外置sdcard无效时的兼容处理.
                result = new File(Environment.getExternalStorageDirectory() + "/android/data/");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

//    //Only for android 2.2
//    private File getExternalFilesDir() {
//        File base = new File(
//                Environment.getExternalStorageDirectory(),
//                "android/data/" + mContext.getPackageName());
//        if (!base.exists()) {
//            base.mkdirs();
//        }
//        return base;
//    }

    /**
     * @see android.content.Context#getExternalCacheDir()
     */
    public File getExternalCacheDir() {
        return getExternalFilesDir("cache");
    }

    /**
     * 外置存储是否可用
     */
    public boolean isExternalStorageAvailable() {
        try {
            return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
        } catch (Exception ex) {
            return false;
        }
    }

    public boolean isSdcard(String currentDir) {

        String sdcard_dir = getSdcard().getAbsolutePath();
        if (!TextUtils.isEmpty(currentDir) && !TextUtils.isEmpty(sdcard_dir)) {
            if (currentDir.equals(sdcard_dir) || currentDir.equals(sdcard_dir + "/")) {
                return true;
            }
        }
        return false;
    }

    public File getSdcard() {
        return Environment.getExternalStorageDirectory();
    }

    public String getExternalTransferPath(String file_name) {
        File airdroid = new File(Environment.getExternalStorageDirectory(), "airdroid");
        if (!airdroid.exists()) {
            airdroid.mkdir();
        }
        File transfer = new File(airdroid, "transfer");
        if (!transfer.exists()) {
            transfer.mkdir();
        }
        return transfer.getAbsolutePath() + File.separator + file_name;
    }

    public String getExternalCameraPath(String file_name) {
        File airdroid = new File(Environment.getExternalStorageDirectory(), "airdroid");
        if (!airdroid.exists()) {
            airdroid.mkdir();
        }
        File transfer = new File(airdroid, "camera");
        if (!transfer.exists()) {
            transfer.mkdir();
        }
        return transfer.getAbsolutePath() + File.separator + file_name;
    }

    public String getExternalDownloadPath(String file_name) {
        File airdroid = new File(Environment.getExternalStorageDirectory(), "airdroid");
        if (!airdroid.exists()) {
            airdroid.mkdir();
        }
        File transfer = new File(airdroid, "download");
        if (!transfer.exists()) {
            transfer.mkdir();
        }
        return transfer.getAbsolutePath() + File.separator + file_name;
    }

    public String getExternalTransferDir() {
        File airdroid = new File(Environment.getExternalStorageDirectory(), "airdroid");
        if (!airdroid.exists()) {
            airdroid.mkdir();
        }
        File transfer = new File(airdroid, "transfer");
        if (!transfer.exists()) {
            transfer.mkdir();
        }
        return transfer.getAbsolutePath();
    }

}
