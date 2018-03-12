package id.wonderdeal.wonderdealapps.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.android.tonyvu.sc.model.Saleable;

/**
 * Created by agustinaindah on 14/09/2017.
 */

public class ItemProduct implements Serializable, Saleable{

    @SerializedName("ID")
    @Expose
    private Integer iD;
    @SerializedName("post_author")
    @Expose
    private String postAuthor;
    @SerializedName("post_date")
    @Expose
    private String postDate;
    @SerializedName("post_date_gmt")
    @Expose
    private String postDateGmt;
    @SerializedName("post_content")
    @Expose
    private String postContent;
    @SerializedName("post_title")
    @Expose
    private String postTitle;
    @SerializedName("post_excerpt")
    @Expose
    private String postExcerpt;
    @SerializedName("post_status")
    @Expose
    private String postStatus;
    @SerializedName("comment_status")
    @Expose
    private String commentStatus;
    @SerializedName("ping_status")
    @Expose
    private String pingStatus;
    @SerializedName("post_password")
    @Expose
    private String postPassword;
    @SerializedName("post_name")
    @Expose
    private String postName;
    @SerializedName("to_ping")
    @Expose
    private String toPing;
    @SerializedName("pinged")
    @Expose
    private String pinged;
    @SerializedName("post_modified")
    @Expose
    private String postModified;
    @SerializedName("post_modified_gmt")
    @Expose
    private String postModifiedGmt;
    @SerializedName("post_content_filtered")
    @Expose
    private String postContentFiltered;
    @SerializedName("post_parent")
    @Expose
    private Integer postParent;
    @SerializedName("guid")
    @Expose
    private String guid;
    @SerializedName("menu_order")
    @Expose
    private Integer menuOrder;
    @SerializedName("post_type")
    @Expose
    private String postType;
    @SerializedName("post_mime_type")
    @Expose
    private String postMimeType;
    @SerializedName("comment_count")
    @Expose
    private String commentCount;
    @SerializedName("filter")
    @Expose
    private String filter;
    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("thumb")
    @Expose
    private String thumb;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("image_gallery")
    @Expose
    private List<ImageGallery> imageGallery = new ArrayList<ImageGallery>();
    @SerializedName("sku")
    @Expose
    private String sku;
    @SerializedName("normal_price")
    @Expose
    private String normalPrice;
    @SerializedName("sale_price")
    @Expose
    private String salePrice;
    @SerializedName("diskon")
    @Expose
    private String diskon;
    @SerializedName("total_sales")
    @Expose
    private String totalSales;
    @SerializedName("stock")
    @Expose
    private String stock;
    @SerializedName("expired_product")
    @Expose
    private String expiredProduct;
    @SerializedName("expired_product_format_enak")
    @Expose
    private String expiredProductFormatEnak;
    @SerializedName("jumlah_bintang")
    @Expose
    private String jumlahBintang;
    @SerializedName("alamat")
    @Expose
    private String alamat;
    @SerializedName("info")
    @Expose
    private String info;
    @SerializedName("konten")
    @Expose
    private String konten;
    @SerializedName("syarat_ketentuan")
    @Expose
    private String syaratKetentuan;
    @SerializedName("logo_merchant")
    @Expose
    private String logoMerchant;
    @SerializedName("maps_alamat")
    @Expose
    private String mapsAlamat;
    @SerializedName("alamat_archive")
    @Expose
    private String alamatArchive;

    public Integer getID() {
        return iD;
    }

    public void setID(Integer iD) {
        this.iD = iD;
    }

    public String getPostAuthor() {
        return postAuthor;
    }

    public void setPostAuthor(String postAuthor) {
        this.postAuthor = postAuthor;
    }

    public String getPostDate() {
        return postDate;
    }

    public void setPostDate(String postDate) {
        this.postDate = postDate;
    }

    public String getPostDateGmt() {
        return postDateGmt;
    }

    public void setPostDateGmt(String postDateGmt) {
        this.postDateGmt = postDateGmt;
    }

    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getPostExcerpt() {
        return postExcerpt;
    }

    public void setPostExcerpt(String postExcerpt) {
        this.postExcerpt = postExcerpt;
    }

    public String getPostStatus() {
        return postStatus;
    }

    public void setPostStatus(String postStatus) {
        this.postStatus = postStatus;
    }

    public String getCommentStatus() {
        return commentStatus;
    }

    public void setCommentStatus(String commentStatus) {
        this.commentStatus = commentStatus;
    }

    public String getPingStatus() {
        return pingStatus;
    }

    public void setPingStatus(String pingStatus) {
        this.pingStatus = pingStatus;
    }

