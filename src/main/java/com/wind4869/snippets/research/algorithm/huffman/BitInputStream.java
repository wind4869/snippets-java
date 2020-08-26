package com.wind4869.snippets.research.algorithm.huffman;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * BitInputStream
 *
 * @author wind4869
 * @since 1.0.0
 */
public class BitInputStream extends FilterInputStream {
    private int buffer;
    private int pos;

    public BitInputStream(InputStream in) {
        super(in);
    }

    public boolean readBit() throws IOException {
        if (pos == 0) {
            buffer = in.read();
            pos = 8;
        }
        pos--;
        return ((buffer >> pos) & 1) == 1;
    }

    public char readChar() throws IOException {
        int c = 0;
        for (int i = 0; i < 8; i++) {
            boolean bit = readBit();
            c <<= 1;
            c |= (bit ? 1 : 0);
        }
        return (char) (c & 0xff);
    }

    public int readInt() throws IOException {
        int x = 0;
        for (int i = 0; i < 4; i++) {
            x <<= 8;
            x |= readChar();
        }
        return x;
    }

    public String readString() throws IOException {
        StringBuilder sb = new StringBuilder();
        char c = readChar();
        while (buffer != -1) {
            sb.append(c);
            c = readChar();
        }
        return sb.toString();
    }
}
