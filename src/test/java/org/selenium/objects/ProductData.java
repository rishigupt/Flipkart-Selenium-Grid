package org.selenium.objects;

import org.selenium.utils.JacksonUtils;

import java.io.IOException;

public class ProductData {
    private int id;
    private String name;

    public ProductData() {
    }

    public ProductData(int id) throws IOException {
        ProductData[] products = JacksonUtils.deserializeJson("products.json", ProductData[].class);
        for(ProductData product: products){
            if(product.getId() == id){
                this.id = id;
                this.name = product.getName();
            }
        }
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
