package Segment;

public class SegmentTree {

	private class Node {
		Node left;
		Node rigt;
		int data;
		int statinterval;
		int endinterval;

	}

	private Node root;

	public SegmentTree(int arr[]) {
		// TODO Auto-generated constructor stub
		this.root = NodeCreate(arr, 0, arr.length - 1);
	}

	private Node NodeCreate(int[] arr, int si, int ei) {
		if (si == ei) {
			Node nn = new Node();
			nn.data = arr[si];
			nn.statinterval = si;
			nn.endinterval = ei;
			return nn;
		}
		int mid = (si + ei) / 2;
		Node node = new Node();
		node.statinterval = si;
		node.endinterval = ei;
		node.left = NodeCreate(arr, si, mid);
		node.rigt = NodeCreate(arr, mid + 1, ei);
		node.data = node.left.data + node.rigt.data;
		return node;

	}

	public void display() {
		display(this.root);
	}

	private void display(Node node) {
		// TODO Auto-generated method stub
		if (node == null) {
			return;
		}
		String str = "";
		if (node.left != null)
			str += "interval [" + node.left.statinterval + " , " + node.left.endinterval + " ] " + node.left.data
					+ " ==> ";
		else {
			str += ". ==> ";
		}
		str += "interval [" + node.statinterval + " , " + node.endinterval + " ] " + node.data + " <== ";
		if (node.rigt != null) {
			str += "interval [" + node.rigt.statinterval + " , " + node.rigt.endinterval + " ] " + node.rigt.data;
		} else {
			str += ".";
		}
		System.out.println(str);
		display(node.left);
		display(node.rigt);
	}

	public int QuarryInteval(int si, int ei) {
		return QuarryInteval(this.root, si, ei);
	}

	private int QuarryInteval(Node node, int si, int ei) {
		// TODO Auto-generated method stub
		if (node == null) {
			return 0;
		}
		// node is completely lying in side the quarry rang
		if (node.statinterval >= si && node.endinterval <= ei) {
			return node.data;
		} else if (node.statinterval > ei || node.endinterval < si) {
			// this is completely outside
			return 0; // default value of Quarry

		}
		// overlapping case
		else {
			int leftaanswer = QuarryInteval(node.left, si, ei);
			int rightanswer = QuarryInteval(node.rigt, si, ei);
			return leftaanswer + rightanswer;
		}

	}

	public void update_value(int val, int idx) {
		System.out.println(update_value(this.root, val, idx));
	}

	private int update_value(Node node, int val, int idx) {
		// TODO Auto-generated method stub
		if (idx >= node.statinterval && idx <= node.endinterval) {
			if (idx == node.statinterval && idx == node.endinterval) {
					node.data=val;
			} else {
				int leftnode = update_value(node.left, val, idx);
				int rightnode = update_value(node.rigt, val, idx);
				node.data=leftnode+rightnode;
			}
			
		}
		return node.data;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int arr[] = { 3, 8, 7, 6, -2, -8, 4, 9 };
		SegmentTree st = new SegmentTree(arr);
		st.display();
		System.out.println("******************************");
		st.update_value(14, 3);
		//System.out.println(st.QuarryInteval(2, 6));
		st.display();
		System.out.println(st.QuarryInteval(2, 6));

	}

}
