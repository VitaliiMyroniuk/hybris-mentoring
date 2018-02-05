package org.training.service.impl;

import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.core.model.product.ProductModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.training.dao.ProductModelDao;
import org.training.service.ProductModelService;
import java.util.List;

public class ProductModelServiceImpl implements ProductModelService {

    @Autowired
    private ProductModelDao productModelDao;

    @Override
    public ProductModel findProductModelByCodeAndCatalogVersion(String code, CatalogVersionModel catalogVersion) {
        return productModelDao.findProductModelByCodeAndCatalogVersion(code, catalogVersion);
    }

    @Override
    public List<ProductModel> findProductsThatBecomeOffline(int days) {
        return productModelDao.findProductsThatBecomeOffline(days);
    }
}