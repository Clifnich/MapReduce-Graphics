package com.puzhen.chord;

import java.util.*;

import org.apache.commons.lang3.tuple.ImmutablePair;

public class Node {

	private int id;
	private Node successor;
	private List<FingerTbRecord> fingerTable;
	
	/**
	 * Two nodes are considered equal iff their ids are identical
	 */
	@Override
	public boolean equals(Object obj) {
		try {
			if (id == ((Node) obj).getId())
				return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}
	
	public Node(int id) {
		this.id = id;
	}
	
	public void configure(Map<Integer, Node> map) {
		successor = findSuccessorInMap(map, id + 1);
		createFingerTable(map);
	}
//	public void configureSuccessor(Map<Integer, Node> map) {
//		successor = findSuccessorInMap(map, id);
//	}
	
	private Node findSuccessorInMap(Map<Integer, Node> map, int start) {
		Node successor = null;
		for (int i = start; i < Math.pow(2, Contract.m); i++) {
			if (map.containsKey(i)) {
				successor = map.get(i);
				break;
			}
		}
		if (successor == null) {
			for (int i = 0; i < start; i++) {
				if (map.containsKey(i)) {
					successor = map.get(i);
					break;
				}
			}
		}
		return successor;
	}
	
	/**
	 * Ask node n to find id's successor
	 * @param id
	 * @return
	 */
	public Node findSuccessor(int id) {
		return findPredecessor(id).getSuccessor();
	}
	
	/**
	 * ask node n to find id's predecessor
	 * @param id
	 * @return
	 */
	public Node findPredecessor(int id) {
		Node n = this;
		while (id <= n.getId() || id > n.getSuccessor().getId()) {
			n = n.closestPrecedingFinger(id);
		}
		return n;
	}
	
	/**
	 * @param id
	 * @return closest finger preceding id
	 */
	public Node closestPrecedingFinger(int id) {
		for (int i = Contract.m; i >= 1; i--) {
			int fingerNode = fingerTable.get(i).getSuccessor().getId();
			if (fingerNode < id && fingerNode > this.id) 
				return fingerTable.get(i).getSuccessor();
		}
		return this;
	}
	
	public void createFingerTable(Map<Integer, Node> map) {
		fingerTable = new ArrayList<FingerTbRecord>(Contract.m + 1);
		// add a dummy record
		fingerTable.add(new FingerTbRecord());
		for (int k = 1; k <= Contract.m; k++) {
			FingerTbRecord record = new FingerTbRecord();
			int start = computeStart(k);
			record.setStart(start);
			record.setInterval(new ImmutablePair<Integer, Integer>(start, computeStart(k+1)));
			Node successor = findSuccessorInMap(map, start);
			record.setSuccessor(successor);
			fingerTable.add(record);
		}
	}
	
	private int computeStart(int k) {
		return (int) ((id + Math.pow(2, k-1)) % Math.pow(2, Contract.m));
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Node getSuccessor() {
		return successor;
	}

	public void setSuccessor(Node successor) {
		this.successor = successor;
	}

	public List<FingerTbRecord> getFingerTable() {
		return fingerTable;
	}

	public void setFingerTable(List<FingerTbRecord> fingerTable) {
		this.fingerTable = fingerTable;
	}
	
	
}
