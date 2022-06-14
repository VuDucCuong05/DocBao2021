package com.example.android45_app_doc_bao;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import androidx.annotation.Nullable;

@SuppressLint("AppCompatCustomView")
public class CustomTV extends TextView {

    public CustomTV(Context context) {
        super(context);
        init();
    }

    public CustomTV(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();

    }

    public CustomTV(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();

    }

    public CustomTV(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();

    }
    private void init(){
        this.setTextSize(20);
    }
}
