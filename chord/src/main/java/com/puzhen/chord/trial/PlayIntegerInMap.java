package com.puzhen.chord.trial;

import java.util.*;

public class PlayIntegerInMap {

	public static void main(String[] args) {
		Map<Integer, String> map = new HashMap<Integer, String>();
		map.put(1, "hi");
		System.out.println("true: " + map.containsKey(1));
	}
}
