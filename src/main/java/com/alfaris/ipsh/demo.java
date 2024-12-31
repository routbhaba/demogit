package com.alfaris.ipsh;

import java.util.HashMap;
import java.util.Map;

public class demo {
	
	private Map<String, Integer> map1=new HashMap<>();
	demo() {
		for(int i=1;i<=10;i++) {
			map1.put("val"+i, i);
		}
		for(Map.Entry<String,Integer> entry : map1.entrySet()) {
			System.out.println(entry.getKey()+":"+entry.getValue());
		}
	}
	
	
	
}
