package id.wonderdeal.wonderdealapps.features.category.category_gambar;

import android.content.SharedPreferences;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import id.wonderdeal.wonderdealapps.WonderdealApp;
import id.wonderdeal.wonderdealapps.model.BaseResponse;
import id.wonderdeal.wonderdealapps.model.Category;
import id.wonderdeal.wonderdealapps.utils.ApiService;
import id.wonderdeal.wonderdealapps.utils.Consts;
import id.wonderdeal.wonderdealapps.utils.Helper;
import id.wonderdeal.wonderdealapps.utils.ServiceInterface;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by agustinaindah on 13/10/2017.
 */

public class CatPresenterImpl implements CatPresenter {

    private View mView;
    private SharedPreferences mPref;

    public CatPresenterImpl(View mView) {
        this.mView = mView;
    }

    @Override
    public void getCatGambar() {
        mPref = WonderdealApp.getInstance().Prefs();
        final SharedPreferences.Editor edit = mPref.edit();
        String categoriesTemp = mPref.getString(Consts.CATEGORY, null);
        if (categoriesTemp !=null){
            JsonArray categories = Helper.parseToJsonArray(categoriesTemp);
            mView.success(parseCategory(categories));
        }
        WonderdealApp.getInstance().service(new ServiceInterface() {
            @Override
            public Call<BaseResponse> callBackResponse(ApiService apiService) {
                return apiService.getCatGambar();
            }

            @Override
            public void showProgress() {
                mView.showProgress();
            }

            @Override
            public void hideProgress() {
                try {
                    mView.hideProgress();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void responseSuccess(Response<BaseResponse> response) {
                try {
                    String data = Helper.getGsonInstance().toJson(response.body().getData());
                    JsonObject jsonData = Helper.parseToJsonObject(data);
                    JsonArray jsonRes = jsonData.get("kategori").getAsJsonArray();
                    edit.putString(Consts.CATEGORY, jsonRes.toString()).commit();
                    List<Category> mData = parseCategory(jsonRes);
                    mView.success(mData);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void responseFailed(Response<BaseResponse> response) {
                try {
                    JsonObject jsonRes = Helper.parseToJsonObject(response.errorBody().string());
                    mView.showMessage(jsonRes.get("msgsek").getAsString());
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

    private List<Category> parseCategory(JsonArray categories) {
        ArrayList<Category> result = new ArrayList<Category>();

        //main category
        for (JsonElement elCat : categories) {
            JsonObject cat = elCat.getAsJsonObject();
            ArrayList<Category> categorySub = new ArrayList<Category>();

            result.add(new Category(
                    cat.get("term_id").getAsInt(),
                    cat.get("name").getAsString(),
                    cat.get("image").getAsString(),
                    categorySub,
                    Consts.CAT_MAIN
                    /* */
            ));
        }
        return result;
    }
}
