package id.wonderdeal.wonderdealapps.utils;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by agustinaindah on 14/09/2017.
 */

public class ViewHelper {

    public static View inflateView(ViewGroup parent, int resId, boolean attachToRoot) {
        return LayoutInflater.from(parent.getContext()).inflate(resId, parent, attachToRoot);
    }

    public static View inflateView(ViewGroup parent, int resId) {
        return inflateView(parent, resId, false);
    }
}
