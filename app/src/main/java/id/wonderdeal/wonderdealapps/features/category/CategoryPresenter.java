package id.wonderdeal.wonderdealapps.features.category;

import java.util.List;

import id.wonderdeal.wonderdealapps.base.BaseView;
import id.wonderdeal.wonderdealapps.model.Category;

/**
 * Created by agustinaindah on 23/09/2017.
 */

public interface CategoryPresenter {

    interface View extends BaseView{
        void success(List<Category> catProducts);
    }

    void getCategory();
}
