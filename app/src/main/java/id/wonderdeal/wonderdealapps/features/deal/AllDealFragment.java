package id.wonderdeal.wonderdealapps.features.deal;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import butterknife.BindView;
import id.wonderdeal.wonderdealapps.R;
import id.wonderdeal.wonderdealapps.base.BaseFragment;

/**
 * Created by agustinaindah on 13/09/2017.
 */

public class AllDealFragment extends BaseFragment {

    @BindView(R.id.rvAllDeal)
    RecyclerView rvAllDeal;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.txtNoData)
    TextView txtNoData;
    @BindView(R.id.layError)
    LinearLayout layError;
    @BindView(R.id.btnError)
    Button btnError;

    public static AllDealFragment newInstance() {
        Bundle args = new Bundle();
        AllDealFragment fragment = new AllDealFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setView() {
        return R.layout.fragment_all_deal;
    }
}
