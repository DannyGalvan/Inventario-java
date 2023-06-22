package com.inventario.modelos;

public class Entry {
    private int CodeEntry;
    private int CodeProduct;
    private Products Product;
    private int PartialQuantity;
    private boolean IsEntriy;

    public int getCodeEntry() {
        return CodeEntry;
    }

    public void setCodeEntry(int codeEntry) {
        CodeEntry = codeEntry;
    }

    public int getCodeProduct() {
        return CodeProduct;
    }

    public void setCodeProduct(int codeProduct) {
        CodeProduct = codeProduct;
    }

    public Products getProduct() {
        return Product;
    }

    public void setProduct(Products product) {
        Product = product;
    }

    public int getPartialQuantity() {
        return PartialQuantity;
    }

    public void setPartialQuantity(int partialQuantity) {
        PartialQuantity = partialQuantity;
    }

    public boolean isIsEntriy() {
        return IsEntriy;
    }

    public void setIsEntriy(boolean isEntriy) {
        IsEntriy = isEntriy;
    }
}
