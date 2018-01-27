package org.training.dao.impl;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.catalog.model.CatalogModel;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.core.model.media.MediaModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import de.hybris.platform.servicelayer.exceptions.ModelNotFoundException;
import de.hybris.platform.servicelayer.model.ModelService;
import org.junit.Before;
import org.junit.Test;
import org.training.dao.MediaModelDao;
import javax.annotation.Resource;
import static org.junit.Assert.assertEquals;

@IntegrationTest
public class MediaModelDaoImplTest extends ServicelayerTransactionalTest {

    private static final String CATALOG_ID = "1";

    private static final String CATALOG_VERSION = "Winter";

    private static final String MEDIA_CODE = "Computer Media";

    private static final String MEDIA_DESCRIPTION = "Computer for the software development";

    private static final String PRODUCT_CODE = "Computer";

    private static final String NON_EXISTING_PRODUCT_CODE = "Phone";

    @Resource
    private MediaModelDao mediaModelDao;

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

        MediaModel media = new MediaModel();
        media.setCode(MEDIA_CODE);
        media.setCatalogVersion(catalogVersion);
        media.setDescription(MEDIA_DESCRIPTION);
        modelService.save(media);

        ProductModel product = new ProductModel();
        product.setCode(PRODUCT_CODE);
        product.setCatalogVersion(catalogVersion);
        product.setPicture(media);
        modelService.save(product);
    }

    @Test
    public void shouldReturnMediaDescriptionByProductCodeAndCatalogVersion() {
        String actualMediaDescription =
                mediaModelDao.findMediaDescriptionByProductCodeAndCatalogVersion(PRODUCT_CODE, catalogVersion);

        assertEquals(MEDIA_DESCRIPTION, actualMediaDescription);
    }

    @Test(expected = ModelNotFoundException.class)
    public void shouldProduceModelNotFoundException() {
        mediaModelDao.findMediaDescriptionByProductCodeAndCatalogVersion(NON_EXISTING_PRODUCT_CODE, catalogVersion);
    }
}