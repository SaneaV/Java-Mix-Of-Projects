The problem with Ehcache turned out to be quite commonplace, but it took a long time to identify it.

We are supposed to use Ehcache and JCacheCacheManager for caching.

We have a library that has a caching mechanism. It uses the Singleton method to create or get an EhcacheCachingProvider.

`Caching.getCachingProvider(CACHE_PROVIDER);`

If the EhcacheCachingProvider has not been created - it will be created, if it already exists in the context, then the already existing EhcacheCachingProvider will be returned to us and we will not be able to add additional Caches to this provider.

The right decision turned out to be to create a separate cache for each application (in the library and in our application).

`final EhcacheCachingProvider ehcacheCachingProvider = new EhcacheCachingProvider();`


To find the right solution, I needed the following resources:

[GitHub Ehcache](https://github.com/ehcache/ehcache-jcache/tree/master/ehcache-jcache#configuring-a-jcache-programmatically)

[Ehcache](https://www.ehcache.org/documentation/2.8/code-samples.html)