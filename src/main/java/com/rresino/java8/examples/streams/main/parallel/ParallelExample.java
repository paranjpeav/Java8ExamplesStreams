package com.rresino.java8.examples.streams.main.parallel;

import com.rresino.java8.examples.streams.utils.UserGenerator;

/**
 * Created by rresino on 06/03/2016.
 */
public class ParallelExample {

    public static void main(String[] args) {

        System.out.println();
        System.out.println("Sorted Distinct Example:");
        System.out.println();

        long start = System.currentTimeMillis();
        UserGenerator.streamOf(10000)
                .map(s->s.getName())
                .distinct()
                .sorted()
                .forEach(s-> System.out.println(s));
        long end = System.currentTimeMillis();
        System.out.println("Time 1: "+(end-start));

        start = System.currentTimeMillis();
        UserGenerator.streamOf(10000)
                .parallel()
                .map(s->s.getName())
                .distinct()
                .sorted()
                .forEach(s-> System.out.println(s));
        end = System.currentTimeMillis();
        System.out.println("Time 2: "+(end-start));
    }

}
