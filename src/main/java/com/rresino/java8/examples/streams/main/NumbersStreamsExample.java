package com.rresino.java8.examples.streams.main;

import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.stream.IntStream;

import com.rresino.java8.examples.streams.utils.Generator;

public class NumbersStreamsExample {

	public static void main(String[] args) {
		
		List<Integer> numbers = Generator.getListIntegers(100);
		
		IntStream intStream1 = IntStream.of(1,2,6,8);
		
		IntStream intStream2 = numbers.stream().mapToInt(item->item);
		
		// intStream1.boxed() -> to Stream<Int> again 
		
		System.out.println(intStream1.sum());
		System.out.println(IntStream.of(1,2,6,8).average());
		System.out.println(IntStream.of(1,2,6,8).max());
		System.out.println(IntStream.of(1,2,6,8).min());
		
		IntSummaryStatistics summary = intStream2.summaryStatistics();
		
		System.out.println(summary.toString());

	}

}
