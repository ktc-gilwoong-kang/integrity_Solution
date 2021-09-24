package com.tmax.fcheck.cmd;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class FcheckCommand {
    private Properties integrityProperties;
    private final static String CONFIG_PATH = "/home/integrity/src/main/java/com/tmax/fcheck/cmd/commandConfig.properties";
    public FcheckCommand(){
        try{
            integrityProperties = new Properties();
            integrityProperties.load(new FileInputStream(CONFIG_PATH));
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public String[] getFcheckUpdateCommand(){
        return new String[]{integrityProperties.getProperty("shell"),
        integrityProperties.getProperty("shell.option"),
        integrityProperties.getProperty("fcheck.path")+integrityProperties.getProperty("fcheck.update")
        };
    }
    public String[] getFcheckRunCommand(){
        return new String[]{integrityProperties.getProperty("shell"),
        integrityProperties.getProperty("shell.option"),
        integrityProperties.getProperty("fcheck.path")+integrityProperties.getProperty("fcheck.run")+
        integrityProperties.getProperty("grep")+integrityProperties.getProperty("grep.total")};
    }

    public String[] getFcheckRunCommandWithoutGrep(){
        return new String[]{integrityProperties.getProperty("shell"),
        integrityProperties.getProperty("shell.option"),
        integrityProperties.getProperty("fcheck.path")+integrityProperties.getProperty("fcheck.run")};
    }
}