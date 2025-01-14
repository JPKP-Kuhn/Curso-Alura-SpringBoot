package br.com.alura.screenmatch.model;

public enum Categoria {
    ACAO("Action", "Ação"),
    COMEDIA("Comedy", "Comédia"),
    DRAMA("Drama", "Drama"),
    SUSPENSE("Thriller", "Suspense"),
    TERROR("Horror", "Terror"),
    ROMANCE("Romance", "Romance"),
    FICCAO("Sci-Fi", "Ficção"),
    DOCUMENTARIO("Documentary", "Documentário"),
    ANIMACAO("Animation", "Animação"),
    MUSICAL("Musical", "Musical"),
    AVENTURA("Adventure", "Aventura"),
    FANTASIA("Fantasy", "Fantasia"),
    FAROESTE("Western", "Faroeste"),
    POLICIAL("Crime", "Policial"),
    GUERRA("War", "Guerra"),
    BIOGRAFIA("Biography", "Biografia"),
    FAMILIA("Family", "Família"),
    HISTORIA("History", "História"),
    MISTERIO("Mystery", "Mistério"),;

    private String categoriaOmdb;

    private String categoriaPortugues;

    Categoria(String categoriaOmdb, String categoriaPortugues) {
        this.categoriaOmdb = categoriaOmdb;
        this.categoriaPortugues = categoriaPortugues;
    }

    public static Categoria fromString(String text) {
        for (Categoria categoria : Categoria.values()) {
            if (categoria.categoriaOmdb.equalsIgnoreCase(text)) {
                return categoria;
            }
        }
        throw new IllegalArgumentException("Nenhuma categoria encontrada para a string fornecida: " + text);
    }
    public static Categoria fromPortugues(String text) {
        for (Categoria categoria : Categoria.values()) {
            if (categoria.categoriaPortugues.equalsIgnoreCase(text)) {
                return categoria;
            }
        }
        throw new IllegalArgumentException("Nenhuma categoria encontrada para a string fornecida: " + text);
    }

}