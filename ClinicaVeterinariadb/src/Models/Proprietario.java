package Models;

public class Proprietario {

    private String cpf;
    private String nome;
    private String telefone;
    private String endereco;
    private String email;

    public Proprietario() {
    }

    public Proprietario(String cpf, String nome, String telefone, String endereco, String email) {
        this.cpf = cpf;
        this.nome = nome;
        this.telefone = telefone;
        this.endereco = endereco;
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String toString() {
        return String.format("CPF: %s | Nome: %s | Telefone: %s | Email: %s",
                cpf, nome, telefone, email);
    }
}
