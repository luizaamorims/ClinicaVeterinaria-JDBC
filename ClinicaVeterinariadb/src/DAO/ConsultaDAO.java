package DAO;


import Models.Consulta;
import config.FabricaConexao;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConsultaDAO {


    public int inserir(Consulta consulta) throws SQLException {
        String sql = "INSERT INTO Consulta (data_hora, id_animal, crmv_veterinario, diagnostico, valor) " +
                "VALUES (?, ?, ?, ?, ?) RETURNING id_consulta";

        try (Connection conn = FabricaConexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setTimestamp(1, Timestamp.valueOf(consulta.getDataHora()));
            stmt.setInt(2, consulta.getIdAnimal());
            stmt.setString(3, consulta.getCrmvVeterinario());
            stmt.setString(4, consulta.getDiagnostico());
            stmt.setDouble(5, consulta.getValor());

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int id = rs.getInt(1);
                System.out.println("Consulta inserida com sucesso! ID: " + id);
                return id;
            }
        }
        return -1;
    }


    public List<Consulta> buscarPorAnimal(int idAnimal) throws SQLException {
        List<Consulta> lista = new ArrayList<>();
        String sql = "SELECT * FROM Consulta WHERE id_animal = ? ORDER BY data_hora DESC";

        try (Connection conn = FabricaConexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idAnimal);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Consulta consulta = new Consulta();
                consulta.setIdConsulta(rs.getInt("id_consulta"));
                consulta.setDataHora(rs.getTimestamp("data_hora").toLocalDateTime());
                consulta.setIdAnimal(rs.getInt("id_animal"));
                consulta.setCrmvVeterinario(rs.getString("crmv_veterinario"));
                consulta.setDiagnostico(rs.getString("diagnostico"));
                consulta.setValor(rs.getDouble("valor"));
                lista.add(consulta);
            }
        }
        return lista;
    }

    public Consulta buscarPorId(int idConsulta) throws SQLException {
        String sql = "SELECT * FROM Consulta WHERE id_consulta = ?";
        try (Connection conn = FabricaConexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idConsulta);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Consulta consulta = new Consulta();
                consulta.setIdConsulta(rs.getInt("id_consulta"));
                consulta.setDataHora(rs.getTimestamp("data_hora").toLocalDateTime());
                consulta.setIdAnimal(rs.getInt("id_animal"));
                consulta.setCrmvVeterinario(rs.getString("crmv_veterinario"));
                consulta.setDiagnostico(rs.getString("diagnostico"));
                consulta.setValor(rs.getDouble("valor"));
                return consulta;
            }
        }
        return null;
    }


    public void gerarRelatorioConsulta(int idConsulta) throws SQLException {
        String sql = "SELECT c.id_consulta, c.data_hora, c.diagnostico, c.valor, " +
                "a.nome AS animal_nome, a.especie, a.raca, " +
                "v.nome AS vet_nome, v.especialidade, " +
                "p.nome AS prop_nome, p.telefone AS prop_tel " +
                "FROM Consulta c " +
                "JOIN Animal a ON c.id_animal = a.id_animal " +
                "JOIN Veterinario v ON c.crmv_veterinario = v.crmv " +
                "JOIN Proprietario p ON a.cpf_proprietario = p.cpf " +
                "WHERE c.id_consulta = ?";

        try (Connection conn = FabricaConexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idConsulta);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                System.out.println("\n------- RELATÓRIO DA CONSULTA -------");
                System.out.println("ID Consulta: " + rs.getInt("id_consulta"));
                System.out.println("Data/Hora: " + rs.getTimestamp("data_hora"));
                System.out.println("\nAnimal: " + rs.getString("animal_nome"));
                System.out.println("Espécie: " + rs.getString("especie") + " | Raça: " + rs.getString("raca"));
                System.out.println("\nProprietário: " + rs.getString("prop_nome"));
                System.out.println("Telefone: " + rs.getString("prop_tel"));
                System.out.println("\nVeterinário: " + rs.getString("vet_nome"));
                System.out.println("Especialidade: " + rs.getString("especialidade"));
                System.out.println("\nDiagnóstico: " + rs.getString("diagnostico"));
                System.out.println("Valor: R$ " + String.format("%.2f", rs.getDouble("valor")));
                System.out.println("-------------------------------------------------\n");
            }
        }
    }


    public void atualizar(Consulta consulta) throws SQLException {
        String sql = "UPDATE Consulta SET diagnostico = ?, valor = ? WHERE id_consulta = ?";

        try (Connection conn = FabricaConexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, consulta.getDiagnostico());
            stmt.setDouble(2, consulta.getValor());
            stmt.setInt(3, consulta.getIdConsulta());

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Consulta atualizada com sucesso!");
            } else {
                System.out.println("Consulta não encontrada.");
            }
        }
    }


    public void deletar(int id) throws SQLException {
        String sql = "DELETE FROM Consulta WHERE id_consulta = ?";

        try (Connection conn = FabricaConexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Consulta deletada com sucesso!");
            } else {
                System.out.println("Consulta não encontrada.");
            }
        }
    }
}
