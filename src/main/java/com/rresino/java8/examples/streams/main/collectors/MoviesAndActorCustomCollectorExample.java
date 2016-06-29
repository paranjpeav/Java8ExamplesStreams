package com.rresino.java8.examples.streams.main.collectors;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.rresino.java8.examples.streams.data.Actor;
import com.rresino.java8.examples.streams.data.Movie;

public class MoviesAndActorCustomCollectorExample {

	public static void main(String[] args) throws IOException {

		Set<Movie> movies = new HashSet<>();
				
		// Load all the data from file
		// http://introcs.cs.princeton.edu/java/data/movies-mpaa.txt
		Stream<String> lines = Files.lines(
				Paths.get("./src/main/resources/files/movies-mpaa.txt")
				);
		
		lines.forEach(
				(String line) -> {
					String[] elements = line.split("/");
					String title = elements[0].substring(0, elements[0].lastIndexOf("(")).trim();
					String releaseYear = elements[0].substring(elements[0].lastIndexOf("(")+1,elements[0].lastIndexOf(")")).trim();
					
					if (releaseYear.contains(",")) {
						// with skip movies with a coma in their title
						return;
					}
					
					Movie movie = new Movie(title, Integer.valueOf(releaseYear));
					
					for (int i = 0; i < elements.length; i++) {
						String[] name = elements[i].split(", ");
						String lastname = name[0].trim();
						String firstName = "";
						
						if (name.length > 1) {
							firstName = name[1].trim();
						}
						
						Actor actor = new Actor(lastname, firstName);
						movie.addActor(actor);
					}
					
					movies.add(movie);
				});

		System.out.println("# movies = " + movies.size());
		
		// We want know the number of actors
		long numberActors = 
			movies.stream()
				.flatMap(mov->mov.getActors().stream()) // Stream<Actor>
				// Bad method
				//.collect(Collectors.toSet()).size();
				.distinct().count();
		
		System.out.println("# actors = " + numberActors);
		
		// The actor who has more films
		Entry<Actor, Long> actorWithMoreFilms = movies.stream()
			.flatMap(mov->mov.getActors().stream()) // Stream<Actor>
			.collect(
						Collectors.groupingBy(Function.identity(), Collectors.counting())	// Map<Actor, Long>
					).entrySet().stream().max(Map.Entry.comparingByValue())		// Optional<Entry<Actor, Long>>
					.get();
		  
		System.out.println("Actor with more films = " + actorWithMoreFilms);
		
		
		// The actor who has more films in one year
		// Map<Release Year, Map<Actor, Num Movies that year>>
		
		Entry<Integer, Entry<Actor, AtomicLong>> actorWithMoreFilmsYear = movies.stream()
				.collect(
							Collectors.groupingBy(
									movie -> movie.getReleaseYear(),
									Collector.of(
										// AtomicLong because we want to increment value per iteration
										() -> new HashMap<Actor, AtomicLong>(), // supplier
										//
										// accumulator 
										(map, movie) -> {
											movie.getActors().forEach(actor -> 
												map.computeIfAbsent(actor, a -> new AtomicLong()).incrementAndGet()
												);
											}, // accumulator
										//
										// combiner , only for parallel operations
										(map1, map2) -> {
											map2.entrySet().forEach(
												entry2 -> map1.merge(
															entry2.getKey(), entry2.getValue(), 
															(al1, al2) -> {
																al1.addAndGet(al2.get());
																return al1;
															}
														)
											);
											return map1;
										},
										//
										// Characteristics
										Collector.Characteristics.IDENTITY_FINISH
									))
						) //Map<Object, HashMap<Actor, AtomicLong>>
				.entrySet().stream().collect(
						Collectors.toMap(
								entry -> entry.getKey(),
								entry -> entry.getValue().entrySet().stream()
									.max(
											Map.Entry.comparingByValue(Comparator.comparing(l->l.get()))
											).get()
								)
						)
				.entrySet().stream().max(
							Map.Entry.comparingByValue(
									Comparator.comparing(entry -> entry.getValue().get())
									)
							).get();
		
			System.out.println("Actor with more films in a year = " + actorWithMoreFilmsYear);
		
	}

}
