package com.rresino.java8.examples.streams.spliterator;

import com.rresino.java8.examples.streams.data.User;

import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * Created by rresino on 06/03/2016.
 */
public class UserSpliterator implements Spliterator<User> {

    private final Spliterator<String> fileLinesSpliterator;

    UserSpliterator(Spliterator<String> fileLinesSpliterator) {
        this.fileLinesSpliterator = fileLinesSpliterator;
    }
    
    @Override
    public boolean tryAdvance(Consumer<? super User> action) {

        final User user = new User();
        if (
            this.fileLinesSpliterator.tryAdvance(line -> user.setName(line)) &&
            this.fileLinesSpliterator.tryAdvance(line -> user.setLastName(line)) &&
            this.fileLinesSpliterator.tryAdvance(line -> user.setAge(Integer.parseInt(line))) &&
            this.fileLinesSpliterator.tryAdvance(line -> user.setCountry(line))) {
            action.accept(user);
            return true;
        }
        return false;
    }

    @Override
    public Spliterator<User> trySplit() {
        return null;
    }

    @Override
    public long estimateSize() {
        return this.fileLinesSpliterator.estimateSize()/4;
    }

    @Override
    public int characteristics() {
        return this.fileLinesSpliterator.characteristics();
    }
}
