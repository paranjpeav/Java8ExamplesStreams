package com.rresino.java8.examples.streams.spliterator;

import com.rresino.java8.examples.streams.data.User;
import com.rresino.java8.examples.streams.utils.UserGenerator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Spliterator;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * Created by rresino on 06/03/2016.
 */
public class SpliteratorExample {

    public static void main(String[] args) {

        Path path = Paths.get("./src/main/resources/files/users.txt");
        //UserGenerator.generateUsersFile(path, 20);

        try {
            Stream<String> fileLinesStream = Files.lines(path);

            Spliterator<String> fileLinesSpliterator = fileLinesStream.spliterator();
            Spliterator<User> userSpliterator = new UserSpliterator(fileLinesSpliterator);

            Stream<User> userStream = StreamSupport.stream(userSpliterator, false); // Important to set NOT parallel support
            userStream.forEach(System.out::println);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
