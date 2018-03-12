package id.wonderdeal.wonderdealapps.features;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.wonderdeal.wonderdealapps.R;
import id.wonderdeal.wonderdealapps.base.BaseRecyclerViewAdapter;
import id.wonderdeal.wonderdealapps.utils.ViewHelper;

/**
 * Created by agustinaindah on 13/10/2017.
 */

public abstract class SimpleItemListAdapter<T> extends BaseRecyclerViewAdapter<T> {

    public SimpleItemListAdapter(List<T> mData, Context mContext) {
        super(mData, mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = ViewHelper.inflateView(parent, R.layout.item_list);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        renderData(get(position), (ViewHolder) holder);
    }

    protected abstract void renderData(T item, ViewHolder holder);

    public class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.layItemList)
        public RelativeLayout layItemList;
        @BindView(R.id.imgItemList)
        public ImageView imgItemList;
        @BindView(R.id.txtItemList)
        public TextView txtItemList;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
