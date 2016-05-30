package tree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;

/**
 * Tree API assignment for CIT594, Spring 2015.
 * 
 * @author Rajveer Parikh
 * @param <V>
 *            The type of value that can be held in each Tree node.
 */
public class Tree<V> implements Iterable<Tree<V>> {
	private V value;
	private ArrayList<Tree<V>> children;

	/**
	 * Constructs a Tree with the given value in the root node, having the given
	 * children.
	 * 
	 * @param value
	 *            The value to be put in the root.
	 * @param children
	 *            The immediate children of the root.
	 */
	@SuppressWarnings("unchecked")
	public Tree(V value, Tree<V>... children) {
		this.value = value;
		this.children = new ArrayList<Tree<V>>();
		for (Tree<V> tree : children) {
			this.children.add(tree);
		}
	}

	/**
	 * Sets the value in this node.
	 * 
	 * @param value
	 *            The value to be stored in this node.
	 */
	public void setValue(V value) {
		this.value = value;
	}

	/**
	 * Returns the value in this node.
	 * 
	 * @return The value in this node.
	 */
	public V getValue() {
		return this.value;
	}

	/**
	 * Adds the child as the new <code>index</code>'th child of this Tree;
	 * subsequent nodes are "moved over" as necessary to make room for the new
	 * child.
	 * 
	 * @param index
	 *            The position in which to put the new child.
	 * @param child
	 *            The child to be added to this node.
	 * @throws IllegalArgumentException
	 *             If the operation would create a circular Tree.
	 */
	public void addChild(int index, Tree<V> child) {
		if (index < 0 || index > this.children.size()) {
			throw new IndexOutOfBoundsException();
		}
		if (child.contains(this)) {
			throw new IllegalArgumentException();
		} else {
			this.children.add(index, child);
		}
	}

	/**
	 * Adds the child as the new last child of this node.
	 * 
	 * @param child
	 *            The child to be added to this node.
	 */
	public void addChild(Tree<V> child) {
		this.children.add(child);
	}

	/**
	 * Adds the children to this node, after the current children.
	 * 
	 * @param children
	 *            The nodes to be added as children of this node.
	 * @throws IllegalArgumentException
	 *             If the operation would create a circular Tree.
	 */
	@SuppressWarnings("unchecked")
	public void addChildren(Tree<V>... children) {
		for (Tree<V> child : children) {
			if (child.contains(this)) {
				throw new IllegalArgumentException();
			} else {
				this.children.add(child);
			}
		}
	}

	/**
	 * Returns the number of children that this node has.
	 * 
	 * @return A count of this node's immediate children.
	 */
	public int getNumberOfChildren() {
		return this.children.size();
	}

	/**
	 * Returns the <code>index</code>'th child of this node.
	 * 
	 * @param index
	 *            The position of the child that is to be returned.
	 * @return The child at that position.
	 * @throws IndexOutOfBoundsException
	 *             If <code>index</code> is negative or is greater than or equal
	 *             to the current number of children of this node.
	 */
	public Tree<V> getChild(int index) {
		if (index < 0 || index >= this.children.size()) {
			throw new IndexOutOfBoundsException();
		} else {
			return this.children.get(index);
		}
	}

	/**
	 * Returns an iterator for the children of this node.
	 * 
	 * @return An iterator for this node's immediate children.
	 */
	@Override
	public Iterator<Tree<V>> iterator() {
		return children.iterator();
	}

	/**
	 * Searchs this Tree for a node that is == to <code>node</code>, and returns
	 * <code>true</code> if found, <code>false</code> otherwise.
	 * 
	 * @param node
	 *            The node to be searched for.
	 * @return <code>true</code> iff the node is found.
	 */
	boolean contains(Tree<V> node) {
		if (this == node) {
			return true;
		} else {
			if (this.children.size() > 0) {
				for (Tree<V> child : children) {
					Boolean ans = (child.contains(node));
					if (ans == true) {
						return true;
					}
				}
			}
		}
		return false;
	}

	/**
	 * Returns a one-line string representing this tree. The form of the output
	 * is:<br>
	 * <code>value(child, child, ..., child)</code>.
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String result = "";
		if (this.children.size() == 0){
			result += this.value.toString();
		}
		else{
			result += this.value.toString();
			result += "(";
			for (Tree<V> child : children){
				if ((")").equals(child.getValue())){
					result += child.getValue();
				}
				else if (result.length() != 0 && result.charAt(result.length() - 1) != 'C'){
					result += " ";
					result += child.toString();
				}
			}
			result += ")";
		}
		return result;
	}
	
	/**
	 * Prints this tree as an indented structure.
	 */
	public void print() {
		print(this, "");
	}

	/**
	 * Prints the given tree as an indented structure, with the given node
	 * indented by the given amount.
	 * 
	 * @param node
	 *            The root of the tree or subtree to be printed.
	 * @param indent
	 *            The amount to indent the root.
	 */
	private static void print(Tree<?> node, String indent) {
		if (node == null)
			return;
		System.out.println(indent + node.value);
		indent += "   ";
		for (int i = 0; i < node.children.size(); i++) {
			print(node.getChild(i), indent);
		}
	}

