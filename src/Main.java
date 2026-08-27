public class Main {
    public static void main(String[] args) {

        Biblioteca biblioteca = new Biblioteca();

        ModeloLivro livro1 = new ModeloLivro(
                "Harry Potter",
                "J.K. Rowling",
                2010
        );

        ModeloLivro livro2 = new ModeloLivro(
                "Java",
                "EBAC",
                2026
        );

        ModeloLivro livro3 = new ModeloLivro(
                "Clean Code",
                "Robert C. Martin",
                2008
        );

        ModeloLivro livro4 = new ModeloLivro(
                "Java Avançado",
                "EBAC",
                2024
        );

        biblioteca.cadastrarLivro(livro1);
        biblioteca.cadastrarLivro(livro2);
        biblioteca.cadastrarLivro(livro3);
        biblioteca.cadastrarLivro(livro4);


        ModeloUsuario usuario1 = new ModeloUsuario(
                "Vitor Gabriel",
                "vitorgabrielcouto16@gmail.com"
        );

        ModeloUsuario usuario2 = new ModeloUsuario(
                "Maikinho",
                "maikinholokinho@gmail.com"
        );

        biblioteca.cadastrarUsuario(usuario1);
        biblioteca.cadastrarUsuario(usuario2);


        System.out.println("\n===== LIVROS CADASTRADOS =====");
        biblioteca.listarLivros();



        System.out.println("\n===== USUÁRIOS CADASTRADOS =====");
        biblioteca.listarUsuarios();



        System.out.println("\n===== BUSCAR LIVRO =====");

        ModeloLivro livroEncontrado =
                biblioteca.buscarLivroPorTitulo("java");

        if (livroEncontrado != null) {
            System.out.println("Livro encontrado:");
            System.out.println(livroEncontrado);
        } else {
            System.out.println("Livro não encontrado!");
        }


        System.out.println("\n===== LIVROS ORDENADOS POR TÍTULO =====");

        biblioteca.orndenarLivrosPorTitulo();
        biblioteca.listarLivros();



        System.out.println("\n===== LIVROS ORDENADOS POR ANO =====");

        biblioteca.ordenarLivrosPorAno();
        biblioteca.listarLivros();
        System.out.println("\n===== LIVROS DO AUTOR EBAC =====");

        for (ModeloLivro livro : biblioteca.buscarLivroPorAutor("EBAC")) {
            System.out.println(livro);
        }
        System.out.println("\n===== BUSCAR USUÁRIO POR EMAIL =====");

        String nomeUsuario =
                biblioteca.buscarNomeUsuarioPorEmail(
                        "vitorgabrielcouto16@gmail.com"
                );

        if (nomeUsuario != null) {
            System.out.println("Usuário encontrado: " + nomeUsuario);
        } else {
            System.out.println("Usuário não encontrado!");
        }
        System.out.println("\n===== LIVROS AGRUPADOS POR AUTOR =====");

        biblioteca.agruparLivros().forEach((autor, livros) -> {

            System.out.println("\nAutor: " + autor);

            for (ModeloLivro livro : livros) {
                System.out.println(" - " + livro.getTitulo());
            }
        });

        System.out.println("\n===== EMPRÉSTIMO =====");

        try {

            biblioteca.emprestarLivro(
                    "Harry Potter",
                    usuario1
            );

            System.out.println(
                    "Livro emprestado com sucesso!"
            );

        } catch (LivroIndisponivelException e) {

            System.out.println(
                    "Erro: " + e.getMessage()
            );
        }

        System.out.println("\n===== TESTANDO LIVRO INDISPONÍVEL =====");

        try {

            biblioteca.emprestarLivro(
                    "Harry Potter",
                    usuario2
            );

        } catch (LivroIndisponivelException e) {

            System.out.println(
                    "Erro: " + e.getMessage()
            );
        }

        System.out.println("\n===== DEVOLUÇÃO =====");

        try {

            biblioteca.devolverLivro("Harry Potter");

            System.out.println(
                    "Livro devolvido com sucesso!"
            );

        } catch (Exception e) {

            System.out.println(
                    "Erro: " + e.getMessage()
            );
        }
        System.out.println("\n===== TESTANDO DEVOLUÇÃO NOVAMENTE =====");

        try {

            biblioteca.devolverLivro("Harry Potter");

        } catch (Exception e) {

            System.out.println(
                    "Erro: " + e.getMessage()
            );
        }


        System.out.println("\n===== ESTADO FINAL DOS LIVROS =====");

        biblioteca.listarLivros();

    }
}