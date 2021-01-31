Instruções para configuração de uma Azure function com NodeJS stack para interação com Azure Cosmos DB com Mongo API.


1. Criar uma Azure Function com a runtime "NodeJS"
2. Depois de criada, navegar até o painel de controle avançado (Kudu)
3. Escolha a função Powershell no menu
4. Navegar até o diretório 'wwwroot' e adicionar um arquivo chamado 'package.json' (que servirá como base para instalação/configuração das dependências do Node através de NPM)
5. Adicionar como conteúdo do 'package.json':

{
    "Name": "<nome do projeto>",
    "Dependencies": {
        "mongodb": "3.x"
    }
}

6. No painel Powershell de linha de comando, rodar o comando "npm install"
7. (algumas mensagens de WARN podem aparecer, mas importante é garantir que ele instala a dependência da biblioteca do MongoAPI)
8. Instalação do pacote mongodb para o Node finalizada.
