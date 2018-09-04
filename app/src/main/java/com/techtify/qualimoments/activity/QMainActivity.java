package com.techtify.qualimoments.activity;

import android.content.Context;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ListView;

import com.techtify.qualimoments.QCore;
import com.techtify.qualimoments.R;
import com.techtify.qualimoments.adapters.MenuAdapter;
import com.techtify.qualimoments.adapters.SectionsPagerAdapter;
import com.techtify.qualimoments.custom.FastViewPager;
import com.techtify.qualimoments.model.QMenuItem;

public class QMainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private TabLayout tabLayout;
    private FastViewPager mViewPager;
    private int[] imageResId = {
            R.drawable.ic_explore,
            R.drawable.ic_feed,
            R.drawable.ic_network,
            R.drawable.ic_notifications,
            R.drawable.ic_profile
    };
    private int selectedTab = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qmain);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        final DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);

        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ListView menuList = findViewById(R.id.menuList);
        final MenuAdapter menuAdapter = new MenuAdapter(this);
        menuList.setAdapter(menuAdapter);
        menuList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                QMenuItem item = menuAdapter.getItem(i);
                if (item == null) return;
                if (item.getId() == 0) {
                    mViewPager.setCurrentItem(0);
                    drawer.closeDrawers();
                }
                if (item.getId() == 1) {
                    mViewPager.setCurrentItem(1);
                    drawer.closeDrawers();
                }
                if (item.getId() == 3) {
                    mViewPager.setCurrentItem(3);
                    drawer.closeDrawers();
                }
                if (item.getId() == 4) {
                    mViewPager.setCurrentItem(2);
                    drawer.closeDrawers();
                }
                if (item.getId() == 7) {
                    QCore.logout(QMainActivity.this);
                }
            }
        });

        tabLayout = findViewById(R.id.tabs);
        tabLayout.setSelectedTabIndicatorHeight(0);
        mViewPager = findViewById(R.id.container);

        updateTab();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.qmain, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        return id == R.id.action_settings || super.onOptionsItemSelected(item);

    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return true;
    }

    private void updateTab() {
        SectionsPagerAdapter mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mSectionsPagerAdapter);
        tabLayout.setupWithViewPager(mViewPager);
        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            tabLayout.getTabAt(i).setIcon(imageResId[i]);
            tabLayout.getTabAt(i).setText("");
        }
        for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
            tabLayout.getTabAt(i).getIcon().setColorFilter(getResources().getColor(R.color.inac), PorterDuff.Mode.SRC_IN);
        }
        try {
            tabLayout.getTabAt(selectedTab).getIcon().setColorFilter(getResources().getColor(R.color.mainBlue), PorterDuff.Mode.SRC_IN);
        } catch (Exception e) {
            e.printStackTrace();
        }

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                selectedTab = position;
            }

            @Override
            public void onPageSelected(int position) {
                selectedTab = position;
                for (int i = 0; i < tabLayout.getTabCount(); i++) {
                    tabLayout.getTabAt(i).getIcon().setColorFilter(getResources().getColor(R.color.inac), PorterDuff.Mode.SRC_IN);
                }
                tabLayout.getTabAt(selectedTab).getIcon().setColorFilter(getResources().getColor(R.color.mainBlue), PorterDuff.Mode.SRC_IN);
                InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

                View v = getCurrentFocus();
                if (v == null)
                    return;

                if (inputManager != null) {
                    inputManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
