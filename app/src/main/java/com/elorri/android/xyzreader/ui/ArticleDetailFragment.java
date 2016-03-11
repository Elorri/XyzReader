package com.elorri.android.xyzreader.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.elorri.android.xyzreader.R;

/**
 * Created by Elorri on 09/03/2016.
 */
public class ArticleDetailFragment extends Fragment {

    private MenuItem mActivityMenuItem;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getResources().getBoolean(R.bool.wide_device))
            setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_article_detail, container, false);

        final CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) view.findViewById(R.id.collapsing_toolbar_layout);
        collapsingToolbar.setTitle("");

        AppBarLayout appBarLayout = (AppBarLayout) view.findViewById(R.id.app_bar_layout);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isCollapsed = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbar.setTitle("A title");
                    isCollapsed = true;
                } else if (isCollapsed) {
                    collapsingToolbar.setTitle("");
                    isCollapsed = false;
                }
            }
        });

        if (!getResources().getBoolean(R.bool.wide_device)) {
            Toolbar toolbar = (Toolbar) view.findViewById(R.id.app_bar);
            ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
            ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);


           ImageButton shareFab = (ImageButton) view.findViewById(R.id.share_fab);
            shareFab.setVisibility(View.VISIBLE);
            shareFab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(Intent.createChooser(createShareIntent(),
                            getString(R.string.action_share)));
                }
            });
        }
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        if (getResources().getBoolean(R.bool.wide_device)) {
            inflater.inflate(R.menu.detail_menu, menu);
            mActivityMenuItem = menu.findItem(R.id.action_share);
            if (mActivityMenuItem != null) {
                mActivityMenuItem.setIntent(createShareIntent());
            }
        }
    }


    private Intent createShareIntent() {
        return ShareCompat.IntentBuilder.from(getActivity())
                .setType("text/plain")
                .setText("Some sample text")
                .getIntent();
    }


}
