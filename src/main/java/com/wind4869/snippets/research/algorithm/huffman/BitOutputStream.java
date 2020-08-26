package com.wind4869.snippets.research.algorithm.huffman;

import java.io.*;

/**
 * BitInputStream
 *
 * @author wind4869
 * @since 1.0.0
 */
public class BitOutputStream extends FilterOutputStream {
    private int buffer;
    private int pos;

    public BitOutputStream(OutputStream out) {
        super(out);
    }

    @Override
    public void flush() throws IOException {
        clearBuffer();
        super.flush();
    }

    public void writeBit(boolean bit) throws IOException {
        buffer <<= 1;
        buffer |= (bit ? 1 : 0);
        pos++;
        if (pos == 8) clearBuffer();
    }

    public void writeChar(int x) throws IOException {
        for (int i = 0; i < 8; i++) {
            boolean bit = ((x >>> (8 - i - 1)) & 1) == 1;
            writeBit(bit);
        }
    }

    public void writeInt(int x) throws IOException {
        writeChar((x >>> 24) & 0xff);
        writeChar((x >>> 16) & 0xff);
        writeChar((x >>> 8) & 0xff);
        writeChar(x & 0xff);
    }

    private void clearBuffer() throws IOException {
        if (pos == 0) return;
        if (pos > 0) buffer <<= (8 - pos);
        out.write(buffer);
        buffer = 0;
        pos = 0;
    }
}
