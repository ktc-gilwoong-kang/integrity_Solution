package com.tmax.fcheck;


import com.tmax.fcheck.cmd.Cmd;
import com.tmax.fcheck.cmd.FcheckCommand;

public class Fcheck {
    private FcheckCommand fcheckCommand;
    
    public Fcheck(){
        fcheckCommand = new FcheckCommand();
    }

    public boolean fcheckUpdate(){
        try{
            Cmd.execute(fcheckCommand.getFcheckUpdateCommand());
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
    public String fcheckRun(){
        try {
            return Cmd.execute(fcheckCommand.getFcheckRunCommand());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public String fcheckRunWithLog(){
        try {
            return Cmd.execute(fcheckCommand.getFcheckRunCommandWithoutGrep());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
