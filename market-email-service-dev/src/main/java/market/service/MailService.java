package market.service;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import market.dto.MailMessageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@Service
public class MailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private FreeMarkerConfigurer freemarkerConfigurer;

    @Value("${spring.mail.username}")
    private String mailMarket;

//    public void sender(String emailTo,String subject, String text) { //простое письмо
//        SimpleMailMessage msg = new SimpleMailMessage();
//        msg.setFrom(mailMarket);
//        msg.setTo(emailTo);
//        msg.setSubject(subject);
//        msg.setText(text);
//        javaMailSender.send(msg);
//    }

    public void sendMessageUsingFreemarkerTemplate(MailMessageDto mailMessageDto)
            throws IOException, TemplateException, MessagingException {

        Map<String, Object> templateModel = new HashMap<>();
        templateModel.put("username", mailMessageDto.getUsername());

        if (mailMessageDto.getMailLanguages()
                .getLanguage()
                .contains("RU")) {

            Template freemarkerTemplate = freemarkerConfigurer.getConfiguration()
                    .getTemplate("welcome-message-ru.ftl");
            String htmlBody = FreeMarkerTemplateUtils.processTemplateIntoString(freemarkerTemplate, templateModel);

            sendEmailByEmailAndSubjectAndHtmlBody(mailMessageDto.getTo(), mailMessageDto.getSubject(), htmlBody);
        } else {
            Template freemarkerTemplate = freemarkerConfigurer.getConfiguration()
                    .getTemplate("welcome-message-eng.ftl");
            String htmlBody = FreeMarkerTemplateUtils.processTemplateIntoString(freemarkerTemplate, templateModel);

            sendEmailByEmailAndSubjectAndHtmlBody(mailMessageDto.getTo(), mailMessageDto.getSubject(), htmlBody);
        }


    }

    private void sendEmailByEmailAndSubjectAndHtmlBody(String emailto, String subject, String htmlBody) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
        helper.setTo(emailto);
        helper.setSubject(subject);
        helper.setText(htmlBody, true);
        javaMailSender.send(message);
        System.out.println("message sent");
    }

    public void sendBirthdayMessage (String username, String email) throws IOException, TemplateException, MessagingException {
        Map <String, Object> templateModel = new HashMap<>();

        templateModel.put("username", username);

        Template template = freemarkerConfigurer.getConfiguration().getTemplate("birthday-message.ftl");
        String htmlbody = FreeMarkerTemplateUtils.processTemplateIntoString(template, templateModel);
        String subject = "Birthday";

        sendEmailByEmailAndSubjectAndHtmlBody(email, subject, htmlbody);
    }

}

