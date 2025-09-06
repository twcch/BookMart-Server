package io.twcch.finapi.service;

import io.twcch.finapi.dto.ProductRequest;
import io.twcch.finapi.model.Product;

public interface ProductService {

    public Integer createProduct(ProductRequest productRequest);

    public Product getProductById(Integer productId);

    public void updateProduct(Integer productId, ProductRequest productRequest);

    public void deleteProductById(Integer productId);

}
