import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Biblioteca {
    private List<ModeloLivro> livros;
    private List<ModeloUsuario> usuarios;

    public Biblioteca() {
        this.livros = new ArrayList<>();
        this.usuarios = new ArrayList<>();
        this.carregarDados();
    }

    public void cadastrarLivro(ModeloLivro livro) {
        livros.add(livro);
        salvaDados();

    }

    public void cadastrarUsuario(ModeloUsuario usuario) {
        usuarios.add(usuario);
        salvaDados();

    }

    public void listarLivros() {
        for (ModeloLivro livro : livros) {
            System.out.println(livro);
        }
    }

    public void listarUsuarios() {
        for (ModeloUsuario usuario : usuarios) {
            System.out.println(usuario);
        }
    }

    public ModeloLivro buscarLivroPorTitulo(String titulo) {
        return livros.stream()
                .filter(livro -> livro.getTitulo().equalsIgnoreCase(titulo))
                .findFirst()
                .orElse(null);
    }
    public void orndenarLivrosPorTitulo(){
        Collections.sort(livros);
    }
    public void ordenarLivrosPorAno() {
        livros.sort((livro1, livro2) ->
                Integer.compare(livro1.getAno(), livro2.getAno()));
    }
    public void emprestarLivro(String titulo, ModeloUsuario usuario) throws LivroIndisponivelException {
        ModeloLivro livro = buscarLivroPorTitulo(titulo);
        if(livro == null){
            throw new LivroNaoEncontrado("Livro não encontrado! ");
        }
        else if(livro.isEmprestado() == false){
            livro.setEmprestado(true);
            livro.setUsuarioComLivro(usuario);
            salvaDados();

        }else {
            throw new LivroIndisponivelException("O livro já está emprestado!");
        }

    }
    public void devolverLivro(String titulo){
        ModeloLivro livro = buscarLivroPorTitulo(titulo);
        if (livro == null){
            throw new LivroNaoEncontrado("Livro não encontrado");
        } else if (livro.isEmprestado() == false ) {
            throw new LivroNaoEmprestado("O livro já está no estoque!");
        }else {
            livro.setEmprestado(false);
            livro.setUsuarioComLivro(null);
            salvaDados();

        }

    }
    public List<ModeloLivro> buscarLivroPorAutor(String autor){
        return livros.stream()
                .filter(livro -> livro.getAutor().equalsIgnoreCase(autor))
                .collect(Collectors.toList());
    }
    public String buscarNomeUsuarioPorEmail(String email){
        return usuarios.stream()
                .filter(usuario -> usuario.getEmail().equalsIgnoreCase(email))
                .map(usuario -> usuario.getNomeUsuario())
                .findFirst()
                .orElse(null);
    }
    public  Map<String, List<ModeloLivro>> agruparLivros(){
        Map<String, List<ModeloLivro>> agrupado = livros.stream()
                .collect(Collectors.groupingBy(livro -> livro.getAutor()));
        return agrupado;
    }

    public void salvaDados(){
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("livros.dat"))) {
            oos.writeObject(livros);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erro ao salvar: "+ e.getMessage());

        }
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("usuarios.dat"))) {
            oos.writeObject(usuarios);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erro ao salvar: "+ e.getMessage());
        }
    }
    public void carregarDados(){
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("livros.dat"))) {
            livros = (List<ModeloLivro>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("usuarios.dat"))) {
            usuarios = (List<ModeloUsuario>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


}