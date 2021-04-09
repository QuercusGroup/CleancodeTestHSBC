package com.resttest.library.model.request;

public class StoreBookRequest {

    private String ISBN;

    private Integer quantity;

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(final String ISBN) {
        this.ISBN = ISBN;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(final Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "StoreBookRequest{" +
                "ISBN='" + ISBN + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
