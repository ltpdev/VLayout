package com.example.gdcp.vlayout;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;

/**
 * Created by asus- on 2017/11/15.
 */

public class DelegateRecyclerAdapter extends DelegateAdapter.Adapter{
    private Context context;
    private LayoutHelper layoutHelper;
    private LayoutInflater layoutInflater;
    private String name;
    public DelegateRecyclerAdapter(Context context,LayoutHelper layoutHelper,String name){
        this.layoutInflater = LayoutInflater.from(context);
        this.layoutHelper = layoutHelper;
        this.context=context;
        this.name=name;
    }


    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return this.layoutHelper;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(layoutInflater.inflate(R.layout.layout_item,parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(position%2==0){
            holder.itemView.setBackgroundColor(0xaa3F51B5);
        }else{
            holder.itemView.setBackgroundColor(0xccFF4081);
        }
        MyViewHolder myViewHolder=(MyViewHolder)holder;
        myViewHolder.name.setText(name+position+"");
    }

    @Override
    public int getItemCount() {
        return 6;
    }
    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView name;
        public MyViewHolder(View itemView) {
            super(itemView);
            name=(TextView)itemView.findViewById(R.id.item_name);
        }
    }


}
