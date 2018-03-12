package id.wonderdeal.wonderdealapps.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by agustinaindah on 23/09/2017.
 */

public class Category implements Serializable {

    private Integer categoryId;
    private String categoryTitle;
    private String categoryImage;
    private List<Category> categorySub = new ArrayList<Category>();
    private String type;

    public Category() {
    }

    public Category(Integer categoryId, String categoryTitle, String categoryImage) {/*, */
        this(categoryId, categoryTitle,categoryImage , null); /*categoryImage,*/
    }

    public Category(Integer categoryId, String categoryTitle, String categoryImage ,List<Category> categorySub) {/*,  String categoryImage*/
        this(categoryId, categoryTitle, categoryImage, categorySub, null);/*, categoryImage*/
    }
    public Category(Integer categoryId, String categoryTitle, String categoryImage, List<Category> categorySub, String type) {/*, String categoryImage*/
        this.categoryId = categoryId;
        this.categoryTitle = categoryTitle;
        this.categoryImage = categoryImage;
        this.categorySub = categorySub;
        this.type = type;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public String getCategoryTitle() {
        return categoryTitle;
    }

    public String getCategoryImage(){
        return categoryImage;
    }

    public List<Category> getCategorySub() {
        return categorySub;
    }

    public String getType() {
        return type;
    }
}
