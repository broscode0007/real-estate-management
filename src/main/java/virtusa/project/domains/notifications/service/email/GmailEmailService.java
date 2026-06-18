package virtusa.project.domains.notifications.service.email;


import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;


/**
 * Gmail SMTP implementation of EmailService.
 */
@Service
@RequiredArgsConstructor
public class GmailEmailService implements EmailService {


    private final JavaMailSender mailSender;


    @Override
    public void sendEmail(
            String to,
            String subject,
            String body
    ) {

        SimpleMailMessage message =
                new SimpleMailMessage();


        message.setTo(to);

        message.setSubject(subject);

        message.setText(body);


        /*
         * Sender address comes from:
         *
         * spring.mail.username
         *
         * configured in application.properties
         */
        mailSender.send(message);
    }
}