package com.example.appodealtestassignment.presentation.ui.activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appodealtestassignment.R;
import com.example.appodealtestassignment.data.InternetConnectionStatus;
import com.example.appodealtestassignment.domain.entities.RssNews;
import com.example.appodealtestassignment.presentation.presenters.ContainerPresenter;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {
    private final int DELETE_NEWS = R.string.delete_news;
    private final int YES = R.string.yes;
    private final int NO = R.string.no;
    private ContainerPresenter presenter;
    private List<RssNews> items;

    public NewsAdapter(ContainerPresenter presenter) {
        items = new ArrayList<>();
        this.presenter = presenter;
    }

    public void setItems(List<RssNews> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_view_item, parent, false);
        return new NewsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        if (items.get(position).wasRead()) {
            markAsRead(holder.title, holder.description);
        }
        holder.title.setText(items.get(position).getItemTitle());
        holder.description.setText(items.get(position).getItemDescription());
        holder.lastUpdate.setText(items.get(position).getItemPubDate());
        holder.rootView.setOnClickListener(v -> {
            if (InternetConnectionStatus.isOnline()) {
                Intent intent = new Intent(holder.moreDetails.getContext(), WebViewActivity.class);
                intent.putExtra(WebViewActivity.URI_KEY, items.get(position).getItemLink());
                items.get(position).setWasRead(true);
                presenter.changeData(items.get(position));
                markAsRead(holder.title, holder.description);
                holder.moreDetails.getContext().startActivity(intent);
            } else {
                Toast.makeText(holder.moreDetails.getContext(), R.string.check_network_connection, Toast.LENGTH_SHORT).show();
            }
        });
        holder.rootView.setOnCreateContextMenuListener((menu, v, menuInfo) -> {
            menu.setHeaderTitle(DELETE_NEWS).add(YES).setOnMenuItemClickListener(menuItem -> {
                removeItem(position);
                return false;
            });
            menu.add(NO);
        });
        holder.moreDetails.setOnClickListener(v -> {
            hideItemData(holder);
        });
        holder.hideIcon.setOnClickListener(v -> {
            removeItem(position);
            hideItemData(holder);
        });
    }

    private void hideItemData(@NonNull NewsViewHolder holder) {
        if (holder.hiddenData.getVisibility() == View.VISIBLE) {
            holder.hiddenData.setVisibility(View.GONE);
            holder.moreDetails.setText(R.string.more_details);
        } else {
            holder.hiddenData.setVisibility(View.VISIBLE);
            holder.moreDetails.setText(R.string.hide);
        }
    }

    private void removeItem(int position) {
        items.get(position).setWasHidden(true);
        presenter.changeData(items.get(position));
        items.remove(position);
        notifyItemRemoved(position);
        notifyDataSetChanged();
    }

    private void markAsRead(TextView... textViews) {
        for (TextView textTV : textViews) {
            textTV.setTypeface(null, Typeface.NORMAL);
            textTV.setTextColor(ContextCompat.getColor(textTV.getContext(), R.color.gray));
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        if (items == null)
            return 0;
        else return items.size();
    }

    class NewsViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.title_tv)
        TextView title;
        @BindView(R.id.description_tv)
        TextView description;
        @BindView(R.id.more_details_tv)
        TextView moreDetails;
        @BindView(R.id.last_update_tv)
        TextView lastUpdate;
        @BindView(R.id.item_root_cv)
        MaterialCardView rootView;
        @BindView(R.id.hide_item_ib)
        ImageView hideIcon;
        @BindView(R.id.hided_data_rl)
        RelativeLayout hiddenData;

        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
