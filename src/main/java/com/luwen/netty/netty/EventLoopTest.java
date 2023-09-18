package com.luwen.netty.netty;

import io.netty.channel.DefaultEventLoopGroup;
import io.netty.channel.EventLoopGroup;
import lombok.extern.slf4j.Slf4j;

/**
 * @author luwen
 */
@Slf4j
public class EventLoopTest {

    public static void main(String[] args) {
        eventLoopGroupTest();
    }

    public static void eventLoopGroupTest() {
        EventLoopGroup group = new DefaultEventLoopGroup(2);
        log.info("group is {}", group.next());
        log.info("group is {}", group.next());
        log.info("group is {}", group.next());
    }
}
