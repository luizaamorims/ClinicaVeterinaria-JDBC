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

O **Sistema de Clínica Veterinária** é uma aplicação desenvolvida para gerenciar as operações de uma clínica veterinária, permitindo o cadastro e controle de:

- 👤 Proprietários de animais
- 🐕 Animais (pets)
- 👨‍⚕️ Veterinários
- 📋 Consultas veterinárias

O sistema foi desenvolvido como projeto acadêmico da disciplina de **Banco de Dados I** da Universidade Católica do Salvador, com foco em:
- Implementação de CRUD completo
- Uso de JDBC puro (sem frameworks)
- Boas práticas de desenvolvimento
- Normalização de banco de dados (3FN)
- Validações e integridade referencial

---

## ✨ Funcionalidades

### 📝 Cadastros (CREATE)
- ✅ Cadastrar proprietários com validação de CPF
- ✅ Cadastrar animais vinculados a proprietários
- ✅ Cadastrar veterinários com CRMV único
- ✅ Cadastrar consultas com validação de relacionamentos

### 📊 Consultas (READ)
- ✅ Listar todos os proprietários
- ✅ Listar animais por proprietário
- ✅ Listar todos os veterinários
- ✅ Gerar relatório completo de consulta com JOIN de 4 tabelas

### ✏️ Atualizações (UPDATE)
- ✅ Atualizar dados de proprietários
- ✅ Atualizar dados de veterinários
- ✅ Atualizar informações de animais
- ✅ Atualizar diagnóstico e valor de consultas

### 🗑️ Exclusões (DELETE)
- ✅ Deletar animais com confirmação
- ✅ Deletar veterinários
- ✅ Deletar proprietários (CASCADE para animais)
- ✅ Deletar consultas
- ✅ Integridade referencial (CASCADE e RESTRICT)

### 🛡️ Validações Implementadas
- ✅ CPF com 11 dígitos numéricos
- ✅ Verificação de duplicidade (CPF, CRMV)
- ✅ Validação de datas e valores
- ✅ Verificação de FK antes de inserir
- ✅ Confirmação antes de deletar
- ✅ Tratamento de erros específicos

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
│      CAMADA DE APRESENTAÇÃO             │
│      ClinicaVeterinaria.java            │  ← Interface do usuário (Console)
└─────────────────────────────────────────┘
                    ↓
┌─────────────────────────────────────────┐
│         CAMADA DE MODELO (Models)       │
│   Proprietario, Animal, Veterinario,    │  ← Classes que representam entidades
│   Consulta                              │
└─────────────────────────────────────────┘
                    ↓
┌─────────────────────────────────────────┐
│    CAMADA DE PERSISTÊNCIA (DAO)         │
│  ProprietarioDAO, AnimalDAO,            │  ← Acesso ao banco de dados
│  VeterinarioDAO, ConsultaDAO            │
└─────────────────────────────────────────┘
                    ↓
┌─────────────────────────────────────────┐
│   GERENCIAMENTO DE CONEXÃO (Config)     │
│      FabricaConexao.java            │  ← Criação de conexões
└─────────────────────────────────────────┘
                    ↓
┌─────────────────────────────────────────┐
│      BANCO DE DADOS PostgreSQL          │
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

#### Opção A - Execução rápida com scripts separados (RECOMENDADO)

```bash
# 1. Criar banco
psql -U postgres -f database/01-criar-banco.sql

# 2. Criar tabelas
psql -U postgres -d clinicaveterinaria -f database/02-criar-tabelas.sql

# 3. Inserir dados de teste (opcional)
psql -U postgres -d clinicaveterinaria -f database/03-dados-teste.sql
```

#### Opção B - Execução manual no pgAdmin

1. Abrir pgAdmin
2. Botão direito em "Databases" → "Query Tool"
3. Executar os scripts na ordem (01, 02, 03)

### 3. Configure a ConnectionFactory

Edite o arquivo `Config/ConnectionFactory.java` com suas credenciais:

```java
private static final String URL = "jdbc:postgresql://localhost:5432/clinicaveterinaria";
private static final String USER = "seu_usuario";
private static final String PASSWORD = "sua_senha";
```

### 4. Adicione o Driver JDBC ao projeto

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

### 5. Compile e Execute

```bash
# Via linha de comando (na pasta raiz do projeto)
javac -cp .:lib/postgresql-42.7.8.jar -d bin src/**/*.java
java -cp bin:lib/postgresql-42.7.8.jar ClinicaVeterinaria

# Ou execute pela IDE (botão Run)
```

---

## 💻 Como Usar

### Menu Principal

Ao executar o sistema, você verá o seguinte menu:

```
=== Sistema - Clínica Veterinária ===

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
11 - Atualizar Animal
12 - Atualizar Consulta

EXCLUSÕES:
13 - Deletar Animal
14 - Deletar Veterinário
15 - Deletar Proprietário
16 - Deletar Consulta

0 - Sair
```

### Exemplo de Uso

#### 1. Cadastrar um Proprietário (com validação)

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

