package id.wonderdeal.wonderdealapps.features.search;

import java.util.List;

import id.wonderdeal.wonderdealapps.base.BaseView;
import id.wonderdeal.wonderdealapps.model.ItemProduct;

/**
 * Created by agustinaindah on 25/09/2017.
 */

public interface SearchPresenter {

    interface View extends BaseView{
        void success (List<ItemProduct> mData);
    }

    void getSearch(String search);
}
