package id.wonderdeal.wonderdealapps.features.category;

import android.os.Bundle;
import android.view.MenuItem;

import id.wonderdeal.wonderdealapps.R;
import id.wonderdeal.wonderdealapps.base.BaseActivity;
import id.wonderdeal.wonderdealapps.features.category.category_gambar.CategoryFragment2;
import id.wonderdeal.wonderdealapps.utils.Consts;

/**
 * Created by agustinaindah on 23/09/2017.
 */

public class CategoryActivity extends BaseActivity {

    @Override
    protected void onActivityCreated(Bundle savedInstanceState) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        gotoFragment(getSupportFragmentManager(), CategoryFragment2.newInstance(Consts.CAT_MAIN, null));
    }

    @Override
    protected int setView() {
        return R.layout.activity_categories;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                super.onBackPressed();
                break;
        }
        return true;
    }
}
