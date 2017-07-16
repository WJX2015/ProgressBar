package com.example.lenovo_g50_70.progressbar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.ProgressBar;

import static android.os.Build.VERSION_CODES.M;

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

    protected int mTextSize = sp2px(DEFAULT_TEXT_SIZE);
    protected int mTextColor = DEFAULT_TEXT_COLOR;
    protected int mUnReachColor = DEFAULT_COLOR_UNREACH;
    protected int mUnReachHeight = dp2px(DEFAULT_HEIGHT_UNREACH);
    protected int mReachColor = DEFAULT_COLOR_REACH;
    protected int mReachHeight = dp2px(DEFAULT_HEIGHT_REACH);
    protected int mTextOffset = dp2px(DEFAULT_TEXT_OFFSET);

    protected Paint mPaint = new Paint();

    protected int mRealWidth;

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

        //避免textHeight测量不准确，需初始化字体大小
        mPaint.setTextSize(mTextSize);
    }

    @Override
    protected synchronized void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        //水平进度条的宽度由用户指定尺寸大小
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = measureHeight(heightMeasureSpec);

        setMeasuredDimension(width, height);
        mRealWidth = getMeasuredWidth() - getPaddingLeft() - getPaddingRight();
    }

    /**
     * 测量进度条高度
     *
     * @param heightMeasureSpec
     * @return
     */
    private int measureHeight(int heightMeasureSpec) {
        int result = 0;
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        if (heightMode == MeasureSpec.EXACTLY) {
            result = heightSize;
        } else {
            //自己来测量决定高度
            int textHeight = (int) (mPaint.descent() - mPaint.ascent());
            result = getPaddingTop() + getPaddingBottom() + Math.max(Math.max(mReachHeight, mUnReachHeight), Math.abs(textHeight));

            if (heightMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, heightSize);
            }
        }
        return result;
    }

    @Override
    protected synchronized void onDraw(Canvas canvas) {
        canvas.save();
        canvas.translate(getPaddingLeft(), getHeight() / 2);

        //draw reach bar
        boolean noNeedUnRech = false;
        String text = getProgress() + "%";
        int textWidth = (int) mPaint.measureText(text);
        float radio = getProgress() * 1.0f / getMax();
        float progressX = radio * mRealWidth;
        if (progressX + textWidth > mRealWidth) {
            progressX = mRealWidth - textWidth;
            noNeedUnRech = true;
        }

        float endX = radio * mRealWidth - mTextOffset / 2;
        if (endX > 0) {
            mPaint.setColor(mReachColor);
            mPaint.setStrokeWidth(mReachHeight);
            canvas.drawLine(0, 0, endX, 0, mPaint);
        }

        //draw text
        mPaint.setColor(mTextColor);
        int y = (int) (-(mPaint.descent() - mPaint.ascent()) / 2);
        canvas.drawText(text, progressX, y, mPaint);

        //draw unreach bar

        if (!noNeedUnRech) {
            float start = progressX + mTextOffset / 2 + textWidth;
            mPaint.setColor(mUnReachColor);
            mPaint.setStrokeWidth(mUnReachHeight);
            canvas.drawLine(start, 0, mRealWidth, 0, mPaint);
        }

        canvas.restore();
    }

    /**
     * @param dpValue
     * @return dp转px
     */
    protected int dp2px(int dpValue) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpValue, getResources().getDisplayMetrics());
    }

    /**
     * @param spValue
     * @return sp转px
     */
    protected int sp2px(int spValue) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, spValue, getResources().getDisplayMetrics());
    }
}
