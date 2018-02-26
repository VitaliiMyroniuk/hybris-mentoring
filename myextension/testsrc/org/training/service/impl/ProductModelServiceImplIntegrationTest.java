package org.training.service.impl;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.catalog.model.CatalogModel;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.ServicelayerBaseTest;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.training.service.ProductModelService;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@IntegrationTest
public class ProductModelServiceImplIntegrationTest extends ServicelayerBaseTest {

    private static final String CATALOG_ID = "Test catalog";

    private static final String CATALOG_VERSION = "Winter";

    private static final String PRODUCT_CODE = "Computer";

    private static final String FIND_ALL_PRODUCTS = "SELECT {PK} FROM {Product}";

    private static final String MANUFACTURER_NAME = "Manufacturer name";

    private static final String ERROR_MESSAGE = "Could not update manufacturer name for products";

    @Resource
    private FlexibleSearchService flexibleSearchService;

    @Resource
    ModelService modelService;

    @Resource
    private ProductModelService productModelService;

    private CatalogModel catalogModel;

    private CatalogVersionModel catalogVersionModel;

    private ProductModel productModel;

    private List<ProductModel> products;

    @Before
    public void setup() {
        catalogModel = modelService.create(CatalogModel.class);
        catalogModel.setId(CATALOG_ID);
        modelService.save(catalogModel);

        catalogVersionModel = modelService.create(CatalogVersionModel.class);
        catalogVersionModel.setCatalog(catalogModel);
        catalogVersionModel.setVersion(CATALOG_VERSION);
        modelService.save(catalogVersionModel);

        productModel = modelService.create(ProductModel.class);
        productModel.setCode(PRODUCT_CODE);
        productModel.setCatalogVersion(catalogVersionModel);
        modelService.save(productModel);
    }

    @After
    public void clean() {
        modelService.remove(productModel);
        modelService.remove(catalogVersionModel);
        modelService.remove(catalogModel);
    }

    @Test
    public void shouldUpdateManufacturerNameForProducts() {
        products = findAllProducts();

        productModelService.updateManufacturerNameForProducts(MANUFACTURER_NAME, products);
        products = findAllProducts();

        for (ProductModel productModel : products) {
            assertEquals(MANUFACTURER_NAME, productModel.getManufacturerName());
        }
    }

    @Test
    public void shouldRollbackDuringProductManufacturerNameUpdate() {
        products = findAllProducts();
        products.add(null);

        try {
            productModelService.updateManufacturerNameForProducts(MANUFACTURER_NAME, products);
        } catch (RuntimeException e) {
            assertEquals(ERROR_MESSAGE, e.getMessage());
        }
        products = findAllProducts();

        for (ProductModel productModel : products) {
            if (this.productModel.equals(productModel)) {
                assertNotEquals(MANUFACTURER_NAME, productModel.getManufacturerName());
            }
        }
    }

    private List<ProductModel> findAllProducts() {
        FlexibleSearchQuery query = new FlexibleSearchQuery(FIND_ALL_PRODUCTS);
        SearchResult<ProductModel> searchResult = flexibleSearchService.search(query);
        return new ArrayList<>(searchResult.getResult());
    }
}