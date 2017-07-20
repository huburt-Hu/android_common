package com.app.julie.common.base;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.app.julie.common.R;

/**
 * Created by hubert
 * <p>
 * Created on 2017/7/7.
 */

public class BaseToolbarActivity extends BaseActivity {

    Toolbar mToolbar;
    private FrameLayout root;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_toolbar);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(mToolbar);

        root = (FrameLayout) findViewById(R.id.fl_root);

    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        View view = LayoutInflater.from(this).inflate(layoutResID, root, false);
        root.removeAllViews();
        root.addView(view);
    }

}
