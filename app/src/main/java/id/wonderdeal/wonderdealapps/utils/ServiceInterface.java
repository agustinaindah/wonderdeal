package id.wonderdeal.wonderdealapps.utils;

import id.wonderdeal.wonderdealapps.model.BaseResponse;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by agustinaindah on 12/09/2017.
 */

public interface ServiceInterface {

    Call<BaseResponse> callBackResponse(ApiService apiService);

    void showProgress();

    void hideProgress();

    void responseSuccess(Response<BaseResponse> response);

    void responseFailed(Response<BaseResponse> response);

    void failed(Throwable t);
}
