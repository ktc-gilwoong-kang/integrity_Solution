package com.tmax;


import com.tmax.fcheck.IntegrityController;
import org.apache.log4j.PropertyConfigurator;

public class App
{
    public static void main( String[] args )
    {
//        PropertyConfigurator.configure(System.getProperty("user.dir") + "/src/main/resources/prop/log4j.properties");
        IntegrityController integrityController = new IntegrityController();
        integrityController.run();

    }

}