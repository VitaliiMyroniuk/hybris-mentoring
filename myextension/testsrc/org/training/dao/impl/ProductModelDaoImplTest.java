package org.training.dao.impl;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.catalog.model.CatalogModel;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import de.hybris.platform.servicelayer.exceptions.ModelNotFoundException;
import de.hybris.platform.servicelayer.model.ModelService;
import org.junit.Before;
import org.junit.Test;
import org.training.dao.ProductModelDao;
import javax.annotation.Resource;
import static org.junit.Assert.assertEquals;

@IntegrationTest
public class ProductModelDaoImplTest extends ServicelayerTransactionalTest {

    private static final String CATALOG_ID = "1";

    private static final String CATALOG_VERSION = "Winter";

    private static final String PRODUCT_CODE = "Computer";

    private static final String NON_EXISTING_PRODUCT_CODE = "Phone";

    @Resource
    private ProductModelDao productModelDao;

    @Resource
    private ModelService modelService;

    private CatalogVersionModel catalogVersion;

    @Before
    public void setup() {
        CatalogModel catalog = new CatalogModel();
        catalog.setId(CATALOG_ID);
        modelService.save(catalog);

        catalogVersion = new CatalogVersionModel();
        catalogVersion.setCatalog(catalog);
        catalogVersion.setVersion(CATALOG_VERSION);
        modelService.save(catalogVersion);
    }

    @Test
    public void shouldReturnProductModelByCodeAndCatalogVersion() {
        ProductModel product = new ProductModel();
        product.setCode(PRODUCT_CODE);
        product.setCatalogVersion(catalogVersion);
        modelService.save(product);

        ProductModel result = productModelDao.findProductModelByCodeAndCatalogVersion(PRODUCT_CODE, catalogVersion);

        assertEquals(product, result);
    }

    @Test(expected = ModelNotFoundException.class)
    public void shouldProduceModelNotFoundException() {
        productModelDao.findProductModelByCodeAndCatalogVersion(NON_EXISTING_PRODUCT_CODE, catalogVersion);
    }
}