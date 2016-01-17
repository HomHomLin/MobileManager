package com.homen.mobilemanager.file.util;

import android.os.Environment;
import android.text.TextUtils;

import java.io.File;

/**
 * Created by linhonghong on 2015/12/30.
 */
public class FileUtil {

//    public File[] getRootDirectoryFileList(){
//        return getDirectoryFileList(E)
//    }

    public static String getRootDirectory(){
        return Environment.getExternalStorageDirectory().getAbsolutePath();
    }

    /**
     * 获得一个路径下的所有文件
     * @param path
     * @return
     */
    public static File[] getDirectoryFileList(String path){
        if(TextUtils.isEmpty(path)){
            return null;
        }
        File file = new File(path);
        if(file == null || !file.exists()){
            return null;
        }
        if(file.isDirectory()) {
            File[] files = file.listFiles();
            return files;
        }
        return null;
    }

    public static File[] getDirectoryFileList(File file){
        if(isDirectory(file)){
            File[] files = file.listFiles();
            return files;
        }
        return null;
    }

    public static boolean isDirectory(File file){
        if(file != null && file.exists() && file.isDirectory()) {
            return true;
        }
        return false;
    }

    /**
     * 获取一个文件的父文件绝对路径
     * @param path
     * @return
     */
    public static String getParentPath(String path){
        if(TextUtils.isEmpty(path)){
            return null;
        }
        File file = new File(path);
        if(file == null || !file.exists()){
            return null;
        }
        File parentFile = file.getParentFile();
        if(parentFile != null && parentFile.exists()) {
            return file.getParentFile().getAbsolutePath();
        }
        return null;
    }
}
