package com.example.lenovo_g50_70.progressbar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.util.AttributeSet;

/**
 * Created by wjx on 2017/7/16.
 */

public class RoundProgressbarWithProgress extends HorizontalProgressbarWithProgress {

    private int mRadius = dp2px(30);

    private int mMaxPaintWidth;

    public RoundProgressbarWithProgress(Context context) {
        this(context, null);
    }

    public RoundProgressbarWithProgress(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RoundProgressbarWithProgress(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mReachHeight = (int) (mUnReachHeight * 2.5f);

        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.RoundProgressbarWithProgress);
        mRadius = (int) array.getDimension(R.styleable.RoundProgressbarWithProgress_radius, mRadius);
        array.recycle();

        init();
    }

    /**
     * 初始化
     */
    private void init() {
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
    }
}