package com.elorri.android.xyzreader.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.elorri.android.xyzreader.R;

/**
 * Created by Elorri on 13/03/2016.
 */
public class DynamicHeightGradientTopBottomImageView extends FrameLayout {

    private float mAspectRatio=1.5f;

    public DynamicHeightGradientTopBottomImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.top_bottom_gradient_imageview, this);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int measuredWidth = getMeasuredWidth();
        int measuredHeight=Math.max(300, (int) (measuredWidth / mAspectRatio));
        setMeasuredDimension(measuredWidth,measuredHeight);
    }
}
