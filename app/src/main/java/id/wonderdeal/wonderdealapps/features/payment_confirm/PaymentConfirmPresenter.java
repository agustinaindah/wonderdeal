package id.wonderdeal.wonderdealapps.features.payment_confirm;

import com.google.gson.JsonObject;

import id.wonderdeal.wonderdealapps.base.BaseView;

/**
 * Created by agustinaindah on 23/10/2017.
 */

public interface PaymentConfirmPresenter {

    interface View extends BaseView{
        boolean validate();

        void success(JsonObject jsonRes);
    }

    void confirmPayment(JsonObject jsonRes);
}
