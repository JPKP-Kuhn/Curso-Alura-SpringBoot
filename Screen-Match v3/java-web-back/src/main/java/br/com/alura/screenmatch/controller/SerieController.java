package br.com.alura.screenmatch.controller;

import br.com.alura.screenmatch.dto.EpisodioDTO;
import br.com.alura.screenmatch.dto.SerieDTO;
import br.com.alura.screenmatch.model.Episodio;
import br.com.alura.screenmatch.service.SerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.spec.ECPoint;
import java.util.List;


@RestController
@RequestMapping("/series")
public class SerieController {

    @Autowired
    private SerieService service;

    @GetMapping
    public List<SerieDTO> obterSeries() {
        return service.obterTodasAsSeries();
    }

    @GetMapping("/top5")
    public List<SerieDTO> obterTop5Series() {
        return service.obterTop5Series();
    }

    @GetMapping("/lancamentos")
    public List<SerieDTO> obterLancamentos() {
        return service.obterLancamentos();
    }

    @GetMapping("/{id}")
    public SerieDTO obterPorId(@PathVariable Long id) {
        return service.obterPorId(id);
    }

    @GetMapping("/{id}/temporadas/todas")
    public List<EpisodioDTO> obterTodasAsTemporadas(@PathVariable Long id) {
        return service.obterTodasAsTemporadas(id);
    }

    @GetMapping("/{id}/temporadas/{numero}")
    public List<EpisodioDTO> obterTemporadasPorNumero(@PathVariable Long id, @PathVariable Long numero){
        return service.obterTemporadasPorNumero(id, numero);
    }

    @GetMapping("/{id}/temporadas/top")
    public List<EpisodioDTO> obterTopEpisodiosPorSerie(@PathVariable Long id){
        return service.obterTopEpisodios(id);
    }

    @GetMapping("/categoria/{nomeGenero}")
    public List<SerieDTO> obterSeriesPorCategoria(@PathVariable String nomeGenero){
        return service.obterSeriesPorCategoria(nomeGenero);
    }

}
