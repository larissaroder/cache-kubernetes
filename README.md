# cache-kubernetes

Projeto que une o estudo sobre caches utilizando spring boot e kubernetes
Existe dois modelos de caches utilizando o Generic SpringBoot e hazelcast
# infraestrutura

1) criar uma imagem com docker build -t <nome_imagem> na pasta root do projeto
2) fazer um push da imagem no docker hub
3) rodar o comando kubectrk create -f kubernetes/hazelcast.yaml (criar o pod)
4) kubectl port-forward <nome_pod> 8080:8080 (expor a porta do pod para ser acessada)
