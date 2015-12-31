package com.homen.mobilemanager.file.util;

import java.io.File;

/**
 * Created by linhonghong on 2015/12/30.
 */
public class FileUtil {

//    public File[] getRootDirectoryFileList(){
//        return getDirectoryFileList(E)
//    }

    public File[] getDirectoryFileList(String path){
        File file = new File(path);
        if(file.isDirectory()) {
            File[] files = file.listFiles();
            return files;
        }
        return null;
    }
}
