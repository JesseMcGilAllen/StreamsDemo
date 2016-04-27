package com.jessemcgilallen.streams;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Created by jessemcgilallen on 4/26/16.
 */
public class StreamDemo {

   public List<String> myList = Arrays.asList("b23", "a1","c9", "a2", "a3", "a4",
            "b1", "a9", "c8", "b2", "a7", "b3","a8", "b4",
            "c1", "c2", "c3", "c4");

    public void printCItems8Style() {
        myList
                .stream()
                .filter(string -> string.startsWith("c"))
                .map(String::toUpperCase)
                .sorted()
                .forEach(System.out::println);
               // .forEach(string -> System.out.println(string));


    }

    public void printCItems7Style() {
        List<String> cItems = new ArrayList<>();
        for (String item: myList) {
            if (item.startsWith("c")) {
                cItems.add(item.toUpperCase());
            }
        }

        cItems.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });

        System.out.println(cItems);
    }

    public void uniqueWordsForFile(String filename) {
        long start = 0;
        long stop = 0;
        try {
            start = System.currentTimeMillis();
            Files.lines(Paths.get(filename))
                    .map(line -> line.split("\\s+"))
                    .flatMap(Arrays::stream)
                    .filter(word -> !word.contains("\\s+"))
                    .distinct()
                    .forEach(System.out::println);
//                    .forEach(string -> {
//                        System.out.format("Word: %s Thread: %s\n", string, Thread.currentThread().getName());
//                    });
            stop = System.currentTimeMillis();
        } catch (IOException ioException) {
            System.out.println("Problem");
        }

        long duration = stop - start;

        System.out.println("Duration in MilliSeconds: " + duration);
    }

    public void uniqueWordsForFileInParallel(String filename) {

        long start = 0;
        long stop = 0;
        try {
            start = System.currentTimeMillis();
            Files.lines(Paths.get(filename))
                    .parallel()
                    .map(line -> line.split("\\s+"))
                    .flatMap(Arrays::stream)
                    .filter(word -> !word.contains("\\s+"))
                    .distinct()
                    .forEach(System.out::println);
//                    .forEach(string -> {
//                        System.out.format("Word: %s Thread: %s\n", string, Thread.currentThread().getName());
//                    });

            stop = System.currentTimeMillis();
        } catch (IOException ioException) {
            System.out.println("Problem");
        }
        long duration = stop - start;

        System.out.println("\nDuration in MilliSeconds: " + duration);
    }

    public void uniqueWordsForFileInParallelWithFJTP(String filename) {

        long start = 0;
        long stop = 0;
        try {
            start = System.currentTimeMillis();
            System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "2");
            Files.lines(Paths.get(filename))
                    .parallel()
                    .map(line -> line.split("\\s+"))
                    .flatMap(Arrays::stream)
                    .filter(word -> !word.contains("\\s+"))
                    .distinct()
                    .forEach(System.out::println);
                   // .forEach(string -> {
                    //    System.out.format("Word: %s Thread: %s\n", string, Thread.currentThread().getName());
                   // });

            stop = System.currentTimeMillis();
        } catch (IOException ioException) {
            System.out.println("Problem");
        }
        long duration = stop - start;

        System.out.println("\nDuration in MilliSeconds: " + duration);
    }


}
