import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * @author bhartisharma
 *
 */
public class Table {

	private Node root;

	/**
	 * Stores the key/value pair in the address book.
	 * 
	 * @param key
	 * @param value
	 * @return boolean
	 */
	public boolean insert(String key, String value) {
		Node newRoot = insertRecursively(root, key, value);
		if (newRoot.equals(root)) {
			return false;
		}
		this.root = newRoot;
		return true;
	}

	/**
	 * This will insert the key into the node and will also check for balancing of the tree.
	 * @param node
	 * @param key
	 * @param value
	 * @return node
	 */
	private Node insertRecursively(Node node, String key, String value) {

		if (node == null) {
			return new Node(key, value, 0, 1);
		}
		if (0 < node.getKey().compareToIgnoreCase(key)) {
			node.setLchild(insertRecursively(node.getLchild(), key, value));
			if (findHeight(node.getLchild()) - findHeight(node.getRchild()) == 2)
				if (0 < node.getLchild().getKey().compareToIgnoreCase(key))
					node = rotateRight(node);
				else
					node = rotateLeftThenRight(node);
		} else if (0 > node.getKey().compareToIgnoreCase(key)) {
			node.setRchild(insertRecursively(node.getRchild(), key, value));

			if (findHeight(node.getLchild()) - findHeight(node.getRchild()) == -2)
				if (0 > node.getRchild().getKey().compareToIgnoreCase(key))
					node = rotateLeft(node);
				else
					node = rotateRightThenLeft(node);
		} else {
			return node;
		}
		node.setHeight(1 + Math.max(findHeight(node.getLchild()), findHeight(node.getRchild())));
		node.setSize(findSize(node.getLchild()) + findSize(node.getRchild()) + 1);
		node.setBalanceFactor(findHeight(node.getLchild()) - findHeight(node.getRchild()));
		return node;
	}

	/**
	 * This will return the size of the node and if the node is null it will return its size as 0.
	 * @param node
	 * @return integer
	 */
	private int findSize(Node node) {
		if (node == null) {
			return 0;
		}
		return node.getSize();
	}

	/**
	 * This will check the balancing of the node.
	 * @param node
	 * @return node
	 */
	private Node rebalance(Node node) {
		if (node == null) {
			return node;
		}
		rebalance(node.getLchild());
		if (findHeight(node.getLchild()) - findHeight(node.getRchild()) == 2) {
			if (node.getLchild() != null) {
				if (node.getLchild().getBalanceFactor() == -1) {
					node = rotateLeftThenRight(node);
				} else {
					node = rotateRight(node);
				}
			}
		}
		rebalance(node.getRchild());
		if (findHeight(node.getLchild()) - findHeight(node.getRchild()) == -2) {
			if (node.getRchild() != null) {
				if (node.getRchild().getBalanceFactor() == 1) {
					node = rotateRightThenLeft(node);
				} else {
					node = rotateLeft(node);
				}
			}
		}
		node.setHeight(1 + Math.max(findHeight(node.getLchild()), findHeight(node.getRchild())));
		node.setSize(findSize(node.getLchild()) + findSize(node.getRchild()) + 1);
		node.setBalanceFactor(findHeight(node.getLchild()) - findHeight(node.getRchild()));
		return node;
	}

	/**
	 * This will rotate the node first Left then right.
	 * @param node
	 * @return Node
	 */
	private Node rotateLeftThenRight(Node node) {
		node.setLchild(rotateLeft(node.getLchild()));
		return rotateRight(node);
	}

	/**
	 * This will rotate the node right then left.
	 * @param node
	 * @return Node
	 */
	private Node rotateRightThenLeft(Node node) {
		node.setRchild(rotateRight(node.getRchild()));
		return rotateLeft(node);
	}

	/**
	 * This will rotate the node left
	 * @param node
	 * @return node
	 */
	private Node rotateLeft(Node node) {
		Node rotate = node.getRchild();
		node.setRchild(rotate.getLchild());
		rotate.setLchild(node);
		node.setHeight(Math.max(findHeight(node.getLchild()), findHeight(node.getRchild())) + 1);
		rotate.setHeight(Math.max(findHeight(rotate.getLchild()), findHeight(rotate.getRchild())) + 1);
		node.setSize(findSize(node.getLchild()) + findSize(node.getRchild()) + 1);
		rotate.setSize(findSize(rotate.getLchild()) + findSize(rotate.getRchild()) + 1);
		node.setBalanceFactor(findHeight(node.getLchild()) - findHeight(node.getRchild()));
		rotate.setBalanceFactor(findHeight(rotate.getLchild()) - findHeight(rotate.getRchild()));
		return rotate;
	}

	/**
	 * This will rotate the node right
	 * @param node
	 * @return Node
	 */
	private Node rotateRight(Node node) {
		Node rotate = node.getLchild();
		node.setLchild(rotate.getRchild());
		rotate.setRchild(node);
		node.setHeight(Math.max(findHeight(node.getLchild()), findHeight(node.getRchild())) + 1);
		rotate.setHeight(Math.max(findHeight(rotate.getLchild()), findHeight(rotate.getRchild())) + 1);
		node.setSize(findSize(node.getLchild()) + findSize(node.getRchild()) + 1);
		rotate.setSize(findSize(rotate.getLchild()) + findSize(rotate.getRchild()) + 1);
		rotate.setBalanceFactor(findHeight(rotate.getLchild()) - findHeight(rotate.getRchild()));
		node.setBalanceFactor(findHeight(node.getLchild()) - findHeight(node.getRchild()));
		return rotate;
	}

