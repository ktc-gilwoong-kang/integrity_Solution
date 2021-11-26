package com.tmax;

import com.tmax.fcheck.Fcheck;
import com.tmax.mail.SendMail;
import org.apache.log4j.Logger;

public class IntegrityController {

    private Fcheck fcheck;
    private SendMail sendMail;
    private final int abnormalValue = 10;
    Logger logger = Logger.getRootLogger();

    public IntegrityController(){
        fcheck = new Fcheck();
        sendMail = new SendMail();
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
            sendMail.sendMail(result);
            return;
        }
        periodIntegrityCheck();
    }

    public void periodIntegrityCheck(){
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
    public void databaseUpdate(){
        logger.info("Fcheck DB Repair.");
        fcheck.fcheckUpdate();
    }
}
