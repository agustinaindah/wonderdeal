package id.wonderdeal.wonderdealapps.features.splash;

import android.content.Intent;
import android.os.Bundle;

import id.wonderdeal.wonderdealapps.R;
import id.wonderdeal.wonderdealapps.WonderdealApp;
import id.wonderdeal.wonderdealapps.base.BaseActivity;
import id.wonderdeal.wonderdealapps.features.main.MainActivity;
import id.wonderdeal.wonderdealapps.features.main.MainActivity2;

/**
 * Created by agustinaindah on 14/09/2017.
 */

public class SplashActivity  extends BaseActivity {

    @Override
    protected void onActivityCreated(Bundle savedInstanceState) {
        WonderdealApp.getBanner();
        android.os.Handler handler = new android.os.Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                next();
            }
        }, 2500);
    }

    private void next() {
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected int setView() {
        return R.layout.activity_splashscreen;
    }
}
