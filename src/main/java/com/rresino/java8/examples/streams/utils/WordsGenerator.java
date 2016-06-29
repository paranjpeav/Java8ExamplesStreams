package com.rresino.java8.examples.streams.utils;

import com.rresino.java8.examples.streams.data.User;

import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * Created by rresino on 06/03/2016.
 */
public class WordsGenerator {

    private final static String[] WORDS = new String[] {"Lorem", "ipsum", "dolor", "sit", "amet", "consectetur", "adipiscing", "elit", "Vestibulum", "vel", "nibh", "ac", "enim", "tristique", "accumsan", "Vivamus", "varius", "sapien", "libero", "Nullam", "placerat", "lectus", "nec", "lorem", "interdum", "quis", "viverra", "purus", "elementum", "Maecenas", "aliquet", "elit", "sodales", "ante", "porta", "eleifend", "Suspendisse", "est", "orci", "suscipit", "a", "turpis", "vitae", "lobortis", "iaculis", "mauris", "Praesent", "cursus", "eros", "et", "sem", "accumsan", "faucibus", "Morbi", "tincidunt", "nunc", "a", "neque", "maximus", "convallis", "Integer", "sed", "molestie", "nisl",
        "Vestibulum", "quis", "magna", "porttitor", "fermentum", "ex", "at", "lobortis", "dolor", "Nam", "eu", "rhoncus", "nisi", "et", "bibendum", "elit", "Aenean", "pulvinar", "blandit", "massa", "eu", "condimentum", "Duis", "congue", "felis", "ac", "placerat", "feugiat", "Maecenas", "lacinia", "hendrerit", "nunc", "a", "tristique", "est", "sodales", "sed", "Maecenas", "varius", "pharetra", "felis", "ultrices", "accumsan", "ex", "Morbi", "et", "porttitor", "ipsum", "Maecenas", "in", "vulputate", "nisl", "in", "ullamcorper", "ante"};

    private static Random random = new Random(System.nanoTime());

    public static Supplier<String> wordSupplier = () -> WORDS[random.nextInt(WORDS.length)];

    public static Stream<String> wordStreamGenerator(int elements) {
        return Stream.generate(wordSupplier).limit(elements);
    }

    public static Stream<String> linesStreamGenerator(final int elementsLines, final int wordsPerLine) {
        return Stream.generate(()-> {
            return wordStreamGenerator(wordsPerLine).reduce((a,b)->a+" "+b).orElse("");
        }).limit(elementsLines);
    }

}
