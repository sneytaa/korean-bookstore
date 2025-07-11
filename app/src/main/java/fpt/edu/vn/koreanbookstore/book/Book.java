package fpt.edu.vn.koreanbookstore.book;

public class Book {
    private int imageResId;
    private String title;
    private int price;
    private String description;
    private String author;
    private String id;
    private String categoryId;
    public Book(int imageResId, String title, int price, String description, String id, String author, String categoryId) {
        this.imageResId = imageResId;
        this.title = title;
        this.price = price;
        this.description = description;
        this.id = id;
        this.author = author;
        this.categoryId = categoryId;
    }

    public int getImageResId() {
        return imageResId;
    }

    public String getTitle() {
        return title;
    }

    public int getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public String getAuthor() {
        return author;
    }

    public String getId() {
        return id;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }
}
