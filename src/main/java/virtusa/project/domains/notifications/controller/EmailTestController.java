package virtusa.project.domains.notifications.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import virtusa.project.domains.notifications.service.email.EmailService;


@RestController
@RequiredArgsConstructor
public class EmailTestController {


    private final EmailService emailService;


    @GetMapping("/api/v1/test/email")
    public String sendTestEmail(
            @RequestParam String to
    ) {

        emailService.sendEmail(
                to,
                "Real Estate Backend Test Email",
                """
                Hello,

                This is a test email from Gmail SMTP.

                If you received this email,
                your email configuration works.
                """
        );

        return "Email sent successfully";
    }
}