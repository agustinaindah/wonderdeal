package id.wonderdeal.wonderdealapps.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by agustinaindah on 17/10/2017.
 */

public class ItemInvoice implements Serializable {

    //product
    @SerializedName("ID")
    @Expose
    private Integer iD;

    //items
    @SerializedName("items")
    @Expose
    private List<CartItem> items;

    public Integer getID() {
        return iD;
    }

    public void setID(Integer iD) {
        this.iD = iD;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }

    public int getGrandTotal() {
        int subTotal = 0;
        for (int i = 0; i < getItems().size(); i++) {
            subTotal += (Integer.valueOf(getItems().get(i).getItemProduct().getSalePrice())
                    * getItems().get(i).getQuantity());
        }
        return subTotal;
    }
}
