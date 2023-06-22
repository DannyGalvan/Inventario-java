package com.inventario.modelos;

public class Products {
    private int CodeProduct;
    private int Quantity;
    private float Price;
    private String Description;
    private int CodeCategory;
    private Category Category;

    public int getCodeCategory() {
        return CodeCategory;
    }

    public void setCodeCategory(int codeCategory) {
        CodeCategory = codeCategory;
    }

    public Category getCategory() {
        return Category;
    }

    public void setCategory(Category category) {
        Category = category;
    }

    public int getCodeProduct() {
        return CodeProduct;
    }

    public void setCodeProduct(int codeProduct) {
        CodeProduct = codeProduct;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public float getPrice() {
        return Price;
    }

    public void setPrice(float price) {
        Price = price;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}
