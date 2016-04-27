package com.jessemcgilallen.streams;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by jessemcgilallen on 4/26/16.
 */
public class StreamDemoTest {
    private StreamDemo demo = new StreamDemo();

    @Test
    public void printCItems8Style() throws Exception {
        demo.printCItems8Style();
    }

    @Test
    public void printCItems7Style() throws Exception {
        demo.printCItems7Style();
    }

    @Test
    public void uniqueWordsForFile() throws Exception {
        demo.uniqueWordsForFile("Syllabus.md");
    }

    @Test
    public void uniqueWordsForFileInParallel() throws Exception {
        demo.uniqueWordsForFileInParallel("Syllabus.md");
    }

    @Test
    public void uniqueWordsForFileInParallelWithFJTP() throws Exception {
        demo.uniqueWordsForFileInParallelWithFJTP("Syllabus.md");
    }

}