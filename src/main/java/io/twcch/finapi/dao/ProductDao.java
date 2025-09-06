package io.twcch.finapi.dao;

import io.twcch.finapi.model.Product;

public interface ProductDao {

    public Product getProductById(Integer productId);

}
