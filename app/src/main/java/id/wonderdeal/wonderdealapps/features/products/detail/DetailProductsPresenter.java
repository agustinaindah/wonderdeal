package id.wonderdeal.wonderdealapps.features.products.detail;

import id.wonderdeal.wonderdealapps.base.BaseView;
import id.wonderdeal.wonderdealapps.model.ItemProduct;

/**
 * Created by agustinaindah on 18/09/2017.
 */

public interface DetailProductsPresenter {

    interface View extends BaseView{
        void success(ItemProduct itemProduct);
    }

    void getSingleProduct(int id);
}
