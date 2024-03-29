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
import java.util.Arrays;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Andrés
 */
public class Consultar {

    /**
     * Lista de campos para setear
     */
    private ArrayList<String> campos = new ArrayList<>();

    public ArrayList<String> getCampos() {
        return campos;
    }

    public void setCampos(ArrayList<String> campos) {
        this.campos = campos;
    }

    /**
     * permite recuperar todos los datos de una tabla parametro nombre de tabla
     *
     * @param tabla
     * @return
     */
    @SuppressWarnings("ConvertToTryWithResources")
    public ArrayList<String> regresarCampos(String tabla) {
        @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
        ArrayList<String> todosDatosTabla = new ArrayList<>();
        String string;
        try {
            FileReader fr = new FileReader(tabla);
            BufferedReader br = new BufferedReader(fr);
            while ((string = br.readLine()) != null) {
                todosDatosTabla.add(string);
            }
            br.close();
        } catch (FileNotFoundException e) {
            System.err.println(e);
        } catch (IOException e1) {
            System.err.println(e1);
        }
        return todosDatosTabla;
    }

    /**
     * Metodo que permite recuperar un registro de una tabla en funcion de un
     * campo regular, recibe la tabla, el numero de columna en donde tiene que
     * consultar y el valor que hay que comparar en ese columna
     *
     * @param tabla
     * @param numColumna
     * @param valorColumna
     * @return
     */
    @SuppressWarnings({"ConvertToTryWithResources", "element-type-mismatch"})
    public ArrayList campoRegular(String tabla, Integer numColumna, String valorColumna) {
        @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
        ArrayList<String> datosRegistro = new ArrayList<>();
        String string;

        try {
            FileReader fr = new FileReader(tabla);
            BufferedReader br = new BufferedReader(fr);
            while ((string = br.readLine()) != null) {
                if (string.length() > 0) {
                    String registro[] = StringUtils.splitPreserveAllTokens(string, "|");
                    if (registro[numColumna].equalsIgnoreCase(valorColumna)) {
                        datosRegistro.addAll(Arrays.asList(registro));
                        break;
                    }
                } else {
                    break;
                }
            }
            br.close();
        } catch (FileNotFoundException e) {
            System.err.println(e);
        } catch (IOException e1) {
            System.err.println(e1);
        }
        return datosRegistro;
    }

    /**
     * Reconoce los campos regulares de la tabla detalle
     *
     * @param tabla
     * @param numColumna
     * @param valorColumna
     * @return
     */
    public ArrayList campoRegularFact(String tabla, Integer numColumna, String valorColumna) {
        @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
        ArrayList<String> datosRegistro = new ArrayList<>();
        String string;

        try {
            FileReader fr = new FileReader(tabla);
            BufferedReader br = new BufferedReader(fr);
            while ((string = br.readLine()) != null) {
                if (string.length() > 0) {
                    String registro[] = StringUtils.splitPreserveAllTokens(string, "|");
                    if (registro[numColumna].equalsIgnoreCase(valorColumna)) {
                        datosRegistro.addAll(Arrays.asList(registro));
                        //break;    
                    }
                } else {
                    break;
                }
            }
            br.close();
        } catch (FileNotFoundException e) {
            System.err.println(e);
        } catch (IOException e1) {
            System.err.println(e1);
        }
        return datosRegistro;
    }

    public Consultar() {
    }

    /**
     * Trae todos los registro de una tabla consulta *
     *
     * @param campos
     * @param tabla
     * @return
     */
    public ArrayList camposConsulta(String campos, String tabla) {
        ArrayList<String> consulta;
        consulta = new ArrayList<>();

        if (campos.equalsIgnoreCase("*")) {
            regresarCampos(tabla);
        }
        return consulta;
    }
//consulta  todos

    /**
     * lista de los campos de una consulta que retorne todos los registros "/"
     *
     * @param campos
     * @param tabla
     * @param numColumna
     * @param valorColumna
     * @return
     */
    public ArrayList camposConsulta(String campos, String tabla, Integer numColumna, String valorColumna) {
        ArrayList<String> consulta;
        consulta = new ArrayList<>();

        if (campos.equals("/")) {
            consulta = campoRegularFact(tabla, numColumna, valorColumna);
        }
        return consulta;

    }

