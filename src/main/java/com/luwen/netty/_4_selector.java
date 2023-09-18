package com.luwen.netty;

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
public class _4_selector {

    public static void main(String[] args) throws IOException {
        selector();
    }


    // 事件类型 accept connect read write
    public static void selector() throws IOException {
        // 1. 创建Selector
        Selector selector = Selector.open();
        // 2. 创建ServerSocketChannel（非阻塞模式）
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        // 3. 绑定端口号
        serverSocketChannel.bind(new InetSocketAddress(9999));
        // 4. 在selector上注册ServerSocketChannel
        SelectionKey selectionKey = serverSocketChannel.register(selector, 0, null);
        selectionKey.interestOps(SelectionKey.OP_ACCEPT);

        while(true){
            // 5. select方法，没有事件发生，线程阻塞。收到事件线程继续运行。
            selector.select();
            // 6. 处理accept事件
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()){
                SelectionKey key = iterator.next();
                System.out.println("SelectionKey : " + selectionKey);
                // 区分事件处理类型
                // accept事件
                if(key.isAcceptable()){
                    // 建立socket连接
                    ServerSocketChannel channel = (ServerSocketChannel) key.channel();
                    SocketChannel socketChannel = channel.accept();
                    socketChannel.configureBlocking(false);
                    // 注册selector
                    SelectionKey sk = socketChannel.register(selector, 0, null);
                    sk.interestOps(SelectionKey.OP_READ);
                }else if(key.isReadable()){
                    // 读事件
                    ByteBuffer byteBuffer = ByteBuffer.allocate(16);
                    SocketChannel readSockChannel = (SocketChannel) key.channel();
                    readSockChannel.read(byteBuffer);
                    byteBuffer.flip();
                    System.out.println(StandardCharsets.UTF_8.decode(byteBuffer));
                    byteBuffer.clear();
                }


            }
            System.out.println("SelectionKey is end ...");
        }
    }
}
