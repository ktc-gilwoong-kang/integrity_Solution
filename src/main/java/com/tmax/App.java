package com.tmax;


import org.apache.log4j.PropertyConfigurator;

public class App
{
    public static void main( String[] args )
    {
        PropertyConfigurator.configure(System.getProperty("user.dir") + "/src/main/java/com/tmax/log4j.properties");
        IntegrityController integrityController = new IntegrityController();
        integrityController.run();

    }

}