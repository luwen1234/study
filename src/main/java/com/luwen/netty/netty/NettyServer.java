package com.luwen.netty.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @author luwen
 */
@Slf4j
public class NettyServer {

    public static void main(String[] args) {

        // 创建一个EventLoop
        DefaultEventLoopGroup defaultEventLoopGroup = new DefaultEventLoopGroup();

        ChannelFuture channelFuture = new ServerBootstrap()
                // 1 NioEventLoopGroup = selector + threadPool
                .group(new NioEventLoopGroup(1), new NioEventLoopGroup(2))
                // 2 选择NioServerSocketChannel
                .channel(NioServerSocketChannel.class)
                // 3 给serverSocketChannel下面的socketChannel添加Handler
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {
                        // 4 在channel添加一个解码器
                        nioSocketChannel.pipeline().addLast(new StringDecoder());
                        nioSocketChannel.pipeline().addLast(new LoggingHandler());
                        // 5 在channel添加一个入站handler
                        nioSocketChannel.pipeline().addLast(new ChannelInboundHandlerAdapter() {
                            @Override
                            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                log.info((String) msg);
                                ctx.fireChannelRead(msg);
                            }
                        }).addLast(defaultEventLoopGroup, "default", new ChannelInboundHandlerAdapter() {
                            @Override
                            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                // 6 添加一个入站handler,指定defaultEventLoopGroup处理
                                log.info((String) msg);
                                ctx.channel().write(msg);
                            }
                        }).addLast(new ChannelOutboundHandlerAdapter() {
                            // 7 添加一个出站handler.
                            @Override
                            public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
                                log.info("write msg : {}", msg);
                                super.write(ctx, msg, promise);
                            }
                        });
                    }
                }).bind(6680);
    }
}
