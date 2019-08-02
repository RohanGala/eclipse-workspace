
public class C extends B{
	public C() {
		System.out.println("C");
	}
	
	public static void main(String[] args) {
		A a =new A(); // prints A
		A a1=new B(); // prints A B
		A a2=new C(); // prints A B C
		
		A a3 =new A(); // prints A
		B b=new B(); // prints A B
		C c=new C(); // prints A B C
		
	}
}
