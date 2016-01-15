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
import com.homen.mobilemanager.log.HomenLoger;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by linhonghong on 2015/12/30.
 */
public class FileActivity extends AppCompatActivity{

    private RecyclerView mRecyclerViewFile;
    private TextView mBtnReturn;
    private File[] mFileListData;

    private OnFileItemClick mOnFileItemClick;

    private Context mContext;
    private FileAdapter mFileAdapter;
    private String mCurrentPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.activity_file);
        findViews();
        initData();
        setAdapter();
        HomenLoger.getLogger().i("test", Environment.getExternalStorageDirectory().getAbsolutePath());
    }

    private void initData(){
        mOnFileItemClick = new OnFileItemClick();
        mCurrentPath = Environment.getExternalStorageDirectory().getAbsolutePath();
        mFileListData = FileUtil.getDirectoryFileList(mCurrentPath);
    }

    private void findViews(){
        mBtnReturn = (TextView)this.findViewById(R.id.tv_return_parent);
        mBtnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String parent_path = FileUtil.getParentPath(mCurrentPath);
                File[] files = FileUtil.getDirectoryFileList(parent_path);
                if(files != null){
                    mFileListData = files;
                    mCurrentPath = parent_path;
                    mFileAdapter.notifyDataSetChanged();
                }
            }
        });
        mRecyclerViewFile = (RecyclerView)this.findViewById(R.id.rv_file);
    }

    private void setAdapter(){
        mFileAdapter = new FileAdapter();
        mRecyclerViewFile.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerViewFile.setHasFixedSize(true);
        mRecyclerViewFile.setAdapter(mFileAdapter);
    }

    public class FileAdapter extends RecyclerView.Adapter<FileAdapter.ViewHolder>{

        public class ViewHolder extends RecyclerView.ViewHolder{
            public TextView mFileName;

            public ViewHolder(View itemView) {
                super(itemView);
                mFileName = (TextView)itemView.findViewById(R.id.tv_file_name);
                mFileName.setOnClickListener(mOnFileItemClick);
            }
        }

        @Override
        public FileAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = View.inflate(mContext,R.layout.list_file_item,null);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(FileAdapter.ViewHolder holder, int position) {
            holder.mFileName.setTag(R.id.tag_id_path,mFileListData[position].getAbsolutePath());
            holder.mFileName.setText(mFileListData[position].getName());
        }

        @Override
        public int getItemCount() {
            return mFileListData == null ? 0 : mFileListData.length;
        }
    }

    class OnFileItemClick implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            String path = (String)v.getTag(R.id.tag_id_path);
            File[] files = FileUtil.getDirectoryFileList(path);
            if(files != null){
                mCurrentPath = path;
                mFileListData = files;
                mFileAdapter.notifyDataSetChanged();
            }

        }
    }
}
