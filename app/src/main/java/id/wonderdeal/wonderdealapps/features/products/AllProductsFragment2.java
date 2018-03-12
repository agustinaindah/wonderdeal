package id.wonderdeal.wonderdealapps.features.products;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import id.wonderdeal.wonderdealapps.R;
import id.wonderdeal.wonderdealapps.base.BaseFragment;
import id.wonderdeal.wonderdealapps.model.ItemProduct;
import id.wonderdeal.wonderdealapps.utils.Consts;
import id.wonderdeal.wonderdealapps.utils.EndlessScrollListener;
import id.wonderdeal.wonderdealapps.utils.Helper;

/**
 * Created by agustinaindah on 22/09/2017.
 */

public class AllProductsFragment2 extends BaseFragment implements AllProductsPresenter.View{

    @BindView(R.id.rvAllProduct)
    RecyclerView rvAllProduct;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.txtNoData)
    TextView txtNoData;
    @BindView(R.id.layError)
    LinearLayout layError;
    @BindView(R.id.btnError)
    Button btnError;

    OnMainFragmentListener mCallback;
    private AllProductsAdapter mAdapter;
    private AllProductsPresenter mPresenter;
    private int lastCount = 0;
    private LinearLayoutManager linearLayoutManager;

    public static AllProductsFragment2 newInstance() {
        Bundle args = new Bundle();
        AllProductsFragment2 fragment = new AllProductsFragment2();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new AllProductsPresenterImpl(this);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mCallback = (OnMainFragmentListener) context;
        }catch (ClassCastException e){
            throw new ClassCastException(context.toString()
                    + "must implement OnMainFragmentListener");
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mPresenter.getAllProduct(Consts.FIRST_PAGE);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        rvAllProduct.addOnScrollListener(new EndlessScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int nextPage) {
                if (lastCount == Consts.LIMIT){
                    mPresenter.getAllProduct(nextPage);
                }
            }
        });
    }

    /*   @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter.getAllProduct(Consts.FIRST_PAGE);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        rvAllProduct.addOnScrollListener(new EndlessScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int nextPage) {
                if (lastCount == Consts.LIMIT){
                    mPresenter.getAllProduct(nextPage);
                }
            }
        });
    }*/

    @Override
    protected int setView() {
        return R.layout.fragment_all_products;
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        /*Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (progressBar != null) {
                    progressBar.setVisibility(View.INVISIBLE);
                }
            }
        }, 2000);*/
        if (progressBar !=null){
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void showMessage(String msg) {
        Helper.createAlert(getActivity(), "Info", msg);
    }

    @Override
    public void notConnect(String msg) {
        layError.setVisibility(View.VISIBLE);
        mPresenter.getAllProduct(Consts.FIRST_PAGE);
    }

    @OnClick(R.id.btnError)
    public void reload(){
        layError.setVisibility(View.GONE);
        mPresenter.getAllProduct(Consts.FIRST_PAGE);
        rvAllProduct.setVisibility(View.VISIBLE);
    }

    @Override
    public void showAllProduct(List<ItemProduct> itemProducts, int page) {
        lastCount = itemProducts.size();

        if (page == Consts.FIRST_PAGE){
            txtNoData.setVisibility((itemProducts.size()==0) ? View.VISIBLE : View.GONE);
            mAdapter = new AllProductsAdapter(itemProducts, getActivity());

            rvAllProduct.setHasFixedSize(true);
            rvAllProduct.setLayoutManager(linearLayoutManager);
            rvAllProduct.setAdapter(mAdapter);
            rvAllProduct.setNestedScrollingEnabled(false);

            Animation animation = AnimationUtils.loadAnimation(getActivity(),
                    R.anim.up_from_bottom);
            rvAllProduct.startAnimation(animation);
        } else {
            for(ItemProduct itemProduct : itemProducts){
                mAdapter.add(itemProduct);
                mAdapter.notifyDataSetChanged();
            }
        }
    }

    public interface OnMainFragmentListener {
        public void onNotConnected(String msg, boolean isExpand);
    }

}
