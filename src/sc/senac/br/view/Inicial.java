/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sc.senac.br.view;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import sc.senac.br.uteis.Conversor;

/**
 *
 * @author Alunos
 */
public class Inicial extends javax.swing.JFrame {

    private CadastrarClienteEnd cadastroCliente = null;
    private ListarCliente frmListarcliente = null;
    private CadastrarEntregador cadastrarEntregador = null;
    private CadastroCategoria cadastrarCat = null;
    private CadastrarProduto cadProduto = null;
    private CadastroEncomenda cadastroPedido = null;
    private ListarEncomenda listEncomenda = null;
    private Sobre sobre = null;

    public Inicial() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        cadastrarCliente = new javax.swing.JMenuItem();
        listarCliente = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        cadastrarProduto = new javax.swing.JMenuItem();
        Categoria = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        cadastrarFuncionario = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        cadastrarPedido = new javax.swing.JMenuItem();
        listarPedido = new javax.swing.JMenuItem();
        jMenu7 = new javax.swing.JMenu();
        Sobre = new javax.swing.JMenuItem();

        jMenu3.setText("File");
        jMenuBar2.add(jMenu3);

        jMenu4.setText("Edit");
        jMenuBar2.add(jMenu4);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1280, Short.MAX_VALUE)
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 668, Short.MAX_VALUE)
        );

        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sc/senac/br/icones/icons8-adicionar-usuário-masculino.png"))); // NOI18N
        jMenu1.setText("Cliente");

        cadastrarCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sc/senac/br/icones/icons8-verifique-macho.png"))); // NOI18N
        cadastrarCliente.setText("Cadastrar");
        cadastrarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cadastrarClienteActionPerformed(evt);
            }
        });
        jMenu1.add(cadastrarCliente);

        listarCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sc/senac/br/icones/icons8-documento-regular.png"))); // NOI18N
        listarCliente.setText("Listar");
        listarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listarClienteActionPerformed(evt);
            }
        });
        jMenu1.add(listarCliente);

        jMenuBar1.add(jMenu1);

        jMenu2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sc/senac/br/icones/icons8-livro-de-física.png"))); // NOI18N
        jMenu2.setText("Produto");

        cadastrarProduto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sc/senac/br/icones/icons8-lista-com-marcadores.png"))); // NOI18N
        cadastrarProduto.setText("Cadastrar");
        cadastrarProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cadastrarProdutoActionPerformed(evt);
            }
        });
        jMenu2.add(cadastrarProduto);

        Categoria.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sc/senac/br/icones/icons8-gráfico-combinado.png"))); // NOI18N
        Categoria.setText("Catagorias");
        Categoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CategoriaActionPerformed(evt);
            }
        });
        jMenu2.add(Categoria);

        jMenuBar1.add(jMenu2);

        jMenu5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sc/senac/br/icones/icons8-fila.png"))); // NOI18N
        jMenu5.setText("Funcionario");

        cadastrarFuncionario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sc/senac/br/icones/icons8-gestão-de-cliente.png"))); // NOI18N
        cadastrarFuncionario.setText("Cadastrar");
        cadastrarFuncionario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cadastrarFuncionarioActionPerformed(evt);
            }
        });
        jMenu5.add(cadastrarFuncionario);

        jMenuBar1.add(jMenu5);

        jMenu6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sc/senac/br/icones/icons8-comprar.png"))); // NOI18N
        jMenu6.setText("Pedido");

        cadastrarPedido.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sc/senac/br/icones/icons8-adicionar-ao-banco-de-dados.png"))); // NOI18N
        cadastrarPedido.setText("Cadastrar");
        cadastrarPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cadastrarPedidoActionPerformed(evt);
            }
        });
        jMenu6.add(cadastrarPedido);

        listarPedido.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sc/senac/br/icones/icons8-documento-regular.png"))); // NOI18N
        listarPedido.setText("Listar");
        listarPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listarPedidoActionPerformed(evt);
            }
        });
        jMenu6.add(listarPedido);

        jMenuBar1.add(jMenu6);

        jMenu7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sc/senac/br/icones/icons8-segurança-verificada.png"))); // NOI18N
        jMenu7.setText("Sobre");

        Sobre.setText("Sobre");
        Sobre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SobreActionPerformed(evt);
            }
        });
        jMenu7.add(Sobre);

        jMenuBar1.add(jMenu7);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane1)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void SobreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SobreActionPerformed

        if (sobre == null) {
            sobre = new Sobre();
            sobre.setVisible(true);

        } else {
            sobre.setVisible(true);
        };
    }//GEN-LAST:event_SobreActionPerformed

    private void cadastrarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cadastrarClienteActionPerformed

        if (cadastroCliente == null) {

            cadastroCliente = new CadastrarClienteEnd();

            jDesktopPane1.add(cadastroCliente);
            cadastroCliente.setVisible(true);
            cadastroCliente.setPosicao();

        } else {
            cadastroCliente.setVisible(true);
        }
    }//GEN-LAST:event_cadastrarClienteActionPerformed

    private void listarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_listarClienteActionPerformed

        if (frmListarcliente == null) {
            frmListarcliente = new ListarCliente();
            jDesktopPane1.add(frmListarcliente);
            frmListarcliente.setVisible(true);
            frmListarcliente.setPosicao();

        } else {
            frmListarcliente.setVisible(true);
            frmListarcliente.cliCtrl.listarAction();
        }
    }//GEN-LAST:event_listarClienteActionPerformed

    private void cadastrarFuncionarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cadastrarFuncionarioActionPerformed
        if (cadastrarEntregador == null) {

            cadastrarEntregador = new CadastrarEntregador();
            jDesktopPane1.add(cadastrarEntregador);
            cadastrarEntregador.setVisible(true);
            cadastrarEntregador.setPosicao();
        } else {
            cadastrarEntregador.setVisible(true);
            cadastrarEntregador.entreCtrl.listarAction();
        }
    }//GEN-LAST:event_cadastrarFuncionarioActionPerformed

    private void CategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CategoriaActionPerformed
        if (cadastrarCat == null) {

            cadastrarCat = new CadastroCategoria();
            jDesktopPane1.add(cadastrarCat);
            cadastrarCat.setVisible(true);
            cadastrarCat.setPosicao();
        } else {
            cadastrarCat.setVisible(true);
        }
    }//GEN-LAST:event_CategoriaActionPerformed

    private void cadastrarProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cadastrarProdutoActionPerformed
        if (cadProduto == null) {

            cadProduto = new CadastrarProduto();
            jDesktopPane1.add(cadProduto);
            cadProduto.setVisible(true);
            cadProduto.setPosicao();
        } else {
            cadProduto.setVisible(true);
        }

    }//GEN-LAST:event_cadastrarProdutoActionPerformed

    private void cadastrarPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cadastrarPedidoActionPerformed
        if (cadastroPedido == null) {
            cadastroPedido = new CadastroEncomenda();
            jDesktopPane1.add(cadastroPedido);
            cadastroPedido.setVisible(true);
            cadastroPedido.setPosicao();
        } else {
            cadastroPedido.setVisible(true);
        }
    }//GEN-LAST:event_cadastrarPedidoActionPerformed

    private void listarPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_listarPedidoActionPerformed
        if (listEncomenda == null) {
            listEncomenda = new ListarEncomenda();
            jDesktopPane1.add(listEncomenda);
            listEncomenda.setVisible(true);
            listEncomenda.setPosicao();
        } else {
            listEncomenda.setVisible(true);
        }
    }//GEN-LAST:event_listarPedidoActionPerformed
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Inicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Inicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Inicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Inicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Inicial().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem Categoria;
    private javax.swing.JMenuItem Sobre;
    private javax.swing.JMenuItem cadastrarCliente;
    private javax.swing.JMenuItem cadastrarFuncionario;
    private javax.swing.JMenuItem cadastrarPedido;
    private javax.swing.JMenuItem cadastrarProduto;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenuItem listarCliente;
    private javax.swing.JMenuItem listarPedido;
    // End of variables declaration//GEN-END:variables
}
