package br.com.ifba;

import br.com.ifba.curso.view.ListarCurso;
import org.springframework.boot.CommandLineRunner;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class Pgr03paulohenriquePart3Application {
    
    public static void main(String[] args) {
        
        new SpringApplicationBuilder(Pgr03paulohenriquePart3Application.class).headless(false).run(args);
    }
    
    @Bean
    public CommandLineRunner executar(ListarCurso listarCurso){
        return args -> {
            java.awt.EventQueue.invokeLater(() -> {
                listarCurso.setVisible(true);
            });
        };
    }
}
