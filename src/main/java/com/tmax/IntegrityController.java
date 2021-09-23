package com.tmax;

import com.tmax.fcheck.Fcheck;
import com.tmax.mail.SendMail;

public class IntegrityController {

    private Fcheck fcheck;
    private SendMail sendMail;
    private final int abnormalValue = 10;

    public IntegrityController(){
        fcheck = new Fcheck();
        sendMail = new SendMail();
    }

    public void dataUpdate(){
        fcheck.fcheckUpdate();
    }

    public void run(){
        String result = fcheck.fcheckRun();
        if(result.length() > abnormalValue){
            System.out.println("Integrity Error. System is NOT Stable.");
            sendMail.sendMail(result);
            return;
        }
        periodIntegrityCheck();
    }

    public void periodIntegrityCheck(){
        while(true){
            System.out.println("Integrity check");
            String report = fcheck.fcheckRun();
            if(report.length()>abnormalValue){
                System.out.println("integrity problem. Detail : ");
                sendMail.sendMail(fcheck.fcheckRunWithLog());
                break;
            }else{
                System.out.println("System Integrity check success. is stable. ["+System.currentTimeMillis()+"]");
            try{
                Thread.sleep(15000);
            }catch(InterruptedException e){
                System.out.println("Interrupt.");
                e.printStackTrace();
            }
               
            }
        }
    }
}
