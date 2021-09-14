package com.tmax.mail;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class SendMail {

    Session session;
    Properties prop = new Properties();

    public SendMail(){
        try {
            prop.load(new FileInputStream("/Users/gilwoongkang/personalLibrary/tmax/supervm-cc/integrity/src/main/java/com/tmax/mail/mailConfig.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        session = Session.getDefaultInstance(prop, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication(prop.getProperty("sender"),prop.getProperty("sender.password"));
            }
        });
    }
    public void sendMail(){
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(prop.getProperty("sender")));
            message.addRecipient(Message.RecipientType.TO,new InternetAddress(prop.getProperty("sender")));
            message.setSubject("테스트 메세지");
            message.setText("테스트 내용");
            Transport.send(message);
            System.out.println("message sent successfully...");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
