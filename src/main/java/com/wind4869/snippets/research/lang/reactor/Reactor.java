package com.wind4869.snippets.research.lang.reactor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Set;

/**
 * Reactor
 *
 * @author wind4869
 * @since 1.0.0
 */
public class Reactor implements Runnable {
    private final Selector selector;
    private final ServerSocketChannel serverSocket;

    public Reactor(int port) throws IOException {
        selector = Selector.open();
        serverSocket = ServerSocketChannel.open();
        serverSocket.socket().bind(new InetSocketAddress(port));
        serverSocket.configureBlocking(false);
        serverSocket.register(selector, SelectionKey.OP_ACCEPT, new Acceptor());
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                selector.select();
                Set<SelectionKey> selectedKeys = selector.selectedKeys();
                for (SelectionKey key : selectedKeys) {
                    dispatch(key);
                }
                selectedKeys.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void dispatch(SelectionKey key) {
        Runnable handler = (Runnable) key.attachment();
        if (handler != null) {
            handler.run();
        }
    }

    private class Acceptor implements Runnable {
        @Override
        public void run() {
            try {
                SocketChannel socket = serverSocket.accept();
                if (socket != null) {
                    new Handler(selector, socket);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new Reactor(4869).run();
    }
}
