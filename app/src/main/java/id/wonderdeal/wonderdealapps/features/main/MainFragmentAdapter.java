package id.wonderdeal.wonderdealapps.features.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import id.wonderdeal.wonderdealapps.features.category.CategoryFragment;
import id.wonderdeal.wonderdealapps.features.deal.AllDealFragment;
import id.wonderdeal.wonderdealapps.features.menu.ListMenuFragment;
import id.wonderdeal.wonderdealapps.features.payment_confirm.PaymentConfirmFragment;
import id.wonderdeal.wonderdealapps.features.products.AllProductsFragment;
import id.wonderdeal.wonderdealapps.features.products.AllProductsFragment2;
import id.wonderdeal.wonderdealapps.utils.Consts;

/**
 * Created by agustinaindah on 13/09/2017.
 */

public class MainFragmentAdapter extends FragmentPagerAdapter {

    private String tabTitles[] = new String[]{"Kategori" , "Urutkan"};

    public MainFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position){
            case 0 :
                fragment = CategoryFragment.newInstance(Consts.CAT_MAIN, null);
                //fragment = AllProductsFragment2.newInstance();
                break;
            case 1 :
                fragment = AllDealFragment.newInstance();
                break;
            /*case 2 :
                fragment = ListMenuFragment.newInstance();
                break;*/
          /*  case 3 :
                fragment = PaymentConfirmFragment.newInstance();
                break;*/
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return tabTitles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}
