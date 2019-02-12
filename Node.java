
/**
 * @author bhartisharma
 *
 */
public class Node {
	private String key;
	private String value;
	private Node lchild;
	private Node rchild;
	private int height;
	private int size;
	private int balanceFactor;

	/**
	 * Getter method for key
	 * 
	 * @return String
	 */
	public String getKey() {
		return key;
	}

	/**
	 * setter method for key
	 * 
	 * @param key
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * This method will get the value
	 * 
	 * @return String
	 */
	public String getValue() {
		return value;
	}

	/**
	 * This will set the value
	 * 
	 * @param value
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * This method will get the left node
	 * 
	 * @return Node
	 */
	public Node getLchild() {
		return lchild;
	}

	/**
	 * This method will set the left node
	 * 
	 * @param lchild
	 */
	public void setLchild(Node lchild) {
		this.lchild = lchild;
	}

	/**
	 * constructor method
	 * 
	 * @param key
	 * @param value
	 * @param height
	 * @param size
	 */
	Node(String key, String value, int height, int size) {
		this.key = key;
		this.value = value;
		this.rchild = null;
		this.lchild = null;
		this.size = size;
		this.height = height;
		this.balanceFactor = 0;
	}

	/**
	 * This method will return the right node
	 * 
	 * @return Node
	 */
	public Node getRchild() {
		return rchild;
	}

	/**
	 * This method will set the right node
	 * 
	 * @param rchild
	 */
	public void setRchild(Node rchild) {
		this.rchild = rchild;
	}

	/**
	 * This method will return the height of the node
	 * 
	 * @return int
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * This method will set the height of the node
	 * 
	 * @param height
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * This method will get the size of the node
	 * 
	 * @return int
	 */
	public int getSize() {
		return size;
	}

	/**
	 * This method will set the size of the node
	 * 
	 * @param size
	 */
	public void setSize(int size) {
		this.size = size;
	}

	public int getBalanceFactor() {
		return balanceFactor;
	}

	public void setBalanceFactor(int balanceFactor) {
		this.balanceFactor = balanceFactor;
	}
}
