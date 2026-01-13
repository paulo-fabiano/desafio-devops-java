# Desafio Técnico - Etapa 2

O objetivo da Etapa 2 foi migrar o que foi desenvolvido na Etapa 1 para um ambiente Kubernetes, garantindo organização, boas práticas e funcionamento adequado da aplicação.

## Pré-requisitos

Para configurar o ambiente Kubernetes, optei por utilizar o K3D, por ser leve, rápido e ideal para ambientes de desenvolvimento local.

A instalação foi realizada seguindo a documentação oficial:

- https://k3d.io/stable/#install-specific-release

Após a instalação do K3D, foi criado o cluster com o seguinte comando:

```bash
k3d cluster create desafio-devops-java -p "80:80@loadbalancer"
```

> Nota: As portas foram mapeadas para o LoadBalancer do K3D com o objetivo de utilizar o Traefik, que já vem configurado por padrão no cluster.

## Imagens Docker

Após a geração das imagens seguindo boas práticas, a enviei para o Docker Hub para que possamos utilizá-la em nosso manifesto:

- pfabianofilho/jenkins-jolokia:1.0.0

Aqui está o repositório no Docker Hub:

- https://hub.docker.com/repository/docker/pfabianofilho/jenkins-jolokia/tags


## Organização dos YALMs K8S

Particularmente, eu prefiro de deixar todos os arquivos separados em vez de todos dentro de um arquivo só. Isso facilita (pra mim):

- Manutenção
- Debug

Todos os manifestos da aplicação estão dentro de [jenkins-jolokia](kubernetes/manifestos/jenkins-jolokia).

### Volume Jenkins 

Para que os dados do Jenkins fossem salvos, configurei uma PVC (Persistente Claim Volume) para que os dados não fossem perdidos em caso de um restart do Pod.

### Jenkins Prefix

Para garantir a integridade das URLs, redirecionamentos e carregamento de assets (CSS/JS), configurei o prefixo da aplicação fiz a adição da variável de ambiente

```shell
JENKINS_PREFIX
```

O valor setar foi `/jenkins`

### Manifestos

Os manifestos foram feitos levando em conta a questão da segurança.

## Deploy da Aplicação no Kubernetes

Após a criação dos manifestos, basta acessar o diretório onde eles estão localizados e aplicar:

```bash
kubectl apply -f .
```

Para validar se os recursos foram criados corretamente:

```bash
kubectl get all -n jenkins
```

Podemos ver que todos os componentes no namespace do Jenkins subiram normalmente

```text
NAME                           READY   STATUS    RESTARTS   AGE
pod/jenkins-8479844d76-55t7h   1/1     Running   0          49s

NAME              TYPE        CLUSTER-IP     EXTERNAL-IP   PORT(S)             AGE
service/jenkins   ClusterIP   10.43.15.173   <none>        8080/TCP,8778/TCP   13m

NAME                      READY   UP-TO-DATE   AVAILABLE   AGE
deployment.apps/jenkins   1/1     1            1           49s

NAME                                 DESIRED   CURRENT   READY   AGE
replicaset.apps/jenkins-8479844d76   1         1         1       49s
```

Todos os componentes do Jenkins subiram corretamente dentro do namespace dedicado.

A partir desse ponto, basta seguir com a configuração padrão do Jenkins via interface web.

![Jenkins K8S](/.github/images/jenkins-k8s.png)

Para buscar a senha inicial basta usar o comando:

```bash
kubectl exec jenkins-deploy-c9fbbc577-jnqq2 -c jenkins-jolokia -n jenkins -- cat /var/jenkins_home/secrets/initialAdminPassword
```

Com isso, agora é só configurar o Jenkins.