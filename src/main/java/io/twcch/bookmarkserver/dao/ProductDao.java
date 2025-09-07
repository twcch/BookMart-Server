package io.twcch.bookmarkserver.dao;

import io.twcch.bookmarkserver.dto.ProductQueryParams;
import io.twcch.bookmarkserver.dto.ProductRequest;
import io.twcch.bookmarkserver.model.Product;

import java.util.List;

public interface ProductDao {

    public Integer createProduct(ProductRequest productRequest);

    public Product getProductById(Integer productId);

    public List<Product> getProducts(ProductQueryParams productQueryParams);

    public void updateProduct(Integer productId, ProductRequest productRequest);

    public void deleteProductById(Integer productId);

}
