package Cracking_The_Coding_Interview;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		NaryTree<Integer> t = new NaryTree<Integer>(2);
		for (int i = 0; i < 10; i++) {
			t.add(i);
		}
		t.remove(5);
	}

}
