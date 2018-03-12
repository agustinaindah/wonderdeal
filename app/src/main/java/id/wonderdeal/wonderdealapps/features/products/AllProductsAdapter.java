package id.wonderdeal.wonderdealapps.features.products;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.ThemedSpinnerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.wonderdeal.wonderdealapps.R;
import id.wonderdeal.wonderdealapps.base.BaseRecyclerViewAdapter;
import id.wonderdeal.wonderdealapps.features.products.detail.DetailProductsActivity;
import id.wonderdeal.wonderdealapps.model.ItemProduct;
import id.wonderdeal.wonderdealapps.utils.Consts;
import id.wonderdeal.wonderdealapps.utils.Helper;

/**
 * Created by agustinaindah on 14/09/2017.
 */

public class AllProductsAdapter extends BaseRecyclerViewAdapter<ItemProduct> {

    public AllProductsAdapter(List<ItemProduct> mData, Context mContext) {
        super(mData, mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_simple_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        renderData(get(position), (ViewHolder) holder);
    }

    private void renderData(final ItemProduct itemProduct, ViewHolder holder) {
        Helper.displayImage(mContext, itemProduct.getThumb(), holder.imgItemProduct);
        holder.txtItemProductMerk.setText(itemProduct.getPostTitle());
        holder.txtItemProductCategory.setText(itemProduct.getCategory());
        holder.txtItemProductDiscPrice.setText(Helper.numberCurrency(Integer.valueOf(itemProduct.getSalePrice())));
        holder.txtItemProductPrice.setText(Helper.numberCurrency(Integer.valueOf(itemProduct.getNormalPrice())));
        holder.txtItemProductPrice.setPaintFlags(holder.txtItemProductPrice.getPaintFlags()
                | Paint.STRIKE_THRU_TEXT_FLAG);
        //holder.txtItemProductExp.setVisibility(isEndDateProduct(itemProduct) ? View.VISIBLE : View.GONE);
        holder.txtItemProductDisc.setText(itemProduct.getDiskon() + "%");
        holder.layItemProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, DetailProductsActivity.class);
                intent.putExtra(Consts.ARG_ID, itemProduct.getID());
                mContext.startActivity(intent);
            }
        });

       /* if (itemProduct.getExpiredProductFormatEnak().equals("")) {
            holder.txtItemProductExp.setVisibility(View.GONE);
        } else {
            holder.txtItemProductExp.setVisibility(View.VISIBLE);
            holder.txtItemProductExp.setText("Expired: \n " +
                    Helper.parseToDateString(itemProduct.getExpiredProductFormatEnak(), Consts.TYPE_DATE));
        }*/

        float rating = Float.parseFloat(itemProduct.getJumlahBintang());
        holder.ratingBar.setRating(rating);
    }

//    private boolean isEndDateProduct(ItemProduct itemProduct){
//        return Helper.parseStringToDate(itemProduct.getExpiredProductFormatEnak(),Consts.TYPE_DATE).after(new Date());
//    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.layItemProduct)
        RelativeLayout layItemProduct;
        @BindView(R.id.imgItemProduct)
        ImageView imgItemProduct;
        @BindView(R.id.txtItemProductMerk)
        TextView txtItemProductMerk;
        @BindView(R.id.txtItemProductCategory)
        TextView txtItemProductCategory;
        @BindView(R.id.txtItemProductPrice)
        TextView txtItemProductPrice;
        @BindView(R.id.txtItemProductDiscPrice)
        TextView txtItemProductDiscPrice;
        @BindView(R.id.txtItemProductDisc)
        TextView txtItemProductDisc;
        /*@BindView(R.id.txtItemProductExp)
        TextView txtItemProductExp;*/
        @BindView(R.id.ratingBar)
        RatingBar ratingBar;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
