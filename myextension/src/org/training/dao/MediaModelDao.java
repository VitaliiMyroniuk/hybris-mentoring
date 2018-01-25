package org.training.dao;

import de.hybris.platform.catalog.model.CatalogVersionModel;

public interface MediaModelDao {

    String findMediaDescriptionByProductCodeAndCatalogVersion(String productCode, CatalogVersionModel catalogVersion);

}