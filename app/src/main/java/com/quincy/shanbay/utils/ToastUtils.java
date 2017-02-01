package com.quincy.shanbay.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by quincy on 1/25/17.
 */
public class ToastUtils {

    private static Toast toast = null;

    public static void showToast(Context context, String content){
        if(toast == null){
            toast = Toast.makeText(context, content, Toast.LENGTH_SHORT);
        }else {
            toast.setText(content);
        }
        toast.show();
    }
}
