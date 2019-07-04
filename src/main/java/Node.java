import lombok.EqualsAndHashCode;
import lombok.Setter;

@Setter
@EqualsAndHashCode
public class Node<T extends Comparable<T>> implements Comparable<Node<T>> {
	//	public final Node<T> NIL = new Node<>(null);
	public static final boolean RED = true;
	public static final boolean BLACK = false;

	public T value;
	public Node<T> left;
	public Node<T> right;
	public Node<T> parent;
	public boolean color;

	Node(T value) {
		this.value = value;
		this.left = null;
		this.right = null;
		this.parent = null;
		this.color = BLACK;
	}

	Node<T> getInOrderChild() {
		Node<T> a = left;
		while (a != null) {
			a = a.left;
		}
		return a;
	}

	boolean isLeaf() {
		return left == null && right == null;
	}

	void replace(Node<T> replacement) {
		if (parent != null) {
			if (this == parent.left) {
				parent.left = replacement;
			} else {
				parent.right = replacement;
			}
			if (replacement != null) {
				replacement.parent = parent;
			}
		}
	}

	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		if (left != null) {
			s.append(left).append(',');
		}
		s.append(value);
		if (right != null) {
			s.append(',').append(right);
		}
		return s.toString();
	}

	@Override
	public int compareTo(Node<T> o) {
		return this.value.compareTo(o.value);
	}
}
