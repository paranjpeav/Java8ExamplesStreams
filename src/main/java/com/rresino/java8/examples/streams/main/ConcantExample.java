package com.rresino.java8.examples.streams.main;

import com.rresino.java8.examples.streams.data.User;
import com.rresino.java8.examples.streams.utils.UserGenerator;

import java.util.function.Function;
import java.util.stream.Stream;

/**
 * Created by rresino on 06/03/2016.
 */
public class ConcantExample {

    public static void main(String[] args) {

        System.out.println();
        System.out.println("Concat:");
        System.out.println();

        Stream<User> stream1 = UserGenerator.streamOf(10);
        Stream<User> stream2 = UserGenerator.streamOf(10);
        // Contact 2 stream preserving the order, and have a cost for parallel operation
        System.out.println(Stream.concat(stream1, stream2).count());


        Stream<User> stream3 = UserGenerator.streamOf(10);
        Stream<User> stream4 = UserGenerator.streamOf(10);
        Stream<Stream<User>> rs = Stream.of(stream3, stream4);

        Stream<User> stream3plus4 = rs.flatMap(Function.identity());
        System.out.println(stream3plus4.count());


    }
}
