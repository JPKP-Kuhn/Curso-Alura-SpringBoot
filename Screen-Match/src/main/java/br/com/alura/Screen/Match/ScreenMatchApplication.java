package br.com.alura.Screen.Match;

import br.com.alura.Screen.Match.model.DadosSerie;
import br.com.alura.Screen.Match.service.ConsumoAPI;
import br.com.alura.Screen.Match.service.ConverteDados;
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
		ConsumoAPI consumoAPI = new ConsumoAPI();
		var json = consumoAPI.obterDados("http://www.omdbapi.com/?t=gilmore+girls&apikey=a1c06498");
		System.out.println(json);

		ConverteDados conversor = new ConverteDados();
		DadosSerie dados = conversor.obterDados(json, DadosSerie.class);
		System.out.println(dados);
	}
}
