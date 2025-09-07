package io.twcch.bookmarkserver.dto;

import io.twcch.bookmarkserver.constant.ProductCategory;

public class ProductQueryParams {

    private ProductCategory productCategory;
    private String search;

    public ProductQueryParams() {

    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    @Override
    public String toString() {
        return "ProductQueryParams{" +
                "productCategory=" + productCategory +
                ", search='" + search + '\'' +
                '}';
    }

}
