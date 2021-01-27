package com.example.appodealtestassignment.presentation.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.appodealtestassignment.R;
import com.example.appodealtestassignment.data.InternetConnectionStatus;
import com.example.appodealtestassignment.domain.entities.RssNews;
import com.example.appodealtestassignment.presentation.presenters.ContainerPresenter;
import com.example.appodealtestassignment.presentation.presenters.ViewFragmentContainer;
import com.example.appodealtestassignment.presentation.ui.adapters.NewsAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FragmentNewsContainer extends Fragment implements ViewFragmentContainer {
    private static final String TAG = FragmentNewsContainer.class.getSimpleName();
    private final int CHECK_INTERNET_CONNECTION = R.string.check_network_connection;
    private static final String KEY = "container_news_type";
    @BindView(R.id.channel_title)
    TextView channelTitle;
    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout refreshLayout;
    @BindView(R.id.news_recycler)
    RecyclerView newsRecycler;
    private ContainerPresenter presenter;
    private NewsAdapter adapter;

    public static FragmentNewsContainer newInstance(int arg) {
        FragmentNewsContainer fragment = new FragmentNewsContainer();
        Bundle args = new Bundle();
        args.putInt(KEY, arg);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.news_container, container, false);
        ButterKnife.bind(this, view);
        refreshLayout.setRefreshing(true);
        presenter = new ContainerPresenter(this, getArguments().getInt(KEY, 1));
        presenter.getDataByParameter(getArguments().getInt(KEY, 1));
        initRecycler();
        initListeners();
        return view;
    }

    private void initRecycler() {
        adapter = new NewsAdapter(presenter);
        newsRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        newsRecycler.setAdapter(adapter);
    }

    private void initListeners() {
        refreshLayout.setOnRefreshListener(() -> {
            if (InternetConnectionStatus.isOnline()) {
                presenter.updateData(getArguments().getInt(KEY, 1));
            } else {
                checkNetworkNotification();
                refreshLayout.setRefreshing(false);
            }
        });
    }

    public void checkNetworkNotification() {
        Toast.makeText(getContext(), CHECK_INTERNET_CONNECTION, Toast.LENGTH_LONG).show();
    }


    @Override
    public void setViewData(List<RssNews> data) {
        adapter.setItems(data);
        refreshLayout.setRefreshing(false);
        runLayoutFallDownAnimation(newsRecycler);
        setChannelData(data);
    }

    private void runLayoutFallDownAnimation(final RecyclerView recyclerView) {
        final Context context = recyclerView.getContext();
        final LayoutAnimationController controller =
                AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation_fall_down);
        recyclerView.setLayoutAnimation(controller);
        if (recyclerView.getAdapter() != null)
            recyclerView.getAdapter().notifyDataSetChanged();
        recyclerView.scheduleLayoutAnimation();
    }

    private void setChannelData(List<RssNews> data) {
        int uiType = getArguments().getInt(KEY, 1);
        for (int i = 0; i < data.size(); i++) {
            if (uiType == data.get(i).getNewsType()) {
                channelTitle.setText(data.get(i).getChannelTitle());
                return;
            }
        }
    }
}

