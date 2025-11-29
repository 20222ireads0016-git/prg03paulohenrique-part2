/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.curso.view.utils;

import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import org.springframework.context.annotation.Scope;

@org.springframework.stereotype.Component
@Scope("prototype")
public class BntRenderer extends JButton implements TableCellRenderer {
    
    private String buttonText; // Novo campo para guardar o texto
    
    // Construtor modificado para aceitar o texto
    public BntRenderer() { 
        setOpaque(true);
    }

    public void setLable(String text){
        this.buttonText = text;
    }
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        setText(this.buttonText);
        return this;
    }
}