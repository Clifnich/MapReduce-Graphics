package com.puzhen.chord;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.TestCase;

public class ClosestPrecedingFingerTest extends TestCase {

	Node node0 = new Node(0);
	Node node1 = new Node(1);
	Node node3 = new Node(3);
	
	public ClosestPrecedingFingerTest(String name) {
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
		assertEquals(node3, node1.closestPrecedingFinger(4));
	}
	
	public void test1() {
		assertEquals(node1, node0.closestPrecedingFinger(2));
	}
	
	public void test2() {
		assertEquals(node3, node3.closestPrecedingFinger(7));
	}
}
