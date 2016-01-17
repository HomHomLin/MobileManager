package com.homen.mobilemanager.file.util;

import android.os.Environment;
import android.text.TextUtils;

import java.io.File;

/**
 * Created by linhonghong on 2015/12/30.
 */
public class FileUtil {
    public static final char separatorChar;

    static {
        separatorChar = System.getProperty("file.separator", "/").charAt(0);
    }

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
        int length = path.length(), firstInPath = 0;
        if (separatorChar == '\\' && length > 2 && path.charAt(1) == ':') {
            firstInPath = 2;
        }
        int index = path.lastIndexOf(separatorChar);
        if (index == -1 && firstInPath > 0) {
            index = 2;
        }
        if (index == -1 || path.charAt(length - 1) == separatorChar) {
            return null;
        }
        if (path.indexOf(separatorChar) == index
                && path.charAt(firstInPath) == separatorChar) {
            return path.substring(0, index + 1);
        }
        return path.substring(0, index);
//        File file = new File(path);
//        if(file == null || !file.exists()){
//            return null;
//        }
//        String parentFile = file.getParent();
//        if(!TextUtils.isEmpty(parentFile)) {
//            return parentFile;
//        }
//        return null;
    }

    /**
     * 获取一个文件的父文件绝对路径
     * @param file
     * @return
     */
    public static String getParentPath(File file){

        if(file == null || !file.exists()){
            return null;
        }
        String parentFile = file.getParent();
        if(!TextUtils.isEmpty(parentFile)) {
            return parentFile;
        }
        return null;
    }

    /**
     * 获得文件后缀
     * @param file_name
     * @return
     */
    public static String getFileExt(String file_name) {
        if (TextUtils.isEmpty(file_name)) {
            return null;
        }
        if (file_name.contains(".")) {
            int from_index = file_name.lastIndexOf(".") + 1;
            if (from_index > file_name.length() - 1) {
                return null;
            } else {
                return file_name.substring(from_index);
            }
        } else {
            return null;
        }
    }
}
