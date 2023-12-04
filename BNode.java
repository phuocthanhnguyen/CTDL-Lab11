import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Stack;

public class BNode<E extends Comparable<E>> {
	private E data;
	private BNode<E> left;
	private BNode<E> right;
	private BNode<E> root;

	public BNode(E data) {
		this.data = data;
		this.left = null;
		this.right = null;
	}

	public BNode(E data, BNode<E> left, BNode<E> right) {
		this.data = data;
		this.left = left;
		this.right = right;
	}

	public E getData() {
		return data;
	}

	public void setData(E data) {
		this.data = data;
	}

	public BNode<E> getLeft() {
		return left;
	}

	public void setLeft(BNode<E> left) {
		this.left = left;
	}

	public BNode<E> getRight() {
		return right;
	}

	public void setRight(BNode<E> right) {
		this.right = right;
	}

	public BNode<E> getRoot() {
		return root;
	}

	public void setRoot(BNode<E> root) {
		this.root = root;
	}

	public void add(E e) {
		root = addNode(root, e);
	}

	public BNode<E> addNode(BNode<E> node, E e) {
		if (node == null) {
			return new BNode<>(e);
		}

		int comp = e.compareTo(node.getData());
		if (comp < 0) {
			node.setLeft(addNode(node.getLeft(), e));
		} else if (comp > 0) {
			node.setRight(addNode(node.getRight(), e));
		}

		return node;
	}

	// Add a collection of elements col into BST
	public void add(Collection<E> col) {
		for (E element : col) {
			add(element);
		}

	}

	// compute the depth of a node in BST
	public int depth(E node) {
		return getDepth(root, node, 0);
	}

	private int getDepth(BNode<E> root2, E node, int i) {
		if (root2 == null) {
			return -1;
		}

		int comp = node.compareTo(root2.getData());
		if (comp == 0) {
			return i;
		} else if (comp < 0) {
			return getDepth(root2.getLeft(), node, i + 1);
		} else {
			return getDepth(root2.getRight(), node, i + 1);
		}

	}

	// compute the height of BST
	public int height() {
		return getHeight(root);
	}

	public int getHeight(BNode<E> root2) {
		if (root2 == null) {
			return 0;
		}
		int le = getHeight(root2.getLeft());
		int ri = getHeight(root2.getRight());

		return Math.max(le, ri) + 1;
	}

	// Compute total nodes in BST
	public int size() {
		return getSize(root);
	}

	public int getSize(BNode<E> root2) {
		if (root2 == null) {
			return 0;
		}

		int le = getSize(root2.getLeft());
		int ri = getSize(root2.getRight());

		return le + ri + 1;
	}

	// Check whether element e is in BST
	public boolean contains(E e) {
		int comp = e.compareTo(data);
		if (comp == 0) {
			return true;
		} else if (comp > 0) {
			return (left == null) ? false : left.contains(e);
		} else
			return (right == null) ? false : right.contains(e);

	}

	// Find the minimum element in BST
	public E findMin() {
		BNode<E> current = this;

		while (current.left != null) {
			current = current.left;
		}

		return current.data;
	}

	// Find the maximum element in BST
	public E findMax() {
		BNode<E> current = this;

		while (current.right != null) {
			current = current.right;
		}

		return current.data;
	}

	// Remove element e from BST
	public boolean remove(E e) {
		BNode<E> current = this;
		BNode<E> parent = null;

		while (current != null) {
			parent = current;

			if (e.compareTo(current.data) < 0) {
				current = current.left;
			}

			else if (e.compareTo(current.data) > 0) {
				current = current.right;
			}

			else {
				if (current.left == null && current.right == null) {
					if (parent.left == current) {
						parent.left = null;
					} else {
						parent.right = null;
					}
				}

				else if (current.left == null) {
					if (parent.left == current) {
						parent.left = current.right;
					} else {
						parent.right = current.right;
					}
				} else if (current.right == null) {
					if (parent.left == current) {
						parent.left = current.left;
					} else {
						parent.right = current.left;
					}
				}

				else {
					BNode<E> successor = current.right;
					BNode<E> successorParent = current;

					while (successor.left != null) {
						successorParent = successor;
						successor = successor.left;
					}

					current.data = successor.data;

					if (successorParent.left == successor) {
						successorParent.left = successor.right;
					} else {
						successorParent.right = successor.right;
					}
				}

				return true;
			}
		}

		return false;
	}

	// get the descendants of a node
	public List<E> descendants(E data) {
		int comp = data.compareTo(this.data);
		List<E> re = new ArrayList<>();
		if (comp == 0) {
			if (left != null) {

			}
		}
		return null;
	}

	// get the ancestors of a node
	public List<E> ancestors(E data) {
		List<E> ancestors = new ArrayList<>();
		BNode<E> current = this;

		while (current != null) {
			if (current.data.compareTo(data) > 0) {
				ancestors.add(current.data);
				current = current.left;
			} else if (current.data.compareTo(data) < 0) {
				current = current.right;
			} else {
				break;
			}
		}

		return ancestors;

	}

	public void inorder() {
		Stack<BNode<E>> stack = new Stack<>();
		BNode<E> current = this;

		while (true) {
			if (current != null) {
				stack.push(current);
				current = current.left;
			} else if (!stack.isEmpty()) {
				current = stack.pop();
				System.out.println(current.data);
				current = current.right;
			} else {
				break;
			}
		}
	}

	public void preorder() {
		Stack<BNode<E>> stack = new Stack<>();
		BNode<E> current = this;

		while (true) {
			if (current != null) {
				System.out.println(current.data);
				stack.push(current.right);
				current = current.left;
			} else if (!stack.isEmpty()) {
				current = stack.pop();
			} else {
				break;
			}
		}
	}

	public void postorder() {
		Stack<BNode<E>> stack = new Stack<>();
		BNode<E> current = this;

		while (true) {
			if (current != null) {
				stack.push(current);
				current = current.left;
			} else if (!stack.isEmpty() && stack.peek().right != null && stack.peek().right != current) {
				current = stack.peek().right;
			} else {
				BNode<E> temp = stack.pop();
				System.out.println(temp.data);

				if (stack.isEmpty()) {
					break;
				}

				current = null;
			}
		}
	}
}
