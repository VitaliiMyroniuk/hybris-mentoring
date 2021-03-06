package org.training.dao;

import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.core.model.product.ProductModel;
import java.util.List;

public interface ProductModelDao {

    ProductModel findProductModelByCodeAndCatalogVersion(String code, CatalogVersionModel catalogVersion);

    List<ProductModel> findProductsThatBecomeOffline(int days);

}