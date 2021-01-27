package com.example.appodealtestassignment.presentation.ui.activities;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.appodealtestassignment.R;
import com.example.appodealtestassignment.presentation.presenters.ActivityPresenter;
import com.example.appodealtestassignment.presentation.presenters.ActivityView;
import com.example.appodealtestassignment.presentation.ui.fragments.FragmentNewsContainer;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.appodealtestassignment.data.NewsTypeEnum.ENGLAND_NEWS;
import static com.example.appodealtestassignment.data.NewsTypeEnum.HOME_NEWS;
import static com.example.appodealtestassignment.data.NewsTypeEnum.WORLD_NEWS;

public class MainActivity extends AppCompatActivity implements ActivityView {
    private final static String TAG = MainActivity.class.getSimpleName();
    private final static String NEWS_TYPE = "NEWS_TYPE";
    private final int CHECK_INTERNET_CONNECTION = R.string.check_network_connection;
    private final int INNER_ERROR = R.string.inner_error;
    @BindView(R.id.bottom_navigation)
    BottomNavigationView navigation;
    @BindView(R.id.view_pager_activity_main)
    ViewPager viewPager;
    private ActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        presenter = new ActivityPresenter(this);
        initViews();
        if (savedInstanceState != null) {
            int newsType = savedInstanceState.getInt(NEWS_TYPE, HOME_NEWS);
            selectViewPosition(newsType);
        } else {
            selectViewPosition(HOME_NEWS);
        }
        initListeners();
    }

    private void selectViewPosition(int newsType) {
        viewPager.setCurrentItem(newsType);
        navigation.getMenu().getItem(newsType).setChecked(true);
    }

    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putInt(NEWS_TYPE, viewPager.getCurrentItem());
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.stop();
    }

    private void initListeners() {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                try {
                    navigation.getMenu().getItem(position).setChecked(true);
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        navigation.setOnNavigationItemSelectedListener(menuItem -> {
            viewPager.setCurrentItem(getSelectedItemPosition(menuItem));
            return false;
        });
    }

    private int getSelectedItemPosition(MenuItem menuItem) {
        Menu menu = navigation.getMenu();
        for (int i = 0; i < menu.size(); i++) {
            MenuItem temp = menu.getItem(i);
            if (temp.equals(menuItem)) {
                return i;
            }
        }
        return 0;
    }

    private void initViews() {
        setupViewPager(viewPager);
        viewPager.setOffscreenPageLimit(3);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), HOME_NEWS);
        adapter.addFragment(FragmentNewsContainer.newInstance(ENGLAND_NEWS));
        adapter.addFragment(FragmentNewsContainer.newInstance(HOME_NEWS));
        adapter.addFragment(FragmentNewsContainer.newInstance(WORLD_NEWS));
        viewPager.setAdapter(adapter);
    }

    @Override
    public void checkNetworkNotification() {
        Toast.makeText(getApplicationContext(), CHECK_INTERNET_CONNECTION, Toast.LENGTH_LONG).show();
    }

    @Override
    public void innerErrorNotification() {
        Toast.makeText(getApplicationContext(), INNER_ERROR, Toast.LENGTH_LONG).show();
    }

    private static class ViewPagerAdapter extends FragmentStatePagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();

        public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
            super(fm, behavior);
        }


        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment) {
            mFragmentList.add(fragment);
        }
    }
}