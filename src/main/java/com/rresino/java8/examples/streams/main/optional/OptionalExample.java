package com.rresino.java8.examples.streams.main.optional;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class OptionalExample {

	public static void main(String[] args) {
		//badApproach();
		goodApproach();
	}

	private static void goodApproach() {
		
		Function<Double, Stream<Double>> flatMapper = 
				d -> NewMath.inv(d)
					.flatMap(inv -> NewMath.sqrt(inv))
					.map(sqrt -> Stream.of(sqrt))
					.orElseGet(() -> Stream.empty());
		
		List<Double> result = ThreadLocalRandom.current()
				.doubles(10_000).boxed()
				// .parallel() // Can be parallel!!!
				.flatMap(flatMapper)
				.collect(Collectors.toList());
		
		System.out.println("# result = "+result.size());
	}

	private static void badApproach() {
		// Not parallel
		// Use a external List to save data
		
		List<Double> result = new ArrayList<>();
		
		ThreadLocalRandom.current()
			.doubles(10_000).boxed()
			.forEach(
					d -> NewMath.inv(d)
						.ifPresent(
							inv -> NewMath.sqrt(inv)
								.ifPresent(
										sqrt -> result.add(sqrt)
										)
							)
					);
		
		System.out.println("# result = "+result.size());
		
	}
	
}
