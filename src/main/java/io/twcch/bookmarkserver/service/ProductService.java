package io.twcch.bookmarkserver.service;

import io.twcch.bookmarkserver.constant.ProductCategory;
import io.twcch.bookmarkserver.dto.ProductRequest;
import io.twcch.bookmarkserver.model.Product;

import java.util.List;

public interface ProductService {

    public Integer createProduct(ProductRequest productRequest);

    public Product getProductById(Integer productId);

    public List<Product> getProducts(ProductCategory productCategory, String search);

    public void updateProduct(Integer productId, ProductRequest productRequest);

    public void deleteProductById(Integer productId);

}
