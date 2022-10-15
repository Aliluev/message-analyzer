package liga.medical.messageanalyzer.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import liga.medical.dto.Message;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AcceptService {

    private final AmqpTemplate amqpTemplate;

    private final ObjectMapper objectMapper;

    @Autowired
    public AcceptService(AmqpTemplate amqpTemplate, ObjectMapper objectMapper) {
        this.amqpTemplate = amqpTemplate;
        this.objectMapper = objectMapper;
    }

    public void sendMessage(Message message, String queue) throws JsonProcessingException {
        String messageStr = objectMapper.writeValueAsString(message);
        amqpTemplate.convertAndSend(queue,messageStr);
        System.out.println("Сообщение" + messageStr + "отправленно");
    }


}
