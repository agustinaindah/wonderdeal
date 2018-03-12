package id.wonderdeal.wonderdealapps.features.products;

import java.util.List;

import id.wonderdeal.wonderdealapps.base.BaseView;
import id.wonderdeal.wonderdealapps.model.ItemProduct;

/**
 * Created by agustinaindah on 14/09/2017.
 */

public interface AllProductsPresenter {

    void getAllProduct(int page);

    interface View extends BaseView{

        void showAllProduct(List<ItemProduct> itemProducts, int page);
    }
}
