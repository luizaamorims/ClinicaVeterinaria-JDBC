import DAO.AnimalDAO;
import DAO.ConsultaDAO;
import DAO.ProprietarioDAO;
import DAO.VeterinarioDAO;
import Models.Animal;
import Models.Consulta;
import Models.Proprietario;
import Models.Veterinario;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class ClinicaVeterinaria {

    private static Scanner scanner = new Scanner(System.in);
    private static ProprietarioDAO propDAO = new ProprietarioDAO();
    private static AnimalDAO animalDAO = new AnimalDAO();
    private static VeterinarioDAO vetDAO = new VeterinarioDAO();
    private static ConsultaDAO consultaDAO = new ConsultaDAO();

    public static void main(String[] args) {
        System.out.println("Sistema - Clinica Veterinária");

        int opcao = -1;

        while (opcao != 0) {
            exibirMenu();

            try {
                opcao = Integer.parseInt(scanner.nextLine());

                switch (opcao) {
                    case 1:
                        cadastrarProprietario();
                        break;
                    case 2:
                        cadastrarAnimal();
                        break;
                    case 3:
                        cadastrarVeterinario();
                        break;
                    case 4:
                        cadastrarConsulta();
                        break;
                    case 5:
                        listarProprietarios();
                        break;
                    case 6:
                        listarAnimaisPorProprietario();
                        break;
                    case 7:
                        listarVeterinarios();
                        break;
                    case 8:
                        gerarRelatorioConsulta();
                        break;
                    case 9:
                        atualizarProprietario();
                        break;
                    case 10:
                        atualizarVeterinario();
                        break;
                    case 11:
                        atualizarAnimal();
                        break;
                    case 12:
                        atualizarConsulta();
                        break;
                    case 13:
                        deletarAnimal();
                        break;
                    case 14:
                        deletarVeterinario();
                        break;
                    case 15:
                        deletarProprietario();
                        break;
                    case 16:
                        deletarConsulta();
                        break;
                    case 0:
                        System.out.println("Encerrando sistema...");
                        break;
                    default:
                        System.out.println("Opção inválida!");
                }

            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }

        scanner.close();
    }

    private static void exibirMenu() {
        System.out.println("\n---- MENU PRINCIPAL ----");
        System.out.println("CADASTROS:");
        System.out.println("1 - Cadastrar Proprietário");
        System.out.println("2 - Cadastrar Animal");
        System.out.println("3 - Cadastrar Veterinário");
        System.out.println("4 - Cadastrar Consulta");
        System.out.println("\nCONSULTAS:");
        System.out.println("5 - Listar Proprietários");
        System.out.println("6 - Listar Animais por Proprietário");
        System.out.println("7 - Listar Veterinários");
        System.out.println("8 - Gerar Relatório de Consulta");
        System.out.println("\nATUALIZAÇÕES:");
        System.out.println("9 - Atualizar Proprietário");
        System.out.println("10 - Atualizar Veterinário");
        System.out.println("11 - Atualizar Animal");
        System.out.println("12 - Atualizar Consulta");
        System.out.println("\nEXCLUSÕES:");
        System.out.println("13 - Deletar Animal");
        System.out.println("14 - Deletar Veterinário");
        System.out.println("15 - Deletar Proprietario");
        System.out.println("16 - Deletar Consulta");
        System.out.println("\n0 - Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static void cadastrarProprietario() throws Exception {
        System.out.println("\n--- CADASTRO DE PROPRIETÁRIO ---");
        System.out.print("CPF (11 dígitos): ");
        String cpf = scanner.nextLine();
        if (cpf.length() != 11) {
            System.out.println("CPF inválido! Deve conter 11 dígitos.");
            return;
        }
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Telefone: (15 digitos)");
        String telefone = scanner.nextLine();
        System.out.print("Endereço: ");
        String endereco = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();

        Proprietario prop = new Proprietario(cpf, nome, telefone, endereco, email);
        propDAO.inserir(prop);
    }

    private static void cadastrarAnimal() throws Exception {
        System.out.println("\n--- CADASTRO DE ANIMAL ---");
        System.out.print("Nome do animal: ");
        String nome = scanner.nextLine();
        System.out.print("Espécie: ");
        String especie = scanner.nextLine();
        System.out.print("Raça: ");
        String raca = scanner.nextLine();
        System.out.print("Data de nascimento (AAAA-MM-DD): ");
        LocalDate dataNasc = LocalDate.parse(scanner.nextLine());
        System.out.print("Peso (kg): ");
        double peso = Double.parseDouble(scanner.nextLine());
        System.out.print("CPF do proprietário: ");
        String cpfProp = scanner.nextLine();

        Proprietario prop = propDAO.buscarPorCpf(cpfProp);
        if (prop == null) {
            System.out.println("Proprietario não cadastrado!");
            return;
        }

        Animal animal = new Animal(nome, especie, raca, dataNasc, peso, cpfProp);
        animalDAO.inserir(animal);
    }

    private static void cadastrarVeterinario() throws Exception {
        System.out.println("\n--- CADASTRO DE VETERINÁRIO ---");
        System.out.print("CRMV: (10 digitos) ");
        String crmv = scanner.nextLine();
        if (crmv.length() != 10) {
            System.out.println("CRMV inválido! Deve conter 10 dígitos.");
            return;
        }
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Especialidade: ");
        String especialidade = scanner.nextLine();
        System.out.print("Telefone: (15 digitos)");
        String telefone = scanner.nextLine();

        Veterinario vet = new Veterinario(crmv, nome, especialidade, telefone);
        vetDAO.inserir(vet);
    }

    private static void cadastrarConsulta() throws Exception {
        System.out.println("\n--- CADASTRO DE CONSULTA ---");
        System.out.print("ID do animal: ");
        int idAnimal = Integer.parseInt(scanner.nextLine());
        System.out.print("CRMV do veterinário: ");
        String crmv = scanner.nextLine();
        System.out.print("Data e hora (AAAA-MM-DDTHH:MM): ");
        LocalDateTime dataHora = LocalDateTime.parse(scanner.nextLine());
        System.out.print("Diagnóstico: ");
        String diagnostico = scanner.nextLine();
        System.out.print("Valor (R$): ");
        double valor = Double.parseDouble(scanner.nextLine());

        Consulta consulta = new Consulta(dataHora, idAnimal, crmv, diagnostico, valor);
        consultaDAO.inserir(consulta);
    }

    private static void listarProprietarios() throws Exception {
        System.out.println("\n=== LISTA DE PROPRIETÁRIOS ===");
        List<Proprietario> lista = propDAO.listarTodos();
        if (lista.isEmpty()) {
            System.out.println("Nenhum proprietário cadastrado.");
        } else {
            for (Proprietario p : lista) {
                System.out.println(p);
            }
        }
    }

    private static void listarAnimaisPorProprietario() throws Exception {
        System.out.print("CPF do proprietário: (11 digitos)");
        String cpf = scanner.nextLine();

        System.out.println("\n=== ANIMAIS DO PROPRIETÁRIO ===");
        List<Animal> lista = animalDAO.listarPorProprietario(cpf);
        if (lista.isEmpty()) {
            System.out.println("Nenhum animal cadastrado para este proprietário.");
        } else {
            for (Animal a : lista) {
                System.out.println(a);
            }
        }
    }

    private static void listarVeterinarios() throws Exception {
        System.out.println("\n=== LISTA DE VETERINÁRIOS ===");
        List<Veterinario> lista = vetDAO.listarTodos();
        if (lista.isEmpty()) {
            System.out.println("Nenhum veterinário cadastrado.");
        } else {
            for (Veterinario v : lista) {
                System.out.println(v);
            }
        }
    }

    private static void gerarRelatorioConsulta() throws Exception {
        System.out.print("ID da consulta: ");
        int id = Integer.parseInt(scanner.nextLine());
        consultaDAO.gerarRelatorioConsulta(id);
    }

    private static void atualizarProprietario() throws Exception {
        System.out.print("CPF do proprietário: (11 digitos)");
        String cpf = scanner.nextLine();

        Proprietario prop = propDAO.buscarPorCpf(cpf);
        if (prop == null) {
            System.out.println("Proprietário não encontrado!");
            return;
        }

        System.out.println("Dados atuais: " + prop);
        System.out.print("Novo telefone: (15 digitos)");
        prop.setTelefone(scanner.nextLine());
        System.out.print("Novo endereço: ");
        prop.setEndereco(scanner.nextLine());

        propDAO.atualizar(prop);
    }

    private static void atualizarVeterinario() throws Exception {
        System.out.print("CRMV do veterinário: (10 digitos) ");
        String crmv = scanner.nextLine();

        if (crmv.length() != 10) {
            System.out.println("CRMV inválido! Deve conter 10 dígitos.");
            return;
        }

        Veterinario vet = vetDAO.buscarPorCrmv(crmv);
        if (vet == null) {
            System.out.println("Veterinário não encontrado!");
            return;
        }

        System.out.println("Dados atuais: " + vet);
        System.out.print("Nova especialidade: ");
        vet.setEspecialidade(scanner.nextLine());
        System.out.print("Novo telefone: 15 digitos");
        vet.setTelefone(scanner.nextLine());

        vetDAO.atualizar(vet);
    }

    private static void atualizarAnimal() throws Exception {
        System.out.print("ID do animal: ");
        int id = Integer.parseInt(scanner.nextLine());
        Animal animal = animalDAO.buscarPorId(id);

        if (animal == null) {
            System.out.println("Animal não encontrado!");
            return;
        }

        System.out.println("Dados atuais: " + animal);
        System.out.print("Novo nome (enter para manter): ");
        String novoNome = scanner.nextLine();
        if (!novoNome.isBlank()) animal.setNome(novoNome);

        System.out.print("Nova espécie (enter para manter): ");
        String novaEspecie = scanner.nextLine();
        if (!novaEspecie.isBlank()) animal.setEspecie(novaEspecie);

        System.out.print("Nova raça (enter para manter): ");
        String novaRaca = scanner.nextLine();
        if (!novaRaca.isBlank()) animal.setRaca(novaRaca);

        System.out.print("Nova data de nascimento (AAAA-MM-DD) (enter para manter): ");
        String data = scanner.nextLine();
        if (!data.isBlank()) animal.setDataNascimento(LocalDate.parse(data));

        System.out.print("Novo peso (kg) (enter para manter): ");
        String pesoStr = scanner.nextLine();
        if (!pesoStr.isBlank()) animal.setPeso(Double.parseDouble(pesoStr));

        animalDAO.atualizar(animal);
    }

    private static void atualizarConsulta() throws Exception {
        System.out.print("ID da consulta: ");
        int id = Integer.parseInt(scanner.nextLine());
        Consulta consulta = consultaDAO.buscarPorId(id);
        if (consulta == null) {
            System.out.println("Consulta não encontrada!");
            return;
        }

        System.out.println("Dados atuais: " + consulta);
        System.out.print("Novo diagnóstico (enter para manter): ");
        String novoDiag = scanner.nextLine();
        if (!novoDiag.isBlank()) consulta.setDiagnostico(novoDiag);

        System.out.print("Novo valor (R$) (enter para manter): ");
        String valorStr = scanner.nextLine();
        if (!valorStr.isBlank()) consulta.setValor(Double.parseDouble(valorStr));

        consultaDAO.atualizar(consulta);
    }

    private static void deletarAnimal() throws Exception {
        System.out.print("ID do animal: ");
        int id = Integer.parseInt(scanner.nextLine());
        animalDAO.deletar(id);
    }

    private static void deletarVeterinario() throws Exception {
        System.out.print("CRMV do veterinário: (10 digitos)");
        String crmv = scanner.nextLine();
        if (crmv.length() != 10) {
            System.out.println("CRMV inválido! Deve conter 10 dígitos.");
            return;
        }
        vetDAO.deletar(crmv);
    }

    private static void deletarProprietario() throws Exception {
        System.out.println("CPF do proprietario: (11 digitos) ");
        String cpf = scanner.nextLine();
        if (cpf.length() != 11) {
            System.out.println("CPF inválido! Deve conter 11 dígitos.");
            return;
        }
        propDAO.deletar(cpf);
    }

    private static void deletarConsulta() throws Exception {
        System.out.print("ID da consulta: ");
        int id = Integer.parseInt(scanner.nextLine());
        consultaDAO.deletar(id);
    }
}

