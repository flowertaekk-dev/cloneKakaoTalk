package com.example.clonekakaotalk.utils.windowSoftware;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class WindowSoft {

    /**
     * Hide default Android keyboard
     */
    public static void hideKeyboardFrom(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);

        if (imm == null) {
            throw new RuntimeException("Problem with handling keyboard");
        }

        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

    }

    /**
     * Show default Android keyboard
     */
    public static void showKeyboardFrom(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);

        if (imm == null) {
            throw new RuntimeException("Problem with handling keyboard");
        }
        imm.showSoftInput(view, 0);
    }
}
