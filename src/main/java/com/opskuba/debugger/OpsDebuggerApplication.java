package com.opskuba.debugger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

import com.opskuba.debugger.configuration.OpsDebuggerConstants;
import com.opskuba.debugger.configuration.banner.OpsDebuggerBanner;
import com.opskuba.debugger.configuration.listener.OpsDebugerStartListener;


@SpringBootApplication
public class OpsDebuggerApplication extends SpringBootServletInitializer{
	
	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(OpsDebuggerApplication.class);
		app.setBanner(new OpsDebuggerBanner());
		app.setShowBanner(true);
		app.addListeners(new OpsDebugerStartListener());
		OpsDebuggerConstants.ctx = app.run(args);
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder applicationBuilder) {
		applicationBuilder.banner(new OpsDebuggerBanner());
		applicationBuilder.showBanner(true);
		applicationBuilder.listeners(new OpsDebugerStartListener());
		return applicationBuilder.sources(OpsDebuggerApplication.class);
	}

	
	//基于嵌入式的tomcat，启用websocket。
	//http://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#howto-create-websocket-endpoints-using-serverendpoint
//	@Bean
//	public ServerEndpointExporter serverEndpointExporter() {
//	    return new ServerEndpointExporter();
//	}
//	
}
