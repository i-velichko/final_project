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

/**
 * @author Ivan Velichko
 *
 * The type Email service.
 */
public class EmailServiceImpl implements EmailService {
    private static final Logger logger = LogManager.getLogger();
    private final URL EMAIL_PROPERTIES_PATH = getClass().getClassLoader().getResource("email.properties");
    private static final String USER_KEY = "mail.smtp.user";
    private static final String PASSWORD_KEY = "mail.smtp.password";
    /**
     * The constant EMAIL_FROM_EPAM_TRAINING_CENTER.
     */
    public static final String EMAIL_FROM_EPAM_TRAINING_CENTER = "Email from EPAM training center";
    /**
     * The constant CONTENT_TYPE.
     */
    public static final String CONTENT_TYPE = "text/html";

    @Override
    public boolean sendEmail(String emailTo, String messageContent) throws ServiceException {
        Properties properties = null;
        if (EMAIL_PROPERTIES_PATH != null) {
            properties = PropertyLoader.loadPropertiesData(EMAIL_PROPERTIES_PATH);
        }
        final String user = properties != null ? properties.getProperty(USER_KEY) : null;
        final String password = properties != null ? properties.getProperty(PASSWORD_KEY) : null;

        final Session session = Session.getDefaultInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, password);
            }
        });
        try {
            Message message = new MimeMessage(session);
            if (user != null) {
                message.setFrom(new InternetAddress(user));
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailTo));
                message.setSubject(EMAIL_FROM_EPAM_TRAINING_CENTER);
                message.setContent(messageContent, CONTENT_TYPE);
                Transport.send(message);
            }
            return true;
        } catch (MessagingException e) {
            logger.log(Level.ERROR, "Error with sending message: ", e);
            throw new ServiceException("Can not send message: ", e);
        }

    }
}
