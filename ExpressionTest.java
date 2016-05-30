package tree;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ExpressionTest {
	Tree<String> testTree;
	Tree<String> treeChild1;
	Tree<String> treeChild2;
	Expression test;
	Expression test2;
	Expression test3;
	Expression test4;
	Expression test5;
	
    @Before
    public void setUp() throws Exception {
    	test = new Expression("+(5 10 - (*(15 20) 25) 30)");
    	test2 = new Expression("+(5 10)");
    	test3 = new Expression("-(10 5)");
    	test4 = new Expression("/(10 5)");
    	test5 = new Expression("/(10 +(2 3))");
    }

    @Test(expected = IllegalArgumentException.class)
    public final void testExpression() {
    	String input = "/(2 20 10)";
    	Expression e = new Expression(input);
    	System.out.println(e.toString());	
    }
    
    @Test(expected = IllegalArgumentException.class) 
    public final void testExpression2() {
    	String input = "-(2 20 10)";
    	Expression e = new Expression(input);
    	System.out.println(e.toString());	
    }

    @Test(expected = IllegalArgumentException.class) 
    public final void testExpression3() {
    	String input = "-($ 20 10)";
    	Expression e = new Expression(input);
    	System.out.println(e.toString());	
    }

    @Test(expected = IllegalArgumentException.class) 
    public final void testExpression4() {
    	String input = "1(5 10)";
    	Expression e = new Expression(input);
    	System.out.println(e.toString());	
    }
    
    @Test
    public final void testEvaluate() {
    	assertEquals(320, test.evaluate());
    	assertEquals(15, test2.evaluate());
    	assertEquals(5, test3.evaluate());
    	assertEquals(2, test4.evaluate());
    	assertEquals(2, test5.evaluate());
    }

    @Test
    public final void testToString() {
    	String string1 = "(5 + 10 + ((15 * 20) - 25) + 30)";
    	String string2 = "(5 + 10)";
    	String string3 = "(10 - 5)";
    	String string4 = "(10 / 5)";
    	String string5 = "(10 / (2 + 3))";
    	assertEquals(string1, test.toString());
    	assertEquals(string2, test2.toString());
    	assertEquals(string3, test3.toString());
    	assertEquals(string4, test4.toString());
    	assertEquals(string5, test5.toString());
    }

}
