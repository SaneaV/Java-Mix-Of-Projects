package ehcahce.library.problem;

import org.springframework.cache.annotation.Cacheable;

public class TestLibraryEhcache {

  @Cacheable(value = "key1", cacheManager = "libraryJCacheCacheManager")
  public String getString() {
    return "hello";
  }

  @Cacheable(value = "key2", key = "#value", cacheManager = "libraryJCacheCacheManager")
  public String getString(String value) {
    return "world";
  }
}
