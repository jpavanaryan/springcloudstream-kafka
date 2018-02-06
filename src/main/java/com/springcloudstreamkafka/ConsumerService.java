package com.springcloudstreamkafka;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.cloud.stream.messaging.Sink;

@EnableBinding(Sink.class)
public class ConsumerService
{
	@StreamListener(Sink.INPUT)
	public void consume(Project project)
	{
		System.out.println("********************************  Message Received  ********************************  ");
		System.out.println("Project Name: "+project.getProjectName());
	}
}