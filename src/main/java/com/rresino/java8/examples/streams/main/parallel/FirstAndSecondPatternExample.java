package com.rresino.java8.examples.streams.main.parallel;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.rresino.java8.examples.streams.data.User;
import com.rresino.java8.examples.streams.utils.UserGenerator;

public class FirstAndSecondPatternExample {

	public static void main(String[] args) {

		final List<User> users = UserGenerator.streamOf(100).collect(Collectors.toList());
		
		System.out.println("Parallel First Pattern:");
		
		users.parallelStream()
			.filter(p->p.getAge()>18)
			.forEach(System.out::println);
		
		System.out.println("Parallel Second Pattern:");
		
		users.stream()
			.parallel()
			.filter(p->p.getAge()>18)
			.forEach(System.out::println);

		System.out.println("But not garantees about order");
		
		System.out.println("Sorted using:");
		
		Comparator<User> comparatorUserAge = (e1, e2) -> Integer.compare(
	            e1.getAge(), e2.getAge());
		
		users.stream()
			.parallel()
			.filter(p->p.getAge()>18)
			.sorted(comparatorUserAge)
			.forEach(System.out::println);
	}

}
