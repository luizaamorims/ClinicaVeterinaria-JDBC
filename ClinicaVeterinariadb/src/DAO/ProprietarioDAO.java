package DAO;

import Models.Proprietario;
import config.FabricaConexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProprietarioDAO {


    public void inserir(Proprietario prop) throws SQLException {
        String sql = "INSERT INTO Proprietario (cpf, nome, telefone, endereco, email) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = FabricaConexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, prop.getCpf());
            stmt.setString(2, prop.getNome());
            stmt.setString(3, prop.getTelefone());
            stmt.setString(4, prop.getEndereco());
            stmt.setString(5, prop.getEmail());

            stmt.executeUpdate();
            System.out.println("Proprietário inserido com sucesso!");
        }
    }


    public Proprietario buscarPorCpf(String cpf) throws SQLException {
        String sql = "SELECT * FROM Proprietario WHERE cpf = ?";

        try (Connection conn = FabricaConexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cpf);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Proprietario prop = new Proprietario();
                prop.setCpf(rs.getString("cpf"));
                prop.setNome(rs.getString("nome"));
                prop.setTelefone(rs.getString("telefone"));
                prop.setEndereco(rs.getString("endereco"));
                prop.setEmail(rs.getString("email"));
                return prop;
            }
        }
        return null;
    }


    public List<Proprietario> listarTodos() throws SQLException {
        List<Proprietario> lista = new ArrayList<>();
        String sql = "SELECT * FROM Proprietario ORDER BY nome";

        try (Connection conn = FabricaConexao.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Proprietario prop = new Proprietario();
                prop.setCpf(rs.getString("cpf"));
                prop.setNome(rs.getString("nome"));
                prop.setTelefone(rs.getString("telefone"));
                prop.setEndereco(rs.getString("endereco"));
                prop.setEmail(rs.getString("email"));
                lista.add(prop);
            }
        }
        return lista;
    }


    public void atualizar(Proprietario prop) throws SQLException {
        String sql = "UPDATE Proprietario SET nome = ?, telefone = ?, endereco = ?, email = ? WHERE cpf = ?";

        try (Connection conn = FabricaConexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, prop.getNome());
            stmt.setString(2, prop.getTelefone());
            stmt.setString(3, prop.getEndereco());
            stmt.setString(4, prop.getEmail());
            stmt.setString(5, prop.getCpf());

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Proprietário atualizado com sucesso!");
            } else {
                System.out.println("Proprietário não encontrado.");
            }
        }
    }


    public void deletar(String cpf) throws SQLException {
        String sql = "DELETE FROM Proprietario WHERE cpf = ?";

        try (Connection conn = FabricaConexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cpf);

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Proprietário deletado com sucesso!");
            } else {
                System.out.println("Proprietário não encontrado.");
            }
        }
    }
}
