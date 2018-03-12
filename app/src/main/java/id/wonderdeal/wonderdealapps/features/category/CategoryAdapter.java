package id.wonderdeal.wonderdealapps.features.category;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.wonderdeal.wonderdealapps.R;
import id.wonderdeal.wonderdealapps.base.BaseActivity;
import id.wonderdeal.wonderdealapps.features.category.productByCategory.ProductByCategoryActivity;
import id.wonderdeal.wonderdealapps.model.Category;
import id.wonderdeal.wonderdealapps.utils.Consts;
import id.wonderdeal.wonderdealapps.utils.Helper;

/**
 * Created by agustinaindah on 23/09/2017.
 */

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    private Context context;
    private List<Category> categories;

    public CategoryAdapter(Context context) {
        this.context = context;
    }

    public void updateData(List<Category> categories){
        this.categories = categories;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_category, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Category category = (Category) categories.get(position);

        //holder.imgCategorySub.setVisibility(isHaveCategorySub(category) ? View.GONE : View.VISIBLE);
        holder.txtCategoryTitle.setText(category.getCategoryTitle());
        Helper.displayImage(context, category.getCategoryImage(), holder.imgCategorySub);
        holder.imgCategorySub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //gotoCategorySub(category);
                gotoProductByCategory(category);
            }
        });

        holder.txtCategoryTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoProductByCategory(category);
            }
        });

        holder.cardItemCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoProductByCategory(category);
            }
        });

        if (category.getCategorySub().equals("")){
            holder.cardItemCategory.setVisibility(View.GONE);
        }
       /* if (category.getType().equals("main")) {
            holder.txtCategoryTitle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    gotoCategorySub(category);
                }
            });
        }*/

    }

    private void gotoCategorySub(Category category) {
        Fragment fragment = CategoryFragment.newInstance(Consts.CAT_CHILD,
                category);
        FragmentManager fm = ((BaseActivity) context).getSupportFragmentManager();
        ((BaseActivity) context).gotoFragment(fm, fragment, true, category.getType());
    }

    private void gotoProductByCategory(Category category) {
        Intent intent = new Intent(context, ProductByCategoryActivity.class);
        intent.putExtra(Consts.ARG_DATA, category);
        context.startActivity(intent);
    }

    private boolean isHaveCategorySub(Category category) {
        return category.getCategorySub().size() == 0;
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.cardItemCategory)
        CardView cardItemCategory;
        @BindView(R.id.imgCategorySub)
        ImageView imgCategorySub;
        @BindView(R.id.txtCategoryTitle)
        TextView txtCategoryTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
