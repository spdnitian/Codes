package freelancer;

interface a{
	interface b{
		
	}
}

class x implements a{
	interface c{
		
	}
}

public abstract class FreeLance {
	{
		//\\
	}
	static void abcd(Object a) {
		System.out.println("lol");
	}
	
	static void abcd(String a){
		System.out.println("no, u lol");
	}
	
	void abcd(){
		System.out.println("no, lul");
	}
	
	public static void main(String[] args) {
		System.out.println(exceptionHandling());
	}
	
	public static String exceptionHandling(){
		try {
			String string = "";
			string.equals(null);
			return "try";
		} catch (Exception e) {
			return "catch";
		}
	}

}

interface i {
	public static void x(){
		System.out.println("OMG");
	};
	public void y();
}

class j implements i{

	@Override
	public void y() {
		// TODO Auto-generated method stub
		
	}
	public static void x(){
		System.out.println("Haha");
	};
	
}

abstract class FE{
	public abstract void count();
}

class FEE extends FE{

	@Override
	public void count() {
		System.out.println("Concepts");
	}
}

class FEEE extends FEE{

	public FEEE() {
		System.out.println();
	}
}