package com.elorri.android.xyzreader;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;

/**
 * Taken from https://github.com/chiuki/android-recyclerview/blob/master/app/src/main/java/com/sqisland/android/recyclerview/AutofitRecyclerView.java
 * Created by Elorri on 10/03/2016.
 */
public class AutofitRecyclerView extends RecyclerView {
    private StaggeredGridLayoutManager manager;
    private int columnWidth = -1;

    public AutofitRecyclerView(Context context) {
        super(context);
        init(context, null);
    }

    public AutofitRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public AutofitRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        //read the value of android:columnWidth and save it in a member variable
        if (attrs != null) {
            int[] attrsArray = {
                    android.R.attr.columnWidth
            };
            TypedArray array = context.obtainStyledAttributes(attrs, attrsArray);
            columnWidth = array.getDimensionPixelSize(0, -1);
            array.recycle();
        }

        manager = new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL);
        setLayoutManager(manager);
    }

    @Override
    protected void onMeasure(int widthSpec, int heightSpec) {
        //ask the super class to perform the measurement
        super.onMeasure(widthSpec, heightSpec);
        if (columnWidth > 0) {
            //take the value from getMeasuredWidth to compute the span count
            //makes sure that we will have at least a span count of 1 with Math.max
            //note : a column width of 72dp will make a span count of 4 (320dp / 72dp = 4.4444)
            // that will items 320dp / 4 = 80dp wide, not 72dp
            int spanCount = Math.max(2, getMeasuredWidth() / columnWidth);
            manager.setSpanCount(spanCount);
        }
    }
}