    //validar campos
    /**
     * metodo existe en recibe el codigo a buscar, la poscion en donde buscar y
     * el nombre de la tabla permite saber si existe o no el registro en
     * especial
     *
     * @param codigo
     * @param posicion
     * @param tabla
     * @return
     */
    @SuppressWarnings("ConvertToTryWithResources")
    public boolean exiteEn(String codigo, Integer posicion, String tabla) {
        @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
        Boolean validador = false;
        String string;
        String registro[];
        try {
            FileReader fr = new FileReader(tabla);
            BufferedReader br = new BufferedReader(fr);

            while ((string = br.readLine()) != null) {
                if (string.length() > 0) {
                    registro = StringUtils.splitPreserveAllTokens(string, "|");
                    System.out.println(registro.length);
                    if (registro[posicion].equalsIgnoreCase(codigo)) {
                        validador = true;
                    }
                } else {
                    break;
                }
            }
            br.close();
        } catch (FileNotFoundException e) {
            System.err.println(e);
        } catch (IOException e1) {
            System.err.println(e1);
        }
        return validador;
    }

    /**
     * Permite validar el user y el pass del login
     *
     * @param usuario
     * @param pass
     * @return
     */
    @SuppressWarnings("ConvertToTryWithResources")
    public boolean exiteEn(String usuario, String pass) {
        @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
        Boolean validador = false;
        String string;
        String registro[];
        try {
            FileReader fr = new FileReader(Archivo.rutaTablaUsuario);
            BufferedReader br = new BufferedReader(fr);
            while ((string = br.readLine()) != null) {
                if (string.length() > 0) {
                    registro = StringUtils.splitPreserveAllTokens(string, "|");
                    if (registro[0].equalsIgnoreCase(usuario) && registro[1].equalsIgnoreCase(pass)) {
                        validador = true;
                    }
                } else {
                    break;
                }
            }
            br.close();
        } catch (FileNotFoundException e) {
            System.err.println(e);
        } catch (IOException e1) {
            System.err.println(e1);
        }
        return validador;
    }

    //factura
    /**
     * Permite concatenear los campos campos para la consulta de la factura por
     * codigo y por cliente
     *
     * @param codigoFactura
     * @return
     */
    public ArrayList<String> camposConsultaFactura(String codigoFactura) {
        ArrayList<String> lsCabecera;
        ArrayList<String> lsCliente;
        ArrayList<String> lsDetalles;
        ArrayList<String> consulta = new ArrayList<>();
        lsCabecera = campoRegular(Archivo.rutaTablaFactura, 0, codigoFactura);
        lsCliente = campoRegular(Archivo.rutaTablaCliente, 1, lsCabecera.get(1));
        lsDetalles = campoRegular(Archivo.rutaTabladDetalle, 1, codigoFactura);
        for (String lsCabecera1 : lsCabecera) {
            consulta.add(lsCabecera1);
        }
        for (String obj : lsCliente) {
            consulta.add(obj);
        }
        for (String obj : lsDetalles) {
            consulta.add(obj);
        }

        return consulta;
    }

    /**
     * permite retirar 1 campo de una lista
     *
     * @param entrada
     * @param campos
     * @return
     */
    public static ArrayList<String> retirarCampos(ArrayList<String> entrada, Integer campos) {
        entrada.remove(campos);
        return entrada;
    }

    /**
     * permite retirar n campos de una lista
     *
     * @param entrada
     * @param campos
     * @return
     */
    public static ArrayList<String> retirarCampos(ArrayList<String> entrada, Integer campos[]) {
        for (Integer campo : campos) {
            entrada.remove(campo);
        }
        return entrada;
    }

    /**
     * permite unir listas para concatenacion
     *
     * @param lista1
     * @param lista2
     * @param lista3
     * @return
     */
    public static ArrayList<String> unirListas(ArrayList<String> lista1, ArrayList<String> lista2, ArrayList<String> lista3) {
        ArrayList<String> resultante = new ArrayList<>();
        for (String lista11 : lista1) {
            resultante.add(lista11);
        }
        for (String lista22 : lista2) {
            resultante.add(lista22);
        }
        for (String lista33 : lista3) {
            resultante.add(lista33);
        }
        return resultante;
    }
}
