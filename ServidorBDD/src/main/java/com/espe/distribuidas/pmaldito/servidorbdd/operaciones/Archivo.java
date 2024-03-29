/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.espe.distribuidas.pmaldito.servidorbdd.operaciones;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Andrés
 */
public class Archivo {

    /**
     * ruta relativa de la tabla cliente
     */
    public static final String rutaTablaCliente = "src/main/java/facturacion/CLIENTE.txt";
    /**
     * ruta relativa de la tabla detalle
     */
    public static final String rutaTabladDetalle = "src/main/java/facturacion/DETALLE.txt";
    /**
     * ruta relativa de la tabla factura
     */
    public static final String rutaTablaFactura = "src/main/java/facturacion/FACTURA.txt";
    /**
     * ruta relativa de la tabla producto
     */
    public static final String rutaTablaProducto = "src/main/java/facturacion/PRODUCTO.txt";
    /**
     * ruta relativa de la tabla usuario
     */
    public static final String rutaTablaUsuario = "src/main/java/facturacion/USUARIO.txt";
    /**
     * nombres de la tablas de la base de datos cliente
     */
    public static final String nombreTablaCliente = "CLIENTE";
    /**
     * nombres de la tablas de la base de datos detalle
     */
    public static final String nombreTablaDetalle = "DETALLE";
    /**
     * nombres de la tablas de la base de datos factura
     */
    public static final String nombreTablaFactura = "FACTURA";
    /**
     * nombres de la tablas de la base de datos producto
     */
    public static final String nombreTablaProducto = "PRODUCTO";
    /**
     * nombres de la tablas de la base de datos usuario
     */
    public static final String nombreTablaUsuario = "USUARIO";

    /**
     * Valida la existencia de la tabla
     *
     * @param ruta
     * @return
     */
    public static Boolean existeTabla(String ruta) {
        File archivo = new File(ruta);
        return archivo.exists();
    }

    /**
     * Permite insertar registros en una tabla
     *
     * @param string
     * @param archivo
     */
    @SuppressWarnings("ConvertToTryWithResources")
    public static void insertar(String string, File archivo) {

        try {
            FileWriter fw = new FileWriter(archivo, true);
            fw.write(string + "\n");
            fw.close();
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    public static void vaciarArchivo(File archivo) {
        try {
            FileWriter fw = new FileWriter(archivo);
            fw.flush();
            fw.close();
        } catch (IOException e) {
            System.err.println(e);
        }

    }

    /**
     * Permite recuperar el ID del mensaje RQ que llega
     *
     * @param string
     * @return
     */
    public static String obtenerId(String string) {
        return string.substring(39, 49);
    }

    /**
     * permite obtener la tabla de la trama
     *
     * @param string
     * @return
     */
    public static String tabla(String string) {
        String partes[] = StringUtils.splitPreserveAllTokens(string, "_");
        return StringUtils.stripEnd(partes[1], "0");
    }

    /**
     * permite dar el formato con |
     *
     * @param valosCampos
     * @return
     */
    public static String parsearCampos(ArrayList<String> valosCampos) {
        String string = "|";
        for (String valosCampo : valosCampos) {
            string = string + valosCampo + "|";
        }
        return string.substring(1);
    }

    public static String parsearCampos(List<String> valosCampos) {
        String string = "|";
        for (String valosCampo : valosCampos) {
            string = string + valosCampo + "|";
        }
        return string.substring(1);
    }

    /**
     * permite separar la lista de detalles en registros de listas
     *
     * @param valosCampos
     * @return
     */
    public static ArrayList<String> detalle(ArrayList<String> valosCampos) {
        int contador = 0;
        ArrayList<String> detalleParse = new ArrayList<>();
        while (contador < valosCampos.size()) {
            detalleParse.add(parsearCampos(valosCampos.subList(contador, contador + 5)));
            contador = contador + 5;
        }
        return detalleParse;
    }

    /**
     * Permite enviar la nueva lista de registros
     *
     * @param campos
     * @param archivo
     */
    public static void insetarCampos(ArrayList<String> campos, File archivo) {
        for (int i = 0; i < campos.size(); i++) {
            insertar(campos.get(i), archivo);
        }
    }

    /**
     * inserta un nuevo dato dentro de la lista de la tabla
     *
     * @param tabla
     * @param nuevo
     * @return
     */
    public static ArrayList<String> insertarNuevo(ArrayList<String> tabla, String nuevo) {
        tabla.add(nuevo);
        return tabla;
    }

    public static ArrayList<String> insertarNuevo(ArrayList<String> tabla, ArrayList<String> nuevo) {
        for (String nuevo1 : nuevo) {
            tabla.add(nuevo1);
        }
        return tabla;
    }

    /**
     * metodo principal de insercion llamado para insertar cmapos nuevos en una
     * tabla
     *
     * @param rutaTabla
     * @param nuevoCampos
     */
    public static void insertarTabla(String rutaTabla, String nuevoCampos) {
        ArrayList<String> campos;
        Consultar co = new Consultar();
        campos = insertarNuevo(co.regresarCampos(rutaTabla), nuevoCampos);
        vaciarArchivo(new File(rutaTabla));
        insetarCampos(campos, new File(rutaTabla));
    }

    /**
     * metodo para llenado de campos en una tabla recibe una lista
     *
     * @param rutaTabla
     * @param nuevoCampos
     */
    public static void insertarTabla(String rutaTabla, ArrayList<String> nuevoCampos) {
        ArrayList<String> campos;
        Consultar co = new Consultar();
        campos = insertarNuevo(co.regresarCampos(rutaTabla), nuevoCampos);
        vaciarArchivo(new File(rutaTabla));
        insetarCampos(campos, new File(rutaTabla));
    }


}
