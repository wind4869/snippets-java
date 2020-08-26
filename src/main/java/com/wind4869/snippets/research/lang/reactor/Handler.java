package com.wind4869.snippets.research.lang.reactor;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Handler
 *
 * @author wind4869
 * @since 1.0.0
 */
public class Handler implements Runnable {
    private final SocketChannel socket;
    private final SelectionKey key;
    private final ByteBuffer buffer = ByteBuffer.allocate(1024);
    private static final ExecutorService threadPool = new ThreadPoolExecutor(
            4, 8, 60, TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(1024)
    );

    public Handler(Selector selector, SocketChannel socket) throws IOException {
        this.socket = socket;
        socket.configureBlocking(false);
        key = socket.register(selector, SelectionKey.OP_READ, this);
    }

    @Override
    public void run() {
        key.interestOps(key.interestOps() & (~SelectionKey.OP_READ));
        threadPool.submit(this::echo);
    }

    private void echo() {
        try {
            if (socket.read(buffer) < 0) {
                socket.close();
                return;
            }
            buffer.flip();
            while (buffer.hasRemaining()) {
                socket.write(buffer);
            }
            buffer.clear();
            key.interestOps(key.interestOps() | SelectionKey.OP_READ);
            key.selector().wakeup();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
