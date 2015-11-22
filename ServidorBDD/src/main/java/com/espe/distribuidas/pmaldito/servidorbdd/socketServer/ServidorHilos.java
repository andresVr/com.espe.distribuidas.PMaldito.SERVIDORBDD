/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.espe.distribuidas.pmaldito.servidorbdd.socketServer;

import com.espe.distribuidas.pmaldito.protocolobdd.mensajesBDD.MensajeBDD;
import com.espe.distribuidas.pmaldito.protocolobdd.mensajesBDD.MensajeRS;
import com.espe.distribuidas.pmaldito.protocolobdd.mensajesBDD.Originador;
import com.espe.distribuidas.pmaldito.protocolobdd.operaciones.ConsultarRS;
import com.espe.distribuidas.pmaldito.protocolobdd.operaciones.InsertarRS;
import com.espe.distribuidas.pmaldito.protocolobdd.seguridad.AutenticacionRS;
import com.espe.distribuidas.pmaldito.servidorbdd.operaciones.Archivo;
import com.espe.distribuidas.pmaldito.servidorbdd.operaciones.Consultar;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

/**
 *
 * @author Andrés
 */
public class ServidorHilos extends Thread {

    private static Integer id = 0;

    private final Socket socket;
    private DataOutputStream salida;
    private String mensaje;
    private Socket conexion;
    private DataInputStream entrada;
    private Socket cliente;

    private void enviar(String mensaje) {
        try {
            salida.writeUTF(mensaje);
            salida.flush(); //flush salida a cliente
            System.out.println(mensaje);

        } //Fin try
        catch (IOException ioException) {
        } //Fin catch  

    }

    public ServidorHilos(Socket socket) throws IOException {
        if (ServidorHilos.id != null) {
            ServidorHilos.id = ServidorHilos.id++;
        }
        this.socket = socket;
        salida = new DataOutputStream(socket.getOutputStream());

    }
    //enviar objeto a cliente 

    @Override
    @SuppressWarnings("SuspiciousIndentAfterControlStatement")
    public void run() {
        try {
            entrada = new DataInputStream(this.socket.getInputStream());
        } catch (IOException ex) {

        }
        do { //procesa los mensajes enviados dsd el servidor
            try {//leer el mensaje y mostrarlo 
                mensaje = entrada.readUTF(); //leer nuevo mensaje
                System.out.println(mensaje);
                switch (Archivo.obtenerId(mensaje)) {
                    case MensajeBDD.idMensajeAutenticacion: {
                        AutenticacionRS aurs = new AutenticacionRS();
                        aurs.buildInput(mensaje);
                        Consultar co = new Consultar();
                        MensajeRS mensajeRSAutentic = new MensajeRS(Originador.getOriginador(Originador.BASE_DATOS), MensajeBDD.idMensajeAutenticacion);
                        if (co.exiteEn(aurs.getUsuario(), aurs.getClave())) {
                            aurs.buildOutput("OK");
                            mensajeRSAutentic.setCuerpo(aurs);
                            enviar(mensajeRSAutentic.asTexto());
                        } else {
                            aurs.buildOutput("NO");
                            mensajeRSAutentic.setCuerpo(aurs);
                            enviar(mensajeRSAutentic.asTexto());

                        }
                    }
                    break;
                    case MensajeBDD.idMensajeInsertar: {
                        switch (Archivo.tabla(mensaje)) {
                            case Archivo.nombreTablaCliente: {
                                InsertarRS is = new InsertarRS();
                                is.buildInput(mensaje);
                                MensajeRS isrs = new MensajeRS(Originador.getOriginador(Originador.BASE_DATOS), MensajeBDD.idMensajeInsertar);
                                try {
                                    Archivo.insertar(Archivo.parsearCampos(is.getValosCamposTabla()), new File(Archivo.rutaTablaCliente));
                                    is.buildOutput("OK");
                                    isrs.setCuerpo(is);
                                    this.enviar(isrs.asTexto());
                                } catch (Exception e) {
                                    is.buildOutput("NO");
                                    isrs.setCuerpo(is);
                                    this.enviar(isrs.asTexto());
                                    System.out.println(e);

                                }
                            }
                            break;
                            case Archivo.nombreTablaFactura: {
                                InsertarRS is = new InsertarRS();
                                is.buildInput(mensaje);
                                MensajeRS isfact = new MensajeRS(Originador.getOriginador(Originador.BASE_DATOS), MensajeBDD.idMensajeInsertar);
                                try {
                                    Archivo.insertar(Archivo.parsearCampos(is.getValosCamposTabla()), new File(Archivo.rutaTablaFactura));
                                    for (int i = 0; i < Archivo.detalle(is.getValosCamposTablaCuerpoFact()).size(); i++) {
                                        Archivo.insertar(Archivo.detalle(is.getValosCamposTablaCuerpoFact()).get(i), new File(Archivo.rutaTabladDetalle));
                                    }
                                    is.buildOutput("OK");
                                    isfact.setCuerpo(is);
                                    this.enviar(isfact.asTexto());
                                } catch (Exception e) {
                                    is.buildOutput("NO");
                                    isfact.setCuerpo(is);
                                    this.enviar(isfact.asTexto());
                                    System.out.println(e);

                                }
                            }
                            break;
                            default:
                                break;
                        }

                    }
                    break;
                    case MensajeBDD.idMensajeConsultar: {
                        switch (Archivo.tabla(mensaje)) {
                            case Archivo.nombreTablaCliente: {
                                ConsultarRS crs = new ConsultarRS();
                                crs.buildInput(mensaje);
                                Consultar con = new Consultar();
                                MensajeRS rscon=new MensajeRS(Originador.getOriginador(Originador.BASE_DATOS), MensajeBDD.idMensajeConsultar);
                                try{
                                    ArrayList lista=con.camposConsulta("/",Archivo.rutaTablaCliente,1,crs.getValorCodigoidentificadorColumna());
                                crs.buildOutput("OKO",lista );
                                rscon.setCuerpo(crs);
                                this.enviar(rscon.asTexto());
                            } catch (Exception e) {
                                    crs.buildOutput("BAD");
                                    rscon.setCuerpo(crs);
                                    this.enviar(rscon.asTexto());
                                    System.out.println(e);

                                }
                                }
                            break;
                        }
                    }
                    break;
                }

            } //fin try //fin try
            catch (SocketException ex) {
            } catch (EOFException eofException) {
                System.out.println("Fin de la conexion");
                break;
            } //fin catch
            catch (IOException ex) {
            } //fin catch               

        } while (!mensaje.equals("Servidor>>> TERMINATE")); //Ejecuta hasta que el server escriba TERMINATE

        try {
            entrada.close(); //cierra input Stream
            cliente.close(); //cieraa Socket
        } //Fin try
        catch (IOException ioException) {
            ioException.printStackTrace();
        } //fin catch

        System.out.println("Fin de la conexion");
        System.exit(0);
    }

}