**Validações aplicadas:**
- CPF deve ter exatamente 11 dígitos
- CPF não pode estar duplicado
- Nome não pode ser vazio

#### 2. Cadastrar um Animal (com verificação de FK)

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

**Validações aplicadas:**
- Nome não pode ser vazio
- Peso deve ser maior que zero
- Proprietário deve existir no banco
- Data deve estar no formato correto

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
sistema-clinica-veterinaria/
│
├── src/
│   ├── Config/
│   │   └── ConnectionFactory.java   # Gerenciamento de conexões
│   │
│   ├── Models/
│   │   ├── Proprietario.java        # Entidade Proprietário
│   │   ├── Animal.java              # Entidade Animal
│   │   ├── Veterinario.java         # Entidade Veterinário
│   │   └── Consulta.java            # Entidade Consulta
│   │
│   ├── DAO/
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
=== Sistema - Clínica Veterinária ===

===== MENU PRINCIPAL =====
CADASTROS:
1 - Cadastrar Proprietário
2 - Cadastrar Animal
...
```

### Exemplo de Validação

```
=== CADASTRO DE PROPRIETÁRIO ===
CPF (11 dígitos): 123
CPF inválido! Deve conter exatamente 11 dígitos.
```

### Exemplo de Confirmação de Exclusão

```
CPF do proprietário: 12345678901
⚠ Tem certeza? Isso deletará todos os animais dele! (S/N): S
✓ Proprietário deletado com sucesso!
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
- **PreparedStatement**: Previne SQL Injection em todas as queries
- **Validação de entrada**: Tratamento robusto de dados do usuário
- **Verificação de FK**: Garante integridade antes de inserções

### 🧹 Código Limpo
- **Padrão DAO**: Separação clara de responsabilidades
- **Try-with-resources**: Gerenciamento automático de recursos
- **Métodos pequenos e focados**: Facilita manutenção e testes
- **Nomenclatura descritiva**: Código auto-explicativo

### 🗄️ Banco de Dados
- **Normalização 3FN**: Elimina redundância de dados
- **Integridade referencial**: Foreign Keys com CASCADE e RESTRICT
- **Índices**: Otimização de consultas frequentes
- **Transações implícitas**: Consistência dos dados

### 🎯 Validações
- **CPF**: 11 dígitos numéricos e unicidade
- **CRMV**: Unicidade garantida
- **Datas**: Formato e validação de entrada
- **Valores numéricos**: Verificação de ranges válidos
- **FKs**: Existência de registros relacionados

### 💬 UX (User Experience)
- **Confirmações**: Antes de operações destrutivas
- **Mensagens claras**: Erros específicos e informativos
- **Contadores**: Totalizadores em listagens
- **Opção de manter**: Updates parciais permitidos

### 📚 Documentação
- Código comentado adequadamente
- README completo e detalhado

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
**Solução**: 
1. Verifique se o PostgreSQL está rodando
2. Confirme as credenciais no ConnectionFactory
3. Teste a conexão com `psql -U postgres`

### Erro: "Violates foreign key constraint"
**Solução**: 
- Você está tentando deletar um registro que tem dependentes
- Delete os dependentes primeiro ou use CASCADE
- Exemplo: Não pode deletar animal que tem consultas

### Erro: "NumberFormatException"
**Solução**: Digite apenas números nos campos numéricos (CPF, ID, peso, valor)

### Erro: "DateTimeParseException"
**Solução**: Use o formato exato solicitado:
- Data: `AAAA-MM-DD` (ex: 2024-11-20)
- Data/Hora: `AAAA-MM-DDTHH:MM` (ex: 2024-11-20T14:30)

### Erro: "❌ CPF já cadastrado!"
**Solução**: Este CPF já existe no banco. Use outro CPF ou atualize o registro existente.

### Erro: "❌ Proprietário não encontrado!"
**Solução**: Cadastre o proprietário antes de cadastrar o animal.

### Caracteres estranhos no menu (�, Ã, etc)
**Solução**: 
1. Salve o arquivo como UTF-8
2. No IntelliJ: `File` → `Settings` → `Editor` → `File Encodings` → UTF-8
3. Recompile o projeto

---

## 📝 Licença

Este projeto foi desenvolvido para fins **acadêmicos** como parte da disciplina de Banco de Dados I.

---

## 📚 Referências

- [Documentação JDBC](https://docs.oracle.com/javase/8/docs/technotes/guides/jdbc/)
- [PostgreSQL Documentation](https://www.postgresql.org/docs/)
- [Java Documentation](https://docs.oracle.com/en/java/)
- [Padrão DAO](https://www.oracle.com/java/technologies/dataaccessobject.html)

---

<div align="center">

**⭐ Se este projeto te ajudou, deixe uma estrela! ⭐**

[![Made with ❤️](https://img.shields.io/badge/Made%20with-%E2%9D%A4%EF%B8%8F-red.svg)](https://github.com/seu-usuario)
[![UCSAL](https://img.shields.io/badge/UCSAL-2024.2-blue.svg)](https://www.ucsal.br)

---

**Sistema de Clínica Veterinária** | Banco de Dados I | 2025

</div>
