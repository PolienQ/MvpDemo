package com.pans.mvpdemo.ui.adatper;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.pans.mvpdemo.R;
import com.pans.mvpdemo.base.BaseActivity;
import com.pans.mvpdemo.ui.activity.DetailActivity;
import com.pans.mvpdemo.ui.activity.MainActivity;
import com.pans.mvpdemo.ui.fragment.MainFragment;

import java.util.List;

/***********
 * @author pans
 * @date 2016/7/25
 * @describ
 */
public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder> implements View.OnClickListener {

    private final List<String> mData;
    private final MainFragment mFragment;

    public MainAdapter(MainFragment fragment, List<String> data) {
        mFragment = fragment;
        mData = data;
    }

    @Override
    public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main_list, parent, false);
        return new MainViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MainViewHolder holder, int position) {
        String text = mData.get(position);
        holder.mTvTitle.setText(text);

        holder.mCardView.setTag(holder.mIvNews);
        holder.mCardView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        ((MainActivity) mFragment.getActivity()).presentController(DetailActivity.class, ((View) v.getTag()));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class MainViewHolder extends RecyclerView.ViewHolder {

        private final CardView mCardView;
        private final ImageView mIvNews;
        private final TextView mTvTitle;

        public MainViewHolder(View itemView) {
            super(itemView);
            mCardView = ((CardView) itemView.findViewById(R.id.cv_item));
            mIvNews = ((ImageView) itemView.findViewById(R.id.iv_news));
            mTvTitle = ((TextView) itemView.findViewById(R.id.tv_title));
        }
    }


}
