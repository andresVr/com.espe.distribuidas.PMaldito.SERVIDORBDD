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
public class Actualizar {
    private String campoTabla;

    public String getCampoTabla() {
        return campoTabla;
    }

    public void setCampoTabla(String campoTabla) {
        this.campoTabla = campoTabla;
    }
    
     public static void actualizarDatos(String string, File archivo) {

        try {
            FileWriter fw = new FileWriter(archivo,true);
            fw.write(string);
            fw.close();
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}
