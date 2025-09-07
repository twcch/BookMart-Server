package io.twcch.bookmarkserver.controller.v1;

import io.twcch.bookmarkserver.constant.ProductCategory;
import io.twcch.bookmarkserver.dto.ProductQueryParams;
import io.twcch.bookmarkserver.dto.ProductRequest;
import io.twcch.bookmarkserver.model.Product;
import io.twcch.bookmarkserver.service.ProductService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@Validated
public class ProductControllerV1 {

    @Autowired
    @Qualifier("productServiceImpl")
    private ProductService productService;

    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProduct(@PathVariable Integer productId) {

        Product product = productService.getProductById(productId);

        if (product != null) {
            return ResponseEntity.status(HttpStatus.OK).body(product);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

    }

    @GetMapping("/")
    public ResponseEntity<List<Product>> getProducts(
            // 查詢條件 Filtering
            @RequestParam(required = false, name = "category") ProductCategory productCategory,
            @RequestParam(required = false, name = "search") String search,

            // 排序 Sorting
            @RequestParam(defaultValue = "created_date", name = "orderBy") String orderBy,
            @RequestParam(defaultValue = "DESC", name = "sort") String sort,

            // 分頁 Pagination
            @RequestParam(defaultValue = "10", name = "limit") @Max(1000) @Min(0) Integer limit,
            @RequestParam(defaultValue = "0", name = "offset") @Min(0) Integer offset
    ) {

        ProductQueryParams productQueryParams = new ProductQueryParams();
        productQueryParams.setProductCategory(productCategory);
        productQueryParams.setSearch(search);
        productQueryParams.setOrderBy(orderBy);
        productQueryParams.setSort(sort);
        productQueryParams.setLimit(limit);
        productQueryParams.setOffset(offset);

        List<Product> productList = productService.getProducts(productQueryParams);

        return ResponseEntity.status(HttpStatus.OK).body(productList);

    }

    @PostMapping("/")
    public ResponseEntity<Product> createProduct(@RequestBody @Valid ProductRequest productRequest) {

        Integer productId = productService.createProduct(productRequest);

        Product product = productService.getProductById(productId);

        return ResponseEntity.status(HttpStatus.CREATED).body(product);

    }

    @PutMapping("/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable Integer productId,
                                                 @RequestBody @Valid ProductRequest productRequest) {

        // 檢查 product 是否存在
        Product product = productService.getProductById(productId);

        if (product == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        // 更新 product
        productService.updateProduct(productId, productRequest);

        Product updatedProduct = productService.getProductById(productId);

        return ResponseEntity.status(HttpStatus.OK).body(updatedProduct);

    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable Integer productId) {

        productService.deleteProductById(productId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

}
