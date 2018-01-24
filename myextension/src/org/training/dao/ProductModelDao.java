package org.training.dao;

import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.core.model.product.ProductModel;

public interface ProductModelDao {

    ProductModel findProductModelByCodeAndCatalogVersion(String code, CatalogVersionModel catalogVersion);

}