package liga.medical.messageanalyzer.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import liga.medical.dto.Message;
import liga.medical.messageanalyzer.config.RabbitMQConfig;
import liga.medical.messageanalyzer.service.AcceptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AcceptController {

    AcceptService acceptService;

    @Autowired
    public AcceptController(AcceptService acceptService) {
        this.acceptService = acceptService;
    }

    @PostMapping("/add")
    public void sendMessage(@RequestBody Message message) throws JsonProcessingException {
        acceptService.sendMessage(message, RabbitMQConfig.ROUTER_QUEUE_NAME);
    }


}
