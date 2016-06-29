package com.rresino.java8.examples.streams.main;

import com.rresino.java8.examples.streams.utils.Generator;

import java.util.List;

/**
 * Created by rresino on 04/03/2016.
 */
public class FilterMapReduceExample {

    public static void main(String[] args) {
        System.out.println();
        System.out.println("FilterMapReduce:");
        System.out.println();

        List<String> data = Generator.getGeekAlphabetList();

        System.out.println("Stream Raw: ");
        data.stream()
                .forEach(x-> System.out.println(x));
        System.out.println();

        System.out.println("Stream Filter: ");
        data.stream()
                .filter( x -> x.length()>3)
                .forEach(x-> System.out.println(x));
        System.out.println();

        System.out.println("Stream Filter: ");
        data.stream()
                .filter( x -> x.length()>3)
                .forEach(x-> System.out.println(x));
        System.out.println();

        System.out.println("Stream Map: ");
        data.stream()
                .filter( x -> x.length()>3)
                .map(p -> p.length())
                .forEach(x-> System.out.println(x));
        System.out.println();

        System.out.println("Stream Reduce: ");
        System.out.println(
            data.stream()
                    .filter( x -> x.length()>3)
                    .map(p -> p.length())
                    .reduce(0, (a,b) -> a+b));
        System.out.println();
    }

}
