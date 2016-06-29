package com.rresino.java8.examples.streams.main.parallel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Stream;

public class FineTunning3Example {

	public static void main(String[] args) {
		
		System.out.println("Step1: ");
		
		List<String> values1 = new ArrayList<>();
		Stream.iterate("*", s->s+"*")
			.limit(10000)
			.forEach(s->values1.add(s));
		System.out.println("Size: "+values1.size());
		System.out.println();
		
		System.out.println("Step2: Not thread safe");
		List<String> values2 = new ArrayList<>();
		try {
			Stream.iterate("*", s->s+"*")
				.parallel()
				.limit(10000)
				.forEach(s->values2.add(s));
			System.out.println("Size: "+values2.size());
		} catch (java.lang.ArrayIndexOutOfBoundsException e) {
			System.out.println("ArrayList is NOT a thread safe and have not be run in a parallel operation");
		}
		System.out.println();
		
		System.out.println("Step3: List thread safe");
		List<String> values3 = new CopyOnWriteArrayList<>();
		Stream.iterate("*", s->s+"*")
			.parallel()
			.limit(10000)
			.forEach(s->values3.add(s));
		System.out.println("Size: "+values3.size());
		System.out.println();
		
	}

}
