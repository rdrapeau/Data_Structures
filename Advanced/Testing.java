package Advanced;

public class Testing {
	public static void main(String[] args) {
		DisjointSet<Integer> set = new DisjointSet<Integer>();
		set.union(0, 4);
		set.union(2, 4);
		set.union(6, 4);
		set.union(4, 3);
		set.union(6, 7);
		set.union(2, 9);
		// 0-4 2-4 6-4 4-3 6-7 2-9
	}
}
