package com.opskuba.debugger.consumer;

import java.io.IOException;
import java.util.concurrent.Callable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opskuba.debugger.configuration.OpsDebuggerConstants;
import com.opskuba.debugger.service.IMProtocolService;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.ConsumerCancelledException;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.ShutdownSignalException;


public class IMProtocolAuditConsumer implements Callable<Integer>{
	
	private Logger logger = LoggerFactory.getLogger(IMProtocolAuditConsumer.class);
	
	private final String exchangeName;
	private final Byte flag;
	
	public IMProtocolAuditConsumer(String exchangeName,Byte flag) {
		this.exchangeName = exchangeName;
		this.flag = flag;
	}

	@Override
	public Integer call() throws Exception {
		
		ConnectionFactory factory = new ConnectionFactory();
		factory.setAutomaticRecoveryEnabled(true);

		factory.setHost("moplus.queue.youja.cn");
		factory.setPort(5600);
		factory.setVirtualHost("message");
		factory.setUsername("message");
		factory.setPassword("message2014");
		
		factory.setRequestedHeartbeat(5);
		factory.setConnectionTimeout(3000);
		
		try {
			Connection connection = factory.newConnection();
			Channel channel= connection.createChannel();
			
			channel.exchangeDeclare(exchangeName, "fanout", true);
			String queueName = channel.queueDeclare().getQueue();
			channel.queueBind(queueName, exchangeName, "");
			
			QueueingConsumer consumer = new QueueingConsumer(channel);
			channel.basicConsume(queueName, true, consumer);
			
			while (true) {
				try {
					
					if (OpsDebuggerConstants.ctx == null
							|| null == OpsDebuggerConstants.ctx.getBean("imProtocolService")) {
						Thread.sleep(1000);
						continue;
					}else{
						
						QueueingConsumer.Delivery delivery = consumer.nextDelivery();
						String message = new String(delivery.getBody());
						
						IMProtocolService imProtocolService = (IMProtocolService) OpsDebuggerConstants.ctx
								.getBean("imProtocolService");
						
						try {
							imProtocolService.audit(message, flag);
						} catch (Exception e) {
							logger.error("写入数据库异常",e);
						}
					}
					
				} catch (ShutdownSignalException | ConsumerCancelledException | InterruptedException e) {
					logger.error("消费["+exchangeName+"]中的消息异常",e);
				}
			}
		} catch (IOException e) {
			logger.error("连接["+exchangeName+"]失败",e);
		}
		return 0;
	}
	

}
