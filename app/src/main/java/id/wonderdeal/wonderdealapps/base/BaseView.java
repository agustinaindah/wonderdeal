package id.wonderdeal.wonderdealapps.base;

/**
 * Created by agustinaindah on 13/09/2017.
 */

public interface BaseView {

    void showProgress();

    void hideProgress();

    void showMessage(String msg);

    void notConnect(String msg);
}
