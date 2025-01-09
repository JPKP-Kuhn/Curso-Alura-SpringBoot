package br.com.alura.TabelaFipe.principal;

import br.com.alura.TabelaFipe.service.ConsumoAPI;

import java.util.Scanner;

public class Principal {

    private Scanner scanner = new Scanner(System.in);
    private final String URL_BASE = "https://parallelum.com.br/fipe/api/v1/";
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    public void exibirMenu(){
        var menu = """
                ***OPÇÕES***
                Carro
                Moto
                Caminhão
                
                Digite uma das opções para consultar: """;

        System.out.println(menu);
        var opcao = scanner.nextLine();
        String endereco;

        if (opcao.toLowerCase().contains("car")) {
            endereco = URL_BASE + "carros/marcas";
        } else if (opcao.toLowerCase().contains("mot")) {
            endereco = URL_BASE + "motos/marcas";
        } else {
            endereco = URL_BASE + "caminhoes/marcas";
        }

        var json = consumoAPI.obterDados(endereco);
        System.out.println(json);
    }
}
