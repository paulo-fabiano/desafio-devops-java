# Desafio Técnico - Etapa 1

Esta etapa consiste na criação de um ambiente containerizado para o **Jenkins** utilizando o **Apache Tomcat** como servidor de aplicação, integrando o **Jolokia** para a exposição de métricas via HTTP.

## Construção do Dockerfile

A construção da imagem foi planejada para ser leve e segura, utilizando a estratégia de **imagem otimizada** para execução.

### Escolha da Imagem Base

Optei pela imagem `tomcat:10-jre17`, uma imagem final significativamente menor, acelerando o processo de construção, pull e deploy.

### Gerenciamento de Artefatos Externos

Para a instalação do Jenkins e do Jolokia utilizei a instrução `ADD`. 

### Configuração da JVM
Para que o Tomcat reconheça o Jolokia, configuramos a variável de ambiente `CATALINA_OPTS`. Isso garante que o agente seja carregado no momento em que a JVM inicia o servidor de aplicação:

```dockerfile
ENV CATALINA_OPTS="-javaagent:/opt/jolokia.jar=port=8778,host=0.0.0.0"
```

## Como Executar o Projeto

Para subir o ambiente, utilize o comando abaixo na raiz do diretório onde se encontra o arquivo `compose.yml`:

```bash
docker compose up -d
```

## Verificação do Jenkins

Após a inicialização do conteiner, o Jenkins estará disponível para configuração inicial no host.

![Jenkins](/.github/images/jenkins.png)

### Obtendo a Senha Inicial

O Jenkins gera uma senha de segurança automática no primeiro boot para proteger a instalação. Para recuperá-la diretamente do contêiner em execução, utilize:

```bash
docker container exec tomcat cat /root/.jenkins/secrets/initialAdminPassword
```

Copie o código exibido e cole no campo "Senha do Administrador" na interface web.

![Customizar o Jenkins](/.github/images/comecando.png)

Após isso já podemos seguir com a instalação das extensões do Jenkins normalmente.

## Monitoramento com Jolokia

O Jolokia foi configurado como um Java Agent dentro da JVM do Tomcat. Ele atua como uma ponte (bridge), transformando as métricas internas JMX (Java Management Extensions) em dados JSON acessíveis via requisições HTTP REST.

### Verificação do Agente 

Para validar se o agente está respondendo corretamente e expondo as informações da JVM, execute o comando:

```bash
curl http://localhost:8778/jolokia | jq .
```

### Exemplo de Resposta JSON

Ao realizar a requisição, o agente retorna o status da conexão e detalhes da versão:

```json
{
  "request": {
    "type": "version"
  },
  "value": {
    "agent": "2.4.2",
    "protocol": "8.1",
    "decorators": {},
    "details": {
      "agent_version": "2.4.2",
      "agent_id": "14514713-jvm",
      "secured": false,
      "url": "http://172.18.0.3:8778/jolokia"
    },
    "id": "14514713-jvm",
    "config": {
      "maxDepth": "15",
      "discoveryEnabled": "false",
      "maxCollectionSize": "0",
      "agentId": "14514713-jvm",
      "debug": "false",
      "dateFormat": "yyyy-MM-dd'T'HH:mm:ss.SSSSSSSSSXXX",
      "historyMaxEntries": "10",
      "agentContext": "/jolokia",
      "maxObjects": "0",
      "debugMaxEntries": "100"
    },
    "info": {
      "proxy": {},
      "jmx": {}
    }
  },
  "status": 200,
  "timestamp": 1768245230
}
```

### Consultando Métricas de Performance

Podemos consultar o consumo de memória Heap, utilizando:

```bash
curl http://localhost:8778/jolokia/read/java.lang:type=Memory/HeapMemoryUsage | jq .
```

```json
{
  "request": {
    "mbean": "java.lang:type=Memory",
    "attribute": "HeapMemoryUsage",
    "type": "read"
  },
  "value": {
    "init": 33554432,
    "committed": 184455168,
    "max": 518979584,
    "used": 150166608
  },
  "status": 200,
  "timestamp": 1768246855
}
```