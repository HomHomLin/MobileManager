package com.homen.mobilemanager.file.ui;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.homen.mobilemanager.R;
import com.homen.mobilemanager.log.HomenLoger;

/**
 * Created by linhonghong on 2015/12/30.
 */
public class FileActivity extends AppCompatActivity{

    private RecyclerView mRecyclerViewFile;

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.activity_file);
        findViews();
        HomenLoger.getLogger().i("test", Environment.getExternalStorageDirectory().getAbsolutePath());
    }

    private void findViews(){
        mRecyclerViewFile = (RecyclerView)this.findViewById(R.id.rv_file);
    }

    public class FileAdapter extends RecyclerView.Adapter<FileAdapter.ViewHolder>{

        public class ViewHolder extends RecyclerView.ViewHolder{

            public ViewHolder(View itemView) {
                super(itemView);
            }
        }

        @Override
        public FileAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = View.inflate(mContext,R.layout.list_file_item,null);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(FileAdapter.ViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 0;
        }
    }
}
