package org.example;

import java.util.ArrayList;
import java.util.List;
import org.apache.thrift.TException;

public class CrossPlatformServiceImpl implements CrossPlatformService.Iface {

  private static final List<CrossPlatformResource> CROSS_PLATFORM_RESOURCES = new ArrayList<>();

  static {
    CROSS_PLATFORM_RESOURCES.add(new CrossPlatformResource(1, "System1"));
    CROSS_PLATFORM_RESOURCES.add(new CrossPlatformResource(2, "System2"));
    CROSS_PLATFORM_RESOURCES.add(new CrossPlatformResource(3, "System3"));
    CrossPlatformResource system4 = new CrossPlatformResource(4, "System4");
    system4.setSalutation("Salutation");
    CROSS_PLATFORM_RESOURCES.add(system4);
  }


  public CrossPlatformResource get(int id) throws TException {
    return CROSS_PLATFORM_RESOURCES.stream()
        .filter(crossPlatformResource -> crossPlatformResource.getId() == id)
        .findFirst()
        .orElseThrow(() -> new EntityNotFoundException(404, "CrossPlatformNotFound"));
  }

  public void save(CrossPlatformResource resource) {
    CROSS_PLATFORM_RESOURCES.add(resource);
  }

  public List<CrossPlatformResource> getList() {
    return CROSS_PLATFORM_RESOURCES;
  }

  public String toString(CrossPlatformResource resource) {
    return "CrossPlatformResource with id = " + resource.getId() + " and name = " + resource.getName()
        + " and optional salutation = " + resource.getSalutation();
  }
}
