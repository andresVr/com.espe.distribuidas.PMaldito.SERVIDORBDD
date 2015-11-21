/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.espe.distribuidas.pmaldito.servidorbdd.operaciones;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Andrés
 */
public class Consultar {

    private Tabla tabla = new Tabla();
    private ArrayList<String> campos = new ArrayList<>();

    public Tabla getTabla() {
        return tabla;
    }

    public ArrayList<String> getCampos() {
        return campos;
    }

    public void setCampos(ArrayList<String> campos) {
        this.campos = campos;
    }

    public void setTabla(Tabla tabla) {
        this.tabla = tabla;
    }

    @SuppressWarnings("ConvertToTryWithResources")
    public ArrayList<String> regresarCampos(String tabla) {
        @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
        ArrayList<String> todosDatosTabla = new ArrayList<>();
        String string;
       try{
        FileReader fr = new FileReader(tabla);
        BufferedReader br = new BufferedReader(fr);
        while ((string = br.readLine()) != null) {
            todosDatosTabla.add(string);
        }
        br.close();
       }catch(FileNotFoundException e)
       {
           System.err.println(e);
       }
       catch(IOException e1)
       {
           System.err.println(e1);
       }
    return todosDatosTabla;
    }

    public Consultar() {
    }

    private void CamposConsulta(String campos,String tabla) {
        if (campos.equalsIgnoreCase("*")) {
            regresarCampos(tabla);
        } else if (campos.equalsIgnoreCase("/")) {
            
        } else {

        }
    }

}
