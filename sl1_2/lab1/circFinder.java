import java.util.Scanner;

class circFinder {
	public static void main(String args[]) {

		double pi = 3.14159;
		double radius = 0;
		double circumference = 0;

		Scanner input = new Scanner(System.in);

		System.out.println("Please input the radius of the circle: ");

		radius = input.nextDouble();

		circumference = 2 * pi * radius;

		System.out.println("The circumference of the circle with radius of " + radius +
							" is " + circumference);

	}
}
