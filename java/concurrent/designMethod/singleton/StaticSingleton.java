public class StaticSingleton {
	private StaticSingleton(){
		System.out.println("StaticSingleton is create");
	}

	private static class SingletonHolder{
		private static StaticSingleton instance = new StaticSingleton();
	}

	public static StaticSingleton getInstance(){
		return SingletonHolder.instance;
	}

	public static void main(String[] args){
		StaticSingleton s = StaticSingleton.getInstance();
		StaticSingleton s1 = StaticSingleton.getInstance();
		//System.out.println(StaticSingleton.STATUS);
	}
}
