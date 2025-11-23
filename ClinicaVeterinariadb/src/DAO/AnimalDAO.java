package DAO;

import Models.Animal;
import config.FabricaConexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class AnimalDAO {

    public int inserir(Animal animal) throws SQLException {
        String sql = "INSERT INTO Animal (nome, especie, raca, data_nascimento, peso, cpf_proprietario) " +
                "VALUES (?, ?, ?, ?, ?, ?) RETURNING id_animal";

        try (Connection conn = FabricaConexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, animal.getNome());
            stmt.setString(2, animal.getEspecie());
            stmt.setString(3, animal.getRaca());
            stmt.setDate(4, Date.valueOf(animal.getDataNascimento()));
            stmt.setDouble(5, animal.getPeso());
            stmt.setString(6, animal.getCpfProprietario());

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int id = rs.getInt(1);
                System.out.println("Animal inserido com sucesso! ID: " + id);
                return id;
            }
        }
        return -1;
    }


    public Animal buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM Animal WHERE id_animal = ?";

        try (Connection conn = FabricaConexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Animal animal = new Animal();
                animal.setIdAnimal(rs.getInt("id_animal"));
                animal.setNome(rs.getString("nome"));
                animal.setEspecie(rs.getString("especie"));
                animal.setRaca(rs.getString("raca"));
                animal.setDataNascimento(rs.getDate("data_nascimento").toLocalDate());
                animal.setPeso(rs.getDouble("peso"));
                animal.setCpfProprietario(rs.getString("cpf_proprietario"));
                return animal;
            }
        }
        return null;
    }


    public List<Animal> listarPorProprietario(String cpf) throws SQLException {
        List<Animal> lista = new ArrayList<>();
        String sql = "SELECT * FROM Animal WHERE cpf_proprietario = ? ORDER BY nome";

        try (Connection conn = FabricaConexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cpf);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Animal animal = new Animal();
                animal.setIdAnimal(rs.getInt("id_animal"));
                animal.setNome(rs.getString("nome"));
                animal.setEspecie(rs.getString("especie"));
                animal.setRaca(rs.getString("raca"));
                animal.setDataNascimento(rs.getDate("data_nascimento").toLocalDate());
                animal.setPeso(rs.getDouble("peso"));
                animal.setCpfProprietario(rs.getString("cpf_proprietario"));
                lista.add(animal);
            }
        }
        return lista;
    }


    public void atualizar(Animal animal) throws SQLException {
        String sql = "UPDATE Animal SET nome = ?, especie = ?, raca = ?, " +
                "data_nascimento = ?, peso = ? WHERE id_animal = ?";

        try (Connection conn = FabricaConexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, animal.getNome());
            stmt.setString(2, animal.getEspecie());
            stmt.setString(3, animal.getRaca());
            stmt.setDate(4, Date.valueOf(animal.getDataNascimento()));
            stmt.setDouble(5, animal.getPeso());
            stmt.setInt(6, animal.getIdAnimal());

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Animal atualizado com sucesso!");
            } else {
                System.out.println("Animal não encontrado.");
            }
        }
    }


    public void deletar(int id) throws SQLException {
        String sql = "DELETE FROM Animal WHERE id_animal = ?";

        try (Connection conn = FabricaConexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Animal deletado com sucesso!");
            } else {
                System.out.println("Animal não encontrado.");
            }
        }
    }
}
