/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.curso.view.utils;

import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class BntRenderer extends JButton implements TableCellRenderer {
    
    private final String buttonText; // Novo campo para guardar o texto
    
    // Construtor modificado para aceitar o texto
    public BntRenderer(String text) { 
        this.buttonText = text;
        setOpaque(true);
    }
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        
        // Define o texto do botão com base no que foi passado no construtor
        setText(this.buttonText); // <--- AQUI ESTÁ A MUDANÇA
        return this;
    }
}