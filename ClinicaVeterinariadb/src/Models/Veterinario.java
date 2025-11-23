package Models;

public class Veterinario {

    private String crmv;
    private String nome;
    private String especialidade;
    private String telefone;

    public Veterinario() {
    }

    public Veterinario(String crmv, String nome, String especialidade, String telefone) {
        this.crmv = crmv;
        this.nome = nome;
        this.especialidade = especialidade;
        this.telefone = telefone;
    }


    public String getCrmv() {
        return crmv;
    }

    public void setCrmv(String crmv) {
        this.crmv = crmv;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String toString() {
        return String.format("CRMV: %s | Nome: %s | Especialidade: %s",
                crmv, nome, especialidade);
    }
}