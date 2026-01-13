# Desafio Técnico - Etapa 3

O objetivo desta etapa foi implementar uma stack completa de monitoramento no cluster Kubernetes utilizando Prometheus para coleta e armazenamento de métricas e Grafana para visualização e dashboards.

## Instalação do Prometheus 

A instalação foi realizada utilizando o Helm Chart oficial da comunidade, garantindo a implantação dos seguintes componentes:

- Prometheus Server: Core da stack, responsável por realizar o scrape e armazenar as métricas.

- Node Exporter: Coleta métricas de hardware e do sistema operacional dos nós.

- Kube-State-Metrics: Escuta a API do Kubernetes e gera métricas sobre o estado dos objetos (deployments, pods, etc).

- Alertmanager: Sistema de gerenciamento e roteamento de alertas.

- Pushgateway: Suporte para coleta de métricas de jobs de curta duração.

> Nota: A unica configuração que fiz no chart foi em relação ao armazenamento que deixei um armazenamento de 10Gi.

###  Instalação

Criação do namespace e instalação: Levando em conta que você deve estar dentro de kubernetes/manifestos/prometheus

```bash
kubectl create ns prometheus

helm install prometheus oci://ghcr.io/prometheus-community/charts/prometheus -f values.yaml -n prometheus
```

Tudo instalado

```text
Pulled: ghcr.io/prometheus-community/charts/prometheus:28.3.0
Digest: sha256:573d5f5d97fe272840bb54c5300ea6e1a07435470980adc7825c8144ecadd838
NAME: prometheus
LAST DEPLOYED: Tue Jan 13 08:32:54 2026
NAMESPACE: prometheus
STATUS: deployed
REVISION: 1
DESCRIPTION: Install complete
TEST SUITE: None
NOTES:
The Prometheus server can be accessed via port 80 on the following DNS name from within your cluster:
prometheus-server.prometheus.svc.cluster.local


Get the Prometheus server URL by running these commands in the same shell:
  export POD_NAME=$(kubectl get pods --namespace prometheus -l "app.kubernetes.io/name=prometheus,app.kubernetes.io/instance=prometheus" -o jsonpath="{.items[0].metadata.name}")
  kubectl --namespace prometheus port-forward $POD_NAME 9090


The Prometheus alertmanager can be accessed via port 9093 on the following DNS name from within your cluster:
prometheus-alertmanager.prometheus.svc.cluster.local


Get the Alertmanager URL by running these commands in the same shell:
  export POD_NAME=$(kubectl get pods --namespace prometheus -l "app.kubernetes.io/name=alertmanager,app.kubernetes.io/instance=prometheus" -o jsonpath="{.items[0].metadata.name}")
  kubectl --namespace prometheus port-forward $POD_NAME 9093
#################################################################################
######   WARNING: Pod Security Policy has been disabled by default since    #####
######            it deprecated after k8s 1.25+. use                        #####
######            (index .Values "prometheus-node-exporter" "rbac"          #####
###### .          "pspEnabled") with (index .Values                         #####
######            "prometheus-node-exporter" "rbac" "pspAnnotations")       #####
######            in case you still need it.                                #####
#################################################################################


The Prometheus PushGateway can be accessed via port 9091 on the following DNS name from within your cluster:
prometheus-prometheus-pushgateway.prometheus.svc.cluster.local


Get the PushGateway URL by running these commands in the same shell:
  export POD_NAME=$(kubectl get pods --namespace prometheus -l "app=prometheus-pushgateway,component=pushgateway" -o jsonpath="{.items[0].metadata.name}")
  kubectl --namespace prometheus port-forward $POD_NAME 9091

For more information on running Prometheus, visit:
https://prometheus.io/
```

## Insalação Grafana

O Grafana foi instalado para atuar como a camada de visualização, conectando-se ao Prometheus como fonte de dados (Datasource).

Instalação:

```bash
kubectl create namespace grafana

helm repo add grafana https://grafana.github.io/helm-charts

helm repo update

helm install grafana grafana/grafana -f values.yaml --namespace grafana
```

Para pegarmos a senha inicial utilizamos o seguinte comando:

```bash
kubectl get secret --namespace grafana grafana -o jsonpath="{.data.admin-password}" | base64 --decode ; echo
```

### Ingress

Foi feita uma configuração no `values.yaml` do Grafana para criar um Ingress para que consigamos acessá-lo.

![Grafana](/.github/images/grafana.png)      

### Outras Configurações

Fiz a configuração de um Dashboard padrão para monitoramento do Cluster

![Grafana Dashboard](/.github/images/dashboard.png)