	/**
	 * Tests whether the input argument is a Tree having the same shape and
	 * containing the same values as this Tree.
	 * 
	 * @param obj
	 *            The object to be compared to this Tree.
	 * @return <code>true</code> if the object is equals to this Tree,
	 *         <code>false</code> otherwise.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Tree)) {
			return false;
		}
		Tree<?> that = (Tree<V>) obj;
		if (this.value == null) {
			return that.value == null;
		}
		if (!this.value.equals(that.value)) {
			return false;
		}
		return (this.value.equals(that.value) && this.children
				.equals(that.children));
	}

	/**
	 * Tests whether two values are equal (either == or <code>equals(obj)</code>
	 * ), when one or both values may be <code>null</code>.
	 * 
	 * @param object1
	 *            The first object to be tested.
	 * @param object2
	 *            The second object to be tested.
	 * @return <code>true</code> iff the objects are equal.
	 */
	@SuppressWarnings("unused")
	private boolean equals(Object object1, Object object2) {
		// Helper method for equals(Object)
		if (object1 == object2)
			return true;
		if (object1 == null)
			return false;
		return object1.equals(object2);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		int hashCode = 0;
		hashCode = hashCodeHelper(this, hashCode);
		return hashCode;
	}
	
	public int hashCodeHelper (Tree<V> tree, int hashCode){
		if (tree.children.size() == 0){
			for (int i = 0; i < tree.toString().length(); i++){
				char treeStringCharacter = tree.toString().charAt(i);
				hashCode += (int)(treeStringCharacter)*(i+1);
			}

			return hashCode;
		}
		else{
			for (Tree<V> child: tree.children){
				return hashCodeHelper(child, hashCode);
			}
		}
		return hashCode;
	}

	/**
	 * Creates a Tree of Strings from the input argument, which must have the
	 * same form as that produced by the <code>toString()</code> method of this
	 * class, namely, <code>value(child, child, ..., child)</code>.
	 * 
	 * @param input
	 *            A representation of a Tree.
	 * @return The Tree represented by the input string.
	 * @throws IllegalArgumentException
	 *             If the input string is malformed.
	 */
	public static Tree<String> parse(String input) {
		PushbackStringTokenizer tokenizer = new PushbackStringTokenizer(input);
		Tree<String> tree = parse(tokenizer);
		if (tokenizer.hasNext()) {
			throw new IllegalArgumentException("Tokenizer error at: "
					+ tokenizer.next());
		}
		return tree;
	}

	/**
	 * Uses the input <code>tokenizer</code> to read and return a single Tree.
	 * Additional tokens are ignored.
	 * 
	 * @param tokenizer
	 *            The source of tokens from which to build a Tree.
	 * @return A Tree built from the string being tokenized.
	 * @throws IllegalArgumentException
	 *             If the tokenized string is malformed.
	 */
	static Tree<String> parse(PushbackStringTokenizer tokenizer)
			throws IllegalArgumentException {
		if (tokenizer.hasNext()) {
			String token = tokenizer.next();
			if (")".equals(token)){
				return null;
			}
			@SuppressWarnings("unchecked")
			Tree<String> parseTree = new Tree<String>(token);
			token = tokenizer.next();
			if (("(").equals(token)) {
				Tree<String> recurseValue = parse(tokenizer);
				while (recurseValue != null) {
					parseTree.addChild(recurseValue);
					if (!tokenizer.hasNext()){
						throw new IllegalArgumentException();
					}
					
					String s = tokenizer.next();
					if (s.equals(")")){
						break;
					}
					tokenizer.pushBack(s);
					recurseValue = parse(tokenizer);
				}
			}
			else{
				tokenizer.pushBack(token);
				return parseTree;
			}
			return parseTree;
		}
		return null;
	}
	

	// ---------------------------------------------------------------------

	/**
	 * A Tokenizer that returns one of four things: a left parenthesis, a right
	 * parenthesis, a sequence of non-whitespace, non-parenthesis characters, or
	 * <code>null</code> if there are no more tokens.
	 * 
	 * @author David Matuszek
	 */
	static class PushbackStringTokenizer {
		private StringTokenizer tokenizer;
		private String pushedValue = null;

		/**
		 * Constructs a tokenizer for the input that uses whitespace and
		 * parentheses as delimiters.
		 * 
		 * @param input
		 *            The string to be tokenized.
		 */
		PushbackStringTokenizer(String input) {
			tokenizer = new StringTokenizer(input, " \t\n\r\f()", true);
			pushedValue = null;
		}

		/**
		 * Tests if there are more tokens in the input string.
		 * 
		 * @return <code>true</code> if there are more tokens,
		 *         <code>false</code> otherwise.
		 */
		boolean hasNext() {
			return pushedValue != null || tokenizer.hasMoreTokens();
		}

		/**
		 * Returns the next token (or a pushed back token, if there is one.) A
		 * token may be a left parenthesis, a right parenthesis, or any sequence
		 * of other, non-whitespace characters.
		 * <p>
		 * Unlike most tokenizers, this tokenizer will return <code>null</code>
		 * if there are no remaining tokens.
		 * 
		 * @return The next token, or <code>null</code> if there are no more.
		 */
		String next() {
			String temp = pushedValue;
			if (temp == null && tokenizer.hasMoreTokens()) {
				temp = tokenizer.nextToken().trim();
			}
			pushedValue = null;
			// skip whitespace tokens
			if (temp != null && temp.length() == 0) {
				temp = next();
			}
			return temp;
		}

		/**
		 * Returns a token to this tokenizer so that it will be returned by the
		 * next call to the <code>next()</code> method.
		 * 
		 * @param token
		 *            The token to be reused.
		 */
		void pushBack(String token) {
			pushedValue = token;
		}
	}
}
