package com.elorri.android.xyzreader.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.elorri.android.xyzreader.R;

/**
 * Created by Elorri on 13/03/2016.
 */
public class DynamicHeightGradientTopBottomImageView extends FrameLayout {

    private float mAspectRatio = 1.5f;
    private NetworkImageView mThumbnailView;

    public DynamicHeightGradientTopBottomImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.top_bottom_gradient_imageview, this);
        mThumbnailView = (NetworkImageView) findViewById(R.id.thumbnail);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = Math.max(300, (int) (measuredWidth / mAspectRatio));
        setMeasuredDimension(measuredWidth, measuredHeight);
    }


    public void setImageUrl(String url, ImageLoader imageLoader) {
        mThumbnailView.setImageUrl(url, imageLoader);
    }

    public void setAspectRatio(float aspectRatio) {
        mAspectRatio = aspectRatio;
        requestLayout();
    }
}
