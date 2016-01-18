package com.homen.mobilemanager.file.ui;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.homen.mobilemanager.R;
import com.homen.mobilemanager.file.FileBean;
import com.homen.mobilemanager.file.util.FileUtil;
import com.homen.mobilemanager.file.widget.FilesRecyclerView;
import com.homen.mobilemanager.file.widget.FilesTabsIndicator;
import com.homen.mobilemanager.log.HomenLoger;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by linhonghong on 2015/12/30.
 */
public class FileActivity extends AppCompatActivity{

    private FilesRecyclerView mRecyclerViewFile;
    private FilesTabsIndicator mFilesTabsIndicator;
    private TextView mBtnReturn;

    private ArrayList<FileBean> mFileBeans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file);
        findViews();
        initData();
    }

    private void initData(){
        initFileBeanData();
        mRecyclerViewFile.setFilePath(FileUtil.getRootDirectory());
        mFilesTabsIndicator.setData(this.mFileBeans);
    }

    private void initFileBeanData(){
        mFileBeans = new ArrayList<FileBean>();
        //添加一个root目录下的filebean
        addFileBeanToData("我的手机",
                FileUtil.getRootDirectory(),
                0);
        addFileBeanToData("sdcard",
                FileUtil.getExternalStorageDirectory(),
                0);
    }

    private void findViews(){
        mFilesTabsIndicator = (FilesTabsIndicator)this.findViewById(R.id.indicator_file_tab);
        mBtnReturn = (TextView)this.findViewById(R.id.tv_return_parent);
        mBtnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String parent_path = FileUtil.getParentPath(mRecyclerViewFile.getCurrentPath());
                if(parent_path != null) {
                    mRecyclerViewFile.setFilePath(parent_path);
                    resetLastRecyclerViewPos();
                }
            }
        });
        mRecyclerViewFile = (FilesRecyclerView)this.findViewById(R.id.rv_file);
        mRecyclerViewFile.setOnFileItemClickListener(new FilesRecyclerView.OnFileItemClickListener() {
            @Override
            public void onClick(File file, String path, String parentPath, View v) {
                if (FileUtil.isDirectory(file)) {
                    addFileBeanToData(file.getName(), parentPath, mRecyclerViewFile.getCurrentScrollY());
                }
            }
        });
    }

    private void addFileBeanToData(String title, String path,int pos){
        FileBean fileBean = new FileBean();
        fileBean.mTitle = title;
        fileBean.mFilePath = path;
        fileBean.mPosition = pos;
        mFileBeans.add(fileBean);
        mFilesTabsIndicator.setData(this.mFileBeans);
    }

    /**
     * 回复到上一个parent的停留位置
     */
    private void resetLastRecyclerViewPos(){
        if(mFileBeans != null && mFileBeans.size() > 1){
            FileBean fileBean = mFileBeans.get(mFileBeans.size() - 1);
            if(mRecyclerViewFile != null){
                mRecyclerViewFile.scrollBy(0, fileBean.mPosition);
            }
            mFileBeans.remove(mFileBeans.size() - 1);
        }
        mFilesTabsIndicator.setData(this.mFileBeans);
    }

}
