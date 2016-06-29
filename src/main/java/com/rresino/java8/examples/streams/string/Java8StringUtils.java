package com.rresino.java8.examples.streams.string;

import java.util.StringJoiner;

public class Java8StringUtils {

	public static void main(String[] args) {
		System.out.println();
		System.out.println("String to Steam<char> Example:");
		System.out.println();

		String str = "Hello world!!!";
		str.chars().forEach(s -> System.out.println(" - " + (char) s));

		System.out.println();
		System.out.println();

		System.out.println();
		System.out.println("StringJoiner Example :");
		System.out.println();

		String[] numbers = { "one", "two", "three", "four", "five" };

		StringJoiner joiner1 = new StringJoiner(",");
		joiner1.add(numbers[0]).add(numbers[1]).add(numbers[2]).add(numbers[3]).add(numbers[4]);
		System.out.println("Joiner1: " + joiner1.toString());

		StringJoiner joiner2 = new StringJoiner(", ", "[", "]");
		for (String strNum : numbers) {
			joiner2.add(strNum);
		}
		System.out.println("Joiner2: " + joiner2.toString());

		StringJoiner joiner3 = new StringJoiner(", ", "[", "]");
		System.out.println("Joiner3: " + joiner3.toString());

		StringJoiner joiner4 = new StringJoiner(", ", "[", "]");
		joiner4.add(numbers[0]);
		System.out.println("Joiner4: " + joiner4.toString());

		System.out.println();
		System.out.println("String Join Example :");
		System.out.println();

		String strJoin = String.join(",", numbers);
		System.out.println("String Join 1: " + strJoin);
	}

}
