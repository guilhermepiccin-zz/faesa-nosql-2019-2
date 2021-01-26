Instruções para configuração de uma Azure function com NodeJS stack para interação com Azure Cosmos DB com Mongo API.


1. Criar uma Azure Function com a runtime "NodeJS"
2. Depois de criada, navegar até o painel de controle avançado (Kudu), escolha a função Powershell no menu, navegar até o diretório 'wwwroot' e adicionar um arquivo chamado 'package.json' (que servirá como base para instalação/configuração das dependências do Node através de NPM)
3. Adicionar como conteúdo do 'package.json':

{
    "Name": "<nome do projeto>",
    "Dependencies": {
        "mongoldb": "3.x"
    }
}

4. No painel Powershell, rodar o comando "npm install" (algumas mensagens de WARN podem aparecer, mas importante é garantir que ele instala a dependência da biblioteca do MongoAPI)
