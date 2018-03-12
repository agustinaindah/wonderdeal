package id.wonderdeal.wonderdealapps.features.category.productByCategory;

import java.util.List;

import id.wonderdeal.wonderdealapps.base.BaseView;
import id.wonderdeal.wonderdealapps.model.ItemProduct;

/**
 * Created by agustinaindah on 25/09/2017.
 */

public interface ProductByCategoryPresenter {

    interface View extends BaseView{
        void showProduct(List<ItemProduct> mData);
    }

    void getProductByCategory(int id);
}
