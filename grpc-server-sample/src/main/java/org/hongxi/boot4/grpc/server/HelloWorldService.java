package org.hongxi.boot4.grpc.server;

import io.grpc.stub.StreamObserver;
import org.hongxi.boot4.proto.HelloReply;
import org.hongxi.boot4.proto.HelloRequest;
import org.hongxi.boot4.proto.HelloWorldGrpc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.grpc.server.service.GrpcService;
import org.springframework.util.Assert;

@GrpcService
public class HelloWorldService extends HelloWorldGrpc.HelloWorldImplBase {

	private static final Logger log = LoggerFactory.getLogger(HelloWorldService.class);

	@Override
	public void sayHello(HelloRequest request, StreamObserver<HelloReply> responseObserver) {
		String name = request.getName();
        log.info("sayHello {}", name);
		Assert.isTrue(!name.startsWith("error"), () -> "Bad name: " + name);
		Assert.state(!name.startsWith("internal"), "Internal error");
		String message = "Hello '%s'".formatted(name);
		HelloReply reply = HelloReply.newBuilder().setMessage(message).build();
		responseObserver.onNext(reply);
		responseObserver.onCompleted();
	}

	@Override
	public void streamHello(HelloRequest request, StreamObserver<HelloReply> responseObserver) {
		String name = request.getName();
        log.info("streamHello {}", name);
		int count = 0;
		while (count < 10) {
			String message = "Hello(" + count + ") '%s'".formatted(name);
			HelloReply reply = HelloReply.newBuilder().setMessage(message).build();
			responseObserver.onNext(reply);
			count++;
			try {
				Thread.sleep(100L);
			}
			catch (InterruptedException ex) {
				Thread.currentThread().interrupt();
				responseObserver.onError(ex);
				return;
			}
		}
		responseObserver.onCompleted();
	}

}