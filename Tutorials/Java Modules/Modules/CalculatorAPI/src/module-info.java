module CalculatorAPI {
//  exports com to CalcIMPL; // Exporting all classes from com package.

  exports com to CalcIMPL, Beginner;// Exporting all classes from implementation package only for Beginner and CalcIMPL modules

//  requires Beginner; // Circular dependency implementation

  uses com.Calculator; // Import the service (interface)

  provides com.Calculator with com.defaultIMPL.DefaultCalculator; // The same module can provide implementation for the service (interface) Calculator
}