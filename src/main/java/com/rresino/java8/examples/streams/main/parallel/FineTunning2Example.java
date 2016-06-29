package com.rresino.java8.examples.streams.main.parallel;

import java.util.stream.Stream;

public class FineTunning2Example {

	public static void main(String[] args) {
		
		System.out.println("Step1: ");
		Stream.iterate("*", s->s+"*")
			.limit(6)
			.forEach(System.out::println);
		System.out.println();
		System.out.println();
		
		System.out.println("Step2: ");
		Stream.iterate("*", s->s+"*")
			.parallel()
			.limit(6)
			.forEach(System.out::println);
		System.out.println();
		System.out.println();
		
		System.out.println("Step3: ");
		Stream.iterate("*", s->s+"*")
			.parallel()
			.limit(6)
			.peek(s->System.out.println(s+" in Thread \t\t"+Thread.currentThread().getName()))
			.forEach(System.out::println);
		System.out.println();
		System.out.println();

		
		System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "2");
		System.out.println("Step4: ");
		Stream.iterate("*", s->s+"*")
			.parallel()
			.limit(6)
			.peek(s->System.out.println(s+" in Thread \t\t"+Thread.currentThread().getName()))
			.forEach(System.out::println);
		System.out.println();
		System.out.println();
	}

}
