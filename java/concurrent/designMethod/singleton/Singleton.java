public class Singleton {
	public static int STATUS = 1;
	private Singleton(){
		System.out.println("Singleton is create");
	}
	private static Singleton instance = new Singleton();
	public static Singleton getInstance(){
		return instance;
	}
	public static void main(String[] args){
		Singleton s = Singleton.getInstance();
		//System.out.println(Singleton.STATUS);
	}
}
