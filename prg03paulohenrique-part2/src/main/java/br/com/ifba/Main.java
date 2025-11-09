/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author paulo
 */
public class Main {
    public static void main(String[] args) {
        final String URL = "jdbc:mysql://localhost:3306/pgr03paulohenrique?useTimezone=true&serverTimezone=UTC";
        final String USER = "root";
        final String PASSWORD = "root";
        
        Connection conexao = null;
        
        try {
            conexao = DriverManager.getConnection(URL, USER, PASSWORD);
            
            System.out.println("✅ Conexão com o MySQL realizada com SUCESSO!");
            
        } catch (SQLException e) {
            System.err.println("❌ ERRO ao conectar com o MySQL!");
            System.err.println("Causa do erro: " + e.getMessage());
            
        } finally {
            if (conexao != null) {
                try {
                    conexao.close();
                    System.out.println("Conexão encerrada.");
                } catch (SQLException e) {
                    System.err.println("Erro ao fechar a conexão: " + e.getMessage());
                }
            }
        }
    }
}
