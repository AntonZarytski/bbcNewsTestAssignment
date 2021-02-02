package com.example.appodealtestassignment.presentation.ui.activities;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.appodealtestassignment.R;
import com.example.appodealtestassignment.presentation.presenters.ActivityPresenter;
import com.example.appodealtestassignment.presentation.presenters.ActivityView;
import com.example.appodealtestassignment.presentation.presenters.mediation.AdManager;
import com.example.appodealtestassignment.presentation.presenters.mediation.IMediationManagerProvider;
import com.example.appodealtestassignment.presentation.ui.appStateSaver.ISharedPreferencesWorker;
import com.example.appodealtestassignment.presentation.ui.appStateSaver.SharedPreferencesWorker;
import com.example.appodealtestassignment.presentation.ui.fragments.FragmentNewsContainer;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.appodealtestassignment.data.NewsTypeEnum.ENGLAND_NEWS;
import static com.example.appodealtestassignment.data.NewsTypeEnum.HOME_NEWS;
import static com.example.appodealtestassignment.data.NewsTypeEnum.WORLD_NEWS;
/**
 * github link: https://github.com/AntonZarytski/bbcNewsTestAssignment
 */
public class MainActivity extends AppCompatActivity implements ActivityView, IMediationManagerProvider {
    private final static String TAG = MainActivity.class.getSimpleName();
    private final static String NEWS_TYPE = String.valueOf("NEWS_TYPE".hashCode());
    private final int CHECK_INTERNET_CONNECTION = R.string.check_network_connection;
    private final int INNER_ERROR = R.string.inner_error;
    private final int CPI_EVENT_SENT = R.string.cpi_event_sent;
    private final int CPA_EVENT_SENT = R.string.cpa_event_sent;
    private final int INTERSTITIAL_WAS_SHOWN = R.string.interstitial_was_swown;
    private AdManager adManager;
    private ISharedPreferencesWorker sharedPreferencesWorker;
    private boolean isFirstShowInSession = true;
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
        sharedPreferencesWorker = new SharedPreferencesWorker(this);
        checkAppState();
        initViews();
        if (savedInstanceState != null) {
            int newsType = savedInstanceState.getInt(NEWS_TYPE, HOME_NEWS);
            selectViewPosition(newsType);
        } else {
            selectViewPosition(HOME_NEWS);
        }
        initListeners();
    }
    /**
     * check app run count and increase from SharedPreferencesWorker
     */
    private void checkAppState() {
        if (sharedPreferencesWorker.getAppState()>2) {
            adManager = new AdManager(this);
        }
        sharedPreferencesWorker.saveAppState(sharedPreferencesWorker.getAppState()+1);
    }
    /**
     * interstitial show action from AdManager
     */
    @Override
    public void interstitialWasShown() {
        Toast.makeText(getApplicationContext(), INTERSTITIAL_WAS_SHOWN, Toast.LENGTH_SHORT).show();
    }

    /**
     * cpi sent action from AppsFlyerManager
     */
    @Override
    public void eventCPIWasSend() {
        Toast.makeText(getApplicationContext(), CPI_EVENT_SENT, Toast.LENGTH_SHORT).show();

    }

    /**
     * cpi sent action from AppsFlyerManager
     */
    @Override
    public void eventCPAWasSend() {
        Toast.makeText(getApplicationContext(), CPA_EVENT_SENT, Toast.LENGTH_SHORT).show();

    }
    @Override
    public void showInterstitial() {
        if (adManager!=null)
            adManager.showInterstitial();
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
    protected void onResume() {
        super.onResume();
        if (adManager!=null)
        adManager.showBanner();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (adManager!=null)
        adManager.hideBanner();
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (isFirstShowInSession) {
            isFirstShowInSession = false;
        }
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