/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package tut.baeldung.avro.model;
@org.apache.avro.specific.AvroGenerated
public enum Active implements org.apache.avro.generic.GenericEnumSymbol<Active> {
  YES, NO  ;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"enum\",\"name\":\"Active\",\"namespace\":\"tut.baeldung.avro.model\",\"symbols\":[\"YES\",\"NO\"]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  @Override
  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
}
