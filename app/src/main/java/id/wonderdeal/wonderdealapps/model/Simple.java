package id.wonderdeal.wonderdealapps.model;

import java.io.Serializable;

/**
 * Created by agustinaindah on 23/09/2017.
 */

public class Simple implements Serializable {
    private String key;
    private String value;

    public Simple() {}

    public Simple(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {

        return key;
    }

    public String getValue() {
        return value;
    }
}
