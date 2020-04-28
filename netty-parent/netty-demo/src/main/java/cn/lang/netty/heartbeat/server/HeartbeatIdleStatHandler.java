package cn.lang.netty.heartbeat.server;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.concurrent.EventExecutorGroup;

public class HeartbeatIdleStatHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent){
            IdleStateEvent event = (IdleStateEvent) evt;
            String stat = this.transStat(event);
            System.out.println(stat);
        }
    }

    private String transStat(IdleStateEvent event){
        switch (event.state()){
            case READER_IDLE:
                return "读超时";
            case WRITER_IDLE:
                return "写超时";
            case ALL_IDLE:
                return "都超时";
            default:return null;
        }
    }
}
