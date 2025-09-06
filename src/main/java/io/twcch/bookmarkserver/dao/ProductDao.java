package io.twcch.bookmarkserver.dao;

import io.twcch.bookmarkserver.dto.ProductRequest;
import io.twcch.bookmarkserver.model.Product;

public interface ProductDao {

    public Integer createProduct(ProductRequest productRequest);

    public Product getProductById(Integer productId);

    public void updateProduct(Integer productId, ProductRequest productRequest);

    public void deleteProductById(Integer productId);

}
