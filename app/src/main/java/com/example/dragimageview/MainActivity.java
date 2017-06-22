package com.example.dragimageview;

import android.app.ActionBar;
import android.content.res.TypedArray;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import butterknife.ButterKnife;
import butterknife.OnTouch;

public class MainActivity extends AppCompatActivity {
    int toolBarHeight;
    int statusBarHeight;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        // Get toolbar heigth
        final TypedArray styledAttributes = getApplicationContext().getTheme().obtainStyledAttributes(
                new int[] { android.R.attr.actionBarSize });
        toolBarHeight = (int) styledAttributes.getDimension(0, 0);
        statusBarHeight = getStatusBarHeight();
    }

    @OnTouch(R.id.iv_0)
    public boolean touch(View view, MotionEvent event) {

        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams)view.getLayoutParams();


        switch (event.getAction())
        {
            case MotionEvent.ACTION_MOVE:
                params.topMargin = (int) event.getRawY() - (view.getHeight() + toolBarHeight + statusBarHeight);
                params.leftMargin = (int) event.getRawX() - (view.getWidth() / 2);
                view.setLayoutParams(params);
                break;

            case MotionEvent.ACTION_UP:
                params.topMargin = (int) event.getRawY() - (view.getHeight() + toolBarHeight + statusBarHeight);
                params.leftMargin = (int) event.getRawX() - (view.getWidth() / 2);
                view.setLayoutParams(params);
                break;

            case MotionEvent.ACTION_DOWN:
                view.setLayoutParams(params);
                break;
        }

        return true;

    }
    private int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

}
