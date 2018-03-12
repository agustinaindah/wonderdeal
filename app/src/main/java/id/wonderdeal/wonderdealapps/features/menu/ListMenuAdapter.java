package id.wonderdeal.wonderdealapps.features.menu;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

import id.wonderdeal.wonderdealapps.R;
import id.wonderdeal.wonderdealapps.base.BaseActivity;
import id.wonderdeal.wonderdealapps.features.SimpleItemListAdapter;
import id.wonderdeal.wonderdealapps.features.payment_confirm.PaymentConfirmActivity;
import id.wonderdeal.wonderdealapps.features.syarat_ketentuan.SyaratKetentuanActivity;
import id.wonderdeal.wonderdealapps.utils.Consts;

/**
 * Created by agustinaindah on 23/09/2017.
 */

public class ListMenuAdapter extends SimpleItemListAdapter<HashMap<String, Integer>>{

    public ListMenuAdapter(List<HashMap<String, Integer>> mData, Context mContext) {
        super(mData, mContext);
    }

    @Override
    protected void renderData(final HashMap<String, Integer> item, ViewHolder holder) {
        holder.imgItemList.setImageResource(item.get(Consts.ICON));
        holder.imgItemList.setPadding(10, 10, 10, 10);
        holder.txtItemList.setText(item.get(Consts.TITLE));
        holder.layItemList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mContext.startActivity(
                        new Intent(mContext, getActivityClass(item.get(Consts.TITLE))));
            }
        });
    }

    private Class<? extends BaseActivity> getActivityClass(Integer title){
        Hashtable ht = new Hashtable();
        ht.put(R.string.payment, PaymentConfirmActivity.class);
        ht.put(R.string.label_syarat, SyaratKetentuanActivity.class);
        return (Class<? extends BaseActivity>) ht.get(title);
    }
}

/*public class ListMenuAdapter extends RecyclerView.Adapter<ListMenuAdapter.ViewHolder> {

    private Context context;
    private List<Simple> simples;

    public ListMenuAdapter(Context context) {
        this.context = context;
    }

    public void updateData(List<Simple> simples){
        this.simples = simples;
        notifyDataSetChanged();
    }

    @Override
    public ListMenuAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = ViewHelper.inflateView(parent, R.layout.item_list);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ListMenuAdapter.ViewHolder holder, int position) {
        final Simple list = (Simple) simples.get(position);

        holder.txtItemList.setText(list.getValue());
        holder.txtItemList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm =
                        ((BaseActivity) context).getSupportFragmentManager();
                Fragment fragment = null;
                switch (list.getKey()){
                    case Consts.PAYMENT:
                        fragment = PaymentConfirmFragment.newInstance();
                        break;
                }
                try {
                    if (fragment !=null){
                        ((BaseActivity) context)
                                .gotoFragment(fm, fragment, true);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return simples.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.imgItemList)
        ImageView imgItemList;
        @BindView(R.id.txtItemList)
        TextView txtItemList;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}*/
