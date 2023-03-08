package ehcahce.project.problem;

import ehcahce.project.problem.participant.ParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.jcache.JCacheCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("ehcahce.project.problem")
@ComponentScan("ehcahce.library.problem")
public class EhcacheProjectApplication {

  public static void main(String[] args) {
    SpringApplication.run(EhcacheProjectApplication.class, args);
  }

  @Bean
  public String testJCacheJCacheManagers(@Qualifier("libraryJCacheCacheManager") JCacheCacheManager libraryJCacheCacheManager,
      @Qualifier("projectJCacheCacheManager") JCacheCacheManager projectJCacheCacheManager,
			@Autowired ParticipantService participantService) {
    System.out.println("libraryJCacheCacheManager: " + libraryJCacheCacheManager.getCacheManager().getCache("key1"));
    System.out.println("projectJCacheCacheManager: " + projectJCacheCacheManager.getCacheManager().getCache("key1"));
    System.out.println("libraryJCacheCacheManager: " + libraryJCacheCacheManager.getCacheManager().getCache("key2"));
    System.out.println("projectJCacheCacheManager: " + projectJCacheCacheManager.getCacheManager().getCache("key2"));
    System.out.println("libraryJCacheCacheManager: " + libraryJCacheCacheManager.getCacheManager().getCache("key3"));
    System.out.println("projectJCacheCacheManager: " + projectJCacheCacheManager.getCacheManager().getCache("key3"));
    System.out.println("libraryJCacheCacheManager: " + libraryJCacheCacheManager.getCacheManager().getCache("key4"));
    System.out.println("projectJCacheCacheManager: " + projectJCacheCacheManager.getCacheManager().getCache("key4"));

		participantService.testCacheable();

    return "";
  }
}
