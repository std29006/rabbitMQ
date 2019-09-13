# Exemplos em Java com RabbitMQ

> Esse projeto foi feito com gradle. Poderá executá-lo usando o gradle por meio de linha de comando ou dentro de uma IDE, como o IntelliJ.



Nesse projeto estão exemplos do tutorial oficial do RabbitMQ

- https://github.com/rabbitmq/rabbitmq-tutorials
- http://www.rabbitmq.com/getstarted.html



## Exemplo 01 - Hello world!

Simples hello world.

![hello world](images/one.png)



## Exemplo 02 - Work queues

Distribuindo tarefas por todos os processos trabalhadores (de forma igualitária - *round robin*).

![work queues](images/two.png)

## Exemplo 03 - Publish / Subscribe

Enviando mensagens para diversos consumidores.

![publish/subscribe](images/three.png)

## Exemplo 04 - Routing

Recebendo mensagens de forma seletiva, escolhendo de qual fila receberá mensagens.

![routing](images/four.png)

## Exemplo 05 - Topics

Recebendo mensagens com base em padrões de texto (tópicos).

![topics](images/five.png)



## Exemplo 06 - RPC

Estilo pedido/resposta de uma chamada de procedimento remota.

![rpc](images/six.png)



## Instruções para compilação e geração do pacote `.jar`

### Usando o IntelliJ

1. Abra o projeto no IntelliJ
2. Abra o painel do Gradle (clique no botão gradle que fica no painel lateral à direita)
3. Abra o projeto `std`, abra o grupo `Tasks`-> `shadow`
4. Clique duas vezes na tarefa `shadowJar`
5. Será criado o pacote `std-1.0.jar` dentro do subdiretório `build/libs`.



### Usando o gradle no terminal

#### Instalando o gradle no Linux

1. Instale o [SDKMAN](https://sdkman.io/) 
   1. `curl -s "https://get.sdkman.io" | bash`
   2. Por fim, feche o terminal e abra um novo terminal
2. Instale o gradle
   1. `sdk install gradle`

#### Usando o gradle para gerar o pacote `.jar`

1. Entre no diretório do projeto
2. Digite: `gradle shadowJar`
3. Será criado o pacote `std-1.0.jar` dentro do subdiretório `build/libs`



## Instruções para execução dos exemplos na linha de comando



> É necessário ter um servidor RabbitMQ em execução na máquina local e com usuário e senha padrão (guest/guest). Veja a [documentação oficial](http://www.rabbitmq.com/download.html) para instalar um servidor e colocá-lo em execução. Se tiver um cenário diferente desse, então altere as configurações da conexão no arquivo [conexao.properties](src/main/resources/conexao.properties).



Cada exemplo é composto por dois programas, geralmente, um produtor e um consumidor. Abaixo é apresentado como executar cada um dos programas. A explicação do funcionamento de cada exemplo pode ser obtida na [documentação oficial do RabbitMQ](http://www.rabbitmq.com/getstarted.html)

#### Exemplo 01

1. Abra um terminal e execute o produtor

   `java -cp std-1.0.jar ex01.hello.Produtor`

2. Abra um outro terminal e execute o consumidor

   `java -cp std-1.0.jar ex01.hello.Consumidor`

#### Exemplo 02
Execute cada uma das linhas abaixo em um terminal diferente e siga essa sequência de execução.

```
java -cp std-1.0.jar ex02.workers.Trabalhador

java -cp std-1.0.jar ex02.workers.Tarefa
```

#### Exemplo 03
Execute cada uma das linhas abaixo em um terminal diferente e siga essa sequência de execução.
```
java -cp std-1.0.jar ex03.pubsub.ReceptorLogs

java -cp std-1.0.jar ex03.pubsub.ProdutorLog
```

#### Exemplo 04
Execute cada uma das linhas abaixo em um terminal diferente e siga essa sequência de execução.
```
java -cp std-1.0.jar ex04.routing.ReceiveLogsDirect info

java -cp std-1.0.jar ex04.routing.EmitLogDirect
```

#### Exemplo 05
Execute cada uma das linhas abaixo em um terminal diferente e siga essa sequência de execução.
```
java -cp std-1.0.jar ex05.topics.ReceiveLogsTopic saudacao

java -cp std-1.0.jar ex05.topics.EmitLogTopic saudacao
```

#### Exemplo 06
Execute cada uma das linhas abaixo em um terminal diferente e siga essa sequência de execução.
```
java -cp std-1.0.jar ex06.rpc.RPCServer

java -cp std-1.0.jar ex06.rpc.RPCClient
```

