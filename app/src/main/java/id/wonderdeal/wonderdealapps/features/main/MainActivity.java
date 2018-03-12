package id.wonderdeal.wonderdealapps.features.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import id.wonderdeal.wonderdealapps.R;
import id.wonderdeal.wonderdealapps.base.BaseActivity;

import id.wonderdeal.wonderdealapps.utils.Helper;

import butterknife.BindString;
import butterknife.BindView;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener,
        TabLayout.OnTabSelectedListener{

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    @BindView(R.id.nav_view)
    NavigationView navigationView;
    @BindView(R.id.appBarLayout)
    AppBarLayout appBarLayout;
    @BindView(R.id.tabsHome)
    TabLayout tabsHome;
    @BindView(R.id.vpHome)
    ViewPager vpHome;

    @BindString(R.string.action_back_pressed)
    String strBackPressed;

    private long backPressedTime = 0;
    private boolean isHome = true;
    private Fragment mFragment = null;
    private FragmentManager fm;
    private MainFragmentAdapter mAdapter;

    @Override
    protected void onActivityCreated(Bundle savedInstanceState) {
        fm = getSupportFragmentManager();
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        setupActionBarDrawer();
        setupNavigationView();
        navigationView.setNavigationItemSelectedListener(this);
        goHome();
    }

    private void goHome() {
        mAdapter = new MainFragmentAdapter(getSupportFragmentManager());
        vpHome.setAdapter(mAdapter);
        tabsHome.setupWithViewPager(vpHome);
    }

    private void setupNavigationView() {
        View header = navigationView.getHeaderView(0);

       /* CircularImageView imgPhoto = (CircularImageView) header.findViewById(R.id.imgProfileHead);
        TextView txtName = (TextView) header.findViewById(R.id.txtName);*/

        /* SharedPreferences sPref = Owner99.getInstance().Prefs();
        Helper.displayImage(getApplicationContext(), sPref.getString(Consts.PHOTO, null), imgPhoto);
        txtName.setText(sPref.getString(Consts.NAME, "#member user"));*/
    }

    private void setupActionBarDrawer() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open,
                R.string.navigation_drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                setupNavigationView();
            }
        };
        drawer.setDrawerListener(toggle);
        toggle.syncState();
    }

    @Override
    protected int setView() {
        return R.layout.activity_main;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (getSupportFragmentManager().getBackStackEntryCount() != 0) {
                super.onBackPressed();
            } else {
                long t = System.currentTimeMillis();
                if (t - backPressedTime > 2000) {
                    backPressedTime = t;
                    Helper.createToast(this, strBackPressed);
                } else {
                    super.onBackPressed();
                }
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
           /* case R.id.action_search:
                Intent intent = new Intent(this, SearchActivity.class);
                startActivity(intent);
                break;*/
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        Intent intent = null;
        switch (item.getItemId()){
           /* case R.id.nav_home:
                mFragment = null;
                goHome();
                break;
            case R.id.nav_category:
                intent = new Intent(this, CategorysActivity.class);
                break;
            case R.id.nav_article:
                intent = new Intent(this, ArticleActivity.class);
                break;
           *//* case R.id.nav_gallery:
                intent = new Intent(this, GalleryActivity.class);
                break;*//*
            case R.id.nav_contact:
                intent = new Intent(this, ContactUsActivity.class);
                break;*/

        }
        if (intent != null){
            startActivity(intent);
        }
        if (mFragment != null){
            gotoFragment(fm, mFragment);
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void setRemoveBackStack(){
        fm.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        vpHome.setCurrentItem(tab.getPosition(), true);
        setRemoveBackStack();
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
