package com.homen.mobilemanager.file.widget;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.homen.mobilemanager.R;
import com.homen.mobilemanager.file.FileBean;
import com.homen.mobilemanager.file.util.FileIconUtil;
import com.homen.mobilemanager.file.util.FileUtil;
import com.homen.mobilemanager.file.widget.fresco.FrescoImageView;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by linhonghong on 16/1/17.
 */
public class FilesRecyclerView extends RecyclerView{
    private LinearLayoutManager mLinearLayoutManager;
    private OnFileItemClickListener mOnFileItemClickListener;
    private File[] mFileListData;

    private FilesRecyclerViewAdapter mAdapter;
    private String mCurrentPath;
    private int mCurrentY;

    public interface OnFileItemClickListener{
        /**
         *
         * @param file 被点击的文件file对象
         * @param path 被点击文件的路径
         * @param parentPath 被点击文件的父路径
         * @param v 被点击的view
         */
        public void onClick(File file, String path, String parentPath, View v);
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
        notifyDataSetChanged();
    }

    private void notifyDataSetChanged(){
        if(mAdapter != null){
            mAdapter.notifyDataSetChanged();
            mCurrentY = 0;
            this.scrollToPosition(0);

        }
    }

    public String getCurrentPath(){
        return this.mCurrentPath;
    }

    public int getCurrentScrollY(){
        return this.mCurrentY;
    }

    public File[] getFiles(){
        return this.mFileListData;
    }

    public int getCurrentPosition(){
        return mLinearLayoutManager.findFirstVisibleItemPosition();
    }

    public void setOnFileItemClickListener(OnFileItemClickListener onFileItemClickListener){
        this.mOnFileItemClickListener = onFileItemClickListener;
    }

    private void initFilesRecyclerView(){
        mCurrentPath = "";
        mCurrentY = 0;
        mFileListData = null;
        mAdapter = new FilesRecyclerViewAdapter();
        this.setItemAnimator(null);
        mLinearLayoutManager = new LinearLayoutManager(getContext());
        mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        this.setHasFixedSize(true);
        this.setLayoutManager(mLinearLayoutManager);
        this.setAdapter(mAdapter);
        this.addOnScrollListener(new FilesRecyclerViewOnScrollListener());
    }

    private class FilesRecyclerViewOnScrollListener extends
            RecyclerView.OnScrollListener{
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            mCurrentY = dy + mCurrentY;
        }
    }

    private class FilesRecyclerViewAdapter extends
            RecyclerView.Adapter<FilesRecyclerViewAdapter.ViewHolder> implements OnClickListener{

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.content:
                    String path = (String)v.getTag(R.id.tag_id_path);
                    String parentPath = mCurrentPath;
                    File file = (File)v.getTag(R.id.tag_id_file);

                    if(mOnFileItemClickListener != null){
                        mOnFileItemClickListener.onClick(file, path, parentPath, v);
                    }

                    if(FileUtil.isDirectory(file)) {
                        mFileListData = FileUtil.getDirectoryFileList(file);
                        mCurrentPath = path;
                        FilesRecyclerView.this.notifyDataSetChanged();
                    }

                    break;
            }
        }

        public class ViewHolder extends RecyclerView.ViewHolder{
            public View mContentView;
            public TextView mFileName;
            public FrescoImageView mFileicon;

            public ViewHolder(View itemView) {
                super(itemView);

                mContentView = itemView.findViewById(R.id.content);

                mFileName = (TextView)itemView.findViewById(R.id.tv_file_name);

                mContentView.setOnClickListener(FilesRecyclerViewAdapter.this);

                mFileicon = (FrescoImageView)itemView.findViewById(R.id.iv_file_icon);
            }
        }

        @Override
        public FilesRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = View.inflate(getContext(), R.layout.list_file_item, null);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(FilesRecyclerViewAdapter.ViewHolder holder, int position) {

            File file = mFileListData[position];

            if(file == null){
                return;
            }

            holder.mContentView.setTag(R.id.tag_id_path, file.getAbsolutePath());
            holder.mContentView.setTag(R.id.tag_id_file, file);

            holder.mFileName.setText(file.getName());

            if(FileUtil.isDirectory(file)){
                holder.mFileicon.loadView(null, R.mipmap.file_icon_folder);
            }else if(FileUtil.isImage(file)){
                int icon = FileIconUtil.getIntance().getFileicon(file.getAbsolutePath());
                holder.mFileicon.loadView(file.getAbsolutePath(), icon);
            }else{
                int icon = FileIconUtil.getIntance().getFileicon(file.getAbsolutePath());
                holder.mFileicon.loadView(null, icon);
            }

        }

        @Override
        public int getItemCount() {
            return mFileListData == null ? 0 : mFileListData.length;
        }
    }
}
