import java.util.Objects;

public class RbTree<T extends Comparable<T>> {

  private Node<T> root = null;

  // treeInsert node x into tree t
  void treeInsert(Node<T> x) {
    Node<T> t = root;

    if (root == null) {
      root = x;
    } else {
      while (true) {
        if (x.compareTo(t) < 0) {
          if (t.left != null) {
            t = t.left;
          } else {
            t.left = x;
            x.parent = t;
          }
        } else if (x.compareTo(t) > 0) {
          if (t.right != null) {
            t = t.right;
          } else {
            t.right = x;
            x.parent = t;
          }
        } else {
          break;
        }
      }
    }
  }

  void rbInsert(T value) {
    Node<T> x = new Node<>(value);
    treeInsert(x);
    x.color = Node.RED;

    if (root == x) {
      x.color = Node.BLACK;
    } else {
      Node<T> p = x.parent;
      if (p.color != Node.BLACK || p != root) {
        Node<T> u = getUncle(x);
        if (u.color == Node.RED) {
          Node<T> g = x.parent.parent;
          p.color = Node.BLACK;
          u.color = Node.BLACK;
          g.color = Node.RED;
          // repeat 2 & 3
        }
      }
    }
  }

  private Node<T> getUncle(Node<T> x) {
    Node<T> g = x.parent.parent;
    if (g.left == x.parent) {
      return g.right;
    } else {
      return g.left;
    }
  }

  // delete node x from tree t
  public void delete(T value) {
    Node<T> x = getNode(value);
    Objects.requireNonNull(x);
    if (x.isLeaf()) {
      x.replace(null);
    } else if (x.left == null) {
      x.replace(x.right);
    } else if (x.right == null) {
      x.replace(x.left);
    } else {
      Node<T> r = x.getInOrderChild();
      r.replace(r.right);
      x.replace(r);
      if (root == x) {
        root = r;
      }
    }
  }

  private Node<T> getNode(T value) {
    Node<T> t = root;

    while (t != null) {
      if (value.compareTo(t.value) < 0) {
        t = t.left;
      } else if (value.compareTo(t.value) > 0) {
        t = t.right;
      } else {
        return t;
      }
    }
    return null;
  }

  @Override
  public String toString() {
    return root.toString();
  }

  public static void main(String[] args) {
    RbTree<Integer> tree = new RbTree<>();
    Integer[] values = new Integer[]{7, 3, 18, 10, 22, 8, 11, 26};
    for (Integer value : values) {
      tree.rbInsert(value);
    }
    tree.print();
//		tree.rbInsert(15);
//		tree.print();
//		tree.rotateRight(tree.getNode(18));
//		tree.print();
//		System.out.println("Removing 30");
//		tree.delete(30);
//		System.out.println(tree);
  }

  private void print() {
    print(root, 0);
  }

  private void print(Node<T> x, int depth) {
    for (int i = 0; i < depth; i++) {
      System.out.print("  ");
    }
    if (x != null) {
      System.out.println(x.value + (x.color == Node.BLACK ? "B" : "R"));
      depth++;
      print(x.left, depth);
      print(x.right, depth);
    } else {
      System.out.println('.');
    }
  }

  public int blackHeight(Node x) {
    int bh = 1;
    Node c = x;
    while (c != null) {
      c = c.right;
      if (c.color == Node.BLACK) {
        bh++;
      }
    }
    return bh;
  }

  void rotateRight(Node<T> x) {
    x.parent.right = x.left;
    x.left.parent = x.parent;
    x.parent = x.left;
    x.left = x.left.right;
    x.parent.right = x;
    x.left.parent = x;
  }

  void rotateLeft(Node<T> x) {
    x.parent.left = x.right;
    x.right.parent = x.parent;
    x.parent = x.right;
    x.right = x.right.left;
    x.parent.left = x;
    x.right.parent = x;
  }
}