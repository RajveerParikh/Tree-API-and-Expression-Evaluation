package tree;

import static org.junit.Assert.*;
import static tree.Tree.*;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

public class TreeTest {
	Tree<Integer> treeChild1;
	Tree<Integer> treeChild2;
	Tree<Integer> treeChild3;
	Tree<Integer> treeChild4;
	Tree<Integer> treeChild5;
	Tree<Integer> treeChild6;
	Tree<Integer> testTree;
	Tree<Integer> testTree2;
	Tree<Integer> testTree3;
	Tree<Integer> testTree4;
	Tree<Integer> testTree5;
	Tree<Integer> testTree6;
	Tree<String> testTree7;
	Tree<String> testTree8;
	Tree<String> testTree9;
	Tree<String> testTree10;
	Tree<String> testTree11;
	Tree<Integer> testTree12;
	Tree<Integer> testTree13;
	Tree<Integer> testTree14;
	Tree<String> testTree15;
	Tree<String> testTree16;
	Tree<String> testTree17;
	Tree<String> testTree18;
	Tree<String> testTree19;
	Tree<String> testTree20;
	Tree<String> testTree21;
	Tree<String> testTree22;
	Tree<String> testTree23;

    @SuppressWarnings("unchecked")
	@Before
    public void setUp() throws Exception {
    	treeChild1 = new Tree<Integer>(2);
    	treeChild2 = new Tree<Integer>(3);
    	treeChild3 = new Tree<Integer>(4);
    	treeChild4 = new Tree<Integer>(5);
    	treeChild5 = new Tree<Integer>(6);
    	treeChild6 = new Tree<Integer>(5);
    	testTree = new Tree<Integer>(1, treeChild1, treeChild2);
    	testTree2 = new Tree<Integer>(1, treeChild1, treeChild2);
    	testTree3 = testTree;
    	testTree4 = new Tree<Integer>(6, treeChild3, treeChild4);
    	testTree5 = new Tree<Integer>(6, treeChild3, treeChild6);
    	testTree6 = new Tree<Integer>(1, treeChild2, treeChild1);
    	testTree7 = new Tree<String>("One");
    	testTree8 = new Tree<String>("Two");
    	testTree9 = new Tree<String>("Three", testTree7, testTree8);
    	testTree10 = new Tree<String>("Three");
    	testTree11 = new Tree<String>("One", testTree8, testTree10);
    	testTree12 = new Tree<Integer>(2, treeChild2);
    	testTree13 = new Tree<Integer>(1, testTree12);
    	testTree15 = new Tree<String>("1");
    	testTree16 = new Tree<String>("2");
    	testTree17 = new Tree<String>("3");
    	testTree15.addChildren(testTree16, testTree17);
    	testTree18 = new Tree<String>("7");
    	testTree19 = new Tree<String>("8");
    	testTree20 = new Tree<String>("9");
    	testTree21 = new Tree<String>("10");
    	testTree22 = new Tree<String>("11");
    	testTree19.addChildren(testTree20, testTree21);
    	testTree18.addChildren(testTree19, testTree22);
    	
    	
    	
    }

    @Test
    public final void testHashCode() {
    	assertNotEquals(testTree.hashCode(), testTree6.hashCode());
    	assertNotEquals(testTree.hashCode(), testTree9.hashCode());
    	assertNotEquals(testTree6.hashCode(), testTree9.hashCode());
    	assertNotEquals(testTree.hashCode(), testTree11.hashCode());
    	assertNotEquals(testTree.hashCode(), testTree13.hashCode());
    	assertEquals(testTree.hashCode(), testTree2.hashCode());
    	assertEquals(testTree.hashCode(), testTree3.hashCode());
    }

    @Test
    public final void testTree() {
    	assertEquals (1, (int)testTree.getValue());
    	assertEquals (2, (int)testTree.getNumberOfChildren());
    	testTree.addChild(treeChild3);
    	assertEquals (3, (int)testTree.getNumberOfChildren());
    	assertEquals("Three", testTree9.getValue());
    	assertEquals(2, (int)testTree9.getNumberOfChildren());
    	
    }

