/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.espe.distribuidas.pmaldito.servidorbdd.socketServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author Andrés
 */
public class ServidorHilos extends Thread{
    private static Integer id=0;
    private final PrintWriter output;
    private final BufferedReader input;
    private final Socket socket;
    public ServidorHilos(Socket socket)throws IOException{
        if(ServidorHilos.id!=null)
        {
            ServidorHilos.id=ServidorHilos.id++;
        }
        this.socket=socket;
        input=new BufferedReader(new InputStreamReader(socket.getInputStream()));
        output=new PrintWriter(socket.getOutputStream(),true);
        
    }
    @Override
    public void run() {
        try{
        String userInput;
           while((userInput=input.readLine())!=null)
            {
                System.out.println("Mensaje Recibido: "+"id"+id+"Mensaje"+userInput);
                StringBuilder sb=new StringBuilder(userInput);
                output.write(sb.reverse().toString());
                output.write('\n');
                output.flush();
                if("FIN".equalsIgnoreCase(userInput)){
                    break;
                }
            }
        
        socket.close();
        }catch(Exception e)
        {
        }
    
    }

    
}
