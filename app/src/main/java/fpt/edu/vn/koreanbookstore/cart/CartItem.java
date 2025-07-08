package fpt.edu.vn.koreanbookstore.cart;

import java.util.Date;

public class CartItem {
    private int imageResId;
    private String title;
    private int quantity;
    private int originalPrice;
    private int discountedPrice;
    private boolean isChecked;
    private Date deliveryDate;

    public CartItem(int imageResId, String title, int originalPrice, int quantity, int discountedPrice, boolean isChecked, Date deliveryDate) {
        this.imageResId = imageResId;
        this.title = title;
        this.originalPrice = originalPrice;
        this.quantity = quantity;
        this.discountedPrice = discountedPrice;
        this.isChecked = isChecked;
        this.deliveryDate = deliveryDate;
    }

    public int getImageResId() {
        return imageResId;
    }

    public void setImageResId(int imageResId) {
        this.imageResId = imageResId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(int originalPrice) {
        this.originalPrice = originalPrice;
    }

    public int getDiscountedPrice() {
        return discountedPrice;
    }

    public void setDiscountedPrice(int discountedPrice) {
        this.discountedPrice = discountedPrice;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }
}
