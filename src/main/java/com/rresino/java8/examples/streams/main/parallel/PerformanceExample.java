package com.rresino.java8.examples.streams.main.parallel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PerformanceExample {

	public static void main(String[] args) {
		
		final int ELEMENTS = 10_000_000; 
		
		long start, end;
		start = System.currentTimeMillis();
		
		List<Long> list1 = new ArrayList<>(ELEMENTS);
		for (int i = 0; i < ELEMENTS; i++) {
			list1.add(ThreadLocalRandom.current().nextLong());			
		}
		
		end = System.currentTimeMillis();
		System.out.println("Method 1: "+(end-start));

		start = System.currentTimeMillis();
		
		Stream<Long> stream = Stream.generate(()->ThreadLocalRandom.current().nextLong());
		List<Long> list2 = stream.limit(ELEMENTS).collect(Collectors.toList()); 
		
		end = System.currentTimeMillis();
		System.out.println("Method 2: "+(end-start));

		start = System.currentTimeMillis();
		
		Stream<Long> stream2 = ThreadLocalRandom.current().longs(ELEMENTS).mapToObj(Long::new);
		List<Long> list3 = stream2.collect(Collectors.toList()); 
		
		end = System.currentTimeMillis();
		System.out.println("Method 3: "+(end-start));
		
		start = System.currentTimeMillis();
		
		Stream<Long> stream3 = Stream.generate(()->ThreadLocalRandom.current().nextLong()).parallel();
		List<Long> list4 = stream3.limit(ELEMENTS).collect(Collectors.toList()); 
		
		end = System.currentTimeMillis();
		System.out.println("Method 2 Parallel: "+(end-start));

		start = System.currentTimeMillis();
		
		Stream<Long> stream4 = ThreadLocalRandom.current().longs(ELEMENTS).parallel().mapToObj(Long::new);
		List<Long> list5 = stream4.collect(Collectors.toList()); 
		
		end = System.currentTimeMillis();
		System.out.println("Method 3 Parallel: "+(end-start));
	}

}
