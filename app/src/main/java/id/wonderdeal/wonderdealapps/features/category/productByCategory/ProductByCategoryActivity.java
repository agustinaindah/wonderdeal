package id.wonderdeal.wonderdealapps.features.category.productByCategory;

import android.content.ClipData;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import id.wonderdeal.wonderdealapps.R;
import id.wonderdeal.wonderdealapps.base.BaseActivity;
import id.wonderdeal.wonderdealapps.features.products.AllProductsAdapter;
import id.wonderdeal.wonderdealapps.model.Category;
import id.wonderdeal.wonderdealapps.model.ItemProduct;
import id.wonderdeal.wonderdealapps.utils.CallbackInterface;
import id.wonderdeal.wonderdealapps.utils.Consts;
import id.wonderdeal.wonderdealapps.utils.Helper;

/**
 * Created by agustinaindah on 25/09/2017.
 */

public class ProductByCategoryActivity extends BaseActivity implements ProductByCategoryPresenter.View{

    // TODO: 25/09/2017 tinggal nunggu api mas sdx

    @BindView(R.id.rvProductByCategory)
    RecyclerView rvProductByCategory;
    @BindView(R.id.layProgress)
    RelativeLayout layProgress;
    @BindView(R.id.txtNoData)
    TextView txtNoData;
    @BindView(R.id.layError)
    LinearLayout layError;
    @BindView(R.id.btnError)
    Button btnError;

     private ProductByCategoryPresenter mPresenter;
    private AllProductsAdapter mAdapter;
    private Category data;

    @Override
    protected void onActivityCreated(Bundle savedInstanceState) {
         getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        data = (Category) getIntent().getSerializableExtra(Consts.ARG_DATA);
        setTitle(data.getCategoryTitle());
        mPresenter = new ProductByCategoryPresenterImpl(this);
        mPresenter.getProductByCategory(data.getCategoryId());
    }

    @Override
    protected int setView() {
        return R.layout.activity_product_by_category;
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
                mPresenter.getProductByCategory(data.getCategoryId());
            }
        });
    }


    @OnClick(R.id.btnError)
    public void reload() {
        layError.setVisibility(View.GONE);
        mPresenter.getProductByCategory(data.getCategoryId());
    }

    @Override
    public void notConnect(String msg) {
        layError.setVisibility(View.VISIBLE);
    }

    @Override
    public void showProduct(List<ItemProduct> mData) {
        txtNoData.setVisibility((mData.size()==0) ? View.VISIBLE : View.GONE);
        mAdapter = new AllProductsAdapter(mData,this);
        rvProductByCategory.setHasFixedSize(true);
        rvProductByCategory.setLayoutManager(new LinearLayoutManager(this));
        rvProductByCategory.setAdapter(mAdapter);
        rvProductByCategory.setNestedScrollingEnabled(false);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.up_from_bottom);
        rvProductByCategory.startAnimation(animation);
    }
}
