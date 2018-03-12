package id.wonderdeal.wonderdealapps.features.checkout;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.tonyvu.sc.model.Cart;
import com.android.tonyvu.sc.model.Saleable;
import com.android.tonyvu.sc.util.CartHelper;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.OnClick;
import id.wonderdeal.wonderdealapps.R;
import id.wonderdeal.wonderdealapps.base.BaseActivity;
import id.wonderdeal.wonderdealapps.features.checkout.order_success.OrderSuccessDialogFragment;
import id.wonderdeal.wonderdealapps.model.CartItem;
import id.wonderdeal.wonderdealapps.model.ItemInvoice;
import id.wonderdeal.wonderdealapps.model.ItemProduct;
import id.wonderdeal.wonderdealapps.utils.Consts;
import id.wonderdeal.wonderdealapps.utils.Helper;

/**
 * Created by agustinaindah on 16/10/2017.
 */

public class CheckoutActivity extends BaseActivity implements
        CheckoutAdapter.CheckoutAdapterListener, CheckoutPresenter.View {

    @BindView(R.id.edtNama)
    EditText edtNama;
    @BindView(R.id.edtAlamat)
    EditText edtAlamat;
    @BindView(R.id.edtTelp)
    EditText edtTelp;
    @BindView(R.id.edtEmail)
    EditText edtEmail;
    @BindView(R.id.edtNotes)
    EditText edtNotes;
    @BindView(R.id.rgPaymentMethod)
    RadioGroup rgPaymentMethod;
    @BindView(R.id.rvCheckout)
    RecyclerView rvCheckout;
    /*@BindView(R.id.txtSubTotal)
    TextView txtSubTotal;*/
    @BindView(R.id.txtGrandTotal)
    TextView txtGrandTotal;
    @BindView(R.id.btnConfrimOrder)
    Button btnConfrimOrder;

    @BindString(R.string.msg_empty)
    String strMsgEmpty;

    private ProgressDialog progressDialog;
    private CheckoutAdapter mAdapter;
    private int totalBill;
    private CheckoutPresenter mPresenter;
    private String paymentMethod;

    @Override
    protected void onActivityCreated(Bundle savedInstanceState) {
        initLoading();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mPresenter = new CheckoutPresenterImpl(this);
        setupCheckout();
    }

    @OnClick(R.id.btnConfrimOrder)
    public void confirmOrder(){
        if (!isNotValidate()){
            mPresenter.confirmOrder(mName(),
                    mAddress(),
                    mPhone(),
                    mEmail(),
                    mNote(),
                    getPaymentMethod(),
                    Helper.calculateTotalBill(mAdapter.getDataCheckout()),
                    mAdapter.getDataCheckout()
            );
        }
    }

    private String getPaymentMethod() {
        return (rgPaymentMethod.getCheckedRadioButtonId() == R.id.rbCod) ?
                Consts.COD : Consts.TRANSFER;
    }

    private String mName(){
        return edtNama.getText().toString();
    }
    private String mAddress(){
        return edtAlamat.getText().toString();
    }
    private String mPhone(){
        return edtTelp.getText().toString();
    }
    private String mEmail(){
        return edtEmail.getText().toString();
    }
    private String mNote(){
        return edtNotes.getText().toString();
    }

    private void setupCheckout() {
        mAdapter = new CheckoutAdapter(this, this);
        Cart cart = CartHelper.getCart();
        ArrayList<ItemInvoice> invoices = new ArrayList<ItemInvoice>();
        Map<Saleable, Integer> itemMap = cart.getItemWithQuantity();
        for (Map.Entry<Saleable, Integer> entry : itemMap.entrySet()) {
            ItemProduct itemProduct = ((ItemProduct) entry.getKey());
            if (invoices.size() == 0) {
                invoices.add(getItemInvoice(entry, itemProduct));
                continue;
            }
            for (int i = 0; i < invoices.size(); i++) {
                if (invoices.get(i).getID() == ((ItemProduct) entry.getKey()).getID()) {
                    CartItem cartItem = new CartItem();
                    cartItem.setItemProduct(itemProduct);
                    cartItem.setQuantity(entry.getValue());

                    invoices.get(i).getItems().add(cartItem);
                } else {
                    invoices.add(getItemInvoice(entry, itemProduct));
                }
                break;
            }
        }
        mAdapter.updateItemInvoices(invoices);
        rvCheckout.setHasFixedSize(true);
        rvCheckout.setLayoutManager(new LinearLayoutManager(this));
        rvCheckout.setAdapter(mAdapter);
        rvCheckout.setNestedScrollingEnabled(false);
        setTotalBill();
    }

    private void setTotalBill() {
        totalBill = Helper.calculateTotalBill(mAdapter.getDataCheckout());
        txtGrandTotal.setText(Helper.numberCurrency(totalBill));
    }

    private void initLoading() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(Consts.STR_LOADING);
    }

    @Override
    protected int setView() {
        return R.layout.activity_checkout_2;
    }

    private ItemInvoice getItemInvoice(Map.Entry<Saleable, Integer> entry, ItemProduct itemProduct) {
        ItemInvoice invoice = new ItemInvoice();

        invoice.setID(itemProduct.getID());

        List<CartItem> cartItems = new ArrayList<CartItem>();
        CartItem cartItem = new CartItem();
        cartItem.setItemProduct(itemProduct);
        cartItem.setQuantity(entry.getValue());

        cartItems.add(cartItem);

        invoice.setItems(cartItems);

        return invoice;
    }

    public boolean isNotValidate(){
        edtNama.setError(null);
        edtAlamat.setError(null);
        edtTelp.setError(null);
        edtEmail.setError(null);

        boolean cancel = false;
        View focus = null;

        if (TextUtils.isEmpty(getInput().get("nama").getAsString())){
            edtNama.setError(strMsgEmpty);
            focus = edtNama;
            cancel = true;
        }
        if (TextUtils.isEmpty(getInput().get("alamat").getAsString())){
            edtAlamat.setError(strMsgEmpty);
            focus = edtAlamat;
            cancel = true;
        }
        if (TextUtils.isEmpty(getInput().get("telp").getAsString())){
            edtTelp.setError(strMsgEmpty);
            focus = edtEmail;
            cancel = true;
        }
        if (TextUtils.isEmpty(getInput().get("email").getAsString())){
            edtEmail.setError(strMsgEmpty);
            focus = edtEmail;
            cancel = true;
        } else if (!Helper.isEmail(getInput().get("email").getAsString())){
            edtEmail.setError(getString(R.string.msg_not_valid));
            focus = edtEmail;
            cancel = true;
        }
        if (cancel){
            focus.requestFocus();
        }
        return cancel;
    }

    private JsonObject getInput() {
        JsonObject jsonInput = new JsonObject();
        try {
            jsonInput.addProperty("nama", edtNama.getText().toString());
            jsonInput.addProperty("alamat", edtAlamat.getText().toString());
            jsonInput.addProperty("telp", edtTelp.getText().toString());
            jsonInput.addProperty("email", edtEmail.getText().toString());
            jsonInput.addProperty("note", edtNotes.getText().toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonInput;
    }

    @Override
    public void onClickItem(int position, ItemInvoice itemInvoice) {

    }

    @Override
    public void showProgress() {
        progressDialog.show();
    }

    @Override
    public void hideProgress() {
        progressDialog.dismiss();
    }

    @Override
    public void showMessage(String msg) {
        Helper.createAlert(this, Consts.STR_INFO, msg);
    }

    @Override
    public void notConnect(String msg) {
        Helper.createAlert(this, Consts.STR_INFO, msg);
    }

    @Override
    public void success(JsonObject jsonRes) {
        //Toast.makeText(this, "sukses deh", Toast.LENGTH_LONG).show();
        String orderId = jsonRes.get("order_id").getAsString();
        DialogFragment dialogFragment = OrderSuccessDialogFragment.newInstance(orderId ,getPaymentMethod());
        dialogFragment.show(getSupportFragmentManager(), Consts.DIALOG);
    }
}
