package id.wonderdeal.wonderdealapps.features.payment_confirm;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import id.wonderdeal.wonderdealapps.R;
import id.wonderdeal.wonderdealapps.base.BaseFragment;

/**
 * Created by agustinaindah on 13/09/2017.
 */

public class PaymentConfirmFragment extends BaseFragment {

    // TODO: 23/09/2017 tinggal tunggu apinya

    @BindView(R.id.edtNama)
    EditText edtNama;
    @BindView(R.id.edtNoPesan)
    EditText edtNoPesan;
    @BindView(R.id.edtJmlTransfer)
    EditText edtJmlTransfer;
    @BindView(R.id.txtTransferDate)
    TextView txtTransferDate;
    @BindView(R.id.edtNoRekAsal)
    EditText edtNoRekAsal;
    @BindView(R.id.edtNamaBankAsal)
    EditText edtNamaBankAsal;
    @BindView(R.id.edtNmaPemilikRekAsal)
    EditText edtNmaPemilikRekAsal;
    @BindView(R.id.imgUploadPhoto)
    ImageView imgUploadPhoto;
    @BindView(R.id.btnUploadPhoto)
    Button btnUploadPhoto;
    @BindView(R.id.btnSubmit)
    Button btnSubmit;

    public static PaymentConfirmFragment newInstance() {
        Bundle args = new Bundle();
        PaymentConfirmFragment fragment = new PaymentConfirmFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setView() {
        return R.layout.activity_form_payment_confirm;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Payment Confirm");
    }
}
