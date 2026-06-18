package virtusa.project.domains.notifications.service.email;


/**
 * Generic email service.
 *
 * The application will use this interface,
 * so we can switch providers later:
 *
 * Gmail SMTP
 * AWS SES
 * SendGrid
 */
public interface EmailService {


    /**
     * Send a simple text email.
     *
     * @param to Recipient email address
     * @param subject Email subject
     * @param body Email message body
     */
    void sendEmail(
            String to,
            String subject,
            String body
    );
}