
public class BST<E extends Comparable<E>> {

	private BNode<E> root;

	public BST() {
		this.root = null;
	}

	public void add(E e) {
		if (root == null) {
			root = new BNode<>(e);
		} else
			root.add(e);
	}
	

}