package id.wonderdeal.wonderdealapps.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by agustinaindah on 10/10/2017.
 */

public class CartItem implements Serializable {

    @SerializedName("itemproduct")
    @Expose
    private ItemProduct itemProduct;
    @SerializedName("quantity")
    @Expose
    private int quantity;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public ItemProduct getItemProduct() {
        return itemProduct;
    }

    public void setItemProduct(ItemProduct itemProduct) {
        this.itemProduct = itemProduct;
    }
}
