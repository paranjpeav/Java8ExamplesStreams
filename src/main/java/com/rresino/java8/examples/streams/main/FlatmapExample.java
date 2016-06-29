package com.rresino.java8.examples.streams.main;

import com.rresino.java8.examples.streams.utils.Generator;
import com.rresino.java8.examples.streams.utils.UserGenerator;

import java.util.Collection;
import java.util.stream.Stream;

/**
 * Created by rresino on 04/03/2016.
 */
public class FlatmapExample {


    public static void main(String[] args) {

        Stream.of(
                UserGenerator.streamOf(10).map(s->s.getName()),
                UserGenerator.streamOf(10).map(s->s.getEmail()),
                Generator.getGeekAlphabetList().stream())
                .flatMap(st->st)
                .forEach(x->System.out.println(x));

    }

}
