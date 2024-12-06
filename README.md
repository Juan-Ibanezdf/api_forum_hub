# API_RET_Forum_Hub
## Descrição

A Forum Hub API é uma aplicação de fórum construída como parte do curso **Praticando Spring Framework: Challenge Fórum Hub** da Alura. Este projeto tem como objetivo consolidar conhecimentos sobre desenvolvimento de aplicações REST com Spring Boot, incluindo autenticação e autorização com Spring Security, persistência de dados com Hibernate, migrações de banco de dados com Flyway e documentação da API com Springdoc OpenAPI. A aplicação permite que os usuários realizem autenticação, criem cursos, postem tópicos e respostas relacionados aos cursos, e visualizem informações sobre cursos, tópicos e respostas de maneira eficiente.
Ela permite que os usuários façam autenticação: Login e Registro, criem cursos, postem tópicos respostas referentes a cada curso e visualizem cusos, tópicos e respostas. A aplicação utiliza as seguintes tecnologias Spring Security para autenticação e autorização, PasswordEncoder para encripitação de senhas, Hibernate para mapeamento objeto-relacional, Flyway para migrações de banco de dados e Springdoc OpenAPI para documentação automatizada da API.

## Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3.3.1**
    - **spring-boot-starter-data-jpa**
    - **spring-boot-starter-security**
    - **spring-boot-starter-validation**
    - **spring-boot-starter-web**
- **Hibernate**
- **PasswordEncoder**
- **Flyway**
- **Springdoc OpenAPI**
- **MySQL**
- **Lombok**

## Configuração do Ambiente
### Pré-requisitos

- Java 17
- SpringBoot 3.3.1
- MySQL
- Maven

## Funcionalidades
### Autenticação e Autorização
A aplicação utiliza JWT (JSON Web Token) para autenticação e autorização. O token é gerado durante o login e deve ser enviado em todas as requisições subsequentes no cabeçalho Authorization com o prefixo Bearer.

## Roles
ADMIN: Tem permissão para criar, atualizar e excluir cursos, tópicos e respostas.
USER: Tem permissão para criar e visualizar tópicos e respostas.

## Endpoints da API
### CursoController
- POST /cursos: Cadastrar um novo curso.
- GET /cursos: Listar todos os cursos.
- GET /cursos/{idCurso}: Detalhar um curso específico.
- PUT /cursos/{idCurso}: Atualizar um curso específico.
- DELETE /cursos/{idCurso}: Excluir um curso específico.
 
### TopicoController
- POST /cursos/{idCurso}/topicos: Cadastrar um novo tópico em um curso.
- GET /cursos/{idCurso}/topicos: Listar todos os tópicos de um curso.
- GET /cursos/{idCurso}/topicos/{idTopico}: Detalhar um tópico específico.
- PUT /cursos/{idCurso}/topicos/{idTopico}: Atualizar um tópico específico.
- DELETE /cursos/{idCurso}/topicos/{idTopico}: Excluir um tópico específico.

### RespostaController
- POST /cursos/{idCurso}/topicos/{idTopico}/respostas: Cadastrar uma nova resposta em um tópico.
- GET /cursos/{idCurso}/topicos/{idTopico}/respostas: Listar todas as respostas de um tópico.
- GET /cursos/{idCurso}/topicos/{idTopico}/respostas/{idResposta}: Detalhar uma resposta específica.
- PUT /cursos/{idCurso}/topicos/{idTopico}/respostas/{idResposta}: Atualizar uma resposta específica.
- DELETE /cursos/{idCurso}/topicos/{idTopico}/respostas/{idResposta}: Excluir uma resposta específica.

### UsuarioController
- POST /usuarios/registro

## Instruções de Configuração

1. Clone o repositório para o seu ambiente local.
   - git clone https://github.com/juan.ibanezdf/api_forum_hub.git
2. Crie um banco de dados MySQL.   
3. Configure o arquivo `application.properties` com as informações do seu banco de dados.
    - spring.datasource.url=jdbc:mysql://localhost:3306/api_forum_hub
    - spring.datasource.username= [seu nome de usuario]
    - spring.datasource.password= [senha do banco de dados]
4. Crie o banco de dados MySql
    - CREATE DATABASE api_forum_hub;
5. Após a configuração, você pode executar a aplicação executando a classe :
   - ForumApplication.java.      
 
OBS:Não esqueça de criar um usuario na rota localhost:8080/usuarios/registro e efetuar o Login. Cada requisição deve ter o Authorization: Bearer <seu_token_jwt_aqui>.

## Estrutura do Projeto
```
src/
└── main/
├── java/
│   └── br/
│       └── com/
│           └── alura/
│               └── forum/
│               |   ├── controller/
|               |   |   ├── AutenticacaoController
│               |   │   ├── CursoController.java
│               |   │   ├── RespostaController.java
│               |   │   ├── TopicoController.java
│               │   │   └── UsuarioController.java
│               |   ├── domain/
│               |   │   ├── curso/
│               |   │   │   ├── Curso.java
│               |   │   │   ├── CursoRepository.java
│               |   │   │   ├── DadosAtualizacaoCurso.java
│               |   │   │   ├── DadosCadastroCurso.java
│               |   │   │   ├── DadosDetalhamentoCurso.java
│               |   │   │   └── DadosListagemCurso.java
│               |   │   ├── resposta/
│               |   │   │   ├── DadosAtualizacaoResposta.java
│               |   │   │   ├── DadosCadastroResposta.java
│               |   │   │   ├── DadosDetalhamentoResposta.java
│               |   │   │   ├── DadosListagemResposta.java
│               |   │   │   ├── Resposta.java
│               |   │   │   └── RespostaRepository.java
│               |   │   ├── topico/
│               |   │   │   ├── DadosAtualizacaoTopico.java
│               |   │   │   ├── DadosCadastroTopico.java
│               |   │   │   ├── DadosDetalhamentoTopico.java
│               |   │   │   ├── DadosListagemTopico.java
│               |   │   │   ├── StatusTopico.java
│               |   │   │   ├── Topico.java
│               |   │   │   └── TopicoRepository.java
│               |   │   └── usuario/
│               |   │       ├── DadosAutenticacao.java
│               |   │       ├── DadosRegistroUsuario.java
│               |   │       ├── Usuario.java
│               |   │       ├── UsuarioRepository.java
│               |   │       └── AltenticacaoService.java
│               |   └── infra
|               |          ├── exception/
│               |          |   ├── Log.java
|               |              └── TratadorDeErros.java
|               |          ├── security/
│               |          |   ├── SecurityFilter
|               |          |   ├── SecurityConfiguration.java
│               |          |   ├── TokenService.java
│               |          |   └── DadosTokenJWT
|               |          └── springdoc
|               |              └── SpingDocConfigurations   
|               └──ForumApplication
└── resources/
├── application.properties
└── db/
└── migration/
└── V1__create_table_usuarios_and_cursos.sql
    V2__create_table_topicos.sql
    V3__create_table_respostas.sql
    V4__create_table_perfil.sql
    V5__create-table_user_roles.sql

```

## Documentação da API
A documentação da API é gerada automaticamente pelo Springdoc OpenAPI e pode ser acessada em:
#### http://localhost:8080/swagger-ui.html

## Contribuições
Contribuições são bem-vindas! Sinta-se à vontade para abrir issues e pull requests ou E-mail.

## Licença
Este projeto está licenciado sob a Licença MIT. Veja o arquivo [Licença MIT](LICENSE) para mais detalhes .


