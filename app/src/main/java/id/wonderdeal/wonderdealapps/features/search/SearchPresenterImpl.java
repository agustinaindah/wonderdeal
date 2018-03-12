package id.wonderdeal.wonderdealapps.features.search;

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
 * Created by agustinaindah on 25/09/2017.
 */

public class SearchPresenterImpl implements SearchPresenter {

    private View mView;

    public SearchPresenterImpl(View mView) {
        this.mView = mView;
    }

    @Override
    public void getSearch(final String search) {
        WonderdealApp.getInstance().service(new ServiceInterface() {
            @Override
            public Call<BaseResponse> callBackResponse(ApiService apiService) {
                return apiService.getSearch(search);
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
                    mView.success(itemProducts);
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
