package Models;

import java.time.LocalDateTime;

public class Consulta {

    private int idConsulta;
    private LocalDateTime dataHora;
    private int idAnimal;
    private String crmvVeterinario;
    private String diagnostico;
    private double valor;

    public Consulta() {
    }

    public Consulta(LocalDateTime dataHora, int idAnimal, String crmvVeterinario, String diagnostico, double valor) {
        this.dataHora = dataHora;
        this.idAnimal = idAnimal;
        this.crmvVeterinario = crmvVeterinario;
        this.diagnostico = diagnostico;
        this.valor = valor;
    }


    public int getIdConsulta() {
        return idConsulta;
    }

    public void setIdConsulta(int idConsulta) {
        this.idConsulta = idConsulta;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public int getIdAnimal() {
        return idAnimal;
    }

    public void setIdAnimal(int idAnimal) {
        this.idAnimal = idAnimal;
    }

    public String getCrmvVeterinario() {
        return crmvVeterinario;
    }

    public void setCrmvVeterinario(String crmvVeterinario) {
        this.crmvVeterinario = crmvVeterinario;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }


    public String toString() {
        return String.format("ID: %d | Data: %s | Animal ID: %d | Valor: R$ %.2f",
                idConsulta, dataHora, idAnimal, valor);
    }
}

