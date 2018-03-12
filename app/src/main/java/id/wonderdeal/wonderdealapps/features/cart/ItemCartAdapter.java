package id.wonderdeal.wonderdealapps.features.cart;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.wonderdeal.wonderdealapps.R;
import id.wonderdeal.wonderdealapps.model.ItemCart;
import id.wonderdeal.wonderdealapps.utils.Helper;

/**
 * Created by agustinaindah on 11/10/2017.
 */

public class ItemCartAdapter extends RecyclerView.Adapter<ItemCartAdapter.ViewHolder> {

    private Context context;
    private List<ItemCart> itemCarts ;

    public ItemCartAdapter(Context context) {
        this.context = context;
    }

    public void updateData(List<ItemCart> itemCarts){
        this.itemCarts = itemCarts;
        notifyDataSetChanged();
    }

    @Override
    public ItemCartAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_product_cart2, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemCartAdapter.ViewHolder holder, int position) {
        ItemCart itemCart = itemCarts.get(position);
        Helper.displayImage(context, itemCart.getImage(), holder.imgProductCart, true);
        holder.txtProductTitle.setText(itemCart.getName());
        holder.txtProductPrice.setText(itemCart.getPrice());
        holder.txtTotalItem.setText(getTextSubTotal(itemCart));
        final String[] qtys = context.getResources().getStringArray(R.array.data_qty);
        int idx = Arrays.asList(qtys).indexOf(String.valueOf(itemCart.getQty()));
        holder.spinQty.setSelection(idx);
        holder.spinQty.setEnabled(false);
        holder.layItemCart.setClickable(false);
        holder.imgClearCart.setVisibility(View.GONE);
    }

    @NonNull
    private String getTextSubTotal(ItemCart itemCart) {
        return "Sub Total Item : " +
                Helper.numberCurrency(itemCart.getPrice() * itemCart.getQty());
    }

    @NonNull
    private String getTextPrice(ItemCart itemCart) {
        return Helper.numberCurrency(itemCart.getPrice());
    }

    @Override
    public int getItemCount() {
        return itemCarts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.layItemCart)
        LinearLayout layItemCart;
        @BindView(R.id.imgClearCart)
        ImageView imgClearCart;
        @BindView(R.id.imgProductCart)
        ImageView imgProductCart;
        @BindView(R.id.txtProductTitle)
        TextView txtProductTitle;
        @BindView(R.id.spinQty)
        Spinner spinQty;
        @BindView(R.id.txtTotalItem)
        TextView txtTotalItem;
        @BindView(R.id.txtProductPrice)
        TextView txtProductPrice;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
