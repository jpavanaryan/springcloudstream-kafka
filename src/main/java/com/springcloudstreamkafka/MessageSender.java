package com.springcloudstreamkafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
@EnableBinding(Source.class)
public class MessageSender 
{
  
  @Autowired
  private Source source; 
  
  public void send(Message<?> m) 
  {
    try 
    {
      // avoid too much magic and transform ourselves
      ObjectMapper mapper = new ObjectMapper();
      String jsonMessage = mapper.writeValueAsString(m);
      
      // wrap into a proper message for the transport (Kafka/Rabbit) and send it      
      source.output().send(MessageBuilder.withPayload(jsonMessage).build());
    } 
    
    catch (Exception e) 
    {
      throw new RuntimeException("Could not tranform and send message due to: "+ e.getMessage(), e);
    }
  }
}