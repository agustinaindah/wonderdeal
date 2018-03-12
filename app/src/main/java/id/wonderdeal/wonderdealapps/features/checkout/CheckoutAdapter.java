package id.wonderdeal.wonderdealapps.features.checkout;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.wonderdeal.wonderdealapps.R;
import id.wonderdeal.wonderdealapps.features.cart.CartAdapter;
import id.wonderdeal.wonderdealapps.model.ItemInvoice;
import id.wonderdeal.wonderdealapps.utils.Helper;

/**
 * Created by agustinaindah on 17/10/2017.
 */

public class CheckoutAdapter extends RecyclerView.Adapter<CheckoutAdapter.ViewHolder>{

    private static final String TAG = "CheckoutAdapter";
    private List<ItemInvoice> itemInvoices = Collections.emptyList();
    private Context context;

    private CheckoutAdapterListener mCallback;

    public CheckoutAdapter(Context context, CheckoutAdapterListener listener) {
        this.context = context;
        mCallback = listener;
    }

    public CheckoutAdapter(Context context) {
        this(context, null);
    }

    public List<ItemInvoice> getDataCheckout() {
        return itemInvoices;
    }

    public void updateItemInvoices(List<ItemInvoice> itemInvoices) {
        this.itemInvoices = itemInvoices;
        notifyDataSetChanged();
    }

    @Override
    public CheckoutAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_transaksi_checkout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final CheckoutAdapter.ViewHolder holder, int position) {

        final ItemInvoice itemInvoice = getItem(position);

        //holder.txtPlaceName.setText(itemInvoice.getMerchantName());
        //holder.txtPriceGrandTotal.setText(Helper.numberCurrency(itemInvoice.getGrandTotal()));

        CartAdapter cartAdapter = new CartAdapter(context);
        cartAdapter.isCheckout = true;
        cartAdapter.updateCartItems(itemInvoice.getItems());

        holder.rvItemPlace.setHasFixedSize(true);
        holder.rvItemPlace.setLayoutManager(new LinearLayoutManager(context));
        holder.rvItemPlace.setNestedScrollingEnabled(false);
        holder.rvItemPlace.setAdapter(cartAdapter);
    }

    public ItemInvoice getItem(int position) {
        return itemInvoices.get(position);
    }

    @Override
    public int getItemCount() {
        return itemInvoices.size();
    }

    public interface CheckoutAdapterListener {
        void onClickItem(int position, ItemInvoice itemInvoice);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.rvItemPlace)
        RecyclerView rvItemPlace;
        @BindView(R.id.layItemTransaction)
        RelativeLayout layItemTransaction;
       /* @BindView(R.id.txtPlaceName)
        TextView txtPlaceName;*/
        /*@BindView(R.id.txtPriceGrandTotal)
        TextView txtPriceGrandTotal;*/

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
