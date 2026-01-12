# API Rest - Lista de Tarefas

O repositório contém uma API Rest para o gerenciamento de tarefas (CRUD). Aproveite para praticar meus conhecimentos em 
Docker e GitHub Actions e configurei um pipeline de CI com o GitHub Actions para fazer o build da imagem e enviá-la ao Docker
Hub.

Você pode executar esse projeto localmente sem precisar fazer o pull da imagem no Docker Hub. Acesse a seção `3.0` do `README`
para seguir o passo a passo.

## 1.0 Tecnologias Utilizadas

- Spring Boot
- Spring Data JPA com Hibernate
- Database H2 - Um banco de dados em memória para não precisar de instalações adicionais.
- Lombok
- Docker
- GitHub Actions

## 2.0 Estrutura

A API consiste em um CRUD para o gerenciamento de tarefas. Podemos nomear, definir prioridade e marcar como concluída ou não a atividade. 
No projeto aproveite para aplicar um conceito que vi no canal do [DevDojo](https://www.youtube.com/channel/UCjF0OccBT05WxsJb2zNkL4g) que é o Handler Global. Com 
o Handler definido globalmente podemos tratar exceções específicas sem a necessidade de definir blocos `try-catch` em todos
os Controllers.


## 3.0 Como executar o projeto

Primeiramente, antes de executar qualquer um dos próximos passos é importante se você possui a JDK instalada na sua máquina.
Com a JDK (Java Development Kit) você irá conseguir compilar e executar o código.

1. Clone o repositório:
   
   ```bash
      git clone https://github.com/paulo-fabiano/java-lista_tarefas.git
   ```
   
2. Acesse o diretório do projeto:
   
   ```bash
      cd java-lista_tarefas
   ```
3. Compile e execute o projeto usando Maven:
   
   ```bash
      mvn spring-boot:run
   ```

## 4.0 Endpoints da API

| Método | Endpoint                     | Descrição               |
|--------|------------------------------|-------------------------|
| `POST` | `/api/tarefa`                | Criar uma nova tarefa   |
| `GET`  | `/api/tarefas`               | Buscar todas as tarefas |
| `GET`  | `/api/tarefa/{id}`           | Buscar tarefa por ID    |
| `PUT`  | `/api/tarefa/atualizar/{id}` | Atualizar uma tarefa    |
| `DELETE` | `/api/tarefa/deletar/{id}`   | Deletar uma tarefa      |

## 5.0 Testando a API

1. Criando uma tarefa:

```bash
    curl http://localhost:8081/api/tarefa -X POST \
    -H "Content-type: application/json" \
    -d '{ 
        "nome" : "Criar API.", 
        "prioridade" : 10, 
        "realizado" : true 
        }'
```

2. Listando todas as tarefas:

```bash
    curl http://localhost:8081/api/tarefas
```

3. Listando uma tarefa pelo ID. Lembre-se de inserir o ID como parâmetro na URL.

```bash
    curl http://localhost:8081/api/tarefa/1
```

4. Atualizando uma tarefa:

```bash
    curl http://localhost:8081/api/tarefa/atualizar/1 -X PUT \
    -H "Content-type:application/json" \
    -d '{"prioridade" : "5"}'
```

5. Deletar uma tarefa:

```bash
    curl http://localhost:8081/api/tarefa/deletar/1 -X DELETE
```

## 6.0 Tratamento de erros com o GlobalExceptionHandler

Quando a API lançar uma exceção do tipo `TarefaNaoEncontradaException` será retornada uma resposta personalizada:

Você verá que a saída é:

```
    {
        "code":"CODE 1001",
        "erro_message":"Tarefa não encontrada.",
        "status_code":404,
        "localDateTime":"2025-03-05T21:50:05.556628388"
    }
```
Os métodos que lançam essa exceção são os de:

- Buscar uma tarefa pelo ID.
- Atualizar uma tarefa.
- Excluir uma tarefa.

  | Método | Endpoint                     | Descrição               |
  |--------|------------------------------|-------------------------|
  | `GET`  | `/api/tarefa/{id}`           | Buscar tarefa por ID    |
  | `PUT`  | `/api/tarefa/atualizar/{id}` | Atualizar uma tarefa    |
  | `DELETE` | `/api/tarefa/deletar/{id}`   | Deletar uma tarefa      |

Faça o teste com o comando `curl` e verifique a implantação do Global Handler.

## 7.0 Conclusão

Como dito no início, configurei um Pipeline de CI com o GitHub Actions para fazer o build e o push da imagem para o 
Docker Hub.

## 8.0 Contatos

Entre em contato comigo via:

- [Linkedin](https://www.linkedin.com/in/paulo-fabiano)
- [Email](mailto:pfabianof@gmail.com)
