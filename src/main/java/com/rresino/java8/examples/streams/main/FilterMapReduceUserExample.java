package com.rresino.java8.examples.streams.main;

import com.rresino.java8.examples.streams.utils.UserGenerator;

/**
 * Created by rresino on 04/03/2016.
 */
public class FilterMapReduceUserExample {

    public static void main(String[] args) {
        System.out.println();
        System.out.println("FilterMapReduce:");
        System.out.println();

        System.out.println("Stream Map: ");
        UserGenerator.streamOf(20)
                .filter( x -> x.getAge()>18)
                .map(p -> p.getCountry())
                .forEach(x-> System.out.println(x));
        System.out.println();

        System.out.println("Stream Reduce: ");
        System.out.println(
            UserGenerator.streamOf(20)
                .filter( x -> x.getAge()>18)
                .map(p -> p.getCountry())
                .reduce((a, b) -> a.contains(b)?a:a+","+b).orElse("Not found")
        );
        System.out.println();
    }

}
