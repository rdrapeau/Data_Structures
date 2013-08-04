package Cracking_The_Coding_Interview;

public class Chapter4 {
	public static void main(String[] args) {
		BinarySearchTree<Integer> t = new BinarySearchTree<Integer>();
		t.add(10);
		t.add(8);
		t.add(12);
		t.add(9);
		t.add(11);
		t.add(13);
		t.add(7);
		System.out.println(t.listByLevel());
	}
}

//    10
//  8    12
// 7 9  11 13