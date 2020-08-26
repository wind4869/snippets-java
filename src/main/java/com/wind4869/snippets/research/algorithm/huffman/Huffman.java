package com.wind4869.snippets.research.algorithm.huffman;

import java.io.*;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Huffman
 *
 * @author wind4869
 * @since 1.0.0
 */
public class Huffman {
    private static final int ALPHABET_SIZE = 256;
    private final BitInputStream in;
    private final BitOutputStream out;

    public Huffman(BitInputStream in, BitOutputStream out) {
        this.in = in;
        this.out = out;
    }

    private static class Node {
        private final char ch;
        private final int freq;
        private final Node left, right;

        public Node(char ch, int freq, Node left, Node right) {
            this.ch = ch;
            this.freq = freq;
            this.left = left;
            this.right = right;
        }

        private boolean isLeaf() {
            return (left == null) && (right == null);
        }
    }

    public void compress() throws IOException {
        // read the input
        String s = in.readString();
        char[] input = s.toCharArray();

        // tabulate frequency counts
        int[] freq = new int[ALPHABET_SIZE];
        for (char c : input) freq[c]++;

        // build Huffman trie
        Node root = buildTrie(freq);

        // build code table
        String[] table = new String[ALPHABET_SIZE];
        buildCode(table, root, "");

        // print trie for decoder
        writeTrie(root);

        // print number of bytes in original uncompressed message
        out.writeInt(input.length);

        // use Huffman code to encode input
        for (char c : input) {
            String code = table[c];
            for (char cc : code.toCharArray()) {
                out.writeBit(cc != '0');
            }
        }
    }

    private static Node buildTrie(int[] freq) {
        PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingInt(n -> n.freq));
        for (char c = 0; c < ALPHABET_SIZE; c++) {
            if (freq[c] > 0) {
                queue.offer(new Node(c, freq[c], null, null));
            }
        }
        while (queue.size() > 1) {
            Node left = queue.poll();
            Node right = queue.poll();
            queue.offer(new Node('\0', left.freq + right.freq, left, right));
        }
        return queue.poll();
    }

    private static void buildCode(String[] table, Node node, String code) {
        if (!node.isLeaf()) {
            buildCode(table, node.left, code + '0');
            buildCode(table, node.right, code + '1');
        } else {
            table[node.ch] = code;
        }
    }

    private void writeTrie(Node node) throws IOException {
        if (node.isLeaf()) {
            out.writeBit(true);
            out.writeChar(node.ch);
            return;
        }
        out.writeBit(false);
        writeTrie(node.left);
        writeTrie(node.right);
    }

    public void decompress() throws IOException {
        // read in Huffman trie from input stream
        Node root = readTrie();

        // number of bytes to write
        int length = in.readInt();

        // decode using the Huffman trie
        for (int i = 0; i < length; i++) {
            Node node = root;
            while (!node.isLeaf()) {
                boolean bit = in.readBit();
                if (bit) node = node.right;
                else node = node.left;
            }
            out.writeChar(node.ch);
        }
    }

    private Node readTrie() throws IOException {
        boolean isLeaf = in.readBit();
        if (isLeaf) {
            return new Node(in.readChar(), -1, null, null);
        } else {
            return new Node('\0', -1, readTrie(), readTrie());
        }
    }

    public static void main(String[] args) throws Exception {
        File file = new File("/Users/wind/original.txt");
        File compressed = new File("/Users/wind/compressed.bin");
        File decompressed = new File("/Users/wind/decompressed.txt");

        try (
                BitInputStream in = new BitInputStream(new FileInputStream(file));
                BitOutputStream out = new BitOutputStream(new FileOutputStream(compressed))
        ) {
            Huffman huffman = new Huffman(in, out);
            huffman.compress();
        }

        try (
                BitInputStream in = new BitInputStream(new FileInputStream(compressed));
                BitOutputStream out = new BitOutputStream(new FileOutputStream(decompressed))
        ) {
            Huffman huffman = new Huffman(in, out);
            huffman.decompress();
        }
    }
}
