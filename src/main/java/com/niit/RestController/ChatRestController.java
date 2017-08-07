package com.niit.RestController;

import java.util.Date;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.niit.model.Message;
import com.niit.model.OutputMessage;

@Controller
@RequestMapping("/")
public class ChatRestController {

  
    
  @MessageMapping("/chat")
  @SendTo("/topic/message")
  public OutputMessage sendMessage( Message message) {
	 // System.out.println(message);
    return new OutputMessage(message, new Date());
  }
}
