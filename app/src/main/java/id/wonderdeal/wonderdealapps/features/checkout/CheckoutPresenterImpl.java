package id.wonderdeal.wonderdealapps.features.checkout;

import android.content.SharedPreferences;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.List;

import id.wonderdeal.wonderdealapps.WonderdealApp;
import id.wonderdeal.wonderdealapps.model.BaseResponse;
import id.wonderdeal.wonderdealapps.model.CartItem;
import id.wonderdeal.wonderdealapps.model.ItemInvoice;
import id.wonderdeal.wonderdealapps.model.ItemProduct;
import id.wonderdeal.wonderdealapps.utils.ApiService;
import id.wonderdeal.wonderdealapps.utils.Consts;
import id.wonderdeal.wonderdealapps.utils.Helper;
import id.wonderdeal.wonderdealapps.utils.ServiceInterface;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by agustinaindah on 17/10/2017.
 */

public class CheckoutPresenterImpl implements CheckoutPresenter{

    private View mView;
    private JsonObject jsonOrder;

    public CheckoutPresenterImpl(View mView) {
        this.mView = mView;
    }

    @Override
    public void confirmOrder(String mName,
                             String mAddress,
                             String mPhone,
                             String mEmail,
                             String mNote,
                             String paymentMethod,
                             int totalBill,
                             List<ItemInvoice> invoices) {

        try {
            jsonOrder = new JsonObject();
            jsonOrder.addProperty("nama",mName);
            jsonOrder.addProperty("alamat",mAddress);
            jsonOrder.addProperty("telp",mPhone);
            jsonOrder.addProperty("email", mEmail);
            jsonOrder.addProperty("note",mNote);
            jsonOrder.addProperty("payment_method",paymentMethod);
            jsonOrder.addProperty("total",totalBill);
            /*payment_method_title" : "Cash on Delivery*/

            JsonArray jsonInvoices = new JsonArray();

            for (ItemInvoice itemInvoice : invoices){

                List<CartItem> cartItems = itemInvoice.getItems();

                JsonObject jsonInvoice = new JsonObject();
                jsonInvoice.addProperty("produk_id", itemInvoice.getID());

                JsonArray jsonItems = new JsonArray();

                for (CartItem cartItem : cartItems) {

                    ItemProduct itemProduct = cartItem.getItemProduct();

                    JsonObject jsonCartItem = new JsonObject();
                    jsonCartItem.addProperty("id", itemProduct.getID());
                    jsonCartItem.addProperty("name", itemProduct.getPostTitle());
                    jsonCartItem.addProperty("image", itemProduct.getImage());
                    jsonCartItem.addProperty("qty", cartItem.getQuantity());
                    jsonCartItem.addProperty("price", itemProduct.getSalePrice());

                    jsonItems.add(jsonCartItem);
                }

                jsonInvoice.add("items", jsonItems);

                jsonInvoices.add(jsonInvoice);
            }
            jsonOrder.add("invoice", jsonInvoices);
            postOrder(jsonOrder);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private void postOrder(final JsonObject jsonOrder) {
        final JsonObject jsonRequest = new JsonObject();
        jsonRequest.add("posts", jsonOrder);
        WonderdealApp.getInstance().service(new ServiceInterface() {
            @Override
            public Call<BaseResponse> callBackResponse(ApiService apiService) {
                return apiService.postOrder(jsonRequest);
            }

            @Override
            public void showProgress() {
                mView.showProgress();
            }

            @Override
            public void hideProgress() {
                mView.hideProgress();
            }

            @Override
            public void responseSuccess(Response<BaseResponse> response) {
                try {
                    String data = Helper.getGsonInstance().toJson(response.body().getData());
                    JsonObject jsonData = Helper.parseToJsonObject(data);/*.get("results")
                            .getAsJsonArray().get(0).getAsJsonObject();*/
                    saveDataToPref(jsonData);
                    mView.success(jsonData);
                } catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void responseFailed(Response<BaseResponse> response) {
                try {
                    JsonObject jsonRes = Helper.parseToJsonObject(response.errorBody().string());
                    mView.showMessage(jsonRes.get("msg").getAsString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void failed(Throwable t) {
                t.printStackTrace();
                mView.notConnect(t.getLocalizedMessage());
            }
        });
    }

    private void saveDataToPref(JsonObject jsonData) {
        String orderId = jsonData.get("order_id").getAsString();

        SharedPreferences.Editor editor = WonderdealApp
                .getInstance()
                .Prefs()
                .edit();

       editor.putString(Consts.ORDER_ID, orderId);
        editor.commit();
    }
}
