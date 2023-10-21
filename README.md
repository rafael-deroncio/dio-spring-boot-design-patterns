# Desafio DIO API Spring Boot - Aplicando Design Patterns

## Visão Geral

Este projeto consiste em uma aplicação Spring Boot que aplica Design Patterns para implementar uma API. A estrutura do projeto é organizada em módulos separados, incluindo camadas API, Core e Domain, com responsabilidades distintas. O Docker Compose é utilizado para configurar um banco de dados PostgreSQL, que já está configurado no arquivo de propriedades.

## Estrutura do Projeto

- **Docker**: O diretório "docker" contém os arquivos do Docker Compose para configurar o banco de dados PostgreSQL.
- **lab-design-patterns-spring**: O diretório "lab-design-patterns-spring" contém o código-fonte da aplicação Spring Boot.

### Módulos do Projeto

#### API

O módulo API é responsável pelas configurações e controladores da API. Ele gerencia as solicitações HTTP e lida com a interação entre a aplicação e os clientes.

#### Core

O módulo Core concentra as regras de negócio da aplicação, incluindo repositórios, configurações e serviços. Aqui, as operações de negócio são implementadas de acordo com os padrões de projeto definidos.

#### Domain

O módulo Domain contém os contratos de consumo do projeto. Define as interfaces e modelos que são compartilhados entre os módulos API e Core.

## Pacotes Utilizados (no arquivo pom.xml)

- `spring-boot-starter-data-jpa`
- `spring-boot-starter-web`
- `postgresql`
- `springdoc-openapi-ui`
- `modelmapper`

## Iniciando o Projeto

Certifique-se de seguir estas etapas para iniciar o projeto:

1. Garanta que o Docker esteja instalado e em execução.

2. Navegue até o diretório "docker" e execute o Docker Compose para configurar o banco de dados PostgreSQL:

   ```shell
   docker-compose up -d
    ````
3. Navegue até o diretório "lab-design-patterns-spring" e inicie a aplicação Spring Boot.

   ```shell
    ./mvnw spring-boot:run
    ````

## Documentação da API

A documentação da API, incluindo os endpoints disponíveis e seus parâmetros, pode ser acessada em http://127.0.0.1:8080/swagger-ui.html após a inicialização da aplicação.

## Dependências
Certifique-se de que as seguintes dependências estejam instaladas:

- Maven
- Java Development Kit (JDK)
- Docker

## Contribuição
Sinta-se à vontade para contribuir para este projeto. Se você encontrar problemas ou tiver sugestões, abra uma issue neste repositório.