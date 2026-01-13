Após gerar a imagem seguindo boas práticas


pfabianofilho/jenkins-jolokia:1.0.0
pfabianofilho/jenkins-jolokia:latest

VocÇe pode consuotar aqui as imagens


https://hub.docker.com/repository/docker/pfabianofilho/jenkins-jolokia/tags

criei os manifestos para rodar a aplicação em Kubernetes

Particularmente gosto de organizar os manifestos por objetos: Namespace, Deployment, Service, Ingress

## Prerequisitos

- k3d para executar os manifestos

Como instalar [Aqui](https://k3d.io/stable/#install-specific-release)

com o k3d instalado

criamos o cluster

```bash
k3d cluster create desafio-devops-java
```

após acessar a pasta onde estão os manistestos e aplciá-los

```bash
kubectl apply -f .
```

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