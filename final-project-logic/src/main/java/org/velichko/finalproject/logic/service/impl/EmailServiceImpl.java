package org.velichko.finalproject.logic.service.impl;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.velichko.finalproject.logic.exception.ServiceException;
import org.velichko.finalproject.logic.pool.PropertyLoader;
import org.velichko.finalproject.logic.service.EmailService;

import java.net.URL;
import java.util.Properties;

public class EmailServiceImpl implements EmailService {
    private static final Logger logger = LogManager.getLogger();
    private final URL EMAIL_PROPERTIES_PATH = getClass().getClassLoader().getResource("email.properties");

    @Override
    public boolean sendEmail(String emailTo, String address) throws ServiceException {
        Properties properties = null;
        final String userKey = "mail.smtp.user";
        final String passwordKey = "mail.smtp.password";
        if (EMAIL_PROPERTIES_PATH != null) {
            properties = PropertyLoader.loadPropertiesData(EMAIL_PROPERTIES_PATH);
        }
        final String user = properties != null ? properties.getProperty(userKey) : null;
        final String password = properties != null ? properties.getProperty(passwordKey) : null;
//        properties.remove(userKey); //todo ??
//        properties.remove(passwordKey);

        final Session session = Session.getDefaultInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailTo));
            final String subject = "Email Confirmation";
            message.setSubject(subject);
            final String content = "Click to confirm email: <a href='" + address + "'>link</a>";
            final String contentType = "text/html";
            message.setContent(content, contentType);
            Transport.send(message);
            return true;
        } catch (MessagingException e) {
            logger.log(Level.ERROR, "Error with sending message: ", e);
            throw new ServiceException("Can not send message: ", e);
        }

    }
}
