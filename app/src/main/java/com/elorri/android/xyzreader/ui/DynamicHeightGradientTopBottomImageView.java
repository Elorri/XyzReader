package com.elorri.android.xyzreader.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;

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
        FrameLayout thumbnail_container = (FrameLayout) findViewById(R.id.thumbnail_container);
        mThumbnailView = new NetworkImageView(getContext());
        thumbnail_container.addView(mThumbnailView);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = Math.max(300, (int) (measuredWidth / mAspectRatio));

        mThumbnailView.getLayoutParams().width = measuredWidth;
        mThumbnailView.getLayoutParams().height = measuredHeight;
        mThumbnailView.setScaleType(ImageView.ScaleType.CENTER_CROP);

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
