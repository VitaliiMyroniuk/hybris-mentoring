package org.training.dao.impl;

import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.training.dao.MediaModelDao;
import java.util.Arrays;

public class MediaModelDaoImpl implements MediaModelDao {

    private static String GET_MEDIA_DESCRIPTION_BY_PRODUCT_CODE_AND_CATALOG_VERSION =
            "SELECT {m.description} FROM " +
                    "{Product as p JOIN Media as m ON  {p.picture} = {m.pk}}" +
                    "WHERE {p.code} = ?code AND {m.catalogVersion} = ?catalogVersion";

    @Autowired
    private FlexibleSearchService flexibleSearchService;

    @Override
    public String findMediaDescriptionByProductCodeAndCatalogVersion(String productCode, CatalogVersionModel catalogVersion) {
        FlexibleSearchQuery query = new FlexibleSearchQuery(GET_MEDIA_DESCRIPTION_BY_PRODUCT_CODE_AND_CATALOG_VERSION);
        query.addQueryParameter("code", productCode);
        query.addQueryParameter("catalogVersion", catalogVersion);
        query.setResultClassList(Arrays.asList(String.class));
        return flexibleSearchService.searchUnique(query);
    }
}