    @Test
    public final void testSetValue() {
    	testTree.setValue(6);
    	assertEquals(6, (int)testTree.getValue());
    	testTree9.setValue("Nine");
    	assertEquals("Nine", testTree9.getValue());
    }

    @Test
    public final void testGetValue() {
    	assertEquals(1, (int)testTree.getValue());
        
    }

    @Test
    public final void testAddChildIntTreeOfV() {
    	assertEquals(2, (int)testTree.getNumberOfChildren());
    	testTree.addChild(1,treeChild3);
        assertEquals(3, (int)testTree.getNumberOfChildren());
        assertEquals(treeChild3, testTree.getChild(1));
    }

    @Test
    public final void testAddChildTreeOfV() {
    	assertEquals(2, (int)testTree.getNumberOfChildren());
    	testTree.addChild(treeChild3);
        assertEquals(3, (int)testTree.getNumberOfChildren());
        assertEquals(treeChild3, testTree.getChild(2));
    }

    @SuppressWarnings("unchecked")
	@Test
    public final void testAddChildren() {
    	assertEquals(2, (int)testTree.getNumberOfChildren());
    	testTree.addChildren(treeChild3, treeChild4, treeChild5);
    	assertEquals(5, (int)testTree.getNumberOfChildren());
    	assertEquals(treeChild3, testTree.getChild(2));
    	assertEquals(treeChild4, testTree.getChild(3));
    	assertEquals(treeChild5, testTree.getChild(4));
    }

    @SuppressWarnings("unchecked")
	@Test
    public final void testGetNumberOfChildren() {
    	assertEquals(2, testTree.getNumberOfChildren());
    	testTree.addChildren(treeChild3, treeChild4, treeChild5);
    	assertEquals(5, (int)testTree.getNumberOfChildren());
    }

    @SuppressWarnings("unchecked")
	@Test
    public final void testGetChild() {
    	testTree.addChildren(treeChild3, treeChild4, treeChild5);
    	assertEquals(5, (int)testTree.getNumberOfChildren());
    	assertEquals(treeChild3, testTree.getChild(2));
    	assertEquals(treeChild4, testTree.getChild(3));
    	assertEquals(treeChild5, testTree.getChild(4));
    }

    @Test
    public final void testIterator() {
    	Iterator<Tree<Integer>> iter = testTree.iterator();
    	int i = 0;
    	while(iter.hasNext()){
    		assertEquals(iter.next(), testTree.getChild(i));
    		i++;
    	}
    }

    @Test
    public final void testContains() {
    	assertTrue(testTree.contains(treeChild1));
    	assertTrue(testTree.contains(treeChild2));
    	assertFalse(testTree.contains(treeChild3));
    	testTree.addChild(treeChild3);
    	assertTrue(testTree.contains(treeChild3));
    	assertFalse(testTree.contains(treeChild4));
    	
    }

    @Test
    public final void testToString() {
    	String expected = "1( 2 3)";
    	String expected2 = "6( 4 5)";
    	String expected3 = "7( 8( 9 10) 11)";
    	assertEquals(expected, testTree.toString());
    	assertEquals(expected2, testTree4.toString());
    	assertEquals(expected3, testTree18.toString());
    }

    @Test
    public final void testEqualsObject() {
    	assertTrue(testTree.equals(testTree2));
    	assertTrue(testTree.equals(testTree3));
    	assertTrue(testTree4.equals(testTree5));
    	assertFalse(testTree2.equals(testTree4));

    }

    @Test
    public final void testParseString() {
    	Tree<String> parseTree = parse("1(2 3)");
    	Tree<String> parseTree2 = parse("7( 8 ( 9 10)11)");
    	assertTrue(testTree15.equals(parseTree));
    	assertTrue(testTree18.equals(parseTree2));
    }
    
    @Test(expected = IllegalArgumentException.class) 
    public final void testParseString2() {
    	String input = "7( 8 ( 9 10)11))";
    	Expression e = new Expression(input);
    	System.out.println(e.toString());	
    }

}
