package org.training.cache;

import de.hybris.platform.core.PK;
import de.hybris.platform.core.model.ItemModel;
import de.hybris.platform.regioncache.CacheController;
import de.hybris.platform.regioncache.region.CacheRegion;
import de.hybris.platform.util.Utilities;

public class CacheManager {

    private static final String ENTITY_CACHE_REGION = "entityCacheRegion";

    private CacheController cacheController;

    public void invalidateItemModel(ItemModel itemModel) {
        PK primaryKey =  itemModel.getPk();
        Utilities.invalidateCache(primaryKey);
    }

    public void clearEntityCacheRegion() {
        cacheController.getRegions()
                .stream()
                .filter(x -> x.getName().equals(ENTITY_CACHE_REGION))
                .forEach(CacheRegion::clearCache);
    }

    public void clearAllCacheRegions() {
        cacheController.getRegions().forEach(CacheRegion::clearCache);
    }

    public CacheController getCacheController() {
        return cacheController;
    }

    public void setCacheController(CacheController cacheController) {
        this.cacheController = cacheController;
    }
}