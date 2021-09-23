package com.tmax.fcheck.cmd;

import exception.CommandExecutionException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Cmd {
    public static String execute(String[] cmd) {
        Process process = null;
        Runtime runtime = Runtime.getRuntime();
        StringBuffer successOutput = new StringBuffer(); // 성공 스트링 버퍼
        StringBuffer errorOutput = new StringBuffer(); // 오류 스트링 버퍼
        BufferedReader successBufferReader = null; // 성공 버퍼
        BufferedReader errorBufferReader = null; // 오류 버퍼
        String msg = null; // 메시지
        String result = null; // 리턴할 결과 값 

        try {
 
            // 명령어 실행
            process = runtime.exec(cmd);

            // shell 실행이 정상 동작했을 경우
            successBufferReader = new BufferedReader(new InputStreamReader(process.getInputStream(), "EUC-KR"));
 
            while ((msg = successBufferReader.readLine()) != null) {
                successOutput.append(msg + System.getProperty("line.separator"));
            }
 
            // shell 실행시 에러가 발생했을 경우
            errorBufferReader = new BufferedReader(new InputStreamReader(process.getErrorStream(), "EUC-KR"));
            while ((msg = errorBufferReader.readLine()) != null) {
                errorOutput.append(msg + System.getProperty("line.separator"));
            }
 
            // 프로세스의 수행이 끝날때까지 대기
            process.waitFor();
 
            // shell 실행이 정상 종료되었을 경우
            if (process.exitValue() == 0) {
                System.out.println(successOutput.toString());
                result = successOutput.toString();
            } else {
                // shell 실행이 비정상 종료되었을 경우
                System.out.print("shell exit error value is Not 0 : ");
                System.out.println(process.exitValue());
                System.out.println(errorOutput.toString()+successOutput.toString());
                result = successOutput.toString() + errorOutput.toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new CommandExecutionException("command execution IO Exception.");
        } catch (InterruptedException e) {
            e.printStackTrace();
            throw new CommandExecutionException("command execution Interrupt Exception.");
        } finally {
            try {
                process.destroy(); 
                if (successBufferReader != null) successBufferReader.close();
                if (errorBufferReader != null) errorBufferReader.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            return result;
        }
    }
}

