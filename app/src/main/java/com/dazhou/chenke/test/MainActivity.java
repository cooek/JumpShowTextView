package com.dazhou.chenke.test;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.dazhou.chenke.test.Utlis.JumpShowTextView;
import com.dazhou.chenke.test.Utlis.Test;

public class MainActivity extends AppCompatActivity {


    private TextView mTextView;
    private String s = "我是测试，孙笑川是项目经理，孙笑川是项目经理，孙笑川是项目经理孙笑川出来挨打.......";
    private JumpShowTextView mFrameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        Test test = new Test(mTextView,s,100);
        initData();


    }

    private void initData() {
//        mFrameLayout
        if (mFrameLayout != null) {
        mFrameLayout.setWithanmation(true);
        mFrameLayout.setTest(s);
        mFrameLayout.SetStart();
    }
    }

    private void initView() {
        mFrameLayout = (JumpShowTextView)findViewById(R.id.framelauouy);

        mTextView = findViewById(R.id.start);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mFrameLayout.stop();
    }
}
