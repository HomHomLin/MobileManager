package com.homen.mobilemanager.file.util;

import android.os.Environment;
import android.text.TextUtils;

import com.homen.mobilemanager.file.FileBean;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by linhonghong on 2015/12/30.
 */
public class FileUtil {
    public static final char separatorChar;
    public static final String separator;

    static {
        separatorChar = System.getProperty("file.separator", "/").charAt(0);
        separator = String.valueOf(separatorChar);
    }

    public static String getRootDirectory(){
        return "/";
    }

    public static String getExternalStorageDirectory(){
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
     * 带有当前目录的filebean集合
     * @param path
     * @return
     */
    public static ArrayList<FileBean> getAllParentFileBeanWithCurrent(String path){
        ArrayList<FileBean> allParent = getAllParentFileBean(path);
        if(allParent == null){
            allParent = new ArrayList<>();
        }
        FileBean fileBean = new FileBean();
        String fileName = getFileName(path);
        if(TextUtils.isEmpty(fileName)){
            fileBean.mTitle = path;
        }else{
            fileBean.mTitle = fileName;
        }
        fileBean.mPosition = 0;
        fileBean.mFilePath = path;
        allParent.add(fileBean);
        return allParent;
    }

    public static ArrayList<FileBean> getAllParentFileBean(String path){
        ArrayList<FileBean> allParent = null;
        do{
            String parent = getParentPath(path);
            if(!TextUtils.isEmpty(parent)){
                if(allParent == null){
                    allParent = new ArrayList<>();
                }
                FileBean fileBean = new FileBean();
                String fileName = getFileName(parent);
                if(TextUtils.isEmpty(fileName)){
                    fileBean.mTitle = parent;
                }else{
                    fileBean.mTitle = fileName;
                }
                fileBean.mPosition = 0;
                fileBean.mFilePath = parent;
                allParent.add(0,fileBean);
            }
            path = parent;
        }while(!TextUtils.isEmpty(path));
        return allParent;
    }

    public static String getFileName(String path) {
        int separatorIndex = path.lastIndexOf(separator);
        return (separatorIndex < 0) ? path : path.substring(separatorIndex + 1, path.length());
    }

    /**
     * 获得一个路径的所有父亲路径
     * @param path
     * @return
     */
    public static ArrayList<String> getAllParentPath(String path){
        ArrayList<String> allParent = null;
        do{
            String parent = getParentPath(path);
            if(!TextUtils.isEmpty(parent)){
                if(allParent == null){
                    allParent = new ArrayList<>();
                }
                allParent.add(parent);
            }
            path = parent;
        }while(!TextUtils.isEmpty(path));
        return allParent;
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
