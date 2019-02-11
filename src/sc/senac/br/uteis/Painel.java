/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sc.senac.br.uteis;

import javax.swing.JOptionPane;

/**
 *
 * @author Alunos
 */
public class Painel {

    public static void show(String msg) {
        JOptionPane.showMessageDialog(null, msg);
    }

    public static int showInput(String msg) {
        int num = Integer.parseInt(JOptionPane.showInputDialog(msg));
        return num;
    }

}
