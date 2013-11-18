/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package backpropagation;

import java.awt.Color;

/**
 *
 * @author fendi
 */
public class Button extends javax.swing.JButton{
    private final int AKTIF=1,TIDAK_AKTIF=0;
    private int status;
    public Button() {
        status=AKTIF;
        tekanButton();
    }
    public void tekanButton(){
        if(status==AKTIF){
            status=TIDAK_AKTIF;
            setBackground(Color.white);
            setText("0");
        }else{
            status=AKTIF;
            setBackground(Color.blue);
            setText("1");
        }
    }
    public int getNilai(){
        return status;
    }
}
