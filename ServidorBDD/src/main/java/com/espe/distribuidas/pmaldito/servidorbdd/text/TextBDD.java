/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.espe.distribuidas.pmaldito.servidorbdd.text;

import com.espe.distribuidas.pmaldito.servidorbdd.operaciones.Archivo;
import java.io.File;

/**
 *
 * @author Andrés
 */
public class TextBDD {
    public static void main(String[] args) {
        System.out.println(Archivo.existeTabla(Archivo.rutaTablaCliente));
        Archivo.insertar("\n", new File(Archivo.rutaTablaCliente) );
     
    }
}