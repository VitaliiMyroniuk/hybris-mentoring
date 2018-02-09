package org.training.service.impl;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.core.model.product.ProductModel;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.training.dao.ProductModelDao;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;

@UnitTest
@RunWith(MockitoJUnitRunner.class)
public class ProductModelServiceImplTest {

    private static final String PRODUCT_CODE = "Computer";

    private static final int DAYS_BEFORE_OFFLINE = 5;

    private ProductModel productModel;

    private List<ProductModel>  products;

    @Mock
    private ProductModelDao productModelDao;

    @Mock
    private CatalogVersionModel catalogVersion;

    @InjectMocks
    private ProductModelServiceImpl productModelService;

    @Before
    public void init() {
        productModel = new ProductModel();
        products = new ArrayList<>();
    }

    @Test
    public void shouldReturnProductModelByCodeAndCatalogVersion() {
        doReturn(productModel).when(productModelDao).findProductModelByCodeAndCatalogVersion(PRODUCT_CODE, catalogVersion);

        ProductModel result = productModelService.findProductModelByCodeAndCatalogVersion(PRODUCT_CODE, catalogVersion);

        assertEquals(productModel, result);
    }

    @Test
    public void shouldReturnProductsThatBecomeOffline() {
        doReturn(products).when(productModelDao).findProductsThatBecomeOffline(DAYS_BEFORE_OFFLINE);

        List<ProductModel> result = productModelService.findProductsThatBecomeOffline(DAYS_BEFORE_OFFLINE);

        assertEquals(products, result);
    }
}