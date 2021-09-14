package com.tmax.fcheck.cmd;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class FcheckCommand {
    private Properties integrityProperties;
    private final static String CONFIG_PATH = "/Users/gilwoongkang/personalLibrary/tmax/supervm-cc/integrity/src/main/java/com/tmax/fcheck/cmd/commandConfig.properties";
    public FcheckCommand(){
        try{
            integrityProperties = new Properties();
            integrityProperties.load(new FileInputStream(CONFIG_PATH));
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public String[] getFcheckUpdateCommand(){
        String[] cmd = {integrityProperties.getProperty("shell"),
        integrityProperties.getProperty("shell.option"),
        integrityProperties.getProperty("fcheck.path")+integrityProperties.getProperty("fcheck.update")
        };
        return cmd;
    }
    public String[] getFcheckRunCommand(){
        String[] cmd = {integrityProperties.getProperty("shell"),
        integrityProperties.getProperty("shell.option"),
        integrityProperties.getProperty("fcheck.path")+integrityProperties.getProperty("fcheck.run")+
        integrityProperties.getProperty("grep")+integrityProperties.getProperty("grep.total")};
        return cmd;
    }

    public String[] getFcheckRunCommandWithoutGrep(){
        String[] cmd = {integrityProperties.getProperty("shell"),
        integrityProperties.getProperty("shell.option"),
        integrityProperties.getProperty("fcheck.path")+integrityProperties.getProperty("fcheck.run")};
        return cmd;
    }
}