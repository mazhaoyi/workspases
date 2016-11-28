package doordu.com.LogProducer.server;

import java.net.InetSocketAddress;

import doordu.com.LogProducer.server.handler.EchoServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class EchoServer {
	private static final int port = 8080;
	
	public void start() throws InterruptedException {
		ServerBootstrap bootstrap = new ServerBootstrap();
		EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
		try {
			bootstrap.group(eventLoopGroup);
			bootstrap.channel(NioServerSocketChannel.class);
			bootstrap.localAddress(new InetSocketAddress(port));
			bootstrap.childHandler(new ChannelInitializer<SocketChannel>() {

				@Override
				protected void initChannel(SocketChannel sc) throws Exception {
					sc.pipeline().addLast(new EchoServerHandler());
				}
			});
			ChannelFuture cf = bootstrap.bind().sync();
			System.out.println(EchoServer.class.getName() + " started and listen on " + cf.channel().localAddress());
			cf.channel().closeFuture().sync();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			eventLoopGroup.shutdownGracefully().sync();
		}
	}
	public static void main(String[] args) {
		try {
			new EchoServer().start();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
