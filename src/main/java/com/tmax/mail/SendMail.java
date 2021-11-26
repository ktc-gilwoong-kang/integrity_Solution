package com.tmax.mail;

import org.apache.log4j.Logger;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class SendMail {

    Session session;
    Properties prop = new Properties();
    Logger logger = Logger.getRootLogger();

    public SendMail(){
        try {
//            prop.load(new FileInputStream("/home/integrity/src/main/java/com/tmax/mail/mailConfig.properties"));
            prop.load(new FileInputStream("/Users/gilwoongkang/personalLibrary/tmax/supervm-cc/integrity/src/main/java/com/tmax/mail/mailConfig.properties"));
            logger.info("Prop file load.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        session = Session.getDefaultInstance(prop, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication(prop.getProperty("sender"),prop.getProperty("sender.password"));
            }
        });
        logger.info("Mail session create.");
    }
    public void sendMail(String errorlog){
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(prop.getProperty("sender")));
            message.addRecipient(Message.RecipientType.TO,new InternetAddress(prop.getProperty("sender")));
            message.setSubject("SuperVM Integrity Error Report");
            message.setText("Integrity check fail. please confirm Error log.\n" + errorlog);
            Transport.send(message);
            logger.info("message send. {subject : "
            +message.getSubject() + ", from : " + message.getFrom()[0].toString() + "}");
//            System.out.println("message sent successfully...");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
