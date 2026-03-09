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
- JDK 1.8 instalado (Obrigatório para compatibilidade com Lombok/Maven neste projeto)
- Node.js (v18 ou superior) e npm instalados
- Maven instalado e configurado no PATH

### 2. Executando o Backend
1. Navegue até a pasta `backend`:
   ```bash
   cd backend
   ```
2. Certifique-se de que seu terminal está utilizando o **Java 8**.
3. Execute a aplicação:
   ```bash
   mvn spring-boot:run
   ```
4. O servidor estará disponível em: `http://localhost:8080`
5. O console do banco de dados H2 pode ser acessado em: `http://localhost:8080/h2-console`
   - **JDBC URL:** `jdbc:h2:mem:duxusdb`
   - **User:** `sa` | **Password:** (vazio)

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
mvn test
```

### Fluxo de Teste Manual (Frontend)

#### 1. Cadastro de Integrantes (Massa de Dados Exemplo)
Cadastre os seguintes integrantes na tela **Integrantes**:
- `Bangalore` (Apex Legends / Ofensivo)
- `Bloodhound` (Apex Legends / Reconhecimento)
- `Tracer` (Overwatch / Dano)
- `Mercy` (Overwatch / Suporte)
- `Gibraltar` (Apex Legends / Defensivo)

#### 2. Montagem de Times
Na aba **Montar Time**, crie pelo menos 3 times em datas diferentes (ex: 01/01/2024, 15/01/2024 e 01/02/2024) selecionando múltiplos integrantes para cada data.

#### 3. Dashboard de Consultas
Na aba **Dashboard**, utilize os filtros de data para visualizar as estatísticas processadas em tempo real pelo backend.

---

## Considerações Técnicas
- **Processamento em Memória**: Conforme solicitado, toda a lógica de agregação e contagem de dados foi implementada utilizando **Java 8 Streams API**, garantindo que o banco de dados seja utilizado apenas para a recuperação bruta dos dados (`findAll`).
- **Injeção de Dependência**: Foi utilizada a injeção via construtor em todos os componentes Spring, seguindo as melhores práticas de testabilidade.
- **CORS**: O backend está configurado para aceitar requisições da origem `http://localhost:4200`.
- **Lombok**: Caso ocorram erros de compilação via terminal, certifique-se de que o Maven está utilizando o JDK 8, pois versões superiores podem exigir configurações adicionais de processamento de anotações.
