package id.wonderdeal.wonderdealapps.features.main;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import java.util.HashMap;

import butterknife.BindString;
import butterknife.BindView;
import id.wonderdeal.wonderdealapps.R;
import id.wonderdeal.wonderdealapps.WonderdealApp;
import id.wonderdeal.wonderdealapps.base.BaseActivity;
import id.wonderdeal.wonderdealapps.features.cart.CartActivity;
import id.wonderdeal.wonderdealapps.features.category.CategoryActivity;
import id.wonderdeal.wonderdealapps.features.category.CategoryFragment;
import id.wonderdeal.wonderdealapps.features.menu.ListMenuActivity;
import id.wonderdeal.wonderdealapps.features.menu.ListMenuFragment;
import id.wonderdeal.wonderdealapps.features.products.AllProductsFragment;
import id.wonderdeal.wonderdealapps.features.products.AllProductsFragment2;
import id.wonderdeal.wonderdealapps.features.search.SearchActivity;
import id.wonderdeal.wonderdealapps.utils.Consts;
import id.wonderdeal.wonderdealapps.utils.Helper;

/**
 * Created by agustinaindah on 20/09/2017.
 */

public class MainActivity2 extends BaseActivity implements OnTabSelectListener
        ,AllProductsFragment2.OnMainFragmentListener
       /*,TabLayout.OnTabSelectedListener*/{

    @BindView(R.id.appBarLayout)
    AppBarLayout appBarLayout;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.bottomBar)
    BottomBar bottomBar;
    @BindView(R.id.edtSearch)
    EditText edtSearch;
   /* @BindView(R.id.tabsHome)
    TabLayout tabsHome;
    @BindView(R.id.vpHome)
    ViewPager vpHome;*/
    @BindView(R.id.sliderBannerHome)
    SliderLayout sliderBannerHome;

    @BindString(R.string.action_back_pressed)
    String strBackPressed;

    private long backPressedTime = 0;
    private boolean isHome = true;
    private Fragment mFragment = null;
    private FragmentManager fm;
    //private MainFragmentAdapter mAdapter;

    @Override
    protected void onActivityCreated(Bundle savedInstanceState) {
        fm = getSupportFragmentManager();
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        bottomBar.setOnTabSelectListener(this);
        setupbanner();
        goSearch();
    }

    private void setupbanner() {
        SharedPreferences sPref = WonderdealApp.getInstance().Prefs();
        String banner = sPref.getString(Consts.BANNER, "[]");
        JsonArray jsonBanner = Helper.parseToJsonArray(banner);
        for (JsonElement element : jsonBanner) {
            JsonObject itemBanner = element.getAsJsonObject();
            DefaultSliderView sliderView = new DefaultSliderView(this);
            sliderView.image(itemBanner.get("image").getAsString())
                    .setScaleType(BaseSliderView.ScaleType.CenterCrop);
            sliderBannerHome.addSlider(sliderView);
        }
        sliderBannerHome.setPresetTransformer(SliderLayout.Transformer.Default);
        sliderBannerHome.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        sliderBannerHome.setCustomAnimation(new DescriptionAnimation());
        sliderBannerHome.setDuration(5000);

       /* HashMap<String,Integer> file_maps = new HashMap<String, Integer>();
        file_maps.put("Voucher Makan",R.drawable.background);
        file_maps.put("Voucher Hotel",R.drawable.background);
        file_maps.put("Voucher Cafe",R.drawable.background);

        for(String name : file_maps.keySet()){
            TextSliderView textSliderView = new TextSliderView(this);
            // initialize a SliderLayout
            textSliderView
                    .description(name)
                    .image(file_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit);

            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra",name);

            sliderBannerHome.addSlider(textSliderView);
        }
        sliderBannerHome.setPresetTransformer(SliderLayout.Transformer.Default);
        sliderBannerHome.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        sliderBannerHome.setCustomAnimation(new DescriptionAnimation());
        sliderBannerHome.setDuration(5000);*/
    }

    @Override
    protected void onStop() {
        super.onStop();
        sliderBannerHome.stopAutoCycle();
    }

    private void goSearch() {
        edtSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoActivity(SearchActivity.class);
            }
        });
    }

    private void goProduct(){
      /*  mAdapter = new MainFragmentAdapter(getSupportFragmentManager());
        vpHome.setAdapter(mAdapter);
        tabsHome.setupWithViewPager(vpHome);*/
    }

    private void goHome() {
       /* gotoFragment(fm, AllProductsFragment2.newInstance());
        setRemoveBackStack();*/
        bottomBar.selectTabWithId(R.id.tab_home);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_cart:
                Intent intent = new Intent(this, CartActivity.class);
                startActivity(intent);
                break;
            case R.id.action_chat:
                Uri uri = Uri.parse(Consts.URL_CONNECT_WA);
                intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (isHome == false) {
            if (getSupportFragmentManager().getBackStackEntryCount() != 0) {
                super.onBackPressed();
            } else {
                goHome();
            }
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

    @Override
    protected int setView() {
        return R.layout.activity_main3;
    }

    @Override
    protected void onResume() {
        super.onResume();
        bottomBar.selectTabWithId(R.id.tab_home);
    }

    @Override
    public void onTabSelected(@IdRes int tabId) {
        Intent intent = null;
        switch (tabId) {
            case R.id.tab_home:
                isHome = true;
                mFragment = AllProductsFragment2.newInstance();
                setRemoveBackStack();
                break;
          /*  case R.id.tab_whatsapp:
                isHome = false;
                Uri uri = Uri.parse(Consts.URL_CONNECT_WA);
                intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
                break;*/
            case R.id.tab_category:
                isHome = false;
                intent = new Intent(this, CategoryActivity.class);
                startActivity(intent);
                //mFragment = CategoryFragment.newInstance(Consts.CAT_MAIN,null);
                break;
            case R.id.tab_menu:
                isHome = false;
                //mFragment = ListMenuFragment.newInstance();
                intent = new Intent(this, ListMenuActivity.class);
                startActivity(intent);
                break;
           /*
            case R.id.tab_filter:
                isHome = false;
                break;*/
        }
        appBarLayout.setExpanded(isHome);
        if (mFragment != null) {
            gotoFragment(fm, mFragment);
        }
    }

    private void setRemoveBackStack() {
        fm.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }

    @Override
    public void onNotConnected(String msg, boolean isExpand) {
        appBarLayout.setExpanded(isExpand);
    }

}
