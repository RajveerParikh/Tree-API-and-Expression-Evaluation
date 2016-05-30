package tree;

/**
 * Class for representing simple arithmetic expressions.
 * @author Rajveer Parikh
 * @version Feb 15, 2015
 */
public class Expression {
    Tree<String> expressionTree;
    
    /**
     * Constructs a Tree<String> representing the given arithmetic expression,
     * then verifies that the newly created Tree is valid as an expression.
     * If the Tree is invalid, throws an IllegalArgumentException.<br>
     * Here are the validity rules:<ul>
     * <li>The value of each node must be one of "+", "-", "*", "/",
     *     or a String representing an unsigned integer.</li>
     * <li>If a node has value "+" or "*", it must have two or more children.</li>
     * <li>If a node has value "-" or "/", it must have exactly two children.</li>
     * <li>If a node contains a numeric string, it must be a leaf.</li></ul>
     * Note that the input parameter uses prefix notation, for example:
     * "+ (5 10 -( *(15 20) 25) 30)"
     * @param expression The String representation of the expression to be constructed.
     */
    public Expression(String expression) {
        expressionTree = Tree.parse(expression);
        if (!valid(expressionTree)) {
            throw new IllegalArgumentException("Invalid expression: " + expression);
        }
    }

    /**
     * Tests whether the given Tree represents a valid Expression.
     * @param tree The input tree.
     * @return <code>true</code> iff the Tree is a valid Expression.
     */
    private boolean valid(Tree<String> tree) {
    	
    	if (tree.getValue().equals("+") || tree.getValue().equals("*")){
    		if (tree.getNumberOfChildren() < 2){
    			return false;
    		}
    		else{
    			for (int i = 0; i < tree.getNumberOfChildren(); i++){
    				if (!(valid(tree.getChild(i)))){
    					return false;
    				}
    			}
    		}
    	}
    	else if (tree.getValue().equals("-") || tree.getValue().equals("/")){
    		if (tree.getNumberOfChildren() != 2){
    			return false;
    		}
    		else{
    			for (int i = 0; i < 2; i++){
    				if (!(valid(tree.getChild(i)))){
    					return false;
    				}
    			}
    		}
    	}
    	else{
    		try {
    		    Integer.parseInt(tree.getValue());
    		    if (tree.getNumberOfChildren() != 0){
    		    	return false;
    		    }
    		    return true;
    		  }
    		catch(NumberFormatException nfe) {
    			return false;
    		}	
    	}
    	return true;
    }
    
    /**
     * Evaluates this Expression.
     * @return The value of this Expression.
     */
    public int evaluate() {
        return evaluate(expressionTree);
    }
    
    /**
     * Evaluates the given Tree, which must represent a valid Expression.
     * @return The value of this Expression.
     */
    private int evaluate(Tree<String> tree) {
    	if (tree.getValue().equals("+")){
    		int sum = 0;
    		for (int i = 0; i < tree.getNumberOfChildren(); i++){
    			int operand = evaluate(tree.getChild(i));
    			sum += operand;
    		}
    		return sum;
    	}
    	else if (tree.getValue().equals("-")){
    		int diff = 0;
    		int diff1 = evaluate(tree.getChild(0));
    		int diff2 = evaluate(tree.getChild(1));
    		diff = diff1 - diff2;
    		return diff;
    	}
    	else if (tree.getValue().equals("*")){
    		int product = 1;
    		for (int i = 0; i < tree.getNumberOfChildren(); i++){
    			int operand = evaluate(tree.getChild(i));
    			product *= operand;
    		}
    		return product;
    	}
    	else if (tree.getValue().equals("/")){
    		int div = 1;
    		int div1 = evaluate(tree.getChild(0));
    		int div2 = evaluate(tree.getChild(1));
    		div = div1/div2;
    		return div;
    	}
    	else{
    		return Integer.parseInt(tree.getValue());
    	}
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return toString(expressionTree);
    }
    
    private static String toString(Tree<String> tree) {
    	String result = "";
    	if (("+").equals(tree.getValue())){
    		result = "(";
    		for (int i = 0; i < tree.getNumberOfChildren(); i++){
    			result += toString(tree.getChild(i));
    			if (i != tree.getNumberOfChildren() -1){
        			result += " + ";
    			}
    		}
    		result += ")";
    	}
    	else if (("-").equals(tree.getValue())){
			result += "(" + toString(tree.getChild(0)) + " - " + toString(tree.getChild(1)) + ")";
    	}
    	else if (("*").equals(tree.getValue())){
    		result = "(";
    		for (int i = 0; i < tree.getNumberOfChildren(); i++){
    			result += toString(tree.getChild(i));
    			if (i != tree.getNumberOfChildren() -1){
        			result += " * ";
    			}
    		}	
    		result += ")";
    	}
    	else if (("/").equals(tree.getValue())){
    		result += "(" + toString(tree.getChild(0)) + " / " + toString(tree.getChild(1)) + ")";
    	}
    	else{
    		try{
    			Integer.parseInt(tree.getValue());
    			return tree.getValue();
    		}
    		catch(NumberFormatException nfe) {
    			return null;
    		}
    	}
		return result;
    }
}