package io.twcch.finapi.service.impl;

import io.twcch.finapi.dao.ProductDao;
import io.twcch.finapi.dto.ProductRequest;
import io.twcch.finapi.model.Product;
import io.twcch.finapi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class ProductServiceImpl implements ProductService {

    @Autowired
    @Qualifier("productDaoImpl")
    private ProductDao productDao;

    @Override
    public Integer createProduct(ProductRequest productRequest) {

        return productDao.createProduct(productRequest);

    }

    @Override
    public Product getProductById(Integer productId) {

        return productDao.getProductById(productId);

    }

}
