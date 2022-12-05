package org.rishi.automation.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class TerminalUtils {

    public static void runTerminalCommand(String command, String logText){
        try{
            String path = System.getProperty("user.dir");
            ProcessBuilder builder = new ProcessBuilder(
                    "cmd.exe", "/c", "cd \""+path+"\" && "+command);
            builder.redirectErrorStream(true);
            Process p = builder.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                if(line.contains(logText)) {
                    System.out.println(line);
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void runTerminalCommandOnLinux(String command, String logText){
        try{
            String path = System.getProperty("user.dir");
            ProcessBuilder builder = new ProcessBuilder();
            builder.command("sh", "-c", path + command);
            builder.redirectErrorStream(true);
            Process p = builder.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                if(line.contains(logText)) {
                    System.out.println(line);
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
