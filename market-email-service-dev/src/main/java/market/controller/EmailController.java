package market.controller;

import market.dto.MailMessageDto;
import market.model.MailLanguages;
import market.service.MailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;


// Тестовый реактивный REST контроллер на отправку писем
@RestController
public class EmailController {

    private final MailService mailService;

    public EmailController(MailService mailService) {
        this.mailService = mailService;
    }

    @GetMapping(value = "/email/{user-email}/{username}")
    public Mono<ResponseEntity> sendEmail(@PathVariable("user-email") String email,
                                          @PathVariable("username") String username) {
        MailMessageDto mailMessageDto = new MailMessageDto(email, username, "", MailLanguages.RU);
        return Mono.fromCallable(() -> {
                    try {
                        mailService.sendMessageUsingFreemarkerTemplate(mailMessageDto);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return "Send email " + email;
                })
                .map(ResponseEntity::ok);
    }

}



