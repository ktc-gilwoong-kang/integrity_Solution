package com.tmax.fcheck;


import com.tmax.fcheck.cmd.Cmd;
import com.tmax.fcheck.cmd.FcheckCommand;
import org.apache.log4j.Logger;

import java.io.IOException;

public class Fcheck {
    private FcheckCommand fcheckCommand;
    Logger logger = Logger.getRootLogger();

    public Fcheck(){
        fcheckCommand = new FcheckCommand();
    }

    public boolean fcheckUpdate(){
        try{
            logger.info(Cmd.execute(fcheckCommand.getFcheckUpdateCommand()));
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
    public String fcheckRun(){
        try {
            String result = Cmd.execute(fcheckCommand.getFcheckRunCommand());
            if(!result.equals(""))logger.error(result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public String fcheckRunWithLog(){
        try {
            String result = Cmd.execute(fcheckCommand.getFcheckRunCommandWithoutGrep());
            logger.error(result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public void configReload() throws IOException {
        fcheckCommand.reloadFcheckConfig();
    }
}
