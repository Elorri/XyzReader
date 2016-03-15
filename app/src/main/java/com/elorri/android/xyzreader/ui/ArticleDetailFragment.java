package com.elorri.android.xyzreader.ui;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.app.ShareCompat;
import android.support.v4.content.Loader;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.format.DateUtils;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.elorri.android.xyzreader.R;
import com.elorri.android.xyzreader.data.ArticleLoader;
import com.elorri.android.xyzreader.data.ItemsContract;

/**
 * Created by Elorri on 09/03/2016.
 */
public class ArticleDetailFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final int XYZ_LOADER = 0;
    public static String DETAIL_URI = "detail_uri";
    private MenuItem mActivityMenuItem;
    private Uri mDetailUri;
    private View mRootView;
    private Cursor mCursor;
    private String mArticleTitle;
    private CollapsingToolbarLayout mCollapsingToolbar;
    private DynamicHeightGradientTopImageView mPhotoView;


    public ArticleDetailFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getResources().getBoolean(R.bool.wide_device))
            setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_article_detail, container, false);

        mCollapsingToolbar =
                (CollapsingToolbarLayout) mRootView.findViewById(R.id.collapsing_toolbar_layout);
        mCollapsingToolbar.setTitle("");

        AppBarLayout appBarLayout = (AppBarLayout) mRootView.findViewById(R.id.app_bar_layout);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isCollapsed = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    mCollapsingToolbar.setTitle(mArticleTitle);
                    isCollapsed = true;
                } else if (isCollapsed) {
                    mCollapsingToolbar.setTitle("");
                    isCollapsed = false;
                }
            }
        });

        if (!getResources().getBoolean(R.bool.wide_device)) {
            Toolbar toolbar = (Toolbar) mRootView.findViewById(R.id.app_bar);
            ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);


            FloatingActionButton shareFab = (FloatingActionButton) mRootView.findViewById(R.id.share_fab);
            shareFab.setVisibility(View.VISIBLE);
            shareFab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(Intent.createChooser(createShareIntent(),
                            getString(R.string.action_share)));
                }
            });
        } else {
            NestedScrollView nestedscrollview =
                    (NestedScrollView)mRootView.findViewById(R.id.nestedscrollview);
            nestedscrollview.setBackgroundColor(getResources().getColor(R.color.material_white));
        }
       return mRootView;
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

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getLoaderManager().initLoader(XYZ_LOADER, null, this);
    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        Bundle arguments = getArguments();
        if (arguments != null) {
            mDetailUri = arguments.getParcelable(DETAIL_URI);
            if (mDetailUri != null) {
                long itemId = ItemsContract.Items.getItemId(mDetailUri);
                return ArticleLoader.newInstanceForItemId(getActivity(), itemId);
            }
        }
        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
       if (!isAdded()) {
            if (data != null) {
                data.close();
            }
            return;
        }

        mCursor = data;
        if (mCursor != null && !mCursor.moveToFirst()) {
            Log.e("XyzReader", "Error reading item detail cursor");
            mCursor.close();
            mCursor = null;
        }
        bindViews();
    }


    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        bindViews();
    }

    private void bindViews() {
        if (mRootView == null) {
            return;
        }

        TextView titleView = (TextView) mRootView.findViewById(R.id.article_title);
        TextView bylineView = (TextView) mRootView.findViewById(R.id.article_byline);
        bylineView.setMovementMethod(new LinkMovementMethod());
        TextView bodyView = (TextView) mRootView.findViewById(R.id.article_body);
        mPhotoView = (DynamicHeightGradientTopImageView) mRootView.findViewById(R.id
                .photo);

        if (mCursor != null) {
            mRootView.setAlpha(0);
            mRootView.setVisibility(View.VISIBLE);
            mRootView.animate().alpha(1);
            mArticleTitle = mCursor.getString(ArticleLoader.Query.TITLE);
            titleView.setText(mArticleTitle);
            bylineView.setText(Html.fromHtml(
                    DateUtils.getRelativeTimeSpanString(
                            mCursor.getLong(ArticleLoader.Query.PUBLISHED_DATE),
                            System.currentTimeMillis(), DateUtils.HOUR_IN_MILLIS,
                            DateUtils.FORMAT_ABBREV_ALL).toString()
                            + " by <font color='#ffffff'>"
                            + mCursor.getString(ArticleLoader.Query.AUTHOR)
                            + "</font>"));
            bodyView.setText(Html.fromHtml(mCursor.getString(ArticleLoader.Query.BODY)));


            String url = mCursor.getString(ArticleLoader.Query.PHOTO_URL);
            ImageLoaderHelper.getInstance(getContext()).getImageLoader()
                    .get(url, new ImageLoader.ImageListener() {
                        @Override
                        public void onResponse(ImageLoader.ImageContainer imageContainer, boolean b) {
                            Bitmap bitmap = imageContainer.getBitmap();
                            if (bitmap != null) {
                                Palette p = Palette.generate(bitmap, 12);
                                int mutedColor = p.getDarkMutedColor(0xFF333333);
                                mPhotoView.setImage(imageContainer.getBitmap());
                                mPhotoView.setAspectRatio(mCursor.getFloat(ArticleLoader.Query.ASPECT_RATIO));
                                mRootView.findViewById(R.id.meta_bar).setBackgroundColor(mutedColor);
                                mCollapsingToolbar.setContentScrimColor(mutedColor);
                            }
                        }
                        @Override
                        public void onErrorResponse(VolleyError volleyError) {

                        }
                    });
        } else {
            mRootView.setVisibility(View.GONE);
            titleView.setText("N/A");
            bylineView.setText("N/A");
            bodyView.setText("N/A");
        }
    }
}
