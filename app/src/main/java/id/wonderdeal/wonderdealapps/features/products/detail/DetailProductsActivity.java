package id.wonderdeal.wonderdealapps.features.products.detail;

import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.tonyvu.sc.model.Cart;
import com.android.tonyvu.sc.util.CartHelper;
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.ms.square.android.expandabletextview.ExpandableTextView;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.OnClick;
import id.wonderdeal.wonderdealapps.R;
import id.wonderdeal.wonderdealapps.base.BaseActivity;
import id.wonderdeal.wonderdealapps.features.cart.CartActivity;
import id.wonderdeal.wonderdealapps.features.search.SearchActivity;
import id.wonderdeal.wonderdealapps.model.ImageGallery;
import id.wonderdeal.wonderdealapps.model.ItemProduct;
import id.wonderdeal.wonderdealapps.utils.CallbackInterface;
import id.wonderdeal.wonderdealapps.utils.Consts;
import id.wonderdeal.wonderdealapps.utils.Helper;

public class DetailProductsActivity extends BaseActivity implements DetailProductsPresenter.View {

    @BindView(R.id.sliderProductDetail)
    SliderLayout sliderProductDetail;
    @BindView(R.id.txtDetailTitle)
    TextView txtDetailTitle;
    @BindView(R.id.txtDetailPriceNormal)
    TextView txtDetailPriceNormal;
    @BindView(R.id.txtDetailDiscount)
    TextView txtDetailDiscount;
    @BindView(R.id.txtDetailSold)
    TextView txtDetailSold;
    @BindView(R.id.txtDetailPriceDisc)
    TextView txtDetailPriceDisc;
    @BindView(R.id.txtDetailCat)
    TextView txtDetailCat;
    /*@BindView(R.id.txtDetailNamaTempat)
    TextView txtDetailNamaTempat;*/
    @BindView(R.id.txtDetailAlamat)
    TextView txtDetailAlamat;
    @BindView(R.id.expandProductDetailDesc)
    ExpandableTextView expandProductDetailDesc;
    @BindView(R.id.expandProductDetailInfo)
    ExpandableTextView expandProductDetailInfo;
    /* @BindView(R.id.txtDetailEndTime)
     TextView txtDetailEndTime;*/
    @BindView(R.id.ratingBarDetail)
    RatingBar ratingBarDetail;
    @BindView(R.id.txtTitleInfo)
    TextView txtTitleInfo;
    @BindView(R.id.btnShopNow)
    Button btnShopNow;
    @BindView(R.id.btnViaWhatsApp)
    Button btnViaWhatsApp;
    /* @BindView(R.id.txtMapsUrl)
     TextView txtMapsUrl;*/
    /* @BindView(R.id.txtDetailAddressMaps)
     TextView txtDetailAddressMaps;*/
    @BindView(R.id.wvGoogleMap)
    WebView wvGoogleMap;
    @BindView(R.id.layProgress)
    RelativeLayout layProgress;
    @BindView(R.id.layError)
    LinearLayout layError;
    @BindView(R.id.btnError)
    Button btnError;

    @BindString(R.string.msg_stock_out)
    String strStockSoldOut;

    private DetailProductsPresenter mPresenter;
    private int id;
    private ItemProduct mItemProduct;

    @Override
    protected void onActivityCreated(Bundle savedInstanceState) {
        mPresenter = new DetailProductsPresenterImpl(this);
        initData();
    }

    private void initData() {
        id = getIntent().getIntExtra(Consts.ARG_ID, 0);
        mItemProduct = (ItemProduct) getIntent().getSerializableExtra(Consts.ARG_DATA);
        mPresenter.getSingleProduct(id);
    }

    private void displayData(final ItemProduct itemProduct) {
        txtDetailTitle.setText(itemProduct.getPostTitle());
        txtDetailCat.setText(itemProduct.getCategory());
        expandProductDetailDesc.setText(Html.fromHtml(itemProduct.getKonten()));

        /*if (itemProduct.getExpiredProductFormatEnak().equals("")) {
            txtDetailEndTime.setVisibility(View.GONE);
        } else {
            txtDetailEndTime.setVisibility(View.VISIBLE);
            txtDetailEndTime.setText("Expired: " +
                    Helper.parseToDateString(itemProduct.getExpiredProductFormatEnak(), Consts.TYPE_DATE));
        }*/

        if (itemProduct.getAlamat().equals("")) {
            txtDetailAlamat.setVisibility(View.GONE);
        } else {
            txtDetailAlamat.setVisibility(View.VISIBLE);
            txtDetailAlamat.setText(itemProduct.getAlamat());
        }

        if (itemProduct.getInfo().equals("")) {
            txtTitleInfo.setVisibility(View.GONE);
            expandProductDetailInfo.setVisibility(View.GONE);
        } else {
            txtTitleInfo.setVisibility(View.VISIBLE);
            expandProductDetailInfo.setVisibility(View.VISIBLE);
            expandProductDetailInfo.setText(Html.fromHtml(itemProduct.getInfo()));
        }

        float rating = Float.parseFloat(itemProduct.getJumlahBintang());
        ratingBarDetail.setRating(rating);
        // txtDetailAddressMaps.setText(itemProduct.getAlamat());
    }

