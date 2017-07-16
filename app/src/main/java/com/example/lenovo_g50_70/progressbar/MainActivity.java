package com.example.lenovo_g50_70.progressbar;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private static final int MSG_UPDATE = 0X001;
    private HorizontalProgressbarWithProgress mProgress;
    private RoundProgressbarWithProgress mWithProgress;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == MSG_UPDATE) {
                int progress = mProgress.getProgress();
                mProgress.setProgress(++progress);
                mWithProgress.setProgress(++progress);
                if (progress >= 100) {
                    mHandler.removeMessages(0X001);
                }
                mHandler.sendEmptyMessageDelayed(0X001, 100);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {
        mProgress = (HorizontalProgressbarWithProgress) findViewById(R.id.progress01);
        mWithProgress = (RoundProgressbarWithProgress) findViewById(R.id.progress02);
        mHandler.sendEmptyMessage(0X001);
    }
}
