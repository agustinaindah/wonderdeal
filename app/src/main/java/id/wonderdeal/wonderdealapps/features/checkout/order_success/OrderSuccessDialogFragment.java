package id.wonderdeal.wonderdealapps.features.checkout.order_success;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import com.android.tonyvu.sc.util.CartHelper;
import com.google.gson.JsonObject;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import id.wonderdeal.wonderdealapps.R;
import id.wonderdeal.wonderdealapps.features.main.MainActivity2;
import id.wonderdeal.wonderdealapps.utils.Consts;

/**
 * Created by agustinaindah on 23/10/2017.
 */

public class OrderSuccessDialogFragment extends DialogFragment {

    @BindView(R.id.txtOrderID)
    TextView txtOrderID;
    @BindView(R.id.tblRekening)
    TableLayout tblRekening;
    @BindView(R.id.txtNamaPerusahaan)
    TextView txtNamaPerusahaan;
    @BindView(R.id.txtTransfer)
    TextView txtTransfer;
    @BindView(R.id.frCheckoutSuccess)
    FrameLayout frCheckoutSuccess;

    @BindString(R.string.label_order_received)
    String strOrderReceived;

    public static OrderSuccessDialogFragment newInstance(String orderId, String paymentMethod) {
        Bundle args = new Bundle();
        args.putString("order_id", orderId);
        args.putString(Consts.ARG_TYPE,paymentMethod);
        OrderSuccessDialogFragment fragment = new OrderSuccessDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_fragment_checkout_success, null);

        ButterKnife.bind(this, view);
        txtOrderID.setText(getArguments().getString("order_id", ""));
        if (getArguments().getString(Consts.ARG_TYPE, "").equals(Consts.COD)){
            txtNamaPerusahaan.setVisibility(View.GONE);
            txtTransfer.setVisibility(View.GONE);
            frCheckoutSuccess.setVisibility(View.GONE);
            tblRekening.setVisibility(View.GONE);
        }

        setCancelable(false);
        builder.setView(view)
                .setTitle(strOrderReceived)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        CartHelper.getCart().clear();
                        Intent intent = new Intent(getActivity(), MainActivity2.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                });

        final AlertDialog alertDialog = builder.create();
        return alertDialog;
    }

    @Override
    public void onResume() {
        super.onResume();

        if (getDialog() == null)
            return;

        int width = getResources().getDisplayMetrics().widthPixels;
        getDialog().getWindow().setLayout(width, WindowManager.LayoutParams.WRAP_CONTENT);
    }

}
