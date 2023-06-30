package org.example.domain;

import static java.lang.Boolean.FALSE;
import static java.util.Collections.singleton;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.util.StringUtils.capitalize;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.function.Supplier;

public abstract class GetterSetterTest {

  // Map of default primitives and standard Java classes
  protected static final Map<Class<?>, Supplier<?>> DEFAULT_SUPPLIERS;

  // Get and Set static Strings
  private static final String GET_KEYWORD = "get";
  private static final int GET_KEYWORD_LENGTH = 3;
  private static final String SET_KEYWORD = "set";
  private static final int SET_KEYWORD_LENGTH = 3;
  private static final String IS_KEYWORD = "is";
  private static final int IS_KEYWORD_LENGTH = 2;

  // Error description
  private static final String ERROR_DESCRIPTION = "Unable to create objects for field: %s from class %s";

  // Static block initializer
  static {
    DEFAULT_SUPPLIERS = new HashMap<>();

    DEFAULT_SUPPLIERS.put(int.class, () -> 0);
    DEFAULT_SUPPLIERS.put(double.class, () -> 0.0d);
    DEFAULT_SUPPLIERS.put(float.class, () -> 0.0f);
    DEFAULT_SUPPLIERS.put(long.class, () -> 0L);
    DEFAULT_SUPPLIERS.put(boolean.class, () -> false);

    DEFAULT_SUPPLIERS.put(Integer.class, () -> 0);
    DEFAULT_SUPPLIERS.put(Double.class, () -> 0.0d);
    DEFAULT_SUPPLIERS.put(Float.class, () -> 0.0f);
    DEFAULT_SUPPLIERS.put(Long.class, () -> 0L);
    DEFAULT_SUPPLIERS.put(Boolean.class, () -> FALSE);
    DEFAULT_SUPPLIERS.put(String.class, () -> "");
    DEFAULT_SUPPLIERS.put(LocalDateTime.class, LocalDateTime::now);
    DEFAULT_SUPPLIERS.put(LocalTime.class, LocalTime::now);

    DEFAULT_SUPPLIERS.put(List.class, Collections::emptyList);
    DEFAULT_SUPPLIERS.put(Map.class, Collections::emptyMap);
    DEFAULT_SUPPLIERS.put(Set.class, Collections::emptySet);
  }

  // Set of ignored methods
  private final Set<String> ignoreMethods = new HashSet<>(singleton("getClass"));
  // Editable supplier map
  private final Map<Class<?>, Supplier<?>> supplierMap = new HashMap<>(DEFAULT_SUPPLIERS);

  // Method for populating ignoreMethods set
  protected void addIgnoreMethods(List<String> ignoreMethodsNames) {
    ignoreMethods.addAll(ignoreMethodsNames);
  }

  // Method for populating supplierMap with non-standard Classes
  protected void addExtraSuppliersMappings(Map<Class<?>, Supplier<?>> supplierMap) {
    this.supplierMap.putAll(supplierMap);
  }

  // Public method to test getters and setters via inheritance
  public <T> void testGettersAndSetters(T instance) throws Exception {
    final Map<String, GetterSetterPair> getterSetterPairMapping = getStringGetterSetterPairMapSecondOption(instance);

    // Traverse every GetterSetterPair into getterSetterPairMapping
    for (final Entry<String, GetterSetterPair> entry : getterSetterPairMapping.entrySet()) {
      // Exctract GetterSetterPair from getterSetterPairMapping
      final GetterSetterPair pair = entry.getValue();

      // Get object name
      final String objectName = entry.getKey();
      // Adjust object name to camel case (FirstName -> firstName)
      final String fieldName = objectName.substring(0, 1).toLowerCase() + objectName.substring(1);

      // In case pair has getter and setter methods
      if (pair.hasGetterAndSetter()) {
        // Get setter parameter type
        final Class<?> parameterType = pair.getSetter().getParameterTypes()[0];
        // Create new object with fieldName and type received from input parameters
        final Object newObject = createObject(fieldName, parameterType);

        // Invoke setter method from instance
        pair.getSetter().invoke(instance, newObject);
      }
      // Check if pair contains getter method
      if (pair.getGetter() != null) {
        // Get return type of getter
        final Class<?> parameterType = pair.getGetter().getReturnType();
        // Create new object with fieldName and type received from return type
        final Object newObject = createObject(fieldName, parameterType);

        // Get field from class for reflection
        final Field field = instance.getClass().getDeclaredField(fieldName);
        // Set accessible to change field value
        field.setAccessible(true);
        // Set value for instance
        field.set(instance, newObject);

        // Verify get method call
        callGetter(pair.getGetter(), instance, newObject);
      }
    }
  }

  private Object createObject(String fieldName, Class<?> clazz) {
    // Get supplier from supplier map based on primitive or class
    final Supplier<?> supplier = this.supplierMap.get(clazz);

    // In case we have such type of supplier -> get standard value for primitive or class
    if (supplier != null) {
      return supplier.get();
    }

    // We don't need to put enum into suppliers map, default value will be taken like a first enum value
    if (clazz.isEnum()) {
      return clazz.getEnumConstants()[0];
    }

    try {
      // In case we don't have class into supplierMap, try to instantiate new object via default constructor
      return clazz.newInstance();
    } catch (InstantiationException | IllegalAccessException e) {
      // Throw exception in case we can't instantiate new object
      throw new RuntimeException(String.format(ERROR_DESCRIPTION, fieldName, clazz.getName()), e);
    }
  }

