package DAO;

import Models.Veterinario;
import config.FabricaConexao;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VeterinarioDAO {

    public void inserir(Veterinario vet) throws SQLException {
        String sql = "INSERT INTO Veterinario (crmv, nome, especialidade, telefone) VALUES (?, ?, ?, ?)";

        try (Connection conn = FabricaConexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, vet.getCrmv());
            stmt.setString(2, vet.getNome());
            stmt.setString(3, vet.getEspecialidade());
            stmt.setString(4, vet.getTelefone());

            stmt.executeUpdate();
            System.out.println("Veterinário inserido com sucesso!");
        }
    }


    public Veterinario buscarPorCrmv(String crmv) throws SQLException {
        String sql = "SELECT * FROM Veterinario WHERE crmv = ?";

        try (Connection conn = FabricaConexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, crmv);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Veterinario vet = new Veterinario();
                vet.setCrmv(rs.getString("crmv"));
                vet.setNome(rs.getString("nome"));
                vet.setEspecialidade(rs.getString("especialidade"));
                vet.setTelefone(rs.getString("telefone"));
                return vet;
            }
        }
        return null;
    }


    public List<Veterinario> listarTodos() throws SQLException {
        List<Veterinario> lista = new ArrayList<>();
        String sql = "SELECT * FROM Veterinario ORDER BY nome";

        try (Connection conn = FabricaConexao.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Veterinario vet = new Veterinario();
                vet.setCrmv(rs.getString("crmv"));
                vet.setNome(rs.getString("nome"));
                vet.setEspecialidade(rs.getString("especialidade"));
                vet.setTelefone(rs.getString("telefone"));
                lista.add(vet);
            }
        }
        return lista;
    }


    public void atualizar(Veterinario vet) throws SQLException {
        String sql = "UPDATE Veterinario SET nome = ?, especialidade = ?, telefone = ? WHERE crmv = ?";

        try (Connection conn = FabricaConexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, vet.getNome());
            stmt.setString(2, vet.getEspecialidade());
            stmt.setString(3, vet.getTelefone());
            stmt.setString(4, vet.getCrmv());

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Veterinário atualizado com sucesso!");
            } else {
                System.out.println("Veterinário não encontrado.");
            }
        }
    }


    public void deletar(String crmv) throws SQLException {
        String sql = "DELETE FROM Veterinario WHERE crmv = ?";

        try (Connection conn = FabricaConexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, crmv);

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Veterinário deletado com sucesso!");
            } else {
                System.out.println("Veterinário não encontrado.");
            }
        }
    }
}
