# ShowCase demonstrating SpringBoot K8S service backed up by OpenAI provider
For those that belief that the next wave of Generative AI applications will not be only for Python developers. This simple Spring REST API exposes AI capabilities by integrating [Spring-ai](https://docs.spring.io/spring-ai/reference/index.html).

## in this demo...
- Gradle build SpringApplication
- HELM charts to package and deploy SB service on Kubernetes
- Instructions how to order and configuring Open-AI API key to access ChatGPT
- Different AI prompt templates and output parsers

# Prerequisites

## create resource group
```
az group create --name aks-cluster-template_rg001 --location centralus
```
## Create ARM template spec and form to deploy POC AKS cluster
```
az ts create --name clusterspec --version 1 --resource-group tks-cluster-template_rg001 --location centralus  --template-file ./arm-templates/azuredeploy.json --ui-form-definition ./ arm-templates/clusterform.json
```

## Deploy AKS POC cluster with custom ARM template from AZ Portal
- From portal search for "Deploy a", this will lead you to custom deployment (Deploy from a custom deployment).
- Build your own template...
- upload your custom  template and go for it

## Test cluster
```
az aks get-credentials --resource-group aks-cluster-template_rg001 --name aks101cluster --overwrite-existing
kubectl get nodes
kubectl describe node
kubectl top node aks-agentpool-14651446-vmss000000
```

## Create an Azure App Configuration Store
```
az appconfig create --name poc-app-config001 --resource-group aks-cluster-template_rg001 --location centralus --sku free
```

## cleanup

```
az appconfig delete --name poc-app-config --resource-group aks-cluster-template_rg001
az group delete --name aks-cluster-template_rg001
```

## references
- [Spring AI](https://docs.spring.io/spring-ai/reference/index.html)
- [Spring AI with Azure OpenAI](https://smoothed9.medium.com/spring-ai-with-azure-openai-0e1498471fe3)