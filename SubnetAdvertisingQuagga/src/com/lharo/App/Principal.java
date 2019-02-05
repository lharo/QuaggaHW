package com.lharo.App;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.net.telnet.TelnetClient;

public class Principal {
    
    static InputStream in;
    static PrintStream out;

    
    public static void advertiseNetwork(Boolean status, String networkToAdvertise) {
        
        try {
            String serverName = "172.25.150.200";
            String user="zebra";
            TelnetClient telnet = new TelnetClient();
            //Abro la conexión al telnet por el puerto 2605
            
            System.out.println("Conectando a Quagga Router");
            telnet.connect(serverName, 2605);
            in = telnet.getInputStream();
            out = new PrintStream(telnet.getOutputStream());
            readUntil("Password: ");
            System.out.println("Conectando con usuario...");
            write(user);
            String temp=readUntil(">");
            System.out.println(temp);
            write("en");
            temp=readUntil("#");
            System.out.println(temp);
            
            
            write("conf term");
            temp=readUntil("config");
            System.out.println(temp);
            if (temp.contains("VTY")) {
                System.out.println("Bloqueado...");
                System.exit(-1);
            }

            write("router bgp 13679");
            temp=readUntil("config-router");
            System.out.println(temp);
            

            //write("network 14.13.12.0/24");
            if(status) write("network " + networkToAdvertise);
            else write("no network " +networkToAdvertise);
            temp=readUntil("config-router");
            System.out.println(temp);

            write("exit");
            temp=readUntil("config");
            System.out.println(temp);

            write("exit");
            temp=readUntil("#");
            System.out.println(temp);

            write("wr");
            temp=readUntil("#");
            System.out.println(temp);
              
            telnet.disconnect();
            
            
            System.out.println("Desconectando a Quagga Router");
        } catch (IOException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    
       public static String readUntil(String pattern) {
        StringBuilder sb = new StringBuilder();

        try {
            char lastChar = pattern.charAt(pattern.length() - 1);

            //boolean found = false;

            int check = in.read();
            char ch = (char) check;
            while (check != -1) {
                //System.out.print(ch);
                sb.append(ch);
                if (ch == lastChar) {
                    //System.out.println(sb.toString()+" = "+pattern);
                    if (sb.toString().endsWith(pattern)) {

                        return sb.toString();
                    }
                }
                check = in.read();
                ch = (char) check;
            }
        } catch (IOException e) {
             System.err.println("readUntil exception: "+e.toString());
        }

        return sb.toString();
    }

        public static void write(String value) {
        try {
            out.println(value);
            out.flush();
            //System.out.println(value);
        } catch (Exception e) {
            System.err.println("write exception: "+e.toString());
        }
    }

    
}

