package com.luwen.netty;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

/**
 * @author luwen
 */
public class _3_socketChannel {

    public static void main(String[] args) throws InterruptedException {

        unBlockNIOMain();
    }

    public static void NIOMain() throws InterruptedException {
        Thread server = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    blockNIOServer();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread client = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    blockNIOClient();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        server.start();
        Thread.sleep(3000);
        client.start();
    }

    /**
     * 阻塞NIO服务端
     */
    public static void blockNIOServer() throws IOException {

        System.out.println(Thread.currentThread().getName()+"start...");
        // 1. allocate分配byteBuffer内存空间
        ByteBuffer byteBuffer = ByteBuffer.allocate(16);
        // 2. 创建ServerSocketChannel
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        // 3. 设置IP和端口
        serverSocketChannel.bind(new InetSocketAddress(9999));
        // 4. 等待客户端连接（阻塞）
        System.out.println("等待Client..." + System.currentTimeMillis());
        SocketChannel socketChannel = serverSocketChannel.accept();
        System.out.println("Client已连接..." + System.currentTimeMillis());
        // 5. 等待读取客户端发送数据（阻塞）
        while(socketChannel.read(byteBuffer) != -1){
            // 切换读模式
            byteBuffer.flip();
            // 读取内容
            System.out.println(StandardCharsets.UTF_8.decode(byteBuffer).toString());
            // 切换写模式
            byteBuffer.clear();
        }
        System.out.println(Thread.currentThread().getName()+"end...");
    }

    /**
     * 阻塞NIO客户端
     */
    public static void blockNIOClient() throws IOException, InterruptedException {
        System.out.println(Thread.currentThread().getName()+"start...");
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress(9999));
        for(int i=0;i<10;i++){
            Thread.sleep(1000);
            socketChannel.write(StandardCharsets.UTF_8.encode("hello..."));
        }
        System.out.println(Thread.currentThread().getName()+"end...");
    }

    /**
     * 非阻塞NIO服务端
     * @throws IOException
     */
    public static void nonBlockNIOServer() throws IOException {
        // 1. allocate分配byteBuffer内存空间
        ByteBuffer byteBuffer = ByteBuffer.allocate(16);
        // 2. 创建ServerSocketChannel,建立SocketChannel非阻塞模式
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        // 3. 设置IP和端口
        serverSocketChannel.bind(new InetSocketAddress(9998));
        // 4. 等待客户端连接（非阻塞）
        SocketChannel socketChannel = serverSocketChannel.accept();
        // 上一步不阻塞, socketChannel未建立连接是为null
        System.out.println(Thread.currentThread().getName() + " 's channel is " + socketChannel);
        // 读取客户端数据为非阻塞模式
        socketChannel.configureBlocking(false);
        // 5. 等待读取客户端发送数据（阻塞）
        while(socketChannel.read(byteBuffer) != -1){
            // 切换读模式
            byteBuffer.flip();
            // 读取内容
            System.out.println(StandardCharsets.UTF_8.decode(byteBuffer).toString());
            // 切换写模式
            byteBuffer.clear();
        }
        System.out.println(Thread.currentThread().getName()+"end...");
    }

    /**
     * 非阻塞NIO客户端
     */
    public static void nonBlockNIOClient() throws IOException, InterruptedException {
        System.out.println(Thread.currentThread().getName()+" start...");
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress(9998));
        for(int i=0;i<10;i++){
            Thread.sleep(1000);
            socketChannel.write(StandardCharsets.UTF_8.encode("hello..."));
        }
        System.out.println(Thread.currentThread().getName()+" end...");
    }

    public static void unBlockNIOMain() throws InterruptedException {
        Thread server = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    nonBlockNIOServer();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread client = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    nonBlockNIOClient();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        server.start();
        Thread.sleep(3000);
        client.start();
    }
}
