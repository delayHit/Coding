class Root {
		public static void main(String[] args){
				int number = 100;
				if (args.length > 0) {
					number = Integer.parseInt(args[0]);
				}
				System.out.println("The suare root of "
								+ number
								+ " is "
								+ Math.sqrt(number)
								);
		}
}
