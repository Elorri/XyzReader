package com.elorri.android.xyzreader;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Elorri on 09/03/2016.
 */
public class ArticleListFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_article_list, container, false);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        int columnCount = getResources().getInteger(R.integer.list_column_count);
        //recyclerView.setLayoutManager(new GridLayoutManager(getContext(), columnCount));
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(columnCount, StaggeredGridLayoutManager.VERTICAL));

        Adapter adapter = new Adapter();
        recyclerView.setAdapter(adapter);
        return view;

    }

    private class Adapter extends RecyclerView.Adapter<ViewHolder> {

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = getLayoutInflater(null).inflate(R.layout.list_item_article, parent, false);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final Bundle bundle= ActivityOptions.makeSceneTransitionAnimation(
                            getActivity(),
                            view,
                            getResources().getString(R.string.keep))
                            .toBundle();
                    getActivity().startActivity(
                            new Intent(getContext(), ArticleDetailActivity.class),
                            bundle);
                }
            });
            final ViewHolder viewHolder = new ViewHolder(view);
            return viewHolder;

        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.text.setText("A");
        }

        @Override
        public int getItemCount() {
            return 30;
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView text;

        public ViewHolder(View itemView) {
            super(itemView);
            text = (TextView) itemView.findViewById(R.id.text);
        }
    }
}
