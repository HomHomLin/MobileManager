package com.homen.mobilemanager.log;

import com.homen.mobilemanager.HomenApp;

import org.apache.log4j.Logger;

/**
 * Created by linhonghong on 2015/12/30.
 */
public class HomenLoger {
    private static boolean mbLog = false;

    private static HomenLoger mHomenLoger;

    public void enableLog(boolean enalbe) {
        mbLog = enalbe;
    }

    public boolean isLogEnalbed() {
        return mbLog;
    }

    public static HomenLoger getLogger(){
        if(mHomenLoger == null){
            mHomenLoger = new HomenLoger();
        }
        return mHomenLoger;
    }

    public void i(String tag, String msg) {
        if (false == mbLog || tag == null || msg == null)
            return;

        Logger.getLogger(tag).info(msg);
    }

    public void d(String tag, String msg) {
        if (false == mbLog || tag == null || msg == null)
            return;

        Logger.getLogger(tag).debug(msg);
    }

    public void e(String tag, String msg) {
        if (false == mbLog || tag == null || msg == null)
            return;

        Logger.getLogger(tag).error(msg);
    }

    public void w(String tag, String msg) {
        if (false == mbLog || tag == null || msg == null)
            return;
        Logger.getLogger(tag).warn(msg);
    }

}
