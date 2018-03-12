package id.wonderdeal.wonderdealapps.features.cart;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.tonyvu.sc.model.Cart;
import com.android.tonyvu.sc.util.CartHelper;

import butterknife.BindView;
import butterknife.OnClick;
import id.wonderdeal.wonderdealapps.R;
import id.wonderdeal.wonderdealapps.base.BaseActivity;
import id.wonderdeal.wonderdealapps.features.checkout.CheckoutActivity;
import id.wonderdeal.wonderdealapps.utils.Helper;

/**
 * Created by agustinaindah on 10/10/2017.
 */

public class CartActivity extends BaseActivity implements CartAdapter.CartAdapterListener{

    @BindView(R.id.rvCart)
    RecyclerView rvCart;
    @BindView(R.id.btnCheckout)
    Button btnCheckout;
    @BindView(R.id.txtNoData)
    TextView txtNoData;
  /*  @BindView(R.id.txtDelete)
    TextView txtDelete;*/

    private CartAdapter mAdapter;

    @Override
    protected void onActivityCreated(Bundle savedInstanceState) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        txtNoData.setVisibility(View.GONE);
        btnCheckout.setVisibility(View.GONE);
        txtNoData.setVisibility(View.GONE);
       // txtDelete.setVisibility(View.GONE);
        setupCart();
    }

    private void setupCart() {
         Cart cart = CartHelper.getCart();
        if (cart.getTotalQuantity() > 0) {
            mAdapter = new CartAdapter(this);
            mAdapter.updateCartItems(Helper.getCartItems(cart));
            mAdapter.setListener(this);
            rvCart.setHasFixedSize(true);
            rvCart.setLayoutManager(new LinearLayoutManager(this));
            rvCart.setAdapter(mAdapter);
            rvCart.setNestedScrollingEnabled(false);
            btnCheckout.setVisibility(View.VISIBLE);
            //txtDelete.setVisibility(View.VISIBLE);
        } else {
            txtNoData.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected int setView() {
        return R.layout.activity_cart;
    }

    @OnClick(R.id.btnCheckout)
    public void checkOut(){
        Intent intent = new Intent(this, CheckoutActivity.class);
        startActivity(intent);
    }

    @Override
    public void onEmpty() {
        txtNoData.setVisibility(View.VISIBLE);
        btnCheckout.setVisibility(View.GONE);
        //txtDelete.setVisibility(View.GONE);
    }
}
