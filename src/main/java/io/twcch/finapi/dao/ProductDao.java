package io.twcch.finapi.dao;

import io.twcch.finapi.dto.ProductRequest;
import io.twcch.finapi.model.Product;

public interface ProductDao {

    public Integer createProduct(ProductRequest productRequest);

    public Product getProductById(Integer productId);

    public void updateProduct(Integer productId, ProductRequest productRequest);

    public void deleteProductById(Integer productId);

}
