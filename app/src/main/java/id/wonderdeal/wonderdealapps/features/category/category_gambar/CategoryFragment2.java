package id.wonderdeal.wonderdealapps.features.category.category_gambar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import id.wonderdeal.wonderdealapps.R;
import id.wonderdeal.wonderdealapps.base.BaseFragment;
import id.wonderdeal.wonderdealapps.features.category.CategoryAdapter;
import id.wonderdeal.wonderdealapps.model.Category;
import id.wonderdeal.wonderdealapps.utils.Consts;
import id.wonderdeal.wonderdealapps.utils.Helper;

/**
 * Created by agustinaindah on 13/10/2017.
 */

public class CategoryFragment2 extends BaseFragment implements CatPresenter.View{

    @BindView(R.id.rvCategories)
    RecyclerView rvCategories;
    @BindView(R.id.layProgress)
    RelativeLayout layProgress;
    @BindView(R.id.layError)
    LinearLayout layError;

    private CatPresenter mPresenter;
    private String mType;

    public static CategoryFragment2 newInstance(String type, Category category) {
        Bundle args = new Bundle();
        args.putString(Consts.ARG_TYPE, type);
        args.putSerializable(Consts.ARG_DATA, category);
        CategoryFragment2 fragment = new CategoryFragment2();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setView() {
        return R.layout.fragment_categories;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (savedInstanceState == null){
            mPresenter = new CatPresenterImpl(this);
            Bundle args = getArguments();
            if (args !=null){
                mType = args.getString(Consts.ARG_TYPE);
                if (mType.equals(Consts.CAT_MAIN)) {
                    mPresenter.getCatGambar();
                } else {
                    Category cat = (Category) args.getSerializable(Consts.ARG_DATA);
                    CategoryAdapter adapter = new CategoryAdapter(getActivity());
                    adapter.updateData(cat.getCategorySub());
                    rvCategories.setAdapter(adapter);
                    if (layProgress.isShown()){
                        layProgress.setVisibility(View.GONE);
                    }
                }
            }
            setupRecycleView();
        }
    }

    private void setupRecycleView() {
        rvCategories.setHasFixedSize(true);
        rvCategories.setLayoutManager(new GridLayoutManager(getActivity(),3));
        rvCategories.setNestedScrollingEnabled(false);
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
        Helper.createAlert(getActivity(), Consts.STR_INFO, msg);
    }

    @Override
    public void notConnect(String msg) {
        rvCategories.setVisibility(View.GONE);
        layError.setVisibility(View.VISIBLE);
    }

    @Override
    public void success(List<Category> catGambar) {
        CategoryAdapter adapter = new CategoryAdapter(getActivity());
        adapter.updateData(catGambar);
        rvCategories.setAdapter(adapter);
    }

    @OnClick(R.id.btnError)
    public void reload(){
        layError.setVisibility(View.GONE);
        mPresenter.getCatGambar();
        rvCategories.setVisibility(View.VISIBLE);
    }
}
