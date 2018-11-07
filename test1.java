
public class test1 {
	public static void main(String[] args) {
		LinSearchIntSet mySet = new LinSearchIntSet();
		System.out.print(mySet.contains(2));
		mySet.add(1);
		System.out.print(mySet.set.length);
		mySet.add(3);
		mySet.add(5);
		System.out.print(mySet.toString());
		System.out.print(mySet.contains(3));
		System.out.print(mySet.set.length);
		mySet.remove(3);
		System.out.print("\n" + mySet.toString());
		System.out.print(mySet.contains(3));
	}
}