package id.wonderdeal.wonderdealapps.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by agustinaindah on 11/10/2017.
 */

public class ItemCart implements Serializable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("price")
    @Expose
    private Integer price;
    @SerializedName("qty")
    @Expose
    private Integer qty;
    @SerializedName("stock")
    @Expose
    private Integer stock;
    @SerializedName("weight")
    @Expose
    private Integer weight;

    /**
     * @return The id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id The id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return The image
     */
    public String getImage() {
        return image;
    }

    /**
     * @param image The image
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * @return The name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return The price
     */
    public Integer getPrice() {
        return price;
    }

    /**
     * @param price The price
     */
    public void setPrice(Integer price) {
        this.price = price;
    }

    /**
     * @return The qty
     */
    public Integer getQty() {
        return qty;
    }

    /**
     * @param qty The qty
     */
    public void setQty(Integer qty) {
        this.qty = qty;
    }

    /**
     * @return The stock
     */
    public Integer getStock() {
        return stock;
    }

    /**
     * @param stock The stock
     */
    public void setStock(Integer stock) {
        this.stock = stock;
    }

    /**
     * @return The weight
     */
    public Integer getWeight() {
        return weight;
    }

    /**
     * @param weight The weight
     */
    public void setWeight(Integer weight) {
        this.weight = weight;
    }

}