    private void displayTitleWa(final ItemProduct itemProduct) {
        final String URL_CONNECT_WA = "https://api.whatsapp.com/send?phone=62817268494&text=*Halo*%20Admin,%20Saya%20mau%20order%20" + itemProduct.getPostTitle() + "%0AJumlah%20%20%20%20%20%20:%0ANama%20%20%20%20%20%20%20%20:%0AAlamat%20%20%20%20%20%20%20:%0AKelurahan%20%20:%0AKecamatan%20:%0AKota/Kab%20%20%20%20:%0AKode%20Pos%20%20%20%20:%0A";
        btnViaWhatsApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse(URL_CONNECT_WA);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
    }

    private void displayMaps(final ItemProduct itemProduct) {
        String iframe = itemProduct.getMapsAlamat();
        wvGoogleMap.getSettings().setJavaScriptEnabled(true);
        wvGoogleMap.loadData(iframe, "text/html", "utf-8");
    }

    @Override
    protected int setView() {
        return R.layout.activity_detail_product;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail_item_product, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
           /* case R.id.action_wishlist:
                break;*/
            case R.id.action_search_detail:
                startActivity(new Intent(this, SearchActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showProgress() {
        layProgress.setVisibility(View.VISIBLE);
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
                mPresenter.getSingleProduct(id);
            }
        });
    }

    @Override
    public void notConnect(String msg) {
        layError.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.btnShopNow)
    public void buy() {
        if (Integer.valueOf(mItemProduct.getStock()) == 0) {
            Helper.createAlert(this, Consts.STR_INFO, strStockSoldOut);
        } else {
            Cart cart = CartHelper.getCart();
            cart.add(mItemProduct, 1);
            Intent intent = new Intent(this, CartActivity.class);
            startActivity(intent);
        }
    }

    /*@OnClick(R.id.btnViaWhatsApp)
    public void clickToWa(View view) {
        Uri uri = Uri.parse(URL_CONNECT_WA);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
*/
    @OnClick(R.id.btnError)
    public void reload() {
        layError.setVisibility(View.GONE);
        mPresenter.getSingleProduct(id);
    }

    @Override
    public void success(ItemProduct itemProduct) {
        mItemProduct = itemProduct;
        displayData(itemProduct);
        displayPrice(itemProduct);
        setupSlider(itemProduct);
        displayMaps(itemProduct);
        displayTitleWa(itemProduct);
    }

    private void displayPrice(final ItemProduct itemProduct) {
        txtDetailDiscount.setText(itemProduct.getDiskon() + "%");
        txtDetailSold.setText(itemProduct.getTotalSales());
        txtDetailPriceNormal.setText(Helper.numberCurrency(Integer.valueOf(itemProduct.getNormalPrice())));
        txtDetailPriceNormal.setPaintFlags(txtDetailPriceNormal.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        txtDetailPriceDisc.setText(Helper.numberCurrency(Integer.valueOf(itemProduct.getSalePrice())));
        //txtMapsUrl.setText(itemProduct.getMapsAlamat());
    }

    private void setupSlider(ItemProduct itemProduct) {
        for (int i = 0; i < itemProduct.getImageGallery().size(); i++) {
            ImageGallery imageGallery = itemProduct.getImageGallery().get(i);
            DefaultSliderView sliderView = new DefaultSliderView(this);
            sliderView
                    .image(imageGallery.getThumbnail())
                    .setScaleType(BaseSliderView.ScaleType.Fit);
            sliderProductDetail.addSlider(sliderView);
        }
        sliderProductDetail.setPresetTransformer(SliderLayout.Transformer.Default);
        sliderProductDetail.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        sliderProductDetail.setCustomAnimation(new DescriptionAnimation());
        sliderProductDetail.stopAutoCycle();
    }
}
