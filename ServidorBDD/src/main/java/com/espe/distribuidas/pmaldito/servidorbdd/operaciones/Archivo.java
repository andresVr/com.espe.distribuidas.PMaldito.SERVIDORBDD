/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.espe.distribuidas.pmaldito.servidorbdd.operaciones;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Andrés
 */
public class Archivo {

    public static final String rutaTablaCliente = "src/main/java/facturacion/CLIENTE.txt";
    public static final String rutaTabladDetalle = "src/main/java/facturacion/DETALLE.txt";
    public static final String rutaTablaFactura = "src/main/java/facturacion/FACTURA.txt";
    public static final String rutaTablaProducto = "src/main/java/facturacion/PRODUCTO.txt";
    public static final String rutaTablaUsuario = "src/main/java/facturacion/USUARIO.txt";

    /**
     *
     * @param ruta
     * @return
     */
    public static Boolean existeTabla(String ruta) {
        File archivo = new File(ruta);
        return archivo.exists();
    }

    @SuppressWarnings("ConvertToTryWithResources")
    public static void insertar(String string, File archivo) {

        try {
            FileWriter fw = new FileWriter(archivo,true);
            fw.write(string);
            fw.close();
        } catch (IOException e) {
            System.err.println(e);
        }
    }
   


}
