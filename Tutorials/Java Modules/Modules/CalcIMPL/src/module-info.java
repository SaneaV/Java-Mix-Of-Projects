module CalcIMPL {
//  requires CalculatorAPI; // This module needs CalculatorAPI module.
  requires transitive CalculatorAPI; // CalculatorAPI becomes implicitly required for all modules that will export CalcIMPL

  exports implementation; // Exporting all classes from implementation package.

  provides com.Calculator with implementation.InteractiveCommandLineCalculator; // We provide implementation for the service (interface) Calculator
}