package com.wind4869.snippets.research.lang;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * TestFileInputStream
 *
 * @author wind4869
 * @since 1.0.0
 */
public class TestFileInputStream {
    public static void main(String[] args) throws IOException {
        // content: Test FileInputStream.
        String name = "/Users/wind/file.txt";
        try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(name))) {
            while (true) {
                int b = in.read();
                if (b == -1) {
                    break;
                }
                // output: Test FileInputStream.
                System.out.print((char) b);
            }
        }
    }
}
