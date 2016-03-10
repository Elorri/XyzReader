package com.elorri.android.xyzreader;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ShareCompat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

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
        if (!getResources().getBoolean(R.bool.wide_device)) {
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
