package com.homen.mobilemanager.file.widget.fresco;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import android.util.AttributeSet;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.homen.mobilemanager.HomenGlobalDef;

/**
 * Created by linhonghong on 2016/1/18.
 */
public class FrescoImageView extends SimpleDraweeView {

    private String mUrl = null;
    private String mPath = null;
    private int  mDefaultResID = 0;

    private ImageRequest mRequest;

    private boolean mAnim = true;//默认开启动画

    private DraweeController mController;

    public FrescoImageView(Context context, GenericDraweeHierarchy hierarchy) {
        super(context, hierarchy);
    }

    public FrescoImageView(Context context) {
        super(context);
    }

    public FrescoImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FrescoImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    private void setController(int resid){
        if(resid == 0){
            return;
        }

        mRequest = ImageRequestBuilder.newBuilderWithResourceId(resid)
                .setLocalThumbnailPreviewsEnabled(true)
                .build();

        mController = Fresco.newDraweeControllerBuilder()
                .setImageRequest(mRequest)
                .setAutoPlayAnimations(mAnim)
                .setOldController(this.getController())
                .build();

        this.setController(mController);
    }

    private void setController(Uri uri, Uri lowResUri){

        if(uri == null){
            return;
        }

        mRequest = ImageRequestBuilder.newBuilderWithSource(uri)
                .setLocalThumbnailPreviewsEnabled(true)
                .build();

        if(ImageRequest.fromUri(lowResUri) == null) {
            mController = Fresco.newDraweeControllerBuilder()
                    .setImageRequest(mRequest)
                    .setAutoPlayAnimations(mAnim)
                    .setOldController(this.getController())
                    .build();

        }else{
            mController = Fresco.newDraweeControllerBuilder()
                    .setImageRequest(mRequest)
                    .setLowResImageRequest(ImageRequest.fromUri(lowResUri))
                    .setAutoPlayAnimations(mAnim)
                    .setOldController(this.getController())
                    .build();
        }
        this.setController(mController);
    }

    public void loadView(String lowUrl , String url, String path, int defaultResID) {
        try {
            if (TextUtils.isEmpty(url) || TextUtils.isEmpty(path)) {
                this.getHierarchy().setPlaceholderImage(defaultResID);
                this.setController(defaultResID);
                mUrl = url;
                mPath = path;
                return;
            }
            mUrl = url;
            mPath = path;
            mDefaultResID = defaultResID;

            if (mUrl.startsWith(HomenGlobalDef.HTTP_PERFIX)) {

                Uri uri = Uri.parse(mUrl);
                this.getHierarchy().setPlaceholderImage(defaultResID);

                Uri lowUri = null;

                if(!TextUtils.isEmpty(lowUrl)){
                    lowUri = Uri.parse(mUrl);
                }

                setController(uri ,lowUri);

            } else {
                this.getHierarchy().setPlaceholderImage(defaultResID);
                this.setController(defaultResID);
            }

        }catch (OutOfMemoryError e){

        }

    }

    public void loadView(String url, String path, int defaultResID) {
        this.loadView(null, url, path, defaultResID);
    }

    public void loadView(String path, int defaultRes){
        this.getHierarchy().setPlaceholderImage(defaultRes);
        if(TextUtils.isEmpty(path)){
            this.setController(defaultRes);
            return;
        }
        if(!path.startsWith("file://")){
            path = "file://" + path;
        }
        Uri uri = Uri.parse(path);
        setController(uri , null);
    }

    public void setCornerRadius(float radius){
        RoundingParams roundingParams = RoundingParams.fromCornersRadius(radius);
        this.getHierarchy().setRoundingParams(roundingParams);
    }

    public void setCornerRadius(float radius, int overlay_color){
        RoundingParams roundingParams = RoundingParams.fromCornersRadius(radius).
                setRoundingMethod(RoundingParams.RoundingMethod.OVERLAY_COLOR).
                setOverlayColor(overlay_color);
        this.getHierarchy().setRoundingParams(roundingParams);
    }

    public void setCircle(int overlay_color){
        RoundingParams roundingParams = RoundingParams.asCircle().
                setRoundingMethod(RoundingParams.RoundingMethod.OVERLAY_COLOR).
                setOverlayColor(overlay_color);
        this.getHierarchy().setRoundingParams(roundingParams);
    }

    public void setAnim(boolean b){
        mAnim = b;
    }
}
