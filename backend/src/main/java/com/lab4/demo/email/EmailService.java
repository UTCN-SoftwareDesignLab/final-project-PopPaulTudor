package com.lab4.demo.email;

import com.lab4.demo.user.UserRepository;
import com.lab4.demo.user.dto.UserMinimalDTO;
import com.lab4.demo.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final UserRepository userRepository;

    public void sendMail(long id) throws Exception {
        User user = userRepository.findById(id).get();
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("tudor.purpuriu@gmail.com", "tudorPurpuriuMaNumesc123");
            }
        });
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(user.getEmail(), false));

        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(user.getEmail()));
        msg.setSubject("Welcome to the group");
        msg.setContent("Hello, " + user.getUsername() + ",\n  Congratulations for joining the group!\n Have fun", "text/html");
        msg.setSentDate(new Date());


        Transport.send(msg);
    }
}
