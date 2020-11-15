package application;

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

    public static JLabel errorLabel;
    public static JFrame jfrm = new JFrame("ByteChanger 2020");

    public metaMain() {
        
        jfrm.setLayout(new FlowLayout());
        jfrm.setSize(400,350);
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        errorLabel = new JLabel(" ", JLabel.CENTER);

        JTextField pathField = new JTextField("", 35);
        pathField.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent ae){
                endPath = pathField.getText();
                pathField.setEnabled(false);
                if(endPath.equals("")){
                    errorLabel.setText("Podałeś pusty katalog.");
                    jfrm.add(errorLabel);
                }
            }
        });

        JTextField extensionField = new JTextField("", 35);
        extensionField.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent ae){
                endExtension = extensionField.getText();
                extensionField.setEnabled(false);
                if(endExtension.equals("")){
                    errorLabel.setText("Podałeś puste rozszerzenie.");
                    jfrm.add(errorLabel);
                }
            }
        });

        JTextField inputOneField = new JTextField("", 35);
        inputOneField.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent ae){
                endInputOne = inputOneField.getText();
                inputOneField.setEnabled(false);
                if(endInputOne.equals("")){
                    errorLabel.setText("Podałeś pusty ciąg.");
                    jfrm.add(errorLabel);
                }
            }
        });

        JTextField inputTwoField = new JTextField("", 35);
        inputTwoField.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent ae){
                endInputTwo = inputTwoField.getText();
                inputTwoField.setEnabled(false);
                if(endInputTwo.equals("")){
                    errorLabel.setText("Podałeś pusty ciąg.");
                    jfrm.add(errorLabel);
                }
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
                        if(test.toString().equals("")){
                            errorLabel.setText("Podana ścieżka nie istnieje lub nie jest to katalog.");
                            jfrm.add(errorLabel);
                        }else{
                            metaFileReader.recDirectoryReader(test, endExtension, 0, temp);
                            metaBytes.listChanger(temp, endInputOne, endInputTwo);
                        }
                    });
                    if(threadFlag){thread.start();}
                    jbtnExecute.setEnabled(true);
                } catch (NullPointerException e) {
                    System.out.println("Wyjątek:" + e);
                }
            }

            
        });

        JButton jbtnAbort = new JButton("Wyczyść wyszukiwanie.");
        jbtnAbort.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent ae){
                threadFlag = false;
                jbtnExecute.setEnabled(true);
                inputTwoField.setEnabled(true);
                inputTwoField.setText("");
                inputOneField.setText("");
                inputOneField.setEnabled(true);
                extensionField.setText("");
                extensionField.setEnabled(true);
                pathField.setText("");
                pathField.setEnabled(true);
                errorLabel.setText("");
            }
        });

        JLabel label1 = new JLabel("Podaj ścieżkę do katalogu.", JLabel.CENTER);
        JLabel label2 = new JLabel("Podaj rozszerzenie pliku.", JLabel.CENTER);
        JLabel label3 = new JLabel("Podaj ciąg bajtów do wyszukania (maks. 32).", JLabel.CENTER);
        JLabel label4 = new JLabel("Podaj ciąg bajtów do zamiany.", JLabel.CENTER);
        ////
        ////
        jfrm.add(label1);
        jfrm.add(pathField);
        jfrm.add(label2);
        jfrm.add(extensionField);
        jfrm.add(label3);
        jfrm.add(inputOneField);
        jfrm.add(label4);
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
