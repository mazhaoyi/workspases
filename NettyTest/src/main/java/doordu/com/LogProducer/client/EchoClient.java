package doordu.com.LogProducer.client;

import java.net.InetSocketAddress;

import doordu.com.LogProducer.client.handler.EchoClientHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;

public class EchoClient {
	private final String host;
	private final int port;
	public EchoClient(String host, int port) {
		this.host = host;
		this.port = port;
	}
	public void start() throws InterruptedException {
		EventLoopGroup group = new NioEventLoopGroup();
		try {
			Bootstrap b = new Bootstrap();
			b.group(group);
			b.channel(SocketChannel.class);
			b.remoteAddress(new InetSocketAddress(host, port));
			b.handler(new ChannelInitializer<SocketChannel>() {

				@Override
				protected void initChannel(SocketChannel ch) throws Exception {
					ch.pipeline().addLast(new EchoClientHandler());
				}
			});
			ChannelFuture cf = b.connect().sync();
			cf.addListener(new ChannelFutureListener() {
				
				@Override
				public void operationComplete(ChannelFuture future) throws Exception {
					if (future.isSuccess()) {
						System.out.println("Client connected !");
					} else {
						System.out.println("Server attemp faild !");
						future.cause().printStackTrace();
					}
				}
			});
			cf.channel().closeFuture().sync();
		} finally {
			group.shutdownGracefully().sync();
		}
	}
	public static void main(String[] args) {
		try {
			new EchoClient("127.0.0.1", 3331).start();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
