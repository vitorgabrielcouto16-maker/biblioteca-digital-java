import java.io.Serializable;

public class ModeloUsuario implements Serializable {
    private String nomeUsuario;
    private String email;

    @Override
    public String toString() {
        return "ModeloUsuario{" +
                "nomeUsuario='" + nomeUsuario + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public String getEmail() {
        return email;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public ModeloUsuario(String nomeUsuario,String email) {
        this.nomeUsuario = nomeUsuario;
        this.email = email;


    }
}
