/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swingbai2;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class ActFile {

    public ArrayList<String> DocFile(String path) throws IOException {
        ArrayList<String> bs = new ArrayList<>();
        BufferedReader bis = null;
        try {
            bis = new BufferedReader(new FileReader(path));
            String str = bis.readLine();
            for(String i:str.split("\\|")){
                bs.add(i);
            }
            
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            bis.close();
            
        }
        return bs;
    }
   

    public boolean LuuFile(String path, String text) throws IOException {
            BufferedReader bis = null;
        try {
            bis = new BufferedReader(new FileReader(path));
            String doclai = bis.readLine();
            doclai += text+"|";
            FileWriter fw = new FileWriter(path);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(doclai);
            bw.close();
            fw.close();
        } catch (Exception e) {
            return false;
        }
        finally{
            bis.close();
        }
        return true;
    }
}
