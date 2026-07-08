package org.hongxi.boot4.grpc.client;

import org.hongxi.boot4.proto.HelloRequest;
import org.hongxi.boot4.proto.HelloWorldGrpc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {
    private static final Logger log = LoggerFactory.getLogger(SampleController.class);

    private final HelloWorldGrpc.HelloWorldBlockingStub hello;

    public SampleController(HelloWorldGrpc.HelloWorldBlockingStub hello) {
        this.hello = hello;
    }

    @GetMapping("/hello")
    public String hello(String name) {
        log.info(">>> {}", name);
        return hello.sayHello(HelloRequest.newBuilder().setName(name).build()).getMessage();
    }
}
