package id.wonderdeal.wonderdealapps.features.cart;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.tonyvu.sc.model.Cart;
import com.android.tonyvu.sc.util.CartHelper;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.wonderdeal.wonderdealapps.R;
import id.wonderdeal.wonderdealapps.model.CartItem;
import id.wonderdeal.wonderdealapps.utils.CallbackInterface;
import id.wonderdeal.wonderdealapps.utils.Helper;

/**
 * Created by agustinaindah on 10/10/2017.
 */

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

    private static final String TAG = "CartAdapter";
    public boolean isCheckout = false;
    private List<CartItem> cartItems = Collections.emptyList();
    private Context context;

    private CartAdapterListener mCallback;

    public CartAdapter(Context context) {
        this.context = context;
    }

    public void setListener(CartAdapterListener cartAdapterListener){
        mCallback=cartAdapterListener;
    }

    public void updateCartItems(List<CartItem> cartItems){
        this.cartItems = cartItems;
        notifyDataSetChanged();
    }

    @Override
    public CartAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_product_cart2, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final CartAdapter.ViewHolder holder, final int position) {
        final CartItem cartItem = cartItems.get(position);

        Helper.displayImage(context, cartItem.getItemProduct().getImageGallery().get(0).getThumbnail(), holder.imgProductCart, true);
        holder.txtProductTitle.setText(cartItem.getItemProduct().getPostTitle());
        holder.txtProductPrice.setText(Helper.numberCurrency(Integer.valueOf(cartItem.getItemProduct().getSalePrice())));
        holder.txtTotalItem.setText("Total : "+
                Helper.numberCurrency(
                        calculateItemPrice(
                                position + 1,
                                Integer.valueOf(cartItem.getItemProduct().getSalePrice())
                        )
                )
        );
        final String[] qtys = context.getResources().getStringArray(R.array.data_qty);
        int idx = Arrays.asList(qtys).indexOf(String.valueOf(cartItem.getQuantity()));
        holder.spinQty.setSelection(idx);
        holder.spinQty.setEnabled(!isCheckout);
        holder.spinQty.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                Cart cart  = CartHelper.getCart();
                cart.update(cartItem.getItemProduct(), position + 1);
                holder.txtTotalItem.setText("Total : "+
                Helper.numberCurrency(
                        calculateItemPrice(
                                position + 1,
                                Integer.valueOf(cartItem.getItemProduct().getSalePrice())
                        )
                ));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        /*if (!isCheckout) {
            holder.layItemCart.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    Helper.createPrompt(context, "Info", "Hapus dari keranjang?",
                            new CallbackInterface() {
                                @Override
                                public void callback() {
                                    Cart cart = CartHelper.getCart();
                                    List<CartItem> cartItems = Helper.getCartItems(cart);
                                    cart.remove(cartItems.get(holder.getAdapterPosition()).getItemProduct());
                                    cartItems.remove(holder.getAdapterPosition());
                                    updateCartItems(cartItems);
                                    notifyDataSetChanged();
                                    if (cartItems.size() == 0) {
                                        mCallback.onEmpty();
                                    }
                                }
                            });
                    return true;
                }
            });
        }else {
            holder.layItemCart.setClickable(false);
        }*/
        if (!isCheckout){
            holder.imgClearCart.setVisibility(View.VISIBLE);
            holder.imgClearCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Helper.createPrompt(context, "Info", "Hapus dari keranjang?",
                            new CallbackInterface() {
                        @Override
                        public void callback() {
                            Cart cart = CartHelper.getCart();
                            List<CartItem> cartItems = Helper.getCartItems(cart);
                            cart.remove(cartItems.get(holder.getAdapterPosition()).getItemProduct());
                            cartItems.remove(holder.getAdapterPosition());
                            updateCartItems(cartItems);
                            notifyDataSetChanged();
                            if (cartItems.size() == 0) {
                                mCallback.onEmpty();
                            }
                        }
                    });
                }
            });
        } else {
            holder.imgClearCart.setClickable(false);
        }
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    public interface CartAdapterListener {
        void onEmpty();
    }

    private int calculateItemPrice(int quantity, Integer salePrice) {
        return quantity * salePrice;
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
