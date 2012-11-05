/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.csvreader.CsvReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Stack;
import model.Move;

/**
 *
 * @author martin
 */
public class SimulatorController {

    String cTime;
    String cDir;
    String cAngle;

    public SimulatorController(String path) throws FileNotFoundException, IOException {

        //Lectura de archivo
        CsvReader reader = new CsvReader(new InputStreamReader(
                new FileInputStream(path), Charset.forName("UTF-8")));
        reader.setDelimiter('\n');
        reader.setRecordDelimiter(';');
        reader.readRecord();
        //Cadena completa de valores
        String strConSalto = "";
        for (String string : reader.getValues()) {
            strConSalto = strConSalto + string;
        }
        //Arreglo de cadenas sin contar los saltos
        String[] strSinSalto = strConSalto.split("\r");
        //Variables para comparar fechas entre instrucciones
        String[] strAnt;
        String[] strSig;
        //Recorrer el arreglo usando los valores correctos
        for (int i = 0; i < strSinSalto.length - 1; i++) {
            //Movimiento anterior
            strAnt = strSinSalto[i].split(",");
            strSig = strSinSalto[i + 1].split(",");
            //Split de fecha para calcular la diferencia de segundos entre instrucciones
            String[] strFecAnt;
            String[] strFecSig;
            //Fraccionar por yyyy-mm-dd-hh-mm-ss
            strFecAnt = strAnt[0].split(":");
            strFecSig = strSig[0].split(":");
            //Pasar a fecha
            java.util.Date fechaAnt = new java.util.Date(Integer.parseInt(strFecAnt[0]), Integer.parseInt(strFecAnt[1]), Integer.parseInt(strFecAnt[2]), Integer.parseInt(strFecAnt[3]), Integer.parseInt(strFecAnt[4]), Integer.parseInt(strFecAnt[5]));
            java.util.Date fechaSig = new java.util.Date(Integer.parseInt(strFecSig[0]), Integer.parseInt(strFecSig[1]), Integer.parseInt(strFecSig[2]), Integer.parseInt(strFecSig[3]), Integer.parseInt(strFecSig[4]), Integer.parseInt(strFecSig[5]));
            //Calcular la diferencia de fechas
            int segundos = getDiferencia(fechaAnt, fechaSig);
            eventos.add(new Move(strAnt[0], strAnt[1], strAnt[2], String.valueOf(segundos)));
            System.out.println(strAnt[0] + "-" + strAnt[1] + "-" + strAnt[2] + "----");

        }
        //Asignar el valor del tiempo de la última instruccion (Debido a que no hay siguiente). Valor por defecto 1 segundo
        strAnt = strSinSalto[strSinSalto.length - 1].split(",");
        eventos.add(new Move(strAnt[0], strAnt[1], strAnt[2], "1"));
        for (Move move : eventos) {
            System.out.println("Time: " + move.getTime() + ", Velocity: " + move.getVelocity() + ", Angle: " + move.getAngle() + ", Seconds: " + move.getSeconds());

        }
    }

    public static int getDiferencia(java.util.Date fecha1, java.util.Date fecha2) {
        java.util.Date fechaMayor = null;
        java.util.Date fechaMenor = null;
        //Horas minutos segundos.
        ArrayList<Integer> hms = new ArrayList<Integer>();
        /* Verificamos cual es la mayor de las dos fechas, para no tener sorpresas al momento
         * de realizar la resta.
         */
        if (fecha1.compareTo(fecha2) > 0) {
            fechaMayor = fecha1;
            fechaMenor = fecha2;
        } else {
            fechaMayor = fecha2;
            fechaMenor = fecha1;
        }
        //los milisegundos
        int diferenciaMils = (int) (fechaMayor.getTime() - fechaMenor.getTime());
        //obtenemos los segundos
        int segundos = diferenciaMils / 1000;
        return segundos;
    }
    
    public static ArrayList<Move> eventos = new ArrayList<Move>();
}
