package com.pharmacy;

public class Store {
    private String storeName;
    private String storeLocation;
    public Store(String storeLocation, String storeName){
        this.storeLocation = storeLocation;
        this.storeName = storeName;
    }

    public void setStoreLocation(String storeLocation) {
        this.storeLocation = storeLocation;
    }

    public String getStoreLocation() {
        return storeLocation;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStoreName() {
        return storeName;
    }
}
