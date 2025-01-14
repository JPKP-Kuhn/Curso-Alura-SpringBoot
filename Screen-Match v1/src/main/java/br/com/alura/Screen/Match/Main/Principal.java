package br.com.alura.Screen.Match.Main;

import br.com.alura.Screen.Match.model.DadosEpisodio;
import br.com.alura.Screen.Match.model.DadosSerie;
import br.com.alura.Screen.Match.model.DadosTemporada;
import br.com.alura.Screen.Match.model.Episodio;
import br.com.alura.Screen.Match.service.ConsumoAPI;
import br.com.alura.Screen.Match.service.ConverteDados;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class Principal {
    private  Scanner leitura = new Scanner(System.in);
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConverteDados converteDados = new ConverteDados();

    private final String ENDERECO = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=a1c06498";


    public void exibeMenu(){
        System.out.println("Digite o nome da série para busca: ");
        var nomeSerie = leitura.nextLine();

        var json = consumoAPI.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + API_KEY);

        DadosSerie dados = converteDados.obterDados(json, DadosSerie.class);
        System.out.println(dados);

		List<DadosTemporada> temporadas = new ArrayList<>();

		for (int i = 1; i <= dados.totalTemporadas(); i++) {
			json = consumoAPI.obterDados(ENDERECO + nomeSerie.replace(" ", "+")+ "&Season="+i + API_KEY);
			DadosTemporada dadosTemporada = converteDados.obterDados(json, DadosTemporada.class);
			temporadas.add(dadosTemporada);
		}
		System.out.println(temporadas);
		temporadas.forEach(System.out::println);

//        for (int i = 0; i < dados.totalTemporadas(); i++){
//            List<DadosEpisodio> episodiostemporada = temporadas.get(i).episodios();
//            for (int j = 0; j < episodiostemporada.size(); j++){
//                System.out.println(episodiostemporada.get(j).titulo());
//            }
//        }

        temporadas.forEach(t -> t.episodios().forEach(e -> System.out.println(e.titulo())));

        List<DadosEpisodio> dadosEpisodios = temporadas.stream()
                .flatMap(t -> t.episodios().stream())
                .collect(Collectors.toList());

        System.out.println("\n Top 10 episódios mais bem avaliados: ");
        dadosEpisodios.stream()
                .filter(e ->!e.avaliacao().equalsIgnoreCase("N/A"))
                .peek(e -> System.out.println("Primeiro filtro N/A " + e))
                .sorted(Comparator.comparing(DadosEpisodio::avaliacao).reversed())
                .peek(e -> System.out.println("Ordenação " + e))
                .limit(10)
                .map(e -> e.titulo().toUpperCase())
                .peek(e -> System.out.println("Mapeamento " + e))
                .forEach(System.out::println);


        //Analisar de qual temporada são esses episódios
        List<Episodio> episodios = temporadas.stream()
                .flatMap(t -> t.episodios().stream()
                        .map(d -> new Episodio(t.numero(), d))).collect(Collectors.toList());

        episodios.forEach(System.out::println);

        System.out.println("Digite um trecho do título do episódio que deseja buscar: ");
        var trechoTitulo = leitura.nextLine();
        Optional<Episodio> episodioBuscado = episodios.stream()
                .filter(e -> e.getTitulo().toUpperCase().contains(trechoTitulo.toUpperCase()))
                .findFirst();

        if(episodioBuscado.isPresent()){
            System.out.println("Episódio encontrado: " + episodioBuscado.get());
            System.out.println("Temporada: " + episodioBuscado.get().getTemporada());
        } else {
            System.out.println("Episódio não encontrado");
        }

        System.out.println("A partir de que ano você deseja ver os episódios? ");
        Integer ano = leitura.nextInt();
        leitura.nextLine();

        LocalDate dataBusca = LocalDate.of(ano, 1, 1);

        DateTimeFormatter dataBrasil = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        episodios.stream()
                .filter(e -> e.getDataLancamento() != null && e.getDataLancamento().isAfter(dataBusca))
                .forEach(e -> System.out.println("Temporada: " + e.getTemporada() +
                        " Episódio: " + e.getTitulo() +
                        " Data de Lançamento: " + e.getDataLancamento().format(dataBrasil)));


        Map<Integer, Double> avaliacaoPorTemporada = episodios.stream()
                .filter(e -> e.getAvaliacao() > 0.0)
                .collect(Collectors.groupingBy(Episodio::getTemporada,
                        Collectors.averagingDouble(Episodio::getAvaliacao)));

        System.out.println("Média de avaliação por temporada: " + avaliacaoPorTemporada);

        DoubleSummaryStatistics est = episodios.stream()
                .filter(e -> e.getAvaliacao() > 0.0)
                .collect(Collectors.summarizingDouble(Episodio::getAvaliacao));

        System.out.println("Média: "+ est.getAverage());
        System.out.println("Melhor episódio: "+ est.getMax());
        System.out.println("Pior episódio: "+ est.getMin());
        System.out.println("Quantidade: "+ est.getCount());
    }
}
