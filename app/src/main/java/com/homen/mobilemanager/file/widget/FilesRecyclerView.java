package com.homen.mobilemanager.file.widget;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.homen.mobilemanager.R;
import com.homen.mobilemanager.file.util.FileUtil;

import java.io.File;

/**
 * Created by linhonghong on 16/1/17.
 */
public class FilesRecyclerView extends RecyclerView{
    private LinearLayoutManager mLinearLayoutManager;
    private OnFileItemClickListener mOnFileItemClickListener;
    private File[] mFileListData;

    private FilesRecyclerViewAdapter mAdapter;
    private String mCurrentPath;

    public interface OnFileItemClickListener{
        public void onClick(View v);
    }

    public FilesRecyclerView(Context context) {
        this(context, null);
    }

    public FilesRecyclerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FilesRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initFilesRecyclerView();
    }

    public void setFilePath(String path){
        this.mCurrentPath = path;
        mFileListData = FileUtil.getDirectoryFileList(mCurrentPath);
        if(mAdapter != null){
            mAdapter.notifyDataSetChanged();
        }
    }

    public String getCurrentPath(){
        return this.mCurrentPath;
    }

    public File[] getFiles(){
        return this.mFileListData;
    }

    public void setOnFileItemClickListener(OnFileItemClickListener onFileItemClickListener){
        this.mOnFileItemClickListener = onFileItemClickListener;
    }

    private void initFilesRecyclerView(){
        mAdapter = new FilesRecyclerViewAdapter();
        mLinearLayoutManager = new LinearLayoutManager(getContext());
        this.setHasFixedSize(true);
        this.setLayoutManager(mLinearLayoutManager);
        this.setAdapter(mAdapter);
    }

    public class FilesRecyclerViewAdapter extends
            RecyclerView.Adapter<FilesRecyclerViewAdapter.ViewHolder> implements OnClickListener{

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.tv_file_name:
                    String path = (String)v.getTag(R.id.tag_id_path);
                    File[] files = FileUtil.getDirectoryFileList(path);
                    if(files != null){
                        mCurrentPath = path;
                        mFileListData = files;
                        if(mAdapter != null) {
                            mAdapter.notifyDataSetChanged();
                        }
                    }
                    if(mOnFileItemClickListener != null){
                        mOnFileItemClickListener.onClick(v);
                    }
                    break;
            }
        }

        public class ViewHolder extends RecyclerView.ViewHolder{
            public TextView mFileName;

            public ViewHolder(View itemView) {
                super(itemView);
                mFileName = (TextView)itemView.findViewById(R.id.tv_file_name);
                mFileName.setOnClickListener(FilesRecyclerViewAdapter.this);
            }
        }

        @Override
        public FilesRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = View.inflate(getContext(), R.layout.list_file_item, null);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(FilesRecyclerViewAdapter.ViewHolder holder, int position) {
            holder.mFileName.setTag(R.id.tag_id_path,mFileListData[position].getAbsolutePath());
            holder.mFileName.setText(mFileListData[position].getName());
        }

        @Override
        public int getItemCount() {
            return mFileListData == null ? 0 : mFileListData.length;
        }
    }
}
