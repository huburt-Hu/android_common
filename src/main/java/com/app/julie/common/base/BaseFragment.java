package com.app.julie.common.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trello.rxlifecycle2.components.support.RxFragment;

/**
 * Created by hubert
 * <p>
 * Created on 2017/6/5.
 */

public abstract class BaseFragment extends RxFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getLayoutId(), container, false);
    }

    public abstract int getLayoutId();

    public void showLoadingDialog() {
        ((BaseActivity) getActivity()).showLoadingDialog();
    }

    public void closeLoadingDialog() {
        ((BaseActivity) getActivity()).closeLoadingDialog();
    }

}
