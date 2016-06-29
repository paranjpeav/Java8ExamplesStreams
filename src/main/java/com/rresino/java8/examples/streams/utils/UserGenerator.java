package com.rresino.java8.examples.streams.utils;

import com.rresino.java8.examples.streams.data.User;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * Created by rresino on 04/03/2016.
 */
public class UserGenerator {

    private static final String[] NAMES = {"Umilawin","Erigop","Landazar","Acuwin","Etaotha","Kedoabard","Praesean","Haeran","Etardord","Cendawyth","Rhonen","Grilinwan","Vilajan","Wev","Eowiragan","Ferraseth","Umeilith","Wice","Brierid","Fedriric","Higod","Gweann","Thigovudd","Fraliwyr","Zardorin","Halrik","Qae","Gwoif","Zoican","Tjolme","Dalibwyn","Miram"};
    private static final String[] LAST_NAMES = {"Trobwyn","Eowendaria","Oloam","Thodda","Nydirat","Roiwiel","Eowedritlan","Aeriniel","Eteannor","Aalelind","Foedon","Rhindra","Lothorewyn","Gayswen","Galaosh","Ibelilla","Haerakith","Kairwen","Dwomald","Glilaweth","Gwelilath","Afima","Braeg","Nyderwen"};
    private static final String[] COUNTRIES = {"Spain","UK","Usa","France","Germany","Italy"};

    private static Random random = new Random(System.nanoTime());

    public static Stream<User> streamOf(int elements) {
        return Stream.generate(supplier).limit(elements);
    }

    public static Supplier<User> supplier = () -> new User(
            generateRandomString(NAMES), generateRandomString(LAST_NAMES), random.nextInt(100), generateRandomString(COUNTRIES));

    private static String generateRandomString(final String[] source) {
        return source[random.nextInt(source.length)];
    }

    public static void generateUsersFile(Path path, int elements) {
        try {
            final BufferedWriter writer = Files.newBufferedWriter(path, StandardOpenOption.CREATE);
            UserGenerator.streamOf(elements).forEach( p-> {
                try {
                    writer.write(p.getName());
                    writer.newLine();
                    writer.write(p.getLastName());
                    writer.newLine();
                    writer.write(Integer.toString(p.getAge()));
                    writer.newLine();
                    writer.write(p.getCountry());
                    writer.newLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }});
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
