package com.rresino.java8.examples.streams.main.parallel;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FineTunningExample {

	public static void main(String[] args) {
		// To set max thread per all parallel operations
		//System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "2");
		
		ForkJoinPool forkJoinPool = new ForkJoinPool(2);
		try {
			forkJoinPool.submit(() -> {
				// implements Callable<T>
				Stream<Long> stream = Stream.generate(()->ThreadLocalRandom.current().nextLong()).parallel();
				List<Long> list = stream.limit(10_000_000).collect(Collectors.toList());
				
			}).get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
