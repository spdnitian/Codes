package freelancer;

public class NewClass {
	int[] ab = new int[10000];
	String id;
	public NewClass(String id) {
		this.id = id;
	}
	public NewClass() {
	}
	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		super.finalize();
		System.out.println(this.id + " finalize called");
	}
	public static void main(String[] args) throws Throwable {
		new NewClass("a").callType1();
		new NewClass("b").callType2();
	}
	private void callType1() {
		long a = 1000;
		for (long i = 0; i < a; i++) {
			new NewClass(this.id + i);
		}
	}
	private void callType2() {
		long a = 1000;
		for (long i = 0; i < a; i++) {
			NewClass nc = new NewClass(this.id + i);
		}
	}
}

class A1{
	public static void abcd(){
		System.out.println("A called");
	}
	
	public void abc(){
		System.out.println("A called");
	}
	
	public final void abcde() {
		
	}
}

class B1 extends A1{
	public static void abcd(){
		System.out.println("B called");
	}
	
	public void abc(){
		super.abcde();
		System.out.println("B called");
	}
	public void xyz(){
		System.out.println("B called");
	}
}

interface i1{
	public static void name() {
		
	}
}
