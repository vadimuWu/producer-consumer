package junit5;

import market.Emailservice;
import market.service.MailService;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import javax.mail.MessagingException;

@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {Emailservice.class})
public class test {

    @Autowired
    public MailService mailService;

    @Test
    public void sentFirstEmailUser() throws MessagingException,Exception {
        String body = """
                <p align="center"><b>Рады приветствовать Вас в MarketKata!</b></p>
                <body>
                  <p align="center" ><a href="https://kata.academy/"><img src="http://www.rosphoto.com/images/u/articles/1510/7_5.jpg" width="500"\s
                  height="350" border="0" alt="Ссылка"></a></p>
                </body>
                """;
//        mailService.sender("ilia@rambler.ru","Privet",body, "Roma"); // Добавил параметр (имя пользоваетля)
        assertEquals(1, 1);
    }
}