  // Populate getterSetter Map based on received instance - First option
  private <T> Map<String, GetterSetterPair> getStringGetterSetterPairMapFirstOption(T instance) {
    final Map<String, GetterSetterPair> getterSetterMapping = new HashMap<>();

    // Extract every method from instance
    for (final Method method : instance.getClass().getMethods()) {
      // Get method name
      final String methodName = method.getName();

      // In case current method is listed into ignoreMethods Set, skip it
      if (this.ignoreMethods.contains(methodName)) {
        continue;
      }

      // Populate getterSetterMapping with get method in case it starts with "get" and has zero parameters
      if (methodName.startsWith(GET_KEYWORD) && method.getParameterCount() == 0) {
        // Get GetterSetterPair
        final GetterSetterPair getterSetterPair = populateGetterSetterPair(getterSetterMapping, methodName, GET_KEYWORD_LENGTH);
        // Add getter method into getterSetterPair
        getterSetterPair.setGetter(method);
      }
      // Populate getterSetterMapping with set method in case it starts with "set" and has one parameter
      else if (methodName.startsWith(SET_KEYWORD) && method.getParameterCount() == 1) {
        // Get GetterSetterPair
        final GetterSetterPair getterSetterPair = populateGetterSetterPair(getterSetterMapping, methodName, SET_KEYWORD_LENGTH);
        // Add setter method into getterSetterPair
        getterSetterPair.setSetter(method);
      }
      // Populate getterSetterMapping with get method in case it starts with "is" and has zero parameters
      else if (methodName.startsWith(IS_KEYWORD) && method.getParameterCount() == 0) {
        // Get GetterSetterPair
        final GetterSetterPair getterSetterPair = populateGetterSetterPair(getterSetterMapping, methodName, IS_KEYWORD_LENGTH);
        // Add getter method into getterSetterPair
        getterSetterPair.setGetter(method);
      }
    }
    return getterSetterMapping;
  }

  // Populate getterSetter Map based on received instance - Second option
  private <T> Map<String, GetterSetterPair> getStringGetterSetterPairMapSecondOption(T instance) throws NoSuchMethodException {
    final Map<String, GetterSetterPair> getterSetterMapping = new HashMap<>();
    // Get all declared fields in class
    final Field[] declaredMethods = instance.getClass().getDeclaredFields();

    // Traverse all declared fields
    for (Field field : declaredMethods) {
      // Skip Synthetic fields
      if (field.isSynthetic()) {
        continue;
      }
      // Filter setter method (set keyword + first big letter + parameter type)
      final Method setter = instance.getClass()
          .getDeclaredMethod(SET_KEYWORD + capitalize(field.getName()), field.getType());

      // Filter getter method base on "get" or "is" first keyword
      Method getter;
      if (field.getType() == boolean.class) {
        getter = instance.getClass().getDeclaredMethod(IS_KEYWORD + capitalize(field.getName()));
      } else {
        getter = instance.getClass().getDeclaredMethod(GET_KEYWORD + capitalize(field.getName()));
      }

      // Skip ignored method
      if (this.ignoreMethods.contains(setter.getName()) || this.ignoreMethods.contains(getter.getName())) {
        continue;
      }

      final GetterSetterPair getterSetterPair = new GetterSetterPair();
      // Add getter and setter to getterSetterPair
      getterSetterPair.setSetter(setter);
      getterSetterPair.setGetter(getter);
      // Put fieldName -> getterSetterPair into map
      getterSetterMapping.putIfAbsent(field.getName(), getterSetterPair);
    }
    return getterSetterMapping;
  }

  private GetterSetterPair populateGetterSetterPair(Map<String, GetterSetterPair> getterSetterMapping, String methodName,
      int keyWordLength) {
    final String fieldName = getFieldName(methodName, keyWordLength);
    return putIfAbsent(getterSetterMapping, fieldName);
  }

  // Get field name based one method name and key word length
  private String getFieldName(String methodName, int keyWordLength) {
    return methodName.substring(keyWordLength);
  }

  // Extract getterSetterPair based on field name, in case it's empty create new one
  // Put getterSetterPair into getterSetterPair in case it's absent
  private GetterSetterPair putIfAbsent(Map<String, GetterSetterPair> getterSetterMapping, String fieldName) {
    final GetterSetterPair getterSetterPair = getterSetterMapping.getOrDefault(fieldName, new GetterSetterPair());
    getterSetterMapping.putIfAbsent(fieldName, getterSetterPair);
    return getterSetterPair;
  }

  private <T> void callGetter(Method getter, T instance, Object expected)
      throws InvocationTargetException, IllegalAccessException {
    // Check if getter returned expected value
    final Object getResult = getter.invoke(instance);
    assertThat(getResult).isEqualTo(expected);
  }
}