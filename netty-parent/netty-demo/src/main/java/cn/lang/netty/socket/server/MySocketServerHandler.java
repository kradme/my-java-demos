package cn.lang.netty.socket.server;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.concurrent.EventExecutorGroup;

import java.nio.channels.SocketChannel;

public class MySocketServerHandler extends SimpleChannelInboundHandler<String> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println("收到-"+ctx.channel().remoteAddress()+"请求："+msg);
        ctx.writeAndFlush("boss has received");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("处理异常");
        cause.printStackTrace();
        ctx.close();
    }
}
