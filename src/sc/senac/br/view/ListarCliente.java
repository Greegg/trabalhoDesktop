/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sc.senac.br.view;

import java.awt.Dimension;
import sc.senac.br.control.ClienteControl;
import sc.senac.br.control.EnderecoControl;

/**
 *
 * @author Alunos
 */
public class ListarCliente extends javax.swing.JInternalFrame {

    ClienteControl cliCtrl;
    EnderecoControl endCtrl;
    CadastrarEndereco cadastrarEnd = null;
    EditarCliente editarcliente = null;

    public ListarCliente() {
        initComponents();
        cliCtrl = new ClienteControl(tbCliente, tbEnd, tfPesquisaCliente, nomeCliente);
        cliCtrl.listarAction();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tbCliente = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        tfPesquisaCliente = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbEnd = new javax.swing.JTable();
        nomeCliente = new javax.swing.JLabel();
        cadEnd = new javax.swing.JButton();
        editarCli = new javax.swing.JButton();
        excluirCliente = new javax.swing.JButton();
        editarEnd = new javax.swing.JButton();
        excluirEnd = new javax.swing.JButton();
        atualizar = new javax.swing.JButton();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);

        tbCliente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "Nome", "Data Nascimento", "E-mail", "Telefone"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbCliente.getTableHeader().setReorderingAllowed(false);
        tbCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tbClienteMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tbCliente);
        if (tbCliente.getColumnModel().getColumnCount() > 0) {
            tbCliente.getColumnModel().getColumn(0).setResizable(false);
            tbCliente.getColumnModel().getColumn(1).setResizable(false);
            tbCliente.getColumnModel().getColumn(2).setResizable(false);
            tbCliente.getColumnModel().getColumn(3).setResizable(false);
            tbCliente.getColumnModel().getColumn(4).setResizable(false);
        }

        jLabel1.setText("Digite o nome do cliente");

        tfPesquisaCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfPesquisaClienteKeyReleased(evt);
            }
        });

        jLabel2.setText("Endereco");

        tbEnd.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "Logradouro", "Bairro", "Cidade", "CEP", "Complemento"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbEnd.getTableHeader().setReorderingAllowed(false);
        tbEnd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tbEndMouseReleased(evt);
            }
        });
        jScrollPane2.setViewportView(tbEnd);
        if (tbEnd.getColumnModel().getColumnCount() > 0) {
            tbEnd.getColumnModel().getColumn(0).setResizable(false);
            tbEnd.getColumnModel().getColumn(1).setResizable(false);
            tbEnd.getColumnModel().getColumn(2).setResizable(false);
            tbEnd.getColumnModel().getColumn(3).setResizable(false);
            tbEnd.getColumnModel().getColumn(4).setResizable(false);
            tbEnd.getColumnModel().getColumn(5).setResizable(false);
        }

        cadEnd.setText("Adicionar");
        cadEnd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cadEndActionPerformed(evt);
            }
        });

        editarCli.setText("Editar");
        editarCli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editarCliActionPerformed(evt);
            }
        });

        excluirCliente.setText("Excluir");
        excluirCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                excluirClienteActionPerformed(evt);
            }
        });

        editarEnd.setText("Editar");
        editarEnd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editarEndActionPerformed(evt);
            }
        });

        excluirEnd.setText("Excluir");
        excluirEnd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                excluirEndActionPerformed(evt);
            }
        });

        atualizar.setText("Atualizar");
        atualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                atualizarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 567, Short.MAX_VALUE)
                    .addComponent(tfPesquisaCliente)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(atualizar)
                        .addGap(81, 81, 81)
                        .addComponent(editarCli)
                        .addGap(34, 34, 34)
                        .addComponent(excluirCliente)
                        .addGap(32, 32, 32))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nomeCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(132, 132, 132)
                        .addComponent(cadEnd)
                        .addGap(28, 28, 28)
                        .addComponent(editarEnd)
                        .addGap(18, 18, 18)
                        .addComponent(excluirEnd)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(editarCli)
                    .addComponent(excluirCliente)
                    .addComponent(atualizar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfPesquisaCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(nomeCliente)
                    .addComponent(cadEnd)
                    .addComponent(editarEnd)
                    .addComponent(excluirEnd))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tfPesquisaClienteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfPesquisaClienteKeyReleased
        cliCtrl.pesquisarAction();
    }//GEN-LAST:event_tfPesquisaClienteKeyReleased

    private void tbClienteMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbClienteMouseReleased
        cliCtrl.enviarClienteEnd();
        cliCtrl.getEndCtrl().listarAction();
    }//GEN-LAST:event_tbClienteMouseReleased

    private void cadEndActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cadEndActionPerformed

        if (cadastrarEnd == null) {
            cadastrarEnd = new CadastrarEndereco(cliCtrl.getCliente());
            cadastrarEnd.setVisible(true);

        } else {
            cadastrarEnd.setVisible(true);
        };

        cliCtrl.enviarClienteEnd();

    }//GEN-LAST:event_cadEndActionPerformed

    private void editarCliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editarCliActionPerformed

        if (editarcliente == null) {

            editarcliente = new EditarCliente(cliCtrl.getCliente());

            editarcliente.setVisible(true);

        } else {
            editarcliente.setVisible(true);
        };

        this.editarcliente = null;
    }//GEN-LAST:event_editarCliActionPerformed

    private void excluirClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_excluirClienteActionPerformed
        cliCtrl.excluirAction();
    }//GEN-LAST:event_excluirClienteActionPerformed

    private void editarEndActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editarEndActionPerformed

        if (cadastrarEnd == null) {

            cadastrarEnd = new CadastrarEndereco(cliCtrl.getEndCtrl().getEndereco());
            cadastrarEnd.setVisible(true);

        } else {
            cadastrarEnd.setVisible(true);
        };

        cliCtrl.enviarClienteEnd();

        this.cadastrarEnd = null;
        cliCtrl.getEndCtrl().listarAction();
    }//GEN-LAST:event_editarEndActionPerformed

    private void tbEndMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbEndMouseReleased
        cliCtrl.enviarEnd();
    }//GEN-LAST:event_tbEndMouseReleased

    private void excluirEndActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_excluirEndActionPerformed
        cliCtrl.getEndCtrl().excluirAction();
        cliCtrl.getEndCtrl().listarAction();
    }//GEN-LAST:event_excluirEndActionPerformed

    private void atualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_atualizarActionPerformed
        cliCtrl.listarAction();
        cliCtrl.getEndCtrl().listarAction();
    }//GEN-LAST:event_atualizarActionPerformed

    public void setPosicao() {
        Dimension d = this.getDesktopPane().getSize();
        this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton atualizar;
    private javax.swing.JButton cadEnd;
    private javax.swing.JButton editarCli;
    private javax.swing.JButton editarEnd;
    private javax.swing.JButton excluirCliente;
    private javax.swing.JButton excluirEnd;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel nomeCliente;
    private javax.swing.JTable tbCliente;
    private javax.swing.JTable tbEnd;
    private javax.swing.JTextField tfPesquisaCliente;
    // End of variables declaration//GEN-END:variables
}
