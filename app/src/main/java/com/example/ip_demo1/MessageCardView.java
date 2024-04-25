package com.example.ip_demo1;

import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;

import androidx.cardview.widget.CardView;

public class MessageCardView extends CardView {

    private static final int MAX_WIDTH_DP = 700;
    private int maxWidthPx;

    public MessageCardView(Context context) {
        super(context);
        init();
    }

    public MessageCardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MessageCardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        // Set maximum width programmatically
        maxWidthPx = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, MAX_WIDTH_DP,
                getResources().getDisplayMetrics());
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // Measure the width with the provided measure spec
        int measuredWidth = MeasureSpec.getSize(widthMeasureSpec);

        // Ensure the measured width does not exceed the maximum width
        if (measuredWidth > MAX_WIDTH_DP) {
            int widthMeasureSpecFixed = MeasureSpec.makeMeasureSpec(MAX_WIDTH_DP, MeasureSpec.EXACTLY);
            super.onMeasure(widthMeasureSpecFixed, heightMeasureSpec);
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }
}
