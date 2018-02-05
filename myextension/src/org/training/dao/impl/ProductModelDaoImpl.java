package org.training.dao.impl;

import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.training.dao.ProductModelDao;
import java.util.List;

public class ProductModelDaoImpl implements ProductModelDao {

    private static final String FIND_PRODUCTS_THAT_BECOME_OFFLINE =
            "SELECT {PK} FROM {Product} WHERE DATEDIFF({offlineDate}, CURDATE()) <= ?days";

    @Autowired
    private FlexibleSearchService flexibleSearchService;

    @Override
    public ProductModel findProductModelByCodeAndCatalogVersion(String code, CatalogVersionModel catalogVersion) {
        ProductModel productModel = new ProductModel();
        productModel.setCode(code);
        productModel.setCatalogVersion(catalogVersion);
        return flexibleSearchService.getModelByExample(productModel);
    }

    @Override
    public List<ProductModel> findProductsThatBecomeOffline(int days) {
        FlexibleSearchQuery query = new FlexibleSearchQuery(FIND_PRODUCTS_THAT_BECOME_OFFLINE);
        query.addQueryParameter("days", days);
        SearchResult<ProductModel> productModels = flexibleSearchService.search(query);
        return productModels.getResult();
    }
}