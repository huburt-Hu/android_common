package com.app.julie.common.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.app.julie.common.R;
import com.app.julie.common.mvp.BaseViewImplFragment;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hubert
 * <p>
 * Created on 2017/7/6.
 */
public abstract class BaseRvFragment<B, P extends BaseRvContract.Presenter>
        extends BaseViewImplFragment<P>
        implements BaseRvContract.View<B, P> {

    protected RecyclerView recyclerView;
    protected SwipeRefreshLayout refreshLayout;
    protected BaseQuickAdapter<B, BaseViewHolder> mAdapter;
    protected int page;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_recycler_view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        recyclerView = (RecyclerView) view.findViewById(R.id.rv);
        refreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.srl);
        init();
    }

    private void init() {
        refreshLayout.setColorSchemeColors(getRefreshLayoutColor());
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.getData(page = 0);
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = onCreateAdapter(new ArrayList<B>());
        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                mPresenter.getData(++page);
            }
        }, recyclerView);
        recyclerView.setAdapter(mAdapter);
        mAdapter.setEmptyView(R.layout.view_empty);

        refreshLayout.setRefreshing(true);
        mPresenter.getData(page = 0);
    }

    @Override
    public void onFailed() {
        refreshLayout.setRefreshing(false);
        mAdapter.loadMoreComplete();
    }

    @Override
    public void onDataReceived(List<B> beans) {
        refreshLayout.setRefreshing(false);
        mAdapter.loadMoreComplete();
        if (page == 0) {
            mAdapter.setNewData(beans);
        } else {
            if (beans != null && beans.size() > 0) {
                mAdapter.addData(beans);
            } else {
                mAdapter.loadMoreEnd(true);
            }
        }
    }

    /**
     * 子类重写以修改下拉刷新空间的颜色
     *
     * @return int[] 颜色数组
     */
    public int[] getRefreshLayoutColor() {
        return new int[]{getResources().getColor(android.R.color.black)};
    }

    public abstract BaseQuickAdapter<B, BaseViewHolder> onCreateAdapter(List<B> list);

}
