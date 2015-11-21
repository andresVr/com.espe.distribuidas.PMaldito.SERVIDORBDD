/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.espe.distribuidas.pmaldito.servidorbdd.operaciones;

import java.util.ArrayList;

/**
 *
 * @author Andrés
 */
public class Tabla {
    private String nombreTabla;
    private ArrayList<String> listaCampos=new ArrayList<>();   
    private ArrayList<String> listaRegistros=new ArrayList<>();
    private ArrayList<String> listaRegistrosCampo=new ArrayList<>();

    public String getNombreTabla() {
        return nombreTabla;
    }

    public void setNombreTabla(String nombreTabla) {
        this.nombreTabla = nombreTabla;
    }

    public ArrayList<String> getListaCampos() {
        return listaCampos;
    }

    public void setListaCampos(ArrayList<String> listaCampos) {
        this.listaCampos = listaCampos;
    }

    public ArrayList<String> getListaRegistros() {
        return listaRegistros;
    }

    public void setListaRegistros(ArrayList<String> listaRegistros) {
        this.listaRegistros = listaRegistros;
    }

    public ArrayList<String> getListaRegistrosCampo() {
        return listaRegistrosCampo;
    }

    public void setListaRegistrosCampo(ArrayList<String> listaRegistrosCampo) {
        this.listaRegistrosCampo = listaRegistrosCampo;
    }
    
}   
