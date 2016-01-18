package com.homen.mobilemanager.file.widget;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.homen.mobilemanager.R;
import com.homen.mobilemanager.file.FileBean;

import java.util.ArrayList;

/**
 * Created by linhonghong on 2016/1/18.
 */
public class FilesTabsIndicator extends RecyclerView{

    private LinearLayoutManager mLinearLayoutManager;

    private FilesTabIndicatorAdapter mFilesTabIndicatorAdapter;

    private ArrayList<FileBean> mFileBeanList;

    public FilesTabsIndicator(Context context) {
        this(context,null);
    }

    public FilesTabsIndicator(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FilesTabsIndicator(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initFilesTabsIndicator();
    }

    public void initFilesTabsIndicator(){
        mLinearLayoutManager = new LinearLayoutManager(getContext());
        mLinearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mFilesTabIndicatorAdapter = new FilesTabIndicatorAdapter();
        this.setHasFixedSize(true);
        this.setLayoutManager(mLinearLayoutManager);
        this.setAdapter(mFilesTabIndicatorAdapter);
    }

    public void setData(ArrayList<FileBean> fileBeans){
        this.mFileBeanList = fileBeans;
        notifyDataSetChanged();
    }

    private void notifyDataSetChanged(){
        if(mFilesTabIndicatorAdapter != null){
            mFilesTabIndicatorAdapter.notifyDataSetChanged();
        }
    }

    public void removeItem(int position){
        this.mFileBeanList.remove(position);
        notifyDataSetChanged();
    }

    public void addItem(FileBean fileBean){
        this.mFileBeanList.add(fileBean);
        notifyDataSetChanged();
    }

    private class FilesTabIndicatorAdapter extends RecyclerView.Adapter<FilesTabIndicatorAdapter.ViewHolder>{

        public class ViewHolder extends RecyclerView.ViewHolder{
            public TextView mTabName;

            public ViewHolder(View itemView) {
                super(itemView);
                mTabName = (TextView)itemView.findViewById(R.id.tab_name);
            }
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = View.inflate(getContext(), R.layout.widget_files_tabs_indicator, null);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            if(mFileBeanList == null){
                return;
            }
            FileBean fileBean = mFileBeanList.get(position);
            if(fileBean == null){
                return;
            }
            holder.mTabName.setText(fileBean.mTitle);
        }

        @Override
        public int getItemCount() {
            return mFileBeanList == null ? 0 : mFileBeanList.size();
        }
    }

}
