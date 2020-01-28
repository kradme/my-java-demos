package cn.lang.netty.socketChat.server;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.concurrent.EventExecutorGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.util.concurrent.Executors;

public class MyChatServerHandler extends SimpleChannelInboundHandler<String> {

    private static ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        channels.writeAndFlush(msg);
        channels.forEach(channel -> {
            if (channel==ctx.channel()){
                channel.writeAndFlush("【我】："+msg+"\r\n");
            }else {
                channel.writeAndFlush("【"+ctx.channel().remoteAddress()+"】："+msg+"\r\n");
            }
        });
    }
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        channels.add(ctx.channel());
        channels.writeAndFlush("客户端服务器["+ctx.channel().remoteAddress()+"]已连接\r\n");
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        channels.remove(ctx.channel());
        channels.writeAndFlush("客户端服务器["+ctx.channel().remoteAddress()+"]断开连接\r\n");
    }


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        channels.writeAndFlush("客户端["+ctx.channel().remoteAddress()+"]上线\r\n");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        channels.writeAndFlush("客户端["+ctx.channel().remoteAddress()+"]下线\r\n");
    }
}