    public String getPostPassword() {
        return postPassword;
    }

    public void setPostPassword(String postPassword) {
        this.postPassword = postPassword;
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    public String getToPing() {
        return toPing;
    }

    public void setToPing(String toPing) {
        this.toPing = toPing;
    }

    public String getPinged() {
        return pinged;
    }

    public void setPinged(String pinged) {
        this.pinged = pinged;
    }

    public String getPostModified() {
        return postModified;
    }

    public void setPostModified(String postModified) {
        this.postModified = postModified;
    }

    public String getPostModifiedGmt() {
        return postModifiedGmt;
    }

    public void setPostModifiedGmt(String postModifiedGmt) {
        this.postModifiedGmt = postModifiedGmt;
    }

    public String getPostContentFiltered() {
        return postContentFiltered;
    }

    public void setPostContentFiltered(String postContentFiltered) {
        this.postContentFiltered = postContentFiltered;
    }

    public Integer getPostParent() {
        return postParent;
    }

    public void setPostParent(Integer postParent) {
        this.postParent = postParent;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public Integer getMenuOrder() {
        return menuOrder;
    }

    public void setMenuOrder(Integer menuOrder) {
        this.menuOrder = menuOrder;
    }

    public String getPostType() {
        return postType;
    }

    public void setPostType(String postType) {
        this.postType = postType;
    }

    public String getPostMimeType() {
        return postMimeType;
    }

    public void setPostMimeType(String postMimeType) {
        this.postMimeType = postMimeType;
    }

    public String getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(String commentCount) {
        this.commentCount = commentCount;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<ImageGallery> getImageGallery() {
        return imageGallery;
    }

    public void setImageGallery(List<ImageGallery> imageGallery) {
        this.imageGallery = imageGallery;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getNormalPrice() {
        return normalPrice;
    }

    public void setNormalPrice(String normalPrice) {
        this.normalPrice = normalPrice;
    }

    public String getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(String salePrice) {
        this.salePrice = salePrice;
    }

    public String getDiskon() {
        return diskon;
    }

    public void setDiskon(String diskon) {
        this.diskon = diskon;
    }

    public String getTotalSales() {
        return totalSales;
    }

    public void setTotalSales(String totalSales) {
        this.totalSales = totalSales;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getExpiredProduct() {
        return expiredProduct;
    }

    public void setExpiredProduct(String expiredProduct) {
        this.expiredProduct = expiredProduct;
    }

    public String getExpiredProductFormatEnak() {
        return expiredProductFormatEnak;
    }

    public void setExpiredProductFormatEnak(String expiredProductFormatEnak) {
        this.expiredProductFormatEnak = expiredProductFormatEnak;
    }

    public String getJumlahBintang() {
        return jumlahBintang;
    }

    public void setJumlahBintang(String jumlahBintang) {
        this.jumlahBintang = jumlahBintang;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getKonten() {
        return konten;
    }

    public void setKonten(String konten) {
        this.konten = konten;
    }

    public String getSyaratKetentuan() {
        return syaratKetentuan;
    }

    public void setSyaratKetentuan(String syaratKetentuan) {
        this.syaratKetentuan = syaratKetentuan;
    }

    public String getLogoMerchant() {
        return logoMerchant;
    }

    public void setLogoMerchant(String logoMerchant) {
        this.logoMerchant = logoMerchant;
    }

    public String getMapsAlamat() {
        return mapsAlamat;
    }

    public void setMapsAlamat(String mapsAlamat) {
        this.mapsAlamat = mapsAlamat;
    }

    public String getAlamatArchive() {
        return alamatArchive;
    }

    public void setAlamatArchive(String alamatArchive) {
        this.alamatArchive = alamatArchive;
    }


     /*@Override
    public BigDecimal getPrice() {
        return BigDecimal.valueOf(getHotPromoPriceFinal());
    }

    @Override
    public String getName() {
        return getHotPromoName();
    }*/

    @Override
    public BigDecimal getPrice() {
        return BigDecimal.valueOf(Integer.valueOf(getSalePrice()));
    }

    @Override
    public String getName() {
        return getPostTitle();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (!(o instanceof ItemProduct)) return false;
        return (this.getID() == ((ItemProduct) o).getID());
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int hash = 1;
        hash = hash * prime + iD;
        hash = hash * prime + (postTitle == null ? 0 : postTitle.hashCode());
        hash = hash * prime + (salePrice == null ? 0 : salePrice.hashCode());
        hash = hash * prime + (konten == null ? 0 : konten.hashCode());

        return hash;
    }

}
