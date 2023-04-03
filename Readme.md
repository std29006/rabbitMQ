# Exemplos em Java com RabbitMQ

> Esse projeto foi feito com gradle. Poderá executá-lo usando o gradle por meio de linha de comando ou dentro de uma IDE, como o IntelliJ ou VSCode.

Nesse projeto estão exemplos do tutorial oficial do RabbitMQ

- https://github.com/rabbitmq/rabbitmq-tutorials
- http://www.rabbitmq.com/getstarted.html

É necessário ter um servidor RabbitMQ em execução na máquina local e com usuário e senha padrão (guest/guest). No arquivo [conexao.properties](src/main/resources/conexao.properties) estão contidas as informações sobre o `host`, `username` e `password` do servidor RabbitMQ. Veja a [documentação oficial](http://www.rabbitmq.com/download.html) para instalar um servidor e colocá-lo em execução. Ou execute o servidor RabbitMQ dentro de um contêiner Docker usando as instruções apresentadas abaixo:

```bash
docker run --name servidor-rabbit -p 15672:15672 -p 5672:5672 rabbitmq:3-management-alpine
```
Cada exemplo é composto por dois programas, geralmente, um produtor e um consumidor. Abaixo é apresentado como executar cada um dos programas. A explicação do funcionamento de cada exemplo pode ser obtida na [documentação oficial do RabbitMQ](http://www.rabbitmq.com/getstarted.html)


## Instruções para compilação

No arquivo [build.gradle](build.gradle) foram criadas [tarefas gradle](https://docs.gradle.org/current/dsl/org.gradle.api.tasks.JavaExec.html) para facilitar a execução de cada exemplo. Para cada exemplo foram criadas 2 tarefas e todas estestão dentro do grupo `execution` do gradle. 

Também é possível gerar um JAR contendo todos os exemplos, bem como as dependências. Para isso execute os passos abaixo:

1. Entre no diretório do projeto
2. Digite: `./gradlew shadowJar`
3. Será criado o pacote `std-1.0-all.jar` dentro do subdiretório `build/libs`

Para executar cada exemplo com o JAR é necessário fazer algo como:

```bash
java -cp build/libs/std-1.0-all.jar ex01.hello.Consumidor
```

Abaixo são apresentados os exemplos, bem como as instruções de execução por meio das tarefas gradle.

## Exemplo 01 - Hello world!

Um simples hello world.

![hello world](images/one.png)

Execute cada uma das linhas abaixo em um terminal diferente e siga essa sequência de execução.

```bash
./gradlew -q ex01Produtor

./gradlew -q ex01Consumidor
```

## Exemplo 02 - Work queues

Distribuindo tarefas por todos os processos trabalhadores (de forma igualitária - *round robin*).

![work queues](images/two.png)

Execute cada uma das linhas abaixo em um terminal diferente e siga essa sequência de execução.

```bash
./gradlew -q ex02Trabalhador

./gradlew -q ex02Tarefa
```

## Exemplo 03 - Publish / Subscribe

Enviando mensagens para diversos consumidores.

![publish/subscribe](images/three.png)

Execute cada uma das linhas abaixo em um terminal diferente e siga essa sequência de execução.

```bash
./gradlew -q ex03Receptor

./gradlew -q ex03Produtor
```

## Exemplo 04 - Routing

Recebendo mensagens de forma seletiva, escolhendo de qual fila receberá mensagens.

![routing](images/four.png)

Execute cada uma das linhas abaixo em um terminal diferente e siga essa sequência de execução.

```bash
./gradlew -q ex04Receptor --args "info"

./gradlew -q ex04Emissor
```

## Exemplo 05 - Topics

Recebendo mensagens com base em padrões de texto (tópicos).

![topics](images/five.png)

Execute cada uma das linhas abaixo em um terminal diferente e siga essa sequência de execução.

```bash
./gradlew -q ex05Receptor --args "saudacao"

./gradlew -q ex05Emissor --args "saudacao"
```

## Exemplo 06 - RPC

Estilo pedido/resposta de uma chamada de procedimento remota.

![rpc](images/six.png)

Execute cada uma das linhas abaixo em um terminal diferente e siga essa sequência de execução.

```bash
./gradlew -q ex06Servidor

./gradlew -q ex06Cliente
```