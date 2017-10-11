package com.puzhen.chord;

import org.apache.commons.lang3.tuple.Pair;

public class FingerTbRecord {

	private int start;
	private Pair<Integer, Integer> interval;
	private Node successor;
	
	/** Standard Getter and Setters */
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public Pair<Integer, Integer> getInterval() {
		return interval;
	}
	public void setInterval(Pair<Integer, Integer> interval) {
		this.interval = interval;
	}
	public Node getSuccessor() {
		return successor;
	}
	public void setSuccessor(Node successor) {
		this.successor = successor;
	}
}
