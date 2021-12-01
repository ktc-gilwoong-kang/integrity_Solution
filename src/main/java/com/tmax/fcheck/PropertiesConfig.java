package com.tmax.fcheck;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesConfig {
    final static String commandConfigFile = "src/main/java/com/tmax/fcheck/prop/commandConfig.properties";
    final static String mailConfigFile =  "src/main/java/com/tmax/fcheck/prop/mailConfig.properties";
    Properties commandProp;
    Properties mailProp;

    public PropertiesConfig() {
        try {
            commandProp = new Properties();
            mailProp = new Properties();
            commandProp.load(new FileInputStream(commandConfigFile));
            mailProp.load(new FileInputStream(mailConfigFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void fcheckPathModify( String newPath) throws IOException {
        commandProp.setProperty("fcheck.path",newPath);
        commandProp.store(new FileOutputStream(commandConfigFile),"");
    }
    public void mailconfigModify(String sender,String senderPw,String mailSmtpHost,
                                 String mailSmtpPort, String auth,String sslEnable,String sslTrust) throws IOException {
        mailProp.setProperty("sender",sender);
        mailProp.setProperty("sender.password",senderPw);
        mailProp.setProperty("mail.smtp.host",mailSmtpHost);
        mailProp.setProperty("mail.smtp.port",mailSmtpPort);
        mailProp.setProperty("mail.smtp.auth",auth);
        mailProp.setProperty("mail.smtp.ssl.enable",sslEnable);
        mailProp.setProperty("mail.smtp.ssl.trust",sslTrust);
        mailProp.store(new FileOutputStream(mailConfigFile),"");
    }
}