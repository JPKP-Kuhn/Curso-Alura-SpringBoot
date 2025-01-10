package br.com.alura.screenmatch.model;

public enum Categoria {
    ACAO("Action"),
    COMEDIA("Comedy"),
    DRAMA("Drama"),
    SUSPENSE("Thriller"),
    TERROR("Horror"),
    ROMANCE("Romance"),
    FICCAO("Sci-Fi"),
    DOCUMENTARIO("Documentary"),
    ANIMACAO("Animation"),
    MUSICAL("Musical"),
    AVENTURA("Adventure"),
    FANTASIA("Fantasy"),
    FAROESTE("Western"),
    POLICIAL("Crime"),
    GUERRA("War"),
    BIOGRAFIA("Biography"),
    FAMILIA("Family"),
    HISTORIA("History"),
    MISTERIO("Mystery");

    private String categoriaOmdb;

    Categoria(String categoriaOmdb) {
        this.categoriaOmdb = categoriaOmdb;
    }

    public static Categoria fromString(String text) {
        for (Categoria categoria : Categoria.values()) {
            if (categoria.categoriaOmdb.equalsIgnoreCase(text)) {
                return categoria;
            }
        }
        throw new IllegalArgumentException("Nenhuma categoria encontrada para a string fornecida: " + text);
    }
}