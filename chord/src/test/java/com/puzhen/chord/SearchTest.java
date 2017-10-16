package com.puzhen.chord;

import junit.framework.TestCase;
import java.util.*;

public class SearchTest extends TestCase {

	Node node0 = new Node(0);
	Node node1 = new Node(1);
	Node node3 = new Node(3);
	
	public SearchTest(String name) {
		super(name);
		Map<Integer, Node> map = new HashMap<Integer, Node>();
		List<Node> list = Arrays.asList(node0, node1, node3);
		map.put(0, node0);
		map.put(1, node1);
		map.put(3, node3);
		for (Node node : list)
			node.configure(map);
	}

	public void test0() {
		assertEquals(3, node0.findSuccessor(2).getId());
	}
	
	public void test1() {
		assertEquals(3, node1.findSuccessor(2).getId());
	}
	
	// TODO this method ends in endless loop, fix it!
	public void test2() {
		assertEquals(3, node3.findSuccessor(2).getId());
	}
}
