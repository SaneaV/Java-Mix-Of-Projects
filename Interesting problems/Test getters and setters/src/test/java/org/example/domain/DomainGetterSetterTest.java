package org.example.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class DomainGetterSetterTest extends GetterSetterTest {

  public static Stream<Arguments> getData() {
    return Stream.of(
        Arguments.of(new Worker()),
        Arguments.of(new Salesman()),
        Arguments.of(new Administrator()),
        Arguments.of(new Responsibility()),
        Arguments.of(new TestClass()));
  }

  @BeforeEach
  public void addMoreSuppliers() {
    final Map<Class<?>, Supplier<?>> supplierMap = new HashMap<>();
    final List<String> ignoreMethods = new ArrayList<>();

    supplierMap.put(Responsibility[].class, () -> new Responsibility[0]);

    ignoreMethods.add("getArrays");

    this.addExtraSuppliersMappings(supplierMap);
    this.addIgnoreMethods(ignoreMethods);
  }

  @ParameterizedTest
  @MethodSource("getData")
  public void testDomain(Object domain) throws Exception {
    this.testGettersAndSetters(domain);
  }
}
