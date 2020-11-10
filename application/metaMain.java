package application;

import java.io.*;
import java.util.*;
import java.util.List;

import javax.swing.*;
import java.nio.file.*;
import java.awt.*;
import java.awt.event.*;

public class metaMain {
    public String endPath;
    public String endExtension;
    public String endInputOne;
    public String endInputTwo;

    public static boolean threadFlag;

    public metaMain() {
        JFrame jfrm = new JFrame("ByteChanger 2020");
        jfrm.setLayout(new FlowLayout());
        jfrm.setSize(400,450);
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTextField pathField = new JTextField("Podaj ścieżkę do katalogu.");
        pathField.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent ae){
                endPath = pathField.getText();
                pathField.setEnabled(false);
            }
        });

        JTextField extensionField = new JTextField("Podaj rozszerzenie.");
        extensionField.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent ae){
                endExtension = extensionField.getText();
                extensionField.setEnabled(false);
            }
        });

        JTextField inputOneField = new JTextField("Podaj sekwencję porównawczą.");
        inputOneField.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent ae){
                endInputOne = inputOneField.getText();
                inputOneField.setEnabled(false);
            }
        });

        JTextField inputTwoField = new JTextField("Podaj sekwencję zamienianą.");
        inputTwoField.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent ae){
                endInputTwo = inputTwoField.getText();
                inputTwoField.setEnabled(false);
            }
        });

        JButton jbtnExecute = new JButton("Wyszukaj i zamień");
        jbtnExecute.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent ae){
                threadFlag = true;
                jbtnExecute.setEnabled(false);
                try {
                    Thread thread = new Thread(() -> {
                        List<Path> temp = new ArrayList<Path>();
                        Path test = Paths.get(endPath);
                        metaFileReader.recDirectoryReader(test, endExtension, 0, temp);
                        metaBytes.listChanger(temp, endInputOne, endInputTwo);
                    });
                    if(threadFlag){thread.start();}
                } catch (Exception e) {
                    //TODO: handle exception
                }
            }
        });

        JButton jbtnAbort = new JButton("Przerwij");
        jbtnAbort.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent ae){
                threadFlag = false;
                jbtnExecute.setEnabled(true);
                inputTwoField.setEnabled(true);
                inputOneField.setEnabled(true);
                extensionField.setEnabled(true);
                pathField.setEnabled(true);
            }
        });

        jfrm.add(pathField);
        jfrm.add(extensionField);
        jfrm.add(inputOneField);
        jfrm.add(inputTwoField);
        jfrm.add(jbtnExecute);
        jfrm.add(jbtnAbort);
        jfrm.setVisible(true);

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run() {
                new metaMain();
            }
        });
    }
}
