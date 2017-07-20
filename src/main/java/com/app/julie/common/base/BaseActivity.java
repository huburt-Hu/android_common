package com.app.julie.common.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.app.julie.common.util.ToastUtils;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by julie
 * <p>
 * Created on 2017/5/5.
 */

public class BaseActivity extends RxAppCompatActivity {

    private SweetAlertDialog pDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void showLoadingDialog() {
        if (pDialog == null)
            pDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE)
                    .setTitleText("加载中");
        pDialog.show();
    }

    public void closeLoadingDialog() {
        if (pDialog != null) {
            pDialog.dismiss();
        }
    }

    public void showToast(String msg) {
        ToastUtils.showShortToast(msg);
    }
}
