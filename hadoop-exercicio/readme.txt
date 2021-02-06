Preparação do Cassandra:
1. Criar CosmosDB/CassandraAPI na Azure
2. Capturar e guardar hostname, username e password
3. Conectar com CQLSH e criar um KEYSPACE chamado 'faesamod05keyspace'
4. Criar uma tabela chamada 'livro' com uma coluna chamada 'chave' do tipo 'varchar'

Preparação do pacote Java-hadoop:
1. Baixar o projeto piloto do git (créditos a Manu Mukerji <next2manu@gmail.com>, que é o autor do projeto original)
    O projeto original tinha dependências Maven antigas, além de uma forma de conexão com um Cassandra local, e não compatível com o CosmosDB. As adequações já foram realizadas.

2. Atualizar as strings de conexão com o host, username e password do Cassandra (Cosmos) dentro da classe CassandraHelper.java

3. Empacotar o projeto através do Maven (necessário ter o maven instalado na máquina): mvn clean install

4. Capturar o arquivo .jar de saída e renomear para o nome desejado (faesamod05mapreduce.jar)

5. Testar classe de teste de conexão e insert de uma linha somente no Cassandra
    java -cp <nome do jar> com.example.com.mr_cassandra.CassandraHelper

6. Validar o estado da tabela "livro" no Cassandra. Deve ter um registro "test1234"

7. Dropar a tabela e criá-la novamente

8. Conectar via SSH no cluster Hadoop
    ssh <user>@<host>
    ssh sshuser@faesamod05hdcluster-ssh.azurehdinsight.net 

9. Criar diretório 'faesamod05' e alterar suas permissões de escrita com os comandos:
    mkdir faesamod05
    sudo chmod 777 faesamod05

10. (fora do SSH ou em outro terminal) Importar o arquivo .jar diretamente para o Cluster Hadoop através de SCP (pacote do SSH):

    scp faesamod05mapreduce.jar sshuser@faesamod05hdcluster-ssh.azurehdinsight.net:~/faesamod05

11. (de volta no SSH) Navegar até a pasta onde está o arquivo faesamod05mapreduce.jar (faesamod05), executar chamada YARN dentro do cluster Hadoop para teste com o Cassandra
    yarn jar faesamod05mapreduce.jar com.example.com.mr_cassandra.CassandraTester

12. Validar novamente se o registro 'test1234' foi criado na tabela 'livro' do Cassandra

13. Executar processo MapReduce que captura todas as frases de um livro armazenado no txt do Storage Account
    É necessário informar o nome do .jar que possui o pacote das classes, o nome da classe principal (que tem o método main), o nome do arquivo de entrada e nome do diretório de saída (para as saídas hadoop normais)

    Comando:
    yarn jar faesamod05mapreduce.jar com.example.com.mr_cassandra.MapReduceExample /livros/metamorfose.txt /livros/meta1
    
14. Validar tabela 'livro' novamente no Cassandra