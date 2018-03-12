package id.wonderdeal.wonderdealapps.features.menu;

import android.os.Bundle;
import android.view.MenuItem;

import id.wonderdeal.wonderdealapps.R;
import id.wonderdeal.wonderdealapps.base.BaseActivity;

/**
 * Created by agustinaindah on 23/09/2017.
 */

public class ListMenuActivity extends BaseActivity {
    @Override
    protected void onActivityCreated(Bundle savedInstanceState) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        gotoFragment(getSupportFragmentManager(), ListMenuFragment.newInstance());
    }

    @Override
    protected int setView() {
        return R.layout.activity_list_menu;
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
