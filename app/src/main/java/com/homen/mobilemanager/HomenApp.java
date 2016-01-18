package com.homen.mobilemanager;

import android.app.Application;
import android.util.Log;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.homen.mobilemanager.log.HomenLoger;
import com.homen.mobilemanager.log.MainLog4jIniter;

import org.apache.log4j.Level;

/**
 * Created by honghong on 2015/12/30.
 */
public class HomenApp extends Application{
    public static boolean GLOBAL_INITED = false;

    private static HomenApp mApplication;

    public static HomenApp getApplication(){
        return mApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
        initLog4j();
        initFresco();
        HomenLoger.getLogger().enableLog(true);
    }

    private void initLog4j() {
        //add traceremotelog code
        Level level = null;
//        if (!BuildConfig.DEBUG) { //正式发布时，INFO; 目前会将WARN级别日志用于远程连接跟踪
//            level = Level.INFO;
//        }
        new MainLog4jIniter(HomenApp.getApplication()).init(level);
    }

    public void initFresco(){
//        ImagePipelineConfig frescoConfig = OkHttpImagePipelineConfigFactory
//                .newBuilder(getApplication(), MyHttpMgr.Instance().mHttpClient)
//                .build();
//        Fresco.initialize(getApplication(), frescoConfig);
        Fresco.initialize(getApplication());
    }
}
