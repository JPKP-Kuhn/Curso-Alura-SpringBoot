package br.com.alura.Screen.Match.service;

public interface IConverteDados {
    <T> T obterDados(String json, Class<T> classe);
}
