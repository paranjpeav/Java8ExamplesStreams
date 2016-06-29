package com.rresino.java8.examples.streams.main;

import com.rresino.java8.examples.streams.utils.WordsGenerator;

import java.util.Set;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by rresino on 06/03/2016.
 */
public class FlatMapPatternAndSplitExample {

    public static void main(String[] args) {

        Function<String, Stream<String>> splitIntoWords = line -> Pattern.compile(" ").splitAsStream(line);

        Stream<String> file1 = WordsGenerator.linesStreamGenerator(10, 6);
        Stream<String> file2 = WordsGenerator.linesStreamGenerator(8, 10);
        Stream<String> file3 = WordsGenerator.linesStreamGenerator(20, 6);

        Stream<String> words =
            Stream.of(file1, file2, file3)      // Streams of streams of lines
                .flatMap(Function.identity())   // Streams of lines
                .flatMap(splitIntoWords);       // Stream of words

        words.forEach(System.out::println);

        System.out.println();
        System.out.println("------------------------------------------------------------------------------");
        System.out.println("Words without duplicates:");
        System.out.println("------------------------------------------------------------------------------");
        System.out.println();
        Stream<String> file4 = WordsGenerator.linesStreamGenerator(10, 6);
        Stream<String> file5 = WordsGenerator.linesStreamGenerator(8, 10);
        Stream<String> file6 = WordsGenerator.linesStreamGenerator(20, 6);

        Set<String> uniqueWords = Stream.of(file4, file5, file6)      // Streams of streams of lines
                .flatMap(Function.identity())   // Streams of lines
                .flatMap(splitIntoWords)       // Stream of words
                .collect(Collectors.toSet());
        uniqueWords.forEach(System.out::println);
    }

}
