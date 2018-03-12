package id.wonderdeal.wonderdealapps.features.checkout;

import com.google.gson.JsonObject;

import java.util.List;

import id.wonderdeal.wonderdealapps.base.BaseView;
import id.wonderdeal.wonderdealapps.model.ItemInvoice;

/**
 * Created by agustinaindah on 17/10/2017.
 */

public interface CheckoutPresenter {

    void confirmOrder(String mName,
                      String mAddress,
                      String mPhone,
                      String mEmail,
                      String mNote,
                      String paymentMethod,
                      int totalBill,
                      List<ItemInvoice> invoices
    );

    interface View extends BaseView {
        void success(JsonObject jsonRes);
    }
}
