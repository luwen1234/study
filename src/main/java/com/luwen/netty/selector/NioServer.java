package com.luwen.netty.selector;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;

/**
 * @author luwen
 */
public class NioServer {

    public static void main(String[] args) throws IOException {
        test();
    }

    /**
     * 多路复用
     */
    public static void test() throws IOException {

        Selector selector = Selector.open();
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(6609));
        serverSocketChannel.configureBlocking(false);
        SelectionKey acceptSelectionKey = serverSocketChannel.register(selector, 0);
        acceptSelectionKey.interestOps(SelectionKey.OP_ACCEPT);

        for (; ; ) {
            selector.select();
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                iterator.remove();
                if (selectionKey.isAcceptable()) {
                    ServerSocketChannel serverSocketChannel1 = (ServerSocketChannel) selectionKey.channel();
                    SocketChannel socketChannel = serverSocketChannel1.accept();
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector, SelectionKey.OP_READ);
                } else if (selectionKey.isReadable()) {
                    SocketChannel readChannel = (SocketChannel) selectionKey.channel();
                    ByteBuffer readBuffer = ByteBuffer.allocate(16);
                    readChannel.read(readBuffer);
                    readBuffer.flip();
                    readBuffer.clear();
                    readChannel.register(selector, SelectionKey.OP_WRITE);
                    readChannel.write(StandardCharsets.UTF_8.encode("test"));
                }else if(selectionKey.isWritable()){
                    SocketChannel writeChannel = (SocketChannel) selectionKey.channel();
                    ByteBuffer writeBuffer = ByteBuffer.allocate(16);
                    writeChannel.read(writeBuffer);
                    System.out.println(StandardCharsets.UTF_8.decode(writeBuffer));
                    selectionKey.cancel();
                }
            }
        }
    }
}
