package com.app.julie.common.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Toast工具类，在连续使用Toast时不添加进等候队列，而是直接改变显示内容
 *
 * @author hxb
 */
public abstract class ToastUtil {

    private static Toast toast;

    public static void showToast(Context context, CharSequence msg) {
        if (toast == null) {
            toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
        } else {
            toast.setText(msg);
        }
        toast.show();
    }
}
