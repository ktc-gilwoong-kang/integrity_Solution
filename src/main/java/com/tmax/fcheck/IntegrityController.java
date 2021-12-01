package com.tmax.fcheck;

import com.tmax.fcheck.mail.SendMail;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.io.IOException;

public class IntegrityController {

    private Fcheck fcheck;
    private SendMail sendMail;
    private final int abnormalValue = 10;
    Logger logger = Logger.getRootLogger();
    private PropertiesConfig propertiesConfig;

    public IntegrityController(){
        PropertyConfigurator.configure("src/main/java/com/tmax/fcheck/prop/log4j.properties");
        fcheck = new Fcheck();
        sendMail = new SendMail();
        propertiesConfig = new PropertiesConfig();
    }

    public void dataUpdate(){
        fcheck.fcheckUpdate();
        logger.info("fcheck update. ");
    }

    public void run(){
        String result = fcheck.fcheckRun();
        if(result.length() > abnormalValue){
            logger.info("Integrity Error. System is NOT Stable.");
//            System.out.println("Integrity Error. System is NOT Stable.");
//            sendMail.sendMail(result);
            return;
        }
        Runnable periodIntegrityRunnable = new Runnable() {
            @Override
            public void run() {
                logger.info("perform periodic verification.");
                while(true){
                    logger.info("Integrity check...");
//            System.out.println("Integrity check");
                    String report = fcheck.fcheckRun();
                    if(report.length()>abnormalValue){
                        logger.error("integrity problem. Detail");
//                System.out.println("integrity problem. Detail : ");
                        sendMail.sendMail(fcheck.fcheckRunWithLog());
                        break;
                    }else{
                        logger.info("System Integrity check success. is stable. ["+System.currentTimeMillis()+"]");
//                System.out.println("System Integrity check success. is stable. ["+System.currentTimeMillis()+"]");
                        try{
                            Thread.sleep(15000);
                        }catch(InterruptedException e){
                            logger.error("System interrupt.");
//                System.out.println("Interrupt.");
                            e.printStackTrace();
                        }

                    }
                }
            }
        };
        Thread periodIntegrityCheckThread = new Thread(periodIntegrityRunnable);
        periodIntegrityRunnable.run();
    }
    public void fcheckConfigModify (String newPath){
        try {
            propertiesConfig.fcheckPathModify(newPath);
            fcheck.configReload();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void mailConfigModify(String sender,String senderPw,String mailSmtpHost,
                                 String mailSmtpPort, String auth,String sslEnable,String sslTrust){
        try {
            propertiesConfig.mailconfigModify(sender,senderPw,mailSmtpHost,mailSmtpPort,auth,sslEnable,sslTrust);
            sendMail.configReload();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
