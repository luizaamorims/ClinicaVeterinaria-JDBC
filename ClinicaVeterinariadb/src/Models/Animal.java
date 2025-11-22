package Models;

import java.time.LocalDate;

public class Animal {
    private int idAnimal;
    private String nome;
    private String especie;
    private String raca;
    private LocalDate dataNascimento;
    private double peso;
    private String cpfProprietario;

    public Animal() {
    }

    public Animal(String nome, String especie, String raca, LocalDate dataNascimento,
                  double peso, String cpfProprietario) {
        this.nome = nome;
        this.especie = especie;
        this.raca = raca;
        this.dataNascimento = dataNascimento;
        this.peso = peso;
        this.cpfProprietario = cpfProprietario;
    }


    public int getIdAnimal() {
        return idAnimal;
    }

    public void setIdAnimal(int idAnimal) {
        this.idAnimal = idAnimal;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public String getCpfProprietario() {
        return cpfProprietario;
    }

    public void setCpfProprietario(String cpfProprietario) {
        this.cpfProprietario = cpfProprietario;
    }

    public String toString() {
        return String.format("ID: %d | Nome: %s | Espécie: %s | Raça: %s | Peso: %.2f kg",
                idAnimal, nome, especie, raca, peso);
    }
}
