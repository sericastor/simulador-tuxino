/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.csvreader.CsvReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import view.MainView;

/**
 *
 * @author martin
 */
public class MainController {

    /**
     * @param args
     */
    public static void main(String[] args) {
        mv.setVisible(true);
    }

    public static void currentPath(String cPath) throws FileNotFoundException, IOException {
        path = cPath;
        SimulatorController sc = new SimulatorController(path);
    }
    public static MainView mv = new MainView();
    public static String path;
}