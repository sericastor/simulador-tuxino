/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.MainController;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

/**
 *
 * @author martin
 */
public class OpenFileAction extends AbstractAction {

    JFrame frame;
    JFileChooser chooser;
    public static String path;

    OpenFileAction(JFrame frame, JFileChooser chooser) {
        super("Open...");
        this.chooser = chooser;
        this.frame = frame;
    }

    public void actionPerformed(ActionEvent evt) {
        // Show dialog; this method does not return until dialog is closed
        chooser.showOpenDialog(frame);

        // Get the selected file
        File file = chooser.getSelectedFile();
        if (!file.toString().equals((null))) {
            path = file.toString();
        }
        System.out.println(path);
        try {
            MainController.currentPath(path);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(OpenFileAction.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(OpenFileAction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
};