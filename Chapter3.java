package Cracking_The_Coding_Interview;

public class Chapter3 {
	public static void main(String[] args) {		
		StackQueue<Integer> s = new StackQueue<Integer>();
		for (int i = 0; i < 15; i++) {
			s.push(i);
		}
		for (int i = 0; i < 9; i++) {
			s.pop();
		}
		System.out.println(s.pop());
		System.out.println("Size: " + s.size());
	}
}
