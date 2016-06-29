package com.rresino.java8.examples.streams.main.collectors;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.rresino.java8.examples.streams.utils.Generator;

public class ScrabbleExample {

	/*
	1 punto: A ×12, E ×12, I ×6, L ×4, N ×5, O ×9, R ×5, S ×6, T ×4, U ×5
	2 puntos: D ×5, G ×2
	3 puntos: B ×2, C ×4, M ×2, P ×2
	4 puntos: F ×1, H ×2, V ×1, Y ×1
	5 puntos: Ch ×1, Q ×1
	8 puntos: J ×1, LL ×1, Ñ ×1, RR ×1, X ×1
	10 puntos: Z ×1
	*/
	// Spanish version
	private static HashMap<Character, Integer> getSpanishScrabblePoint() {
		HashMap<Character, Integer> values = new HashMap<>();
		values.put('a', 1);
		values.put('e', 1);
		values.put('i', 1);
		values.put('l', 1);
		values.put('n', 1);
		values.put('o', 1);
		values.put('r', 1);
		values.put('s', 1);
		values.put('t', 1);
		values.put('u', 1);
		values.put('d', 2);
		values.put('g', 2);
		values.put('b', 3);
		values.put('c', 3);
		values.put('m', 3);
		values.put('p', 3);
		values.put('f', 4);
		values.put('h', 4);
		values.put('v', 4);
		values.put('y', 4);
		values.put('q', 5);
		//values.put('ch', 5);
		values.put('j', 8);
		//values.put('ll', 8);
		values.put('ñ', 8);
		//values.put('rr', 8);
		values.put('x', 8);
		values.put('z', 10);
				
		return values;
	}
	
	public static void main(String[] args) {
		
		final HashMap<Character, Integer> points = getSpanishScrabblePoint();
		
		Function<String, Integer> score = 
				word -> word.toLowerCase().chars().map(letter-> {
					Character l = Character.toChars(letter)[0];					
					return(points.containsKey(l)?points.get(l):0);
				}).sum();
				
		ToIntFunction<String> intScore = 
				word -> word.toLowerCase().chars().map(letter->{
					Character l = Character.toChars(letter)[0];					
					return(points.containsKey(l)?points.get(l):0);
				}).sum();
		
		System.out.println("Hello value: "+intScore.applyAsInt("Hello"));
						
		List<String> sourceWords = Generator.getGeekAlphabetList();
		
		String bestWord = sourceWords.stream().max(Comparator.comparing(score)).orElse(null);
		
		Map<Integer, List<String>> histoWordsByScore =
				sourceWords.stream()
				//.filter(validWords::contains)
				.collect(Collectors.groupingBy(score));
		
		histoWordsByScore.entrySet().stream()
			.sorted(Comparator.comparing(entry -> -entry.getKey()))
			.limit(3)
			.forEach(entry -> System.out.println(entry.getKey()+" - "+entry.getValue()));
		
		Function<String, Map<Integer, Long>> histoWord = 
				word -> word.chars()
					.boxed()
					.collect(
							Collectors.groupingBy(letter->letter, Collectors.counting())
							);
		
		
		System.out.println("Max points word: " + bestWord);
		if (bestWord != null)
			System.out.println("Value: "+intScore.applyAsInt(bestWord));

	}

}