	/**
	 * This will return the height of the node and if the height is null it will return -1.
	 * @param node
	 * @return Integer
	 */
	private int findHeight(Node node) {
		if (node == null) {
			return -1;
		}
		return node.getHeight();
	}

	/**
	 * Looks up the entry with the given key and returns the associated value. If no
	 * entry is found null is returned.
	 * 
	 * @param key
	 * @return String
	 */
	public String lookUp(String key) {
		return lookUpRecurvive(key, root);
	}

	/**
	 * This will recursively look for the value, if not found it will return the
	 * empty string.
	 * 
	 * @param key
	 * @param root
	 * @return String
	 */
	private String lookUpRecurvive(String key, Node root) {
		if (root == null) {
			return "";
		} else if (0 == root.getKey().compareToIgnoreCase(key)) {
			return root.getValue();
		} else if (0 < root.getKey().compareToIgnoreCase(key)) {
			return lookUpRecurvive(key, root.getLchild());
		} else {
			return lookUpRecurvive(key, root.getRchild());
		}
	}

	/**
	 * Deletes the entry with the given key.
	 * 
	 * @param key
	 * @return boolean
	 */
	public boolean deleteContact(String key) {
		if ("" != lookUp(key)) {
			this.root = deleteContactRecursively(key, root);
			this.root = rebalance(root);
			return true;
		}

		return false;
	}

	/**
	 * This will delete the contact using recursion.
	 * 
	 * @param key
	 * @param root
	 * @return Node
	 */
	private Node deleteContactRecursively(String key, Node root) {
		Node temp = root;
		if (temp == null) {
			return null;
		} else if (0 < temp.getKey().compareToIgnoreCase(key)) {
			temp.setLchild(deleteContactRecursively(key, temp.getLchild()));
		} else if (0 > temp.getKey().compareToIgnoreCase(key)) {
			temp.setRchild(deleteContactRecursively(key, temp.getRchild()));
		} else {

			if (temp.getLchild() == null) {

				return root.getRchild();

			} else if (root.getRchild() == null) {

				return root.getLchild();
			}

			Node tempNode = temp.getRchild();

			while (tempNode.getLchild() != null) {
				tempNode = tempNode.getLchild();
			}

			temp.setKey(tempNode.getKey());
			temp.setRchild(deleteContactRecursively(temp.getKey(), temp.getRchild()));
		}

		temp.setHeight(1 + Math.max(findHeight(temp.getLchild()), findHeight(temp.getRchild())));
		temp.setSize(temp.getSize() - 1);
		temp.setBalanceFactor(findHeight(temp.getLchild()) - findHeight(temp.getRchild()));
		return temp;
	}

	/**
	 * 
	 * Replaces the old value associated with with the given key with the newValue
	 * string.
	 * 
	 * @param key
	 * @param newValue
	 * @return boolean
	 */
	public boolean update(String key, String newValue) {
		Node itrTree = root;
		while (itrTree != null) {
			if (itrTree.getKey().compareToIgnoreCase(key) > 0) {
				itrTree = itrTree.getLchild();
			} else if (itrTree.getKey().compareToIgnoreCase(key) < 0) {
				itrTree = itrTree.getRchild();
			} else if (itrTree.getKey().compareToIgnoreCase(key) == 0) {
				itrTree.setValue(newValue);
				return true;
			}
		}
		return false;
	}

	/**
	 * Displays Name/Address for each table entry, the list of entries is sorted by
	 * the keys.
	 * 
	 * @return integer
	 */
	public int displayAll() {
		displayAllRecursively(root);
		System.out.println("Tree size = " + root.getSize());
		System.out.println("Number of contacts in addressbook = " + root.getHeight());
		return root.getSize();
	}

	/**
	 * This is a recursive in-order traversal.
	 * 
	 * @param root
	 */
	private void displayAllRecursively(Node root) {
		if (root == null)
			return;
		displayAllRecursively(root.getLchild());
		System.out.println(root.getKey());
		System.out.println(root.getValue());
		System.out.println("     --- Node height = " + root.getBalanceFactor());
		System.out.println();
		displayAllRecursively(root.getRchild());
	}

	/**
	 * reads the name of a text output file, and will write a list of the table
	 * entries to an the output file.
	 * 
	 * @throws IOException
	 */
	public void save() throws IOException {
		System.out.print("Please enter the file name: ");
		@SuppressWarnings("resource")
		Scanner scn = new Scanner(System.in);
		String key = scn.nextLine();
		FileWriter fWriter = new FileWriter(key + ".txt");
		BufferedWriter writer = new BufferedWriter(fWriter);
		saveRecursively(writer, root);
		writer.newLine();
		writer.close();
	}

	/**
	 * This will save the file recursively.
	 * 
	 * @param writer
	 * @param root
	 * @throws IOException
	 */
	private void saveRecursively(BufferedWriter writer, Node root) throws IOException {
		if (root == null) {
			return;
		}
		writer.write(root.getKey() + "\n");
		writer.write(root.getValue() + "\n");
		saveRecursively(writer, root.getLchild());
		saveRecursively(writer, root.getRchild());
	}
}
