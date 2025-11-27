/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.curso.view.utils;

import br.com.ifba.curso.entity.Curso;
import br.com.ifba.curso.controler.CursoIControler;
import br.com.ifba.curso.controler.CursoControler;
import br.com.ifba.curso.view.EditarCurso;
import br.com.ifba.curso.view.ListarCurso;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author paulo
 */
public class BntEditor extends AbstractCellEditor implements TableCellEditor, ActionListener{
    
    private final JButton button;
    private final JTable table;
    private int row, column;
    private final String buttonText; 

    public BntEditor(JTable table, String text) { 
        this.table = table;
        this.buttonText = text;
        this.button = new JButton(this.buttonText);
        this.button.setOpaque(true);
        this.button.addActionListener(this); 
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        this.row = row;
        this.column = column;
        
        this.button.setText(this.buttonText); 
        return this.button;
    }

    @Override
    public Object getCellEditorValue() {
        return this.button.getText();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        fireEditingStopped();

        // LÓGICA DE AÇÃO
        switch (column) {
            case 3 -> {
                String codidoCursoEditar = (String) table.getValueAt(row, 0);
                
                try{
                    CursoIControler cursoControler = new CursoControler();
                    
                    Curso cursoAEditar = cursoControler.findByCodigoCurso(codidoCursoEditar);
                    
                    if(cursoAEditar != null){
                        ListarCurso telaPai = (ListarCurso) ListarCurso.getFrames()[0];
                        EditarCurso viewEditarCurso = new EditarCurso(telaPai, true, cursoAEditar);
                        viewEditarCurso.setVisible(true);
                        telaPai.loadTableCursos();
                    } else {
                        JOptionPane.showMessageDialog(table, "Erro: não foi possivel localizar o curso para edição");
                    }
                } catch (RuntimeException ey){
                    JOptionPane.showMessageDialog(table, ey.getMessage(),"Erro", JOptionPane.WARNING_MESSAGE);
                }
            }
            case 4 -> {
                String codigoCursoDeletar = (String) table.getValueAt(row, 0);
                
                int resposta = JOptionPane.showConfirmDialog(table, "Deseja realmente excluir esse curso?", "Excluir", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                
                if(resposta == JOptionPane.YES_OPTION){
                    try{
                        CursoIControler cursoControler = new CursoControler();
                        
                        Curso cursoADeletar = cursoControler.findByCodigoCurso(codigoCursoDeletar);
                        
                        System.out.println(row);
                        
                        if (cursoADeletar != null){
                            cursoControler.delete(cursoADeletar);
                            ((DefaultTableModel) table.getModel()).removeRow(row);
                            JOptionPane.showMessageDialog(table, "Exclusão realizada com sucesso!");
                        } else {
                            JOptionPane.showMessageDialog(table, "Erro, Não foi possivel localizar o curso em sua base de dados");
                        }
                    } catch (RuntimeException ex){
                        JOptionPane.showMessageDialog(table, ex.getMessage(),"Erro", JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
            default -> {
                JOptionPane.showMessageDialog(table, "Função Indisponivel no momento");
                throw new AssertionError();
            }
        }
    }
}