package com.opskuba.debugger;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;


@SpringBootApplication
@EnableRabbit
public class OpsDebuggerApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(OpsDebuggerApplication.class, args);
	}

	
	//基于嵌入式的tomcat，启用websocket。
	//http://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#howto-create-websocket-endpoints-using-serverendpoint
	@Bean
	public ServerEndpointExporter serverEndpointExporter() {
	    return new ServerEndpointExporter();
	}
}
