package id.wonderdeal.wonderdealapps.features.search;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import id.wonderdeal.wonderdealapps.R;
import id.wonderdeal.wonderdealapps.base.BaseActivity;
import id.wonderdeal.wonderdealapps.features.products.AllProductsAdapter;
import id.wonderdeal.wonderdealapps.model.ItemProduct;
import id.wonderdeal.wonderdealapps.utils.CallbackInterface;
import id.wonderdeal.wonderdealapps.utils.Consts;
import id.wonderdeal.wonderdealapps.utils.Helper;

/**
 * Created by agustinaindah on 22/09/2017.
 */

public class SearchActivity extends BaseActivity implements
        SearchView.OnQueryTextListener, SearchPresenter.View {

    @BindView(R.id.rvSearch)
    RecyclerView rvSearch;
    @BindView(R.id.txtNoData)
    TextView txtNoData;
    @BindView(R.id.layProgress)
    RelativeLayout layProgress;
    @BindView(R.id.layError)
    LinearLayout layError;
    @BindView(R.id.btnError)
    Button btnError;

    private MenuItem mSearch;
    private SearchView searchView;
    private String searchInput;
    private SearchPresenter mPresenter;
    private AllProductsAdapter mAdapter;
    private String mQuery;

    @Override
    protected void onActivityCreated(Bundle savedInstanceState) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mPresenter = new SearchPresenterImpl(this);
    }

    @Override
    protected int setView() {
        return R.layout.activity_search;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);

        mSearch = menu.findItem(R.id.menu_search);

        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView =
                (SearchView) mSearch.getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));

        setupSearchView();
        return true;
    }

    private void setupSearchView() {
        EditText search_edit_text = ((EditText)
                searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text));
        search_edit_text.setHint("Cari Deal Terbaik mu");

        searchView.setQuery(searchInput, false);
        searchView.setIconified(false);
        searchView.setImeOptions(EditorInfo.IME_ACTION_SEARCH);
        searchView.setOnQueryTextListener(this);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        mQuery = query;
        mPresenter.getSearch(query);
        Helper.hideKeyboard(this);
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    @Override
    public void showProgress() {
        layProgress.setVisibility(View.VISIBLE);
        txtNoData.setVisibility(View.GONE);
    }

    @Override
    public void hideProgress() {
        layProgress.setVisibility(View.GONE);
    }

    @Override
    public void showMessage(String msg) {
        Helper.createAlert(this, Consts.STR_INFO, msg, true, new CallbackInterface() {
            @Override
            public void callback() {
                mPresenter.getSearch(mQuery);
            }
        });
    }

    @OnClick(R.id.btnError)
    public void reload(){
        layError.setVisibility(View.GONE);
        mPresenter.getSearch(mQuery);
    }

    @Override
    public void notConnect(String msg) {
        layError.setVisibility(View.VISIBLE);
    }

    @Override
    public void success(List<ItemProduct> mData) {
        mAdapter = new AllProductsAdapter(mData, this);

        txtNoData.setVisibility((mData.size()==0)? View.VISIBLE : View.GONE);
        rvSearch.setHasFixedSize(true);
        rvSearch.setLayoutManager(new LinearLayoutManager(this));
        rvSearch.setAdapter(mAdapter);
        rvSearch.setNestedScrollingEnabled(false);

        Animation animation = AnimationUtils.loadAnimation(this, R.anim.up_from_bottom);
        rvSearch.startAnimation(animation);
    }
}
