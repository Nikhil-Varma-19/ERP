package com.example.erp.backend.utilizs;

import com.example.erp.backend.dtos.logger_dtos.EmailLoggerDto;
import com.example.erp.backend.entities.EmailDetail;
import com.example.erp.backend.exceptions.EmailException;
import com.example.erp.backend.repositories.EmailDetailResp;
import com.example.erp.backend.services.LoggerService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;
import org.springframework.mail.MailException;

import java.util.Optional;
import java.util.Properties;


@Service
@RequiredArgsConstructor
public class EmailServices {
    private final EmailDetailResp emailDetailResp;
    private final LoggerService loggerService;


    public void sendEmail(String[] toMail,String subject,String content,String[] ccMail){
        String others = ccMail != null ? String.join(",", ccMail) : null;
        try{
            Optional<EmailDetail> emailDetail=emailDetailResp.getEmailData();
            if(emailDetail.isEmpty()) throw  new EmailException("Email Data is missing.");

            JavaMailSenderImpl mailSender=new JavaMailSenderImpl();
            mailSender.setHost(emailDetail.get().getHost());
            mailSender.setPort(emailDetail.get().getPort());
            mailSender.setUsername(emailDetail.get().getUsername());
            mailSender.setPassword(emailDetail.get().getPassword());
            Properties properties=mailSender.getJavaMailProperties();
            properties.put("mail.smtp.auth",String.valueOf(emailDetail.get().isAuth()));
            properties.put("mail.smtp.starttls.enable",String.valueOf(emailDetail.get().isStarttls()));


            SimpleMailMessage mail=new SimpleMailMessage();
            if(ccMail != null && ccMail.length > 0) mail.setCc(ccMail);
            mail.setSubject(subject);
            mail.setTo(toMail);
            mail.setText(content);

            mailSender.send(mail);

            EmailLoggerDto emailLoggerDto=EmailLoggerDto.builder()
                    .body(content).error(null).toMail(String.join(",",toMail))
                    .others(others).subject(subject)
                    .build();


            loggerService.createEmailLogger(emailLoggerDto);

        } catch (MailException  e) {
            EmailLoggerDto emailLoggerDto=EmailLoggerDto.builder()
                    .body(content).error(e.getMessage()).toMail(String.join(",",toMail))
                    .others(others).subject(subject)
                    .build();
            loggerService.createEmailLogger(emailLoggerDto);
            throw new EmailException("Failed to send mail: "+e.getMessage());
        }
    }
}
