package com.javhl.course.fstransfer.netty.client;

import com.javhl.course.fstransfer.FsManager;
import com.javhl.course.fstransfer.netty.FsDecoder;
import com.javhl.course.fstransfer.netty.FsEncoder;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class FsClient {

    private String host;
    private int port;
    private String loaderId;

    public FsClient(String host,int port,String loaderId){

        this.host = host;
        this.port = port;
        this.loaderId = loaderId;
    }

    public void run(){

        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            Bootstrap b = new Bootstrap(); // (1)
            b.group(workerGroup); // (2)
            b.channel(NioSocketChannel.class); // (3)
            b.option(ChannelOption.SO_KEEPALIVE, true); // (4)
            b.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel ch) throws Exception {

                    ch.pipeline().addLast(new FsEncoder(),new FsDecoder(),new ClientHandler(ClientTypeEnum.FILE_SENDER,loaderId));
                }
            });

            // Start the client.
            ChannelFuture f = b.connect(host, port).sync(); // (5)

            // Wait until the connection is closed.
            f.channel().closeFuture().sync();

        }catch (InterruptedException e){

            e.printStackTrace();
        }
        finally {

            workerGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args){

        FsManager.getSingleton().init("D:\\tmp\\fstransfer","D:\\tmp");
        new FsClient("127.0.0.1",8080,"test").run();
    }
}
