Após executar o comando 

```bash
docker compose up -d
```

Podemos verificar que o Jenkins está funcionando 

![Jenkins](/.github/images/jenkins.png)

Para pegar a senha inicial podemos executar o comando

```bash
docker container exec tomcat cat /root/.jenkins/secrets/initialAdminPassword
```

Receberemos a senha no terminal, após isso é só seguir com a configuração do Jenkins

![alt text](/.github/images/comecando.png)