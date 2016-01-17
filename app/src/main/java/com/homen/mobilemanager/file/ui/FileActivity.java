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
import com.homen.mobilemanager.file.util.FileUtil;
import com.homen.mobilemanager.file.widget.FilesRecyclerView;
import com.homen.mobilemanager.log.HomenLoger;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by linhonghong on 2015/12/30.
 */
public class FileActivity extends AppCompatActivity{

    private FilesRecyclerView mRecyclerViewFile;
    private TextView mBtnReturn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file);
        findViews();
        initData();
    }

    private void initData(){
        mRecyclerViewFile.setFilePath(Environment.getExternalStorageDirectory().getAbsolutePath());
    }

    private void findViews(){
        mBtnReturn = (TextView)this.findViewById(R.id.tv_return_parent);
        mBtnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String parent_path = FileUtil.getParentPath(mRecyclerViewFile.getCurrentPath());
                if(parent_path != null) {
                    mRecyclerViewFile.setFilePath(parent_path);
                }
            }
        });
        mRecyclerViewFile = (FilesRecyclerView)this.findViewById(R.id.rv_file);
    }

}
