package com.elorri.android.xyzreader.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.elorri.android.xyzreader.R;
import com.elorri.android.xyzreader.data.UpdaterService;

public class ArticleListActivity extends AppCompatActivity {

    ArticleListFragment mArticleListFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e("XyzReader",Thread.currentThread().getStackTrace()[2]+"");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_list);

        mArticleListFragment=(ArticleListFragment)getSupportFragmentManager().findFragmentById(R.id
                .activityListFragment);

        Typeface courgette = Typeface.createFromAsset(getAssets(), "courgette-regular.ttf");
        TextView appTitleXyz = (TextView) findViewById(R.id.appTitleXyz);
        TextView appTitleReader = (TextView) findViewById(R.id.appTitleReader);
        appTitleXyz.setTypeface(courgette);

        if (savedInstanceState == null) {
            refresh();
        }
    }

    private void refresh() {
        Log.e("XyzReader", Thread.currentThread().getStackTrace()[2] + "");
        startService(new Intent(this, UpdaterService.class));
    }

    @Override
    protected void onStart() {
        super.onStart();
        registerReceiver(mRefreshingReceiver,
                new IntentFilter(UpdaterService.BROADCAST_ACTION_STATE_CHANGE));
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(mRefreshingReceiver);
    }

    private boolean mIsRefreshing = false;
    private BroadcastReceiver mRefreshingReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (UpdaterService.BROADCAST_ACTION_STATE_CHANGE.equals(intent.getAction())) {
                mIsRefreshing = intent.getBooleanExtra(UpdaterService.EXTRA_REFRESHING, false);
                mArticleListFragment.updateRefreshingUI(mIsRefreshing);
            }
        }
    };

}
