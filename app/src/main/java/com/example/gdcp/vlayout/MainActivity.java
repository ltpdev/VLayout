package com.example.gdcp.vlayout;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.ColumnLayoutHelper;
import com.alibaba.android.vlayout.layout.FixLayoutHelper;
import com.alibaba.android.vlayout.layout.FloatLayoutHelper;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.alibaba.android.vlayout.layout.OnePlusNLayoutHelper;
import com.alibaba.android.vlayout.layout.ScrollFixLayoutHelper;
import com.alibaba.android.vlayout.layout.StaggeredGridLayoutHelper;
import com.alibaba.android.vlayout.layout.StickyLayoutHelper;

import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    public DelegateAdapter adapter;
    private List<DelegateAdapter.Adapter>adapters=new LinkedList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView= (RecyclerView) findViewById(R.id.recyclerView);
        VirtualLayoutManager virtualLayoutManager=new VirtualLayoutManager(this);
        recyclerView.setLayoutManager(virtualLayoutManager);
        RecyclerView.RecycledViewPool recycledViewPool= new RecyclerView.RecycledViewPool();
        recyclerView.setRecycledViewPool(recycledViewPool);
        recycledViewPool.setMaxRecycledViews(0,10);
        adapter =new DelegateAdapter(virtualLayoutManager);
        initLinearLayoutHelper(this);
        initGridLayoutHelper(this);
        initScrollFixLayout(this);
        initStaggeredGridLayoutHelper(this);
        initFixLayoutHelper(this);
        initFloatLayoutHelper(this);
        initColumnLayout(this);
        initOnePlusNLayout(this);
        initStickyLayoutHelper(this);
        adapter.setAdapters(adapters);
        recyclerView.setAdapter(adapter);
    }

    private void initLinearLayoutHelper(Context context) {
        LinearLayoutHelper linearLayoutHelper=new LinearLayoutHelper();
        linearLayoutHelper.setDividerHeight(5);
        linearLayoutHelper.setMarginBottom(20);
        linearLayoutHelper.setMargin(20,20,20,20);
        DelegateRecyclerAdapter delegateRecyclerAdapter=new DelegateRecyclerAdapter(context,linearLayoutHelper,"LinearLayoutHelper");
        adapters.add(delegateRecyclerAdapter);
    }


    private void initGridLayoutHelper(Context context) {
        GridLayoutHelper gridLayoutHelper=new GridLayoutHelper(4);
        //自定义设置某些位置的Item的占格数
        gridLayoutHelper.setSpanSizeLookup(new GridLayoutHelper.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (position >5) {
                    return 2;
                }else {
                    return 1;
                }
            }
        });
        //是否填满可用区域
        gridLayoutHelper.setAutoExpand(false);
        DelegateRecyclerAdapter delegateRecyclerAdapter=new DelegateRecyclerAdapter(context,gridLayoutHelper,"GridLayoutHelper");
        adapters.add(delegateRecyclerAdapter);
    }
    //瀑布流布局，可配置间隔高度/宽度,
    private void initStaggeredGridLayoutHelper(Context context) {
        StaggeredGridLayoutHelper staggeredGridLayoutHelper=new StaggeredGridLayoutHelper(3,20);
        StaggeredGridLayoutAdapter staggeredGridLayoutAdapter=new StaggeredGridLayoutAdapter(context,staggeredGridLayoutHelper,"StaggeredGridLayoutHelper");
        adapters.add(staggeredGridLayoutAdapter);
    }

    private  void initFixLayoutHelper(Context context){
        FixLayoutHelper fixLayoutHelper=new FixLayoutHelper(FixLayoutHelper.BOTTOM_LEFT, 200, 200);
        ScrollFixLayoutAdapter fixLayoutAdapter=new ScrollFixLayoutAdapter(context,fixLayoutHelper,"fixlayouthelp");
        adapters.add(fixLayoutAdapter);
    }
    private void  initScrollFixLayout(Context context){
        ScrollFixLayoutHelper scrollFixLayoutHelper = new ScrollFixLayoutHelper(15,15);
        //show_always:总是显示
        //show_on_enter:当页面滚动到这个视图的位置的时候，才显示
        //show_on_leave:当页面滚出这个视图的位置的时候显示
        scrollFixLayoutHelper.setShowType(ScrollFixLayoutHelper.SHOW_ON_ENTER);
        ScrollFixLayoutAdapter scrollFixLayoutAdapter=new ScrollFixLayoutAdapter(context,scrollFixLayoutHelper,"scrollFixLayoutHelper");
        adapters.add(scrollFixLayoutAdapter);

    }


    public void initFloatLayoutHelper(Context context){
        FloatLayoutHelper floatLayoutHelper=new FloatLayoutHelper();
        floatLayoutHelper.setDefaultLocation(20,250);
        ScrollFixLayoutAdapter fixLayoutAdapter=new ScrollFixLayoutAdapter(context,floatLayoutHelper,"floatlayouthelper");
        adapters.add(fixLayoutAdapter);
    }

    public void  initColumnLayout(Context context){
        ColumnLayoutHelper columnLayoutHelper=new ColumnLayoutHelper();
        columnLayoutHelper.setWeights(new float[]{20,20,20,20,20});
        columnLayoutHelper.setMarginBottom(20);
        DelegateRecyclerAdapter columnLayoutAdapter=new DelegateRecyclerAdapter(context,columnLayoutHelper,"ColumnLayoutHelper");
        adapters.add(columnLayoutAdapter);
    }
//一拖N布局，可以配置1-5个子元素,根据个数的不同所呈现的界面也是不一样的,不同个数效果如下：
    public void initOnePlusNLayout(Context context){
        OnePlusNLayoutHelper onePlusNLayoutHelper=new OnePlusNLayoutHelper();
        //设置布局底部与下个布局的间隔
        onePlusNLayoutHelper.setMarginBottom(20);
        DelegateRecyclerAdapter onePlusNLayoutAdapter=new DelegateRecyclerAdapter(context,onePlusNLayoutHelper,"OnePlusNLayoutHelper");
        adapters.add(onePlusNLayoutAdapter);
    }
    public void initStickyLayoutHelper(Context context){
        StickyLayoutHelper stickyLayoutHelper=new StickyLayoutHelper();
        DelegateRecyclerAdapter stickyLayoutAdapter=new DelegateRecyclerAdapter(context,stickyLayoutHelper,"StickyLayoutHelper");
        adapters.add(stickyLayoutAdapter);
    }



















}
