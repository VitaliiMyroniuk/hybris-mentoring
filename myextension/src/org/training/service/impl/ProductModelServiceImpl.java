package org.training.service.impl;

import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.product.impl.DefaultProductService;
import de.hybris.platform.tx.Transaction;
import de.hybris.platform.tx.TransactionBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.training.dao.ProductModelDao;
import org.training.service.ProductModelService;
import java.util.List;

public class ProductModelServiceImpl extends DefaultProductService implements ProductModelService {

    private static final String ERROR_MESSAGE = "Could not update manufacturer name for products";

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

    @Override
    public void updateManufacturerNameForProducts(String manufacturerName, List<ProductModel> products) {
        try {
            Transaction.current().execute(new TransactionBody() {
                @Override
                public Object execute() throws Exception {
                    for (ProductModel product : products) {
                        product.setManufacturerName(manufacturerName);
                        getModelService().save(product);
                    }
                    return new Object();
                }
            });
        } catch (Exception e) {
            throw new RuntimeException(ERROR_MESSAGE, e);
        }
    }
}