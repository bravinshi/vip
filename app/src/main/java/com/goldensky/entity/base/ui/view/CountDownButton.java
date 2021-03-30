package com.goldensky.entity.base.ui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.CountDownTimer;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

import com.goldensky.entity.R;

/**
 * Created by Administrator on 2017/10/11 0011.
 */

public class CountDownButton extends AppCompatTextView {

    private static final long DEFAULT_MILLIS_INFUTURE = 60;

    private long countDownInterval;
    private long millisInFuture;
    private String countHint;
    private MyCount timer;
    private OnCountListener mListener;

    public CountDownButton(Context context) {
        this(context, null);
    }

    public CountDownButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }


    public CountDownButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.getResources().obtainAttributes(attrs, R.styleable.CountDownButton);
        countDownInterval = typedArray.getInteger(R.styleable.CountDownButton_countDownInterval, 1) * 1000;
        millisInFuture = typedArray.getInteger(R.styleable.CountDownButton_millisInFuture, 60) * 1000;
        countHint = typedArray.getString(R.styleable.CountDownButton_countHint);
        typedArray.recycle();

    }

    public void setTotalSecond(int second) {
        this.millisInFuture = second * 1000;
        startCountDown();
    }

    public int getTotalSecond() {
        return (int) (millisInFuture / 1000);
    }

    public void startCountDown() {
        if (timer != null && !timer.isFinished()) {
            return;
        }
        if (timer == null) {
            timer = new MyCount(millisInFuture, countDownInterval);
        }
        timer.start();
        setEnabled(false);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        cancel();
    }

    public void cancel() {
        if (timer != null) {
            timer.cancel();
        }
    }

    public void setOnCountListener(OnCountListener mListener) {
        this.mListener = mListener;
    }

    public interface OnCountListener {
        void onTick(CountDownButton button, String second);

        void onFinish(CountDownButton button);
    }

    private class MyCount extends CountDownTimer {

        private static final String TAG = "MyCount";
        private boolean isFinished = true;

        public MyCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            setEnabled(false);
            isFinished = false;
            String time = (millisUntilFinished / 1000) + "s";

            if (mListener != null) {
                mListener.onTick(CountDownButton.this, time);
            } else {
                CountDownButton.this.setText(countHint != null ? time.concat(countHint) : time);
            }
        }

        @Override
        public void onFinish() {
            setEnabled(true);
            if (mListener != null) {
                mListener.onFinish(CountDownButton.this);
            } else {
                CountDownButton.this.setText(R.string.get_code);
            }
            isFinished = true;
        }

        public boolean isFinished() {
            return isFinished;
        }
    }


}