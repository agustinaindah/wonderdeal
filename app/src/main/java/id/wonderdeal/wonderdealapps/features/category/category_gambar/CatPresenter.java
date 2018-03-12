package id.wonderdeal.wonderdealapps.features.category.category_gambar;

import java.util.List;

import id.wonderdeal.wonderdealapps.base.BaseView;
import id.wonderdeal.wonderdealapps.model.Category;

/**
 * Created by agustinaindah on 13/10/2017.
 */

public interface CatPresenter {

    interface View extends BaseView{
        void success(List<Category> catGambar);
    }

    void getCatGambar();
}
