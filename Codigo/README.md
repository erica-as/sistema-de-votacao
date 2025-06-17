# Sistema de Votação Online

Sistema de votação online desenvolvido em Java com Spring Boot, permitindo a criação e gerenciamento de enquetes com múltiplas opções de resposta.

## Funcionalidades

- Criação de enquetes com múltiplas opções
- Registro de votos com validação de voto único por usuário
- Visualização de resultados em tempo real
- Interface de linha de comando (CLI) para interação
- API REST documentada com Swagger
- Banco de dados H2 em memória

## Tecnologias Utilizadas

- Java 24
- Spring Boot 3.5.0
- Spring Data JPA
- Spring Security
- H2 Database
- Swagger/OpenAPI
- Lombok

## Padrões de Projeto

- Singleton: Utilizado para gerenciar instâncias únicas de serviços
- Strategy: Implementado para validação de votos, permitindo diferentes estratégias de validação

## Como Executar

1. Clone o repositório
2. Execute o projeto usando Maven:
   ```bash
   ./mvnw spring-boot:run
   ```
3. Acesse a interface Swagger em: http://localhost:8080/swagger-ui.html
4. Acesse o console H2 em: http://localhost:8080/h2-console

## Endpoints da API

### Enquetes

- `POST /api/enquetes`: Criar nova enquete
- `GET /api/enquetes`: Listar todas as enquetes
- `GET /api/enquetes/{id}`: Buscar enquete por ID
- `POST /api/enquetes/{id}/opcoes`: Adicionar opção de voto

### Votos

- `POST /api/votos`: Registrar voto
- `GET /api/votos/enquete/{enqueteId}`: Listar votos de uma enquete

## CLI

O sistema possui uma interface de linha de comando que pode ser acessada ao executar a aplicação. As opções disponíveis são:

1. Listar Enquetes
2. Criar Enquete
3. Votar
4. Ver Resultados
0. Sair

## Segurança

- Validação de voto único por CPF
- Proteção contra manipulação de dados
- Validação de datas de início e fim das enquetes

## Contribuição

1. Faça o fork do projeto
2. Crie uma branch para sua feature (`git checkout -b feature/nova-feature`)
3. Commit suas mudanças (`git commit -m 'Adiciona nova feature'`)
4. Push para a branch (`git push origin feature/nova-feature`)
5. Abra um Pull Request 