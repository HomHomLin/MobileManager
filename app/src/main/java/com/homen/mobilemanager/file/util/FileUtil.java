package com.homen.mobilemanager.file.util;

import android.text.TextUtils;

import java.io.File;

/**
 * Created by linhonghong on 2015/12/30.
 */
public class FileUtil {

//    public File[] getRootDirectoryFileList(){
//        return getDirectoryFileList(E)
//    }

    public static File[] getDirectoryFileList(String path){
        if(TextUtils.isEmpty(path)){
            return null;
        }
        File file = new File(path);
        if(file == null){
            return null;
        }
        if(file.isDirectory()) {
            File[] files = file.listFiles();
            return files;
        }
        return null;
    }

    public static String getParentPath(String path){
        if(TextUtils.isEmpty(path)){
            return null;
        }
        File file = new File(path);
        if(file == null){
            return null;
        }
        File parentFile = file.getParentFile();
        if(parentFile != null) {
            return file.getParentFile().getAbsolutePath();
        }
        return null;
    }
}
