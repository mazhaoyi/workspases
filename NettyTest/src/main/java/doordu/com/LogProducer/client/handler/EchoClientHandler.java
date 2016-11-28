package doordu.com.LogProducer.client.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;
import io.netty.channel.ChannelHandler.Sharable;

@Sharable
public class EchoClientHandler extends SimpleChannelInboundHandler<ByteBuf> {
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		ctx.write(Unpooled.copiedBuffer("hello server !", CharsetUtil.UTF_8));
	}

	@Override
	protected void channelRead0(ChannelHandlerContext arg0, ByteBuf arg1) throws Exception {
		System.out.println("Client receive: " + ByteBufUtil.hexDump(arg1.readBytes(arg1.readableBytes())));
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}

}
