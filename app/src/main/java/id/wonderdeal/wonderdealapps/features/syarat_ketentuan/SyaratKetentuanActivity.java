package id.wonderdeal.wonderdealapps.features.syarat_ketentuan;

import android.os.Bundle;
import android.webkit.WebView;

import butterknife.BindView;
import id.wonderdeal.wonderdealapps.R;
import id.wonderdeal.wonderdealapps.base.BaseActivity;

/**
 * Created by agustinaindah on 26/10/2017.
 */

public class SyaratKetentuanActivity extends BaseActivity {

    @BindView(R.id.wvSyaratKetentuan)
    WebView wvSyaratKetentuan;

    @Override
    protected void onActivityCreated(Bundle savedInstanceState) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        wvSyaratKetentuan.getSettings().setJavaScriptEnabled(true);
        wvSyaratKetentuan.loadUrl("http://wonderdeal.id/syarat-dan-ketentuan/");
    }

    @Override
    protected int setView() {
        return R.layout.activity_syarat_ketentuan;
    }

    /*	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.webview);

		webView = (WebView) findViewById(R.id.webView1);
		webView.getSettings().setJavaScriptEnabled(true);
		webView.loadUrl("http://www.google.com");*/
}
