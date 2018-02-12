package org.training.service;

import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.core.model.product.ProductModel;
import java.util.List;

public interface ProductModelService {

    ProductModel findProductModelByCodeAndCatalogVersion(String code, CatalogVersionModel catalogVersion);

    List<ProductModel> findProductsThatBecomeOffline(int days);

    void updateManufacturerNameForProducts(String manufacturerName, List<ProductModel> products);
}