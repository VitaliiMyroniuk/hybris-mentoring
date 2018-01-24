package org.training.dao.impl;

import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.training.dao.ProductModelDao;

public class ProductModelDaoImpl implements ProductModelDao {

    @Autowired
    private FlexibleSearchService flexibleSearchService;

    @Override
    public ProductModel findProductModelByCodeAndCatalogVersion(String code, CatalogVersionModel catalogVersion) {
        ProductModel productModel = new ProductModel();
        productModel.setCode(code);
        productModel.setCatalogVersion(catalogVersion);
        return flexibleSearchService.getModelByExample(productModel);
    }
}