package com.luwen.netty.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;

import java.util.Objects;
import java.util.Scanner;

/**
 * @author luwen
 */
public class NettyClient {

    public static void main(String[] args) throws InterruptedException {
        NioEventLoopGroup group = new NioEventLoopGroup();
        ChannelFuture future = new Bootstrap()
                // 1 创建NioEventLoopGroup处理者
                .group(group)
                // 2 选择客户 Socket 实现类，NioSocketChannel 表示基于 NIO 的客户端实现
                .channel(NioSocketChannel.class)
                // 3 添加 SocketChannel 的处理器，ChannelInitializer 处理器（仅执行一次），
                // 它的作用是待客户端 SocketChannel 建立连接后，执行 initChannel 以便添加更多的处理器
                .handler(new ChannelInitializer<Channel>() {
                    @Override
                    protected void initChannel(Channel channel) throws Exception {
                        // 7 消息会经过通道 handler 处理，这里是将 String => ByteBuf 发出
                        channel.pipeline().addLast(new StringEncoder());
                    }
                })
                // 4 指定要连接的服务器和端口
                .connect("127.0.0.1", 6680);
        // 5 ，Netty 中很多方法都是异步的，如 connect，这时需要使用 sync 方法等待 connect 建立连接完毕
        future.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                Channel channel = future.channel();
                Scanner scanner = new Scanner(System.in);
                while(true){
                    String msg = scanner.nextLine();
                    if(Objects.equals("q", msg)){
                        channel.close();
                        break;
                    }
                    channel.writeAndFlush(msg);
                }
                // 6 异步关闭资源
                channel.closeFuture().addListener(new ChannelFutureListener() {
                    @Override
                    public void operationComplete(ChannelFuture future) throws Exception {
                        group.shutdownGracefully();
                    }
                });
            }
        });

    }
}
