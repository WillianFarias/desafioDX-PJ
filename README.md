# Desafio de Desenvolvimento - Escalação de Times

Este projeto é uma solução para o desafio técnico de desenvolvimento de um sistema de escalação de times, permitindo o cadastro de integrantes, montagem de times semanais e análise de dados estatísticos.

## Tecnologias Utilizadas

### Backend
- **Java 8** com **Spring Boot 2.5.3**
- **Spring Data JPA** para persistência
- **H2 Database** (Banco de dados em memória para facilidade de teste)
- **Lombok** para redução de boilerplate
- **JUnit 4** para testes unitários
- **Maven** para gerenciamento de dependências

### Frontend
- **Angular 17** (Arquitetura Standalone)
- **Bootstrap 5** para estilização e responsividade
- **Reactive Forms** para validações de formulário
- **HttpClient** para integração com a API REST

---

## Como Executar o Projeto

### 1. Pré-requisitos
- JDK 1.8 instalado
- Node.js (v18 ou superior) e npm instalados
- Maven instalado (ou use o `mvnw` incluso)

### 2. Executando o Backend
1. Navegue até a pasta `backend`:
   ```bash
   cd backend
   ```
2. Execute a aplicação:
   ```bash
   ./mvnw spring-boot:run
   ```
3. O servidor estará disponível em: `http://localhost:8080`
4. O console do banco de dados H2 pode ser acessado em: `http://localhost:8080/h2-console` (JDBC URL: `jdbc:h2:mem:duxusdb`)

### 3. Executando o Frontend
1. Navegue até a pasta `frontend`:
   ```bash
   cd frontend
   ```
2. Instale as dependências:
   ```bash
   npm install
   ```
3. Inicie o servidor de desenvolvimento:
   ```bash
   ng serve
   ```
4. Acesse a aplicação em: `http://localhost:4200`

---

## Como Testar a Implementação

### Testes Unitários (Backend)
Para validar as regras de negócio e o processamento de dados via Java Streams, execute:
```bash
cd backend
./mvnw test
```

### Fluxo de Teste Manual (Frontend)

#### 1. Cadastro de Integrantes (Massa de Dados Exemplo)
Cadastre pelo menos os seguintes integrantes na tela **Integrantes**:

1. Nome: `Bangalore`, Franquia: `Apex Legends`, Função: `Ofensivo`
2. Nome: `Bloodhound`, Franquia: `Apex Legends`, Função: `Reconhecimento`
3. Nome: `Tracer`, Franquia: `Overwatch`, Função: `Dano`
4. Nome: `Mercy`, Franquia: `Overwatch`, Função: `Suporte`
5. Nome: `Gibraltar`, Franquia: `Apex Legends`, Função: `Defensivo`

Após salvar, verifique se cada integrante aparece na "Lista de Integrantes".

#### 2. Montagem de Times
Na aba **Montar Time**, utilize a massa de dados cadastral para criar pelo menos 3 times:

- **Time A** 
  Data: `2024-01-01` 
  Integrantes: `Bangalore`, `Bloodhound`

- **Time B** 
  Data: `2024-01-15` 
  Integrantes: `Bangalore`, `Tracer`

- **Time C** 
  Data: `2024-02-01` 
  Integrantes: `Tracer`, `Mercy`

Após salvar, verifique se os cards de times aparecem na parte inferior da tela com a data e a lista de integrantes correta.

#### 3. Dashboard de Consultas
Na aba **Dashboard**:

- Sem preencher datas (período completo), clique em **Filtrar Resultados** e observe:
  - **Integrante Mais Usado**: deverá ser `Bangalore` ou `Tracer` (ambos aparecem em 2 times).
  - **Função Mais Comum**: refletirá a função com maior número de aparições nas composições.
  - **Contagem por Franquia**: deverá contabilizar quantas vezes cada franquia aparece nos times.

- Informe **Data Inicial** `2024-01-01` e **Data Final** `2024-01-31` e clique em **Filtrar Resultados**:
  - Somente os times A e B (de janeiro) serão considerados.
  - O **Integrante Mais Usado** deverá ser `Bangalore` (presente nos dois times de janeiro).

- Informe um período sem times (por exemplo, ano de 2023) e clique em **Filtrar Resultados**:
  - Os cards deverão indicar ausência de dados ("Nenhum" / "Nenhuma") e as contagens ficarão vazias.

---

## Considerações Técnicas
- **Processamento em Memória**: Conforme solicitado, toda a lógica de agregação e contagem de dados foi implementada utilizando **Java 8 Streams API**, garantindo que o banco de dados seja utilizado apenas para a recuperação bruta dos dados (`findAll`).
- **Injeção de Dependência**: Foi utilizada a injeção via construtor em todos os componentes Spring, seguindo as melhores práticas de testabilidade e clareza de dependências.
- **CORS**: O backend está configurado para aceitar requisições da origem `http://localhost:4200`, permitindo a integração com o frontend Angular.
