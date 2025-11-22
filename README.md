# 🐾 Sistema de Gestão para Clínica Veterinária 

> Sistema completo de gerenciamento de clínica veterinária desenvolvido em Java com JDBC e PostgreSQL

[![Java](https://img.shields.io/badge/Java-8+-orange.svg)](https://www.oracle.com/java/)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-12+-blue.svg)](https://www.postgresql.org/)
[![JDBC](https://img.shields.io/badge/JDBC-Puro-green.svg)](https://docs.oracle.com/javase/8/docs/technotes/guides/jdbc/)
[![License](https://img.shields.io/badge/License-Academic-yellow.svg)]()

---

## 📋 Sumário

- [Sobre o Projeto](#sobre-o-projeto)
- [Funcionalidades](#funcionalidades)
- [Tecnologias Utilizadas](#tecnologias-utilizadas)
- [Arquitetura](#arquitetura)
- [Modelo de Dados](#modelo-de-dados)
- [Pré-requisitos](#pré-requisitos)
- [Instalação](#instalação)
- [Como Usar](#como-usar)
- [Estrutura do Projeto](#estrutura-do-projeto)
- [Demonstração](#demonstração)
- [Boas Práticas Implementadas](#boas-práticas-implementadas)

---

## 🎯 Sobre o Projeto

O **Sistema** é uma aplicação desenvolvida para gerenciar as operações de uma clínica veterinária, permitindo o cadastro e controle de:

- 👤 Proprietários de animais
- 🐕 Animais (pets)
- 👨‍⚕️ Veterinários
- 📋 Consultas veterinárias

O sistema foi desenvolvido como projeto acadêmico da disciplina de **Banco de Dados I** da Universidade Católica do Salvador, com foco em:
- Implementação de CRUD completo
- Uso de JDBC puro (sem frameworks)
- Boas práticas de desenvolvimento
- Normalização de banco de dados

---

## ✨ Funcionalidades

### 📝 Cadastros (CREATE)
- ✅ Cadastrar proprietários
- ✅ Cadastrar animais
- ✅ Cadastrar veterinários
- ✅ Cadastrar consultas

### 📊 Consultas (READ)
- ✅ Listar todos os proprietários
- ✅ Listar animais por proprietário
- ✅ Listar todos os veterinários
- ✅ Gerar relatório completo de consulta (com JOIN)

### ✏️ Atualizações (UPDATE)
- ✅ Atualizar dados de proprietários
- ✅ Atualizar dados de veterinários

### 🗑️ Exclusões (DELETE)
- ✅ Deletar animais
- ✅ Deletar veterinários
- ✅ Integridade referencial (CASCADE e RESTRICT)

---

## 🛠️ Tecnologias Utilizadas

### Backend
- **Java 8+** - Linguagem de programação
- **JDBC** - API de conexão com banco de dados
- **PostgreSQL Driver** - Driver JDBC para PostgreSQL

### Banco de Dados
- **PostgreSQL 12+** - Sistema gerenciador de banco de dados

### Ferramentas de Desenvolvimento
- **IntelliJ IDEA / Eclipse / NetBeans** - IDE
- **DBeaver / pgAdmin** - Gerenciamento do banco (opcional)
- **Git** - Controle de versão

---

## 🏗️ Arquitetura

O sistema segue o padrão **DAO (Data Access Object)** com separação de responsabilidades:

```
┌─────────────────────────────────────────┐
│           CAMADA DE APRESENTAÇÃO        │
│         ClinicaVeterinaria.java             │  ← Interface do usuário (Console)
└─────────────────────────────────────────┘
                    ↓
┌─────────────────────────────────────────┐
│           CAMADA DE MODELO              │
│   Proprietario.java, Animal.java, etc. │  ← Classes que representam entidades
└─────────────────────────────────────────┘
                    ↓
┌─────────────────────────────────────────┐
│           CAMADA DE PERSISTÊNCIA        │
│  ProprietarioDAO, AnimalDAO, etc.       │  ← Acesso ao banco de dados
└─────────────────────────────────────────┘
                    ↓
┌─────────────────────────────────────────┐
│         GERENCIAMENTO DE CONEXÃO        │
│         FabricaConexao.java          │  ← Criação de conexões
└─────────────────────────────────────────┘
                    ↓
┌─────────────────────────────────────────┐
│         BANCO DE DADOS PostgreSQL       │
└─────────────────────────────────────────┘
```

---

## 🗄️ Modelo de Dados

### Diagrama Entidade-Relacionamento (DER)

```
Proprietario (1,n) ─────┐
                        │ possui
                        ↓
                    Animal (1,1) ─────┐
                                      │ tem
                                      ↓
                                  Consulta (n,1)
                                      ↑
                                      │ realiza
Veterinario (1,1) ────────────────────┘
```

### Tabelas

#### 📋 Proprietario
| Campo    | Tipo         | Descrição           |
|----------|--------------|---------------------|
| cpf      | VARCHAR(11)  | PK - CPF            |
| nome     | VARCHAR(100) | Nome completo       |
| telefone | VARCHAR(15)  | Telefone de contato |
| endereco | VARCHAR(200) | Endereço completo   |
| email    | VARCHAR(100) | E-mail              |

#### 🐕 Animal
| Campo            | Tipo         | Descrição                    |
|------------------|--------------|------------------------------|
| id_animal        | SERIAL       | PK - ID auto-incremento      |
| nome             | VARCHAR(100) | Nome do animal               |
| especie          | VARCHAR(50)  | Espécie (Cachorro, Gato...)  |
| raca             | VARCHAR(50)  | Raça                         |
| data_nascimento  | DATE         | Data de nascimento           |
| peso             | DECIMAL(6,2) | Peso em kg                   |
| cpf_proprietario | VARCHAR(11)  | FK - CPF do proprietário     |

#### 👨‍⚕️ Veterinario
| Campo         | Tipo         | Descrição                |
|---------------|--------------|--------------------------|
| crmv          | VARCHAR(10)  | PK - CRMV                |
| nome          | VARCHAR(100) | Nome completo            |
| especialidade | VARCHAR(100) | Especialidade            |
| telefone      | VARCHAR(15)  | Telefone de contato      |

#### 📋 Consulta
| Campo             | Tipo          | Descrição                   |
|-------------------|---------------|-----------------------------|
| id_consulta       | SERIAL        | PK - ID auto-incremento     |
| data_hora         | TIMESTAMP     | Data e hora da consulta     |
| id_animal         | INTEGER       | FK - ID do animal           |
| crmv_veterinario  | VARCHAR(10)   | FK - CRMV do veterinário    |
| diagnostico       | TEXT          | Diagnóstico da consulta     |
| valor             | DECIMAL(10,2) | Valor da consulta           |

### Normalização

O banco de dados está na **Terceira Forma Normal (3FN)**:
- ✅ **1FN**: Todos os atributos são atômicos
- ✅ **2FN**: Não há dependências parciais
- ✅ **3FN**: Não há dependências transitivas

---

## 📋 Pré-requisitos

Antes de começar, você precisa ter instalado:

- ☕ [Java JDK 8+](https://www.oracle.com/java/technologies/downloads/)
- 🐘 [PostgreSQL 12+](https://www.postgresql.org/download/)
- 📦 [Driver JDBC PostgreSQL](https://jdbc.postgresql.org/download/) (`postgresql-42.7.8.jar`)
- 💻 Uma IDE Java (IntelliJ IDEA, Eclipse ou NetBeans)

---

## 🚀 Instalação

### 1. Clone o repositório

```bash
git clone https://github.com/seu-usuario/sistema-petcare.git
cd sistema-petcare
```

### 2. Configure o Banco de Dados

Execute o script SQL para criar o banco e as tabelas:

```bash
psql -U postgres -f ddl_modelo.sql
```

**Ou** execute manualmente no pgAdmin/DBeaver:

```sql
CREATE DATABASE clinicaveterinaria;
\c clinicaveterinaria;

-- Criar as tabelas (ver arquivo ddl_modelo.sql)
```

### 3. (Opcional) Insira dados de teste

```bash
psql -U postgres -d clinicaveterinaria -f dml_dados_teste.sql
```

### 4. Configure a ConnectionFactory

Edite o arquivo `ConnectionFactory.java` com suas credenciais:

```java
private static final String URL = "jdbc:postgresql://localhost:5432/clinicaveterinaria";
private static final String USER = "seu_usuario";
private static final String PASSWORD = "sua_senha";
```

### 5. Adicione o Driver JDBC ao projeto

#### No IntelliJ IDEA:
1. `File` → `Project Structure` → `Modules`
2. Aba `Dependencies`
3. Clique no `+` → `JARs or directories`
4. Selecione o arquivo `postgresql-42.7.8.jar`
5. `Apply` → `OK`

#### No Eclipse:
1. Botão direito no projeto → `Build Path` → `Configure Build Path`
2. Aba `Libraries` → `Add External JARs`
3. Selecione o arquivo `postgresql-42.7.8.jar`
4. `Apply and Close`

### 6. Compile e Execute

```bash
# Via linha de comando
javac -cp .:postgresql-42.7.8.jar *.java
java -cp .:postgresql-42.7.8.jar SistemaPetCare

# Ou execute pela IDE
```

---

## 💻 Como Usar

### Menu Principal

Ao executar o sistema, você verá o seguinte menu:

```
===== MENU PRINCIPAL =====
CADASTROS:
1 - Cadastrar Proprietário
2 - Cadastrar Animal
3 - Cadastrar Veterinário
4 - Cadastrar Consulta

CONSULTAS:
5 - Listar Proprietários
6 - Listar Animais por Proprietário
7 - Listar Veterinários
8 - Gerar Relatório de Consulta

ATUALIZAÇÕES:
9 - Atualizar Proprietário
10 - Atualizar Veterinário

EXCLUSÕES:
11 - Deletar Animal
12 - Deletar Veterinário

0 - Sair
```

### Exemplo de Uso

#### 1. Cadastrar um Proprietário

```
Escolha uma opção: 1

=== CADASTRO DE PROPRIETÁRIO ===
CPF (11 dígitos): 12345678901
Nome: João Silva
Telefone: 71987654321
Endereço: Rua das Flores, 123, Salvador-BA
Email: joao.silva@email.com

✓ Proprietário inserido com sucesso!
```

#### 2. Cadastrar um Animal

```
Escolha uma opção: 2

=== CADASTRO DE ANIMAL ===
Nome do animal: Rex
Espécie: Cachorro
Raça: Labrador
Data de nascimento (AAAA-MM-DD): 2020-05-15
Peso (kg): 28.5
CPF do proprietário: 12345678901

✓ Animal inserido com sucesso! ID: 1
```

#### 3. Gerar Relatório de Consulta

```
Escolha uma opção: 8

ID da consulta: 1

========== RELATÓRIO DA CONSULTA ==========
ID Consulta: 1
Data/Hora: 2024-11-15 10:00:00

Animal: Rex
Espécie: Cachorro | Raça: Labrador

Proprietário: João Silva
Telefone: 71987654321

Veterinário: Dr. Carlos Mendes
Especialidade: Clínica Geral

Diagnóstico: Consulta de rotina - Animal saudável
Valor: R$ 150.00
==========================================
```

---

## 📁 Estrutura do Projeto

```
sistema-petcare/
│
├── src/
│   ├── config/
│   │   └── ConnectionFactory.java   # Gerenciamento de conexões
│   │
│   ├── models/
│   │   ├── Proprietario.java        # Entidade Proprietário
│   │   ├── Animal.java              # Entidade Animal
│   │   ├── Veterinario.java         # Entidade Veterinário
│   │   └── Consulta.java            # Entidade Consulta
│   │
│   ├── dao/
│   │   ├── ProprietarioDAO.java     # CRUD Proprietário
│   │   ├── AnimalDAO.java           # CRUD Animal
│   │   ├── VeterinarioDAO.java      # CRUD Veterinário
│   │   └── ConsultaDAO.java         # CRUD Consulta
│   │
│   └── ClinicaVeterinaria.java      # Classe principal (Main)
│
│
├── lib/
│   └── postgresql-42.7.8.jar        # Driver JDBC PostgreSQL
│
│
└── README.md                        # Este arquivo
```

---

## 📸 Demonstração

### Tela do Menu
```
=== SISTEMA DE GESTÃO - CLÍNICA VETERINÁRIA PETCARE ===

===== MENU PRINCIPAL =====
CADASTROS:
1 - Cadastrar Proprietário
2 - Cadastrar Animal
...
```

### Verificação no Banco de Dados

```sql
-- Consultar proprietários e seus animais
SELECT 
    p.nome AS proprietario,
    a.nome AS animal,
    a.especie,
    a.raca
FROM Proprietario p
JOIN Animal a ON p.cpf = a.cpf_proprietario
ORDER BY p.nome;

-- Resultado:
 proprietario  | animal | especie  |     raca      
---------------+--------+----------+---------------
 João Silva    | Rex    | Cachorro | Labrador
 João Silva    | Mia    | Gato     | Siamês
```

---

## ✅ Boas Práticas Implementadas

### 🔒 Segurança
- **PreparedStatement**: Previne SQL Injection
- **Validação de entrada**: Tratamento de exceções

### 🧹 Código Limpo
- **Padrão DAO**: Separação de responsabilidades
- **Try-with-resources**: Gerenciamento automático de recursos
- **Métodos pequenos e focados**: Facilita manutenção

### 🗄️ Banco de Dados
- **Normalização 3FN**: Elimina redundância
- **Integridade referencial**: Foreign Keys com CASCADE e RESTRICT
- **Índices**: Otimização de consultas

### 📚 Documentação
- Código comentado
- Nomenclatura clara e descritiva
- README completo

---

## 🎓 Conceitos Aplicados

### JDBC
- ✅ DriverManager e Connection
- ✅ PreparedStatement
- ✅ ResultSet
- ✅ Transações (implícitas)

### Java
- ✅ POO (Classes, Objetos, Encapsulamento)
- ✅ Collections (List, ArrayList)
- ✅ Exception Handling
- ✅ Try-with-resources

### SQL
- ✅ DDL (CREATE, ALTER, DROP)
- ✅ DML (INSERT, UPDATE, DELETE, SELECT)
- ✅ Constraints (PK, FK, NOT NULL)
- ✅ JOIN (INNER JOIN)

### Banco de Dados
- ✅ Modelagem ER
- ✅ Normalização
- ✅ Cardinalidade
- ✅ Integridade Referencial

---

## 🐛 Possíveis Problemas e Soluções

### Erro: "ClassNotFoundException: org.postgresql.Driver"
**Solução**: Certifique-se de que o driver JDBC está no classpath do projeto.

### Erro: "Connection refused"
**Solução**: Verifique se o PostgreSQL está rodando e se as credenciais estão corretas.

### Erro: "Violates foreign key constraint"
**Solução**: Você está tentando deletar um registro que tem dependentes. Delete os dependentes primeiro ou use CASCADE.

### Erro: "NumberFormatException"
**Solução**: Você digitou texto onde era esperado um número. Digite apenas números nos campos numéricos.

---

## 🚀 Melhorias Futuras

- [ ] Interface gráfica (JavaFX ou Swing)
- [ ] Sistema de autenticação
- [ ] Relatórios em PDF
- [ ] Agendamento de consultas
- [ ] Controle de estoque de medicamentos
- [ ] Sistema de notificações (vacinas, retornos)
- [ ] Histórico completo do animal
- [ ] Exportação de dados (CSV, Excel)

---

## 📝 Licença

Este projeto foi desenvolvido para fins **acadêmicos** como parte da disciplina de Banco de Dados I.

---


## 🙏 Agradecimentos

- Professor **Fernando Borges** pela orientação
- Universidade Católica do Salvador
- Comunidade PostgreSQL
- Documentação oficial do Java

---

## 📚 Referências

- [Documentação JDBC](https://docs.oracle.com/javase/8/docs/technotes/guides/jdbc/)
- [PostgreSQL Documentation](https://www.postgresql.org/docs/)
- [Java Documentation](https://docs.oracle.com/en/java/)
- [Padrão DAO](https://www.oracle.com/java/technologies/dataaccessobject.html)

---

<div align="center">

**⭐ Se este projeto te ajudou, deixe uma estrela! ⭐**

Feito com ❤️ e ☕ por Luiza

</div>
