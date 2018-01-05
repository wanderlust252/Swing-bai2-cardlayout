/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swingbai2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Admin
 */
public class MyWord extends JFrame {

    private JPanel wordpanel;
    private JLabel lblword;
    private JLabel lblword1;
    private JTextField txt;
    private JPanel wordpanel1;
    private JTextField txt1;
    private JPanel wordpanel2;
    private JButton btnSave;
    private Container con;

    public MyWord() {
        super("Thêm từ");

        this.addControl();
        this.addEvent();
        this.showWindow();
    }

    public void showWindow() {
        this.setSize(450, 150);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void addControl() {
        con = this.getContentPane();
        con.setLayout(new BorderLayout());

        wordpanel = new JPanel();
        wordpanel.setLayout(new BorderLayout());
        wordpanel1 = new JPanel();
        wordpanel1.setLayout(new BorderLayout());
        wordpanel2 = new JPanel();
        wordpanel2.setLayout(new BorderLayout());
        lblword = new JLabel("Word:     ");
        lblword.setFont(new Font("Consolas", Font.PLAIN, 20));

        lblword1 = new JLabel("Translate:");
        lblword1.setFont(new Font("Consolas", Font.PLAIN, 20));

        txt = new JTextField("");
        txt1 = new JTextField("");
        //txt.setSize(10, 30);
        wordpanel.add(lblword, BorderLayout.WEST);
        wordpanel.add(txt, BorderLayout.CENTER);
        wordpanel1.add(lblword1, BorderLayout.WEST);
        wordpanel1.add(txt1, BorderLayout.CENTER);
        wordpanel2.add(wordpanel, BorderLayout.NORTH);
        wordpanel2.add(wordpanel1, BorderLayout.SOUTH);
        con.add(wordpanel2, BorderLayout.NORTH);

    }

    private void addEvent() {
        btnSave = new JButton("Confirm");
        con.add(btnSave, BorderLayout.CENTER);
        
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    save(txt.getText(),txt1.getText());
                    JOptionPane.showMessageDialog(new Frame(), "Lưu thành công. Xin hãy khởi động lại chương trình");
                } catch (IOException ex) {
                    Logger.getLogger(MyWord.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    public void save(String word,String trans) throws IOException{
        ActFile act = new ActFile();
        act.LuuFile("word.txt",word);
        act.LuuFile("trans.txt", trans);
    }
}
