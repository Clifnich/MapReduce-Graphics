package com.puzhen.chord;

import junit.framework.TestCase;
import java.util.*;

public class FIndSuccessorTest extends TestCase {

	Node node0 = new Node(0);
	Node node1 = new Node(1);
	Node node3 = new Node(3);
	
	public FIndSuccessorTest(String name) {
		super(name);
		Map<Integer, Node> map = new HashMap<Integer, Node>();
		List<Node> list = Arrays.asList(node0, node1, node3);
		map.put(0, node0);
		map.put(1, node1);
		map.put(3, node3);
		for (Node node : list)
			node.configure(map);
//		node0.setSuccessor(node1);
//		node1.setSuccessor(node3);
//		node3.setSuccessor(node0);
	}
	
	public void test0() {
		System.out.println(node0.getSuccessor().getId());
		assertTrue(node0.getSuccessor().equals(node1));
	}
	
	public void test1() {
		assertTrue(node1.getSuccessor().equals(node3));
	}
	
	public void test2() {
		assertTrue(node3.getSuccessor().equals(node0));
	}

}
