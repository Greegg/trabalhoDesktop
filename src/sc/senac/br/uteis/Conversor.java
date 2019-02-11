/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sc.senac.br.uteis;

import java.sql.Date;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alunos
 */
public class Conversor {

    public static float realUsuarioParaBanco(String valor) {
        valor = valor.replace(".", ",");
        valor = valor.replace(",", ".");

        try {
            return Float.parseFloat(valor);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }

    public static float realBancoUsuario(float num) {
        DecimalFormat df = new DecimalFormat("###,##");
        df.format(num);

        try {
            return num;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }

    public static String dataBancoParaUsuario(Date data) {

        SimpleDateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy");
        return dataFormatada.format(data);
    }

    public static Date dataUsuarioParaBanco(String data) {
        SimpleDateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy");

        try {
            //Converter de string para Data util
            java.util.Date dataUtil = dataFormatada.parse(data);

            //Converter de data util para data sql
            java.sql.Date dataSql = new java.sql.Date(dataUtil.getTime());

            //Retorna data sql
            return dataSql;
        } catch (ParseException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

}
