package org.training.cache;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.regioncache.CacheController;
import de.hybris.platform.regioncache.region.CacheRegion;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import de.hybris.platform.servicelayer.model.ModelService;
import org.junit.Before;
import org.junit.Test;
import javax.annotation.Resource;
import static org.junit.Assert.assertEquals;

@IntegrationTest
public class CacheManagerIntegrationTest extends ServicelayerTransactionalTest {

    private static final String ENTITY_CACHE_REGION = "entityCacheRegion";

    private static final String TYPE_SYSTEM_CACHE_REGION = "typesystemCacheRegion";

    private static final String QUERY_CACHE_REGION = "queryCacheRegion";

    private static final String MEDIA_CACHE_REGION = "mediaCacheRegion";

    @Resource
    private ModelService modelService;

    @Resource
    private CacheController cacheController;

    @Resource
    private CacheManager cacheManager;

    private CacheRegion entityCacheRegion;

    private CacheRegion typeSystemCacheRegion;

    private CacheRegion queryCacheRegion;

    private CacheRegion mediaCacheRegion;

    @Before
    public void setup() {
        entityCacheRegion = cacheController.getRegions()
                .stream()
                .filter(x -> x.getName().equals(ENTITY_CACHE_REGION))
                .findFirst()
                .orElseThrow(Error::new);

        typeSystemCacheRegion = cacheController.getRegions()
                .stream()
                .filter(x -> x.getName().equals(TYPE_SYSTEM_CACHE_REGION))
                .findFirst()
                .orElseThrow(Error::new);

        queryCacheRegion = cacheController.getRegions()
                .stream()
                .filter(x -> x.getName().equals(QUERY_CACHE_REGION))
                .findFirst()
                .orElseThrow(Error::new);

        mediaCacheRegion = cacheController.getRegions()
                .stream()
                .filter(x -> x.getName().equals(MEDIA_CACHE_REGION))
                .findFirst()
                .orElseThrow(Error::new);
    }

    @Test
    public void shouldClearEntityCacheRegion() {
        cacheManager.clearEntityCacheRegion();

        long entityCount = entityCacheRegion.getCacheRegionStatistics().getInstanceCount();
        assertEquals(0, entityCount);
    }

    @Test
    public void shouldClearAllCacheRegions() {
        cacheManager.clearAllCacheRegions();

        long entityCount = entityCacheRegion.getCacheRegionStatistics().getInstanceCount();
        long typeCount = typeSystemCacheRegion.getCacheRegionStatistics().getInstanceCount();
        long queryCount = queryCacheRegion.getCacheRegionStatistics().getInstanceCount();
        long mediaCount = typeSystemCacheRegion.getCacheRegionStatistics().getInstanceCount();
        assertEquals(0, entityCount);
        assertEquals(0, typeCount);
        assertEquals(0, queryCount);
        assertEquals(0, mediaCount);
    }
}