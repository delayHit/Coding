import java.awt.*;

class PointTester {
		public static void main(String[] args) {
				Point object1 = new Point(11,22);
				Point3D object2 = new Point3D(7,6,64);

				System.out.println("The 2D point is located at (" + object1.x
								+ ", " + object1.y + ")");
				
				System.out.println("\tIt`s being moved to (4,13)");
				object1.move(4,13);
				System.out.println("The 2D point is located at (" + object1.x
								+ ", " + object1.y + ")");

				System.out.println("\tIt`s being moved -10 units on both the x and y axes");
				object1.translate(-10,-10);
				System.out.println("The 2D point is located at (" + object1.x
								+ ", " + object1.y + ")");
				
				
				System.out.println("The 3D point is located at (" + object2.x
								+ ", " + object2.y + ", " + object2.z + ")");
				
				System.out.println("\tIt`s being moved to (10,22,71)");
				object2.move(10,22,71);
				System.out.println("The 3D point is located at (" + object2.x
								+ ", " + object2.y + ", " + object2.z + ")");

				System.out.println("\tIt`s being moved -10 units on both the x and y and z axes");
				object2.translate(-10,-10,-10);
				System.out.println("The 3D point is located at (" + object2.x
								+ ", " + object2.y + ", " + object2.z + ")");
		}

}
