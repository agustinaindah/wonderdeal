package id.wonderdeal.wonderdealapps.features.payment_confirm;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.JsonObject;
import com.kosalgeek.android.photoutil.GalleryPhoto;
import com.kosalgeek.android.photoutil.ImageBase64;
import com.kosalgeek.android.photoutil.ImageLoader;

import java.io.FileNotFoundException;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.OnClick;
import id.wonderdeal.wonderdealapps.R;
import id.wonderdeal.wonderdealapps.base.BaseActivity;
import id.wonderdeal.wonderdealapps.features.DateDialog;
import id.wonderdeal.wonderdealapps.utils.CallbackInterface;
import id.wonderdeal.wonderdealapps.utils.Consts;
import id.wonderdeal.wonderdealapps.utils.Helper;

/**
 * Created by agustinaindah on 16/10/2017.
 */

public class PaymentConfirmActivity extends BaseActivity implements
        DateDialog.OnDateDialog, PaymentConfirmPresenter.View {

    private static final int GALLERY_REQUEST = 565;

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

    @BindString(R.string.msg_empty)
    String strMsgEmpty;
    @BindString(R.string.msg_success_payment_confirm)
    String strSuccessConfirm;
    @BindString(R.string.label_payment_form)
    String strPaymentConfirm;
    @BindString(R.string.loading)
    String strLoading;

    private String newPhoto = null;
    private String mTransferDate;
    private ProgressDialog mProgress;
    private PaymentConfirmPresenter mPresenter;
    private GalleryPhoto galleryPhoto;
    private String fileEvidence;

    @Override
    protected void onActivityCreated(Bundle savedInstanceState) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(strPaymentConfirm);
        mPresenter = new PaymentConfirmPresenterImpl(this);
        galleryPhoto = new GalleryPhoto(this);
        initProgress();
        displayData();

        int permission = ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
           // Log.i("Tes", "Permission to record denied");
            makeRequest();
        }
    }

    protected void makeRequest() {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                GALLERY_REQUEST);
    }

    @OnClick(R.id.btnUploadPhoto)
    public void clickUploadPhoto(View view) {
        galleryIntent();
    }

    private void galleryIntent() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        if (intent.resolveActivity(this.getPackageManager()) != null) {
            startActivityForResult(intent, GALLERY_REQUEST);
        }
    }

    private void displayData() {
        mTransferDate = Helper.getDateNow();
        txtTransferDate.setText(Helper.parseToDateString(mTransferDate, Consts.TYPE_DATE));
    }

    private void initProgress() {
        mProgress = new ProgressDialog(this);
        mProgress.setMessage(strLoading);
    }

    @Override
    protected int setView() {
        return R.layout.activity_form_payment_confirm;
    }

    @OnClick(R.id.btnSubmit)
    public void submit(View view) {
        mPresenter.confirmPayment(getInput());
    }

    @OnClick(R.id.txtTransferDate)
    public void clickTransferDate(View view) {
        DialogFragment dialogFragment = DateDialog.newInstance(mTransferDate, this);
        dialogFragment.show(getSupportFragmentManager(), Consts.DIALOG);
    }

    @Override
    public void onSelectedDate(String selectedDate) {
        mTransferDate = selectedDate;
        txtTransferDate.setText(Helper.parseToDateString(mTransferDate, Consts.TYPE_DATE));
    }

    private JsonObject getInput() {
        JsonObject jInput = new JsonObject();

        jInput.addProperty("nama", edtNama.getText().toString());
        jInput.addProperty("nomor_order", edtNoPesan.getText().toString());
        jInput.addProperty("jml_transfer", edtJmlTransfer.getText().toString());
        jInput.addProperty("tgl_transfer", mTransferDate);
        jInput.addProperty("no_rek_asal", edtNoRekAsal.getText().toString());
        jInput.addProperty("nama_bank_asal", edtNamaBankAsal.getText().toString());
        jInput.addProperty("nama_pemegang_rekening", edtNmaPemilikRekAsal.getText().toString());
        jInput.addProperty("img_bukti_transfer", fileEvidence);
        return jInput;
    }

    @Override
    public void showProgress() {
        mProgress.show();
    }

    @Override
    public void hideProgress() {
        mProgress.dismiss();
    }

    @Override
    public void showMessage(String msg) {
        Helper.createAlert(this, Consts.STR_INFO, msg);
    }

    @Override
    public void notConnect(String msg) {
        Helper.createAlert(this, Consts.STR_INFO, msg);
    }

    @Override
    public boolean validate() {
        edtNama.setError(null);
        edtNoPesan.setError(null);
        edtJmlTransfer.setError(null);
        edtNoRekAsal.setError(null);
        edtNamaBankAsal.setError(null);
        edtNmaPemilikRekAsal.setError(null);

        boolean cancel = false;
        View focus = null;

        if (TextUtils.isEmpty(getInput().get("nama").getAsString())) {
            edtNama.setError(strMsgEmpty);
            focus = edtNama;
            cancel = true;
        }
        if (TextUtils.isEmpty(getInput().get("nomor_order").getAsString())) {
            edtNoPesan.setError(strMsgEmpty);
            focus = edtNoPesan;
            cancel = true;
        }
        if (TextUtils.isEmpty(getInput().get("jml_transfer").getAsString())) {
            edtJmlTransfer.setError(strMsgEmpty);
            focus = edtJmlTransfer;
            cancel = true;
        }
        if (TextUtils.isEmpty(getInput().get("no_rek_asal").getAsString())) {
            edtNoRekAsal.setError(strMsgEmpty);
            focus = edtNoRekAsal;
            cancel = true;
        }
        if (TextUtils.isEmpty(getInput().get("nama_pemegang_rekening").getAsString())) {
            edtNmaPemilikRekAsal.setError(strMsgEmpty);
            focus = edtNmaPemilikRekAsal;
            cancel = true;
        }
        if (TextUtils.isEmpty(getInput().get("nama_bank_asal").getAsString())) {
            edtNamaBankAsal.setError(strMsgEmpty);
            focus = edtNamaBankAsal;
            cancel = true;
        }
        if (cancel) {
            focus.requestFocus();
        }
        return cancel;
    }

    @Override
    public void success(JsonObject jsonRes) {
        Helper.createAlert(this, Consts.STR_INFO, strSuccessConfirm, false, new CallbackInterface() {
            @Override
            public void callback() {
                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == GALLERY_REQUEST)
                onSelectFromGalleryResult(data);
        }
    }

    private void onSelectFromGalleryResult(Intent data) {
        galleryPhoto.setPhotoUri(data.getData());
        String photoPath = galleryPhoto.getPath();
        try {
            Bitmap bitmap = ImageLoader
                    .init()
                    .from(photoPath)
                    .requestSize(512, 512)
                    .getBitmap();
            imgUploadPhoto.setImageBitmap(bitmap);
            newPhoto = photoPath;
            if (newPhoto != null) {
                try {
                    ImageLoader imgLoader = ImageLoader
                            .init()
                            .from(newPhoto);
                    Bitmap newPhoto = imgLoader.requestSize(512, 512).getBitmap();
                    fileEvidence = ImageBase64.encode(newPhoto);

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
