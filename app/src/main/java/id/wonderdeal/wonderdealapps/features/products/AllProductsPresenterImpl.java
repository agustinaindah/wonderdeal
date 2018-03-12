package id.wonderdeal.wonderdealapps.features.products;

import android.view.View;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;

import id.wonderdeal.wonderdealapps.WonderdealApp;
import id.wonderdeal.wonderdealapps.model.BaseResponse;
import id.wonderdeal.wonderdealapps.model.ItemProduct;
import id.wonderdeal.wonderdealapps.utils.ApiService;
import id.wonderdeal.wonderdealapps.utils.Helper;
import id.wonderdeal.wonderdealapps.utils.ServiceInterface;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by agustinaindah on 14/09/2017.
 */

public class AllProductsPresenterImpl implements AllProductsPresenter {

    private View mView;

    public AllProductsPresenterImpl(View mView) {
        this.mView = mView;
    }

    @Override
    public void getAllProduct(final int page) {
        WonderdealApp.getInstance().service(new ServiceInterface() {
            @Override
            public Call<BaseResponse> callBackResponse(ApiService apiService) {
                return apiService.getAllProduct(page);
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
                    ArrayList<ItemProduct> itemProducts = new ArrayList<ItemProduct>();
                    for (JsonElement element : jsonRes){
                        ItemProduct itemProduct =
                                Helper.getGsonInstance().fromJson(element, ItemProduct.class);
                        itemProducts.add(itemProduct);
                    }
                    mView.showAllProduct(itemProducts, page);
                }catch (Exception e){
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
