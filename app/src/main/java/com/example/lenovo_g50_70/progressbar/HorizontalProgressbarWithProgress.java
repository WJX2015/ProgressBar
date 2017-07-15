package com.example.lenovo_g50_70.progressbar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.ProgressBar;

/**
 * 水平的进度条，自带进度
 * Created by wjx on 2017/7/15.
 */

public class HorizontalProgressbarWithProgress extends ProgressBar {

    private static final int DEFAULT_TEXT_SIZE = 10; //sp
    private static final int DEFAULT_TEXT_COLOR = 0xFFFC00D1;
    private static final int DEFAULT_COLOR_UNREACH = 0xFFD3D6DA;
    private static final int DEFAULT_HEIGHT_UNREACH = 2; //dp
    private static final int DEFAULT_COLOR_REACH = DEFAULT_TEXT_COLOR;
    private static final int DEFAULT_HEIGHT_REACH = 2;  //dp
    private static final int DEFAULT_TEXT_OFFSET = 10;  //dp

    private int mTextSize = sp2px(DEFAULT_TEXT_SIZE);
    private int mTextColor = DEFAULT_TEXT_COLOR;
    private int mUnReachColor = DEFAULT_COLOR_UNREACH;
    private int mUnReachHeight = dp2px(DEFAULT_HEIGHT_UNREACH);
    private int mReachColor = DEFAULT_COLOR_REACH;
    private int mReachHeight = dp2px(DEFAULT_HEIGHT_REACH);
    private int mTextOffset = dp2px(DEFAULT_TEXT_OFFSET);

    private Paint mPaint = new Paint();

    private int mRealWidth;

    public HorizontalProgressbarWithProgress(Context context) {
        this(context, null);
    }

    public HorizontalProgressbarWithProgress(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HorizontalProgressbarWithProgress(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        //获取自定义属性
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.HorizontalProgressbarWithProgress);
        mTextSize = (int) array.getDimension(R.styleable.HorizontalProgressbarWithProgress_progress_text_size, mTextSize);
        mTextColor = array.getColor(R.styleable.HorizontalProgressbarWithProgress_progress_text_color, mTextColor);
        mTextOffset = (int) array.getDimension(R.styleable.HorizontalProgressbarWithProgress_progress_text_offset, mTextOffset);
        mUnReachColor = array.getColor(R.styleable.HorizontalProgressbarWithProgress_progress_unreach_color, mUnReachColor);
        mUnReachHeight = (int) array.getDimension(R.styleable.HorizontalProgressbarWithProgress_progress_unreach_height, mUnReachHeight);
        mReachColor = array.getColor(R.styleable.HorizontalProgressbarWithProgress_progress_reach_color, mReachColor);
        mReachHeight = (int) array.getDimension(R.styleable.HorizontalProgressbarWithProgress_progress_reach_height, mReachHeight);
        array.recycle();
    }

    /**
     * @param dpValue
     * @return dp转px
     */
    private int dp2px(int dpValue) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpValue, getResources().getDisplayMetrics());
    }

    /**
     * @param spValue
     * @return sp转px
     */
    private int sp2px(int spValue) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, spValue, getResources().getDisplayMetrics());
    }
}
