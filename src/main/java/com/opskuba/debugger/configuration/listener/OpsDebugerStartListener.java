package com.opskuba.debugger.configuration.listener;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;

import com.opskuba.debugger.consumer.IMProtocolAuditConsumer;


/**
 * 初始化启动
 */
public class OpsDebugerStartListener implements ApplicationListener<ApplicationStartedEvent> {
	
	private final ExecutorService executorService = Executors.newFixedThreadPool(2);

	@Override
	public void onApplicationEvent(ApplicationStartedEvent event) {
		//跟踪进站协议
		executorService.submit(new IMProtocolAuditConsumer("uplus.websock.inbound.exchanges"));
		
		
		//executorService.submit(new IMProtocolAuditConsumer("uplus.websock.outbound.exchanges"));
	}

}
