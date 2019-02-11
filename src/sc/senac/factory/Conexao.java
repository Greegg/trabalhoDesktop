/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sc.senac.factory;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author hvb
 */
public class Conexao {

    private static final String URL = "jdbc:mysql://localhost:3306/";
    private static final String USER = "root";
    private static final String PASS = null;
    private static Connection conexao = null;

    public static Connection getConexao() {
        if (conexao == null) {
            try {
                conexao = DriverManager.getConnection(URL, USER, PASS);
                System.out.println("Conectou...");
            } catch (Exception e) {
            }
        }
        return conexao;
    }

}
