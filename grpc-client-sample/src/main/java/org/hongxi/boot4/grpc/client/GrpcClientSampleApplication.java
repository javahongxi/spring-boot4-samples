package org.hongxi.boot4.grpc.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.grpc.client.ImportGrpcClients;

@SpringBootApplication
@ImportGrpcClients(basePackages = {
        "org.hongxi.boot4.proto"
})
public class GrpcClientSampleApplication {
    public static void main(String[] args) {
        SpringApplication.run(GrpcClientSampleApplication.class, args);
    }
}
