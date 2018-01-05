/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swingbai2;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Font;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Admin
 */
public class MyFrame extends JFrame {

    private CardLayout cardLayout;
    private JPanel cardPanel;
    private JLabel lblTime;
    private JLabel lblTrans;
    private ArrayList<String> newWord = new ArrayList<>();
    private ArrayList<String> newTrans = new ArrayList<>();
    private Thread t;
    private int index = 0;

    //private ArrayList<MyWord> word = new ArrayList<MyWord>();
    public MyFrame() throws Exception {
        super("Bai 2");
        this.setSize(500, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.inputData();
        this.initLayout();
        this.setVisible(true);
    }

    public void initLayout() throws Exception {
        cardPanel = new JPanel();
        cardLayout = new CardLayout();
        cardPanel.setLayout(cardLayout);
//        JLabel textTest1 = new JLabel();
//        textTest1.setFont(new Font("Consolas", Font.PLAIN, 30));// set text 
//        textTest1.setText("Hello");  // cho card1 noi dung la "Hello"
//        JPanel card1 = new JPanel();
//        card1.add(textTest1);

        nhapNewWord();

        JPanel navPanel = new JPanel();
        JButton btnNext = new JButton("Next");
        JButton btnMean = new JButton("Translate");
        JButton btnSave = new JButton("Give me more word");
        JButton btnPrev = new JButton("Previous");

        btnNext.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                next();
            }
        }
        );

        btnMean.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                trans();
            }
        }
        );
        
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new MyWord();
                } catch (Exception ex) {
                    Logger.getLogger(MyFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        btnPrev.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prev();
            }
        });
        navPanel.add(btnPrev);
        navPanel.add(btnSave);
        navPanel.add(btnMean);
        navPanel.add(btnNext);

        JPanel timePanel = new JPanel();
        lblTime = new JLabel("10");
        lblTime.setFont(new Font("Consolas", Font.PLAIN, 20));
        timePanel.add(lblTime);
        
        JPanel transPanel = new JPanel();
        lblTrans = new JLabel(" ");
        lblTrans.setFont(new Font("Consolas", Font.PLAIN, 60));
        transPanel.add(lblTrans);
        
        this.add(transPanel,BorderLayout.CENTER);
        this.add(cardPanel, BorderLayout.NORTH);
        this.add(navPanel, BorderLayout.SOUTH);
        this.add(timePanel, BorderLayout.LINE_END);

        try {
            t = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (true) {
                            setTime();
                            Thread.sleep(1000);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();

                    }

                }
            });

            t.start();

        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    public boolean setTime() {
        String xtext = null;
        xtext = (Integer.parseInt(this.lblTime.getText()) - 1) + "";
        if (Integer.parseInt(xtext) <= 0) {
            this.lblTime.setText("10");
            next();
            return false;
        } else if (Integer.parseInt(xtext) > 0 ) {
            this.lblTime.setText(xtext);
        }
        return true;

    }

    public void next() {
        lblTrans.setText(" ");
        index++;
        if (index >= newTrans.size()) {
            index = 0;
        }
        cardLayout.next(cardPanel);
        this.lblTime.setText("10");
    }
    public void prev(){
        lblTrans.setText(" ");
        index--;
        if(index < 0){
            index = newTrans.size()-1;
        }
        cardLayout.previous(cardPanel);
        this.lblTime.setText("10");
    }

    public void trans() {
        this.lblTrans.setText(newTrans.get(index));
        this.lblTime.setText("99");
    }

    private void nhapNewWord() {
        for (String i : newWord) {
            JLabel textTest = new JLabel();
            textTest.setFont(new Font("Consolas", Font.PLAIN, 40));
            textTest.setText(i);
            JPanel card = new JPanel();
            card.add(textTest);
            cardPanel.add(card, i);
        }
    }

    private void inputData() throws IOException {
        ActFile act = new ActFile();
        newWord.addAll(act.DocFile("word.txt"));
        newTrans.addAll(act.DocFile("trans.txt"));
    }
}
