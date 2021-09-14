package com.tmax;

import com.tmax.mail.SendMail;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        IntegrityController integrityController = new IntegrityController();
//        integrityController.run();
        SendMail sendMail = new SendMail();
        sendMail.sendMail();
    }

}
