package id.wonderdeal.wonderdealapps.features.products.detail;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import id.wonderdeal.wonderdealapps.WonderdealApp;
import id.wonderdeal.wonderdealapps.model.BaseResponse;
import id.wonderdeal.wonderdealapps.model.ItemProduct;
import id.wonderdeal.wonderdealapps.utils.ApiService;
import id.wonderdeal.wonderdealapps.utils.Helper;
import id.wonderdeal.wonderdealapps.utils.ServiceInterface;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by agustinaindah on 18/09/2017.
 */

public class DetailProductsPresenterImpl implements DetailProductsPresenter {

    private View mView;

    public DetailProductsPresenterImpl(View mView) {
        this.mView = mView;
    }

    @Override
    public void getSingleProduct(final int id) {
        WonderdealApp.getInstance().service(new ServiceInterface() {
            @Override
            public Call<BaseResponse> callBackResponse(ApiService apiService) {
                return apiService.getSingleProduct(id);
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
                    JsonObject jsonData = Helper.parseToJsonObject(data);
                    JsonArray jsonRes = jsonData.get("product").getAsJsonArray();
                    JsonObject item = jsonRes.get(0).getAsJsonObject();
                    ItemProduct itemProduct = (new Gson()).fromJson(item, ItemProduct.class);
                    mView.success(itemProduct);
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
                mView.notConnect(t.getLocalizedMessage());
            }
        });

    }
}
