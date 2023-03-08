package ehcahce.library.problem;

import static org.ehcache.config.units.EntryUnit.ENTRIES;
import static org.ehcache.config.units.MemoryUnit.MB;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import javax.cache.CacheManager;
import org.ehcache.config.CacheConfiguration;
import org.ehcache.config.ResourcePools;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.ExpiryPolicyBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.core.config.DefaultConfiguration;
import org.ehcache.jsr107.EhcacheCachingProvider;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.jcache.JCacheCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@EnableCaching
public class EhcacheLibraryConfig {

  private static final String CACHE_PROVIDER = "org.ehcache.jsr107.EhcacheCachingProvider";

  @Value("${cache.onheap.size}")
  private Integer onHeapSize;

  @Value("${cache.offheap.size}")
  private Integer offHeapSize;

  @Value("${cache.expiry.time}")
  private Integer expiryTime;

  // Use Qualified CacheManager Bean - Works
//  @Primary
//  @Bean("libraryJCacheCacheManager")
//  public JCacheCacheManager jCacheCacheManager(@Qualifier("projectCacheManager") CacheManager cacheManager) {
//    return new JCacheCacheManager(cacheManager);
//  }

  // Use Primary CacheManager Bean - Works
//  @Bean("libraryJCacheCacheManager")
//  public JCacheCacheManager jCacheCacheManager(CacheManager cacheManager) {
//    return new JCacheCacheManager(cacheManager);
//  }

  // Use Method call to set up CacheManager - Works
  @Primary
  @Bean("libraryJCacheCacheManager")
  public JCacheCacheManager jCacheCacheManager(@Qualifier("libraryCacheManager") CacheManager cacheManager) {
    return new JCacheCacheManager(cacheManager);
  }

  @Primary
  @Bean(name = "libraryCacheManager", destroyMethod = "close")
  public CacheManager cacheManager() {
    final EhcacheCachingProvider ehcacheCachingProvider = new EhcacheCachingProvider();

    final ResourcePools resourcePools = ResourcePoolsBuilder.newResourcePoolsBuilder()
        .heap(onHeapSize, ENTRIES)
        .offheap(offHeapSize, MB)
        .build();

    final CacheConfiguration<Object, Object> cacheConfiguration = CacheConfigurationBuilder
        .newCacheConfigurationBuilder(Object.class, Object.class, resourcePools)
        .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofMinutes(expiryTime)))
        .build();

    final Map<String, CacheConfiguration<?, ?>> caches = new HashMap<>();
    caches.put("key1", cacheConfiguration);
    caches.put("key2", cacheConfiguration);

    final org.ehcache.config.Configuration configuration = new DefaultConfiguration(caches,
        ehcacheCachingProvider.getDefaultClassLoader());

    return ehcacheCachingProvider.getCacheManager(ehcacheCachingProvider.getDefaultURI(), configuration);
  }
}
