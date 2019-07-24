
public class Derived extends Base {
  static void printValue() {
    System.out.println("  Called static Derived method.");
  }
  void nonStatPrintValue() {
    System.out.println("  Called non-static Derived method.");
  }
  void localIndirectStatMethod() {
    System.out.println("  Non-static calls own static:");
    System.out.print("  ");
    printValue();
  }
  
  public static void main(String[] args) {
	    System.out.println("Object: static type Base; runtime type Derived:");
	    Base base = new Derived();
	    base.printValue();
	    base.nonStatPrintValue();
	    System.out.println("Object: static type Derived; runtime type Derived:");
	    Derived Derived = new Derived();
	    Derived.printValue();
	    Derived.nonStatPrintValue();
	    System.out.println("Class: Derived static call:");
	    Derived.printValue();
	    System.out.println("Class: Base static call:");
	    Base.printValue();
	    System.out.println("Object: static/runtime type Derived -- call static from non-static method of Derived:");
	    Derived.localIndirectStatMethod();
	    System.out.println("Object: static/runtime type Derived -- call static from non-static method of Base:");
	    Derived.nonLocalIndirectStatMethod();
	  }
}