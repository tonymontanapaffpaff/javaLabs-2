package org.gsu.pms.dao;

import org.gsu.pms.entity.Product;
import org.gsu.pms.model.PaginationResult;
import org.gsu.pms.model.ProductInfo;

public interface ProductDAO {

    Product findProduct(String code);

    ProductInfo findProductInfo(String code) ;

    PaginationResult<ProductInfo> queryProducts(int page,
                                                int maxResult, int maxNavigationPage);

    PaginationResult<ProductInfo> queryProducts(int page, int maxResult,
                                                int maxNavigationPage, String likeName);

    void save(ProductInfo productInfo);

    void delete(String code);

}
