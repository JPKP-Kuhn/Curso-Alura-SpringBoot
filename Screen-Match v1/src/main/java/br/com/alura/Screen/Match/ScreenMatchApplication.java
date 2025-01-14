package br.com.alura.Screen.Match;

import br.com.alura.Screen.Match.Main.Principal;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScreenMatchApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ScreenMatchApplication.class, args);
		System.out.println("Olá Mundo!"); //Executa depois
	}

	@Override
	public void run(String... args) throws Exception { //Executa primeiro para inicialização da aplicação
		Principal main = new Principal();
		main.exibeMenu();


	}
}
