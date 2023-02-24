package ehcahce.project.problem;

import static org.ehcache.config.units.EntryUnit.ENTRIES;
import static org.ehcache.config.units.MemoryUnit.MB;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import javax.cache.CacheManager;
import javax.cache.Caching;
import org.ehcache.config.CacheConfiguration;
import org.ehcache.config.ResourcePools;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.ExpiryPolicyBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.core.config.DefaultConfiguration;
import org.ehcache.jsr107.EhcacheCachingProvider;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.jcache.JCacheCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
//@ConditionalOnProperty(value = "merged.ehcache.config", havingValue = "false")
@ConditionalOnBean(name = "libraryCacheManager")
public class EhcacheProjectConfig {

  private static final String CACHE_PROVIDER = "org.ehcache.jsr107.EhcacheCachingProvider";

  @Value("${cache.onheap.size}")
  private Integer onHeapSize;

  @Value("${cache.offheap.size}")
  private Integer offHeapSize;

  @Value("${cache.expiry.time}")
  private Integer expiryTime;

  //Use Qualified CacheManager Bean - Works
  @Bean("projectJCacheCacheManager")
  public JCacheCacheManager jCacheCacheManager(@Qualifier("projectCacheManager") CacheManager cacheManager) {
    return new JCacheCacheManager(cacheManager);
  }

  //Use Primary CacheManager Bean - Works
//  @Bean("projectJCacheCacheManager")
//  public JCacheCacheManager jCacheCacheManager(CacheManager cacheManager) {
//    return new JCacheCacheManager(cacheManager);
//  }

  //Use Method call to set up CacheManager - Works
  @Bean("projectJCacheCacheManager")
  public JCacheCacheManager jCacheCacheManager() {
    return new JCacheCacheManager(cacheManager());
  }

  //!!! CACHING SINGLETON ENTRYPOINT TO RETRIEVE IT. !!!
  @Bean(name = "projectCacheManager", destroyMethod = "close")
  public CacheManager cacheManager() {
    final EhcacheCachingProvider ehcacheCachingProvider = (EhcacheCachingProvider) Caching.getCachingProvider(CACHE_PROVIDER);

    final ResourcePools resourcePools = ResourcePoolsBuilder.newResourcePoolsBuilder()
        .heap(onHeapSize, ENTRIES)
        .offheap(offHeapSize, MB)
        .build();

    final CacheConfiguration<Object, Object> cacheConfiguration = CacheConfigurationBuilder
        .newCacheConfigurationBuilder(Object.class, Object.class, resourcePools)
        .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofMinutes(expiryTime)))
        .build();

    final Map<String, CacheConfiguration<?, ?>> caches = new HashMap<>();
    caches.put("key3", cacheConfiguration);
    caches.put("participant", cacheConfiguration);

    final org.ehcache.config.Configuration configuration = new DefaultConfiguration(caches,
        ehcacheCachingProvider.getDefaultClassLoader());

    return ehcacheCachingProvider.getCacheManager(ehcacheCachingProvider.getDefaultURI(), configuration);
  }
}
