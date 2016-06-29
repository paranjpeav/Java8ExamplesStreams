package com.rresino.java8.examples.streams.main;

import com.rresino.java8.examples.streams.utils.Generator;

/**
 * Created by rresino on 04/03/2016.
 */
public class LimitAndPeekExample {

    public static void main(String[] args) {

        System.out.println();
        System.out.println("Limit and Peek:");
        System.out.println();

        Generator.getListIntegers(1000).stream()
                .peek(s->System.out.println("Peek:"+s))
                .limit(100)
                .forEach(x -> System.out.println(x));
    }
}
