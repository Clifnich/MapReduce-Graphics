package com.puzhen.chord;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.TestCase;

public class FingerTableTest extends TestCase {

	Node node0 = new Node(0);
	Node node1 = new Node(1);
	Node node3 = new Node(3);
	
	public FingerTableTest(String name) {
		super(name);
		Map<Integer, Node> map = new HashMap<Integer, Node>();
		List<Node> list = Arrays.asList(node0, node1, node3);
		map.put(0, node0);
		map.put(1, node1);
		map.put(3, node3);
		for (Node node : list)
			node.configure(map);
	}
	
	public void testRecord01() {
		List<FingerTbRecord> fingerTable = node0.getFingerTable();
		FingerTbRecord record = fingerTable.get(1);
		assertEquals(1, record.getStart());
		assertEquals(1, (int)record.getInterval().getLeft());
		assertEquals(2, (int)record.getInterval().getRight());
		assertEquals(node1, record.getSuccessor());
	}
	
	public void testRecord02() {
		List<FingerTbRecord> fingerTable = node0.getFingerTable();
		FingerTbRecord record = fingerTable.get(2);
		assertEquals(2, record.getStart());
		assertEquals(2, (int)record.getInterval().getLeft());
		assertEquals(4, (int)record.getInterval().getRight());
		assertEquals(node3, record.getSuccessor());
	}
	
	public void testRecord03() {
		List<FingerTbRecord> fingerTable = node0.getFingerTable();
		FingerTbRecord record = fingerTable.get(3);
		assertEquals(4, record.getStart());
		assertEquals(4, (int)record.getInterval().getLeft());
		assertEquals(0, (int)record.getInterval().getRight());
		assertEquals(node0, record.getSuccessor());
	}
	
	public void testRecord11() {
		List<FingerTbRecord> fingerTable = node1.getFingerTable();
		FingerTbRecord record = fingerTable.get(1);
		assertEquals(2, record.getStart());
		assertEquals(2, (int)record.getInterval().getLeft());
		assertEquals(3, (int)record.getInterval().getRight());
		assertEquals(node3, record.getSuccessor());
	}
	
	public void testRecord12() {
		List<FingerTbRecord> fingerTable = node1.getFingerTable();
		FingerTbRecord record = fingerTable.get(2);
		assertEquals(3, record.getStart());
		assertEquals(3, (int)record.getInterval().getLeft());
		assertEquals(5, (int)record.getInterval().getRight());
		assertEquals(node3, record.getSuccessor());
	}
	
	public void testRecord13() {
		List<FingerTbRecord> fingerTable = node1.getFingerTable();
		FingerTbRecord record = fingerTable.get(3);
		assertEquals(5, record.getStart());
		assertEquals(5, (int)record.getInterval().getLeft());
		assertEquals(1, (int)record.getInterval().getRight());
		assertEquals(node0, record.getSuccessor());
	}
	
	public void testRecord31() {
		List<FingerTbRecord> fingerTable = node3.getFingerTable();
		FingerTbRecord record = fingerTable.get(1);
		assertEquals(4, record.getStart());
		assertEquals(4, (int)record.getInterval().getLeft());
		assertEquals(5, (int)record.getInterval().getRight());
		assertEquals(node0, record.getSuccessor());
	}
	
	public void testRecord32() {
		List<FingerTbRecord> fingerTable = node3.getFingerTable();
		FingerTbRecord record = fingerTable.get(2);
		assertEquals(5, record.getStart());
		assertEquals(5, (int)record.getInterval().getLeft());
		assertEquals(7, (int)record.getInterval().getRight());
		assertEquals(node0, record.getSuccessor());
	}
	
	public void testRecord33() {
		List<FingerTbRecord> fingerTable = node3.getFingerTable();
		FingerTbRecord record = fingerTable.get(3);
		assertEquals(7, record.getStart());
		assertEquals(7, (int)record.getInterval().getLeft());
		assertEquals(3, (int)record.getInterval().getRight());
		assertEquals(node0, record.getSuccessor());
	}

}
