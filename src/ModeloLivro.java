import java.io.Serializable;

public class  ModeloLivro implements Serializable, Comparable<ModeloLivro> {
        private String titulo;
        private String autor;
        private int ano;
        private boolean emprestado;
        private ModeloUsuario usuarioComLivro;


    public ModeloLivro(String titulo, String autor, int ano) {
        this.titulo = titulo;
        this.autor = autor;
        this.ano = ano;
    }

    public int getAno() {
        return ano;
    }

    public String getAutor() {
        return autor;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setEmprestado(boolean emprestado) {
        this.emprestado = emprestado;
    }

    public void setUsuarioComLivro(ModeloUsuario usuarioComLivro) {
        this.usuarioComLivro = usuarioComLivro;
    }

    public ModeloUsuario getUsuarioComLivro() {
        return usuarioComLivro;
    }

    @Override
    public String toString() {
        return "ModeloLivro{" +
                "titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                ", ano=" + ano +
                ", emprestado=" + emprestado +
                ", usuarioComLivro=" + usuarioComLivro +
                '}';
    }

    public boolean isEmprestado() {
        return emprestado;
    }

    @Override
    public int compareTo(ModeloLivro outroLivro){
        return this.titulo.compareTo(outroLivro.getTitulo());
    }
}
