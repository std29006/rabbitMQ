# Exemplos em Java com RabbitMQ

O servidor RabbitMQ é um *broker* de mensagens que implementa o protocolo AMQP (*Advanced Message Queuing Protocol*). O RabbitMQ é um sistema de mensagens que permite a comunicação entre diferentes aplicações, facilitando a troca de informações de forma assíncrona e escalável.

Neste repositório são apresentados exemplos do tutorial oficial do RabbitMQ. Cada exemplo é um programa Java que utiliza a biblioteca [RabbitMQ Java Client](https://www.rabbitmq.com/java-client.html) para se comunicar com o servidor RabbitMQ.


## Servidor RabbitMQ

Para executar os exemplos, é necessário ter um servidor RabbitMQ em execução. Neste projeto, utilizamos o RabbitMQ na versão `3-management-alpine`, que é uma imagem leve do RabbitMQ com a interface de gerenciamento habilitada.

Para facilitar o processo de instalação e configuração do RabbitMQ, utilizamos o Docker. Assim, não é necessário instalar o RabbitMQ diretamente na máquina, mas sim executar um contêiner Docker com o RabbitMQ já configurado.

```bash
docker run --name servidor-rabbit -p 15672:15672 -p 5672:5672 rabbitmq:3-management-alpine
```

Após executar o comando acima, o servidor RabbitMQ estará rodando em segundo plano. Você pode acessar a interface de gerenciamento do RabbitMQ através do navegador, utilizando o endereço `http://localhost:15672`.

Na interface de gerenciamento, você poderá visualizar as filas, trocas, conexões e outras informações sobre o servidor RabbitMQ. O usuário e senha padrão são `guest/guest`, que são os valores padrão do RabbitMQ.


## Instruções para execução dos exemplos

Cada exemplo é composto por dois programas, geralmente, um produtor e um consumidor. No arquivo [build.gradle](build.gradle) foram criadas [tarefas gradle](https://docs.gradle.org/current/dsl/org.gradle.api.tasks.JavaExec.html) para facilitar a execução de cada exemplo. Para cada exemplo foram criadas 2 tarefas e todas estestão dentro do grupo `execution` do gradle. 

No arquivo [conexao.properties](src/main/resources/conexao.properties) estão contidas as informações sobre o `host`, `username` e `password` do servidor RabbitMQ que será utilizado nos exemplos em Java. Por padrão, o usuário e senha são `guest/guest`, que são os valores padrão do RabbitMQ.


A explicação do funcionamento de cada exemplo pode ser obtida na [documentação oficial do RabbitMQ](http://www.rabbitmq.com/getstarted.html)

### Exemplo 01 - Hello world!

![hello world](images/one.png)

Neste exemplo, um produtor envia uma mensagem para uma fila e um consumidor recebe essa mensagem. Se você executar o exemplo várias vezes, verá que o consumidor receberá todas as mensagens enviadas pelo produtor, mesmo que o consumidor não esteja em execução no momento em que as mensagens são enviadas.

```bash
# Primeiro, execute o consumidor para que ele esteja pronto para receber mensagens.
./gradlew -q ex01Consumidor
```

```bash
# Em seguida, execute o produtor para enviar uma mensagem.
./gradlew -q ex01Produtor
```

### Exemplo 02 - Work queues

![work queues](images/two.png)

Neste exemplo, um produtor envia mensagens para uma fila e vários consumidores (trabalhadores) recebem essas mensagens. O objetivo é distribuir as tarefas entre os trabalhadores de forma equilibrada, utilizando o padrão varreduda cíclica (*round robin*).


```bash
# Abra três terminais e execute o trabalhador em cada um deles com o comando abaixo.
./gradlew -q ex02Trabalhador
```

```bash
# Em seguida, abra um novo terminal e execute o produtor para enviar mensagens.
./gradlew -q ex02Tarefa
```

### Exemplo 03 - Publish / Subscribe

![publish/subscribe](images/three.png)

Neste exemplo, um produtor envia mensagens para uma *exchange* e vários consumidores recebem essas mensagens. O objetivo é demonstrar o padrão de publicação/assinatura (*publish/subscribe*), onde as mensagens são enviadas para todos os consumidores conectados à *exchange*.

```bash
# Abra dois terminais e execute o receptor em cada um deles com o comando abaixo.
./gradlew -q ex03Receptor
```

```bash
# Em seguida, abra um novo terminal e execute o produtor para enviar mensagens.
./gradlew -q ex03Produtor
```

### Exemplo 04 - Routing

![routing](images/four.png)

Neste exemplo, um produtor envia mensagens para uma *exchange* com base em uma chave de roteamento. Vários consumidores recebem essas mensagens, mas cada consumidor está interessado apenas em mensagens com uma chave de roteamento específica. O objetivo é demonstrar o padrão de roteamento, onde as mensagens são enviadas para filas específicas com base na chave de roteamento.

```bash
# Abra dois terminais e execute o receptor em cada um deles com o comando abaixo.
./gradlew -q ex04Receptor --args "info"
```
```bash
# Abra um novo terminal e execute o receptor com uma chave de roteamento diferente.
./gradlew -q ex04Receptor --args "std"
```

```bash
# Em seguida, abra um novo terminal e execute o emissor para enviar mensagens.
# Emissor envia mensagens com diferentes chaves de roteamento. Use "info" ou "std" como argumento.
./gradlew -q ex04Emissor --args "info"
```

### Exemplo 05 - Topics

![topics](images/five.png)

Neste exemplo, um produtor envia mensagens para uma *exchange* com base em padrões de texto (tópicos). Vários consumidores recebem essas mensagens, mas cada consumidor está interessado apenas em mensagens que correspondam a um padrão específico. O objetivo é demonstrar o padrão de tópicos, onde as mensagens são enviadas para filas específicas com base em padrões de texto.

```bash
# Abra um terminal e execute o receptor com um padrão específico.
./gradlew -q ex05Receptor --args "std.aula"
```
```bash
# Abra um terminal e execute o receptor com um padrão específico.
./gradlew -q ex05Receptor --args "std.prova"
```

```bash
# Abra um novo terminal e execute o receptor com um padrão mais amplo.
./gradlew -q ex05Receptor --args "std.*"
```

```bash
# Em seguida, abra um novo terminal e execute o emissor para enviar mensagens.
./gradlew -q ex05Emissor --args "std.aula"
```


### Exemplo 06 - RPC

![rpc](images/six.png)

Neste exemplo, um cliente envia uma solicitação para um servidor e aguarda uma resposta. O objetivo é demonstrar o padrão de chamada de procedimento remoto (*RPC*), onde o cliente faz uma solicitação ao servidor e recebe uma resposta.

```bash
# Primeiro, execute o servidor para que ele esteja pronto para receber solicitações.
./gradlew -q ex06Servidor
```

```bash
# Em seguida, execute o cliente para enviar uma solicitação e receber uma resposta.
./gradlew -q ex06Cliente
```

Faça o contrário, suba o cliente primeiro e depois o servidor, para ver como o cliente aguarda a resposta do servidor.


## Referências 

- https://github.com/rabbitmq/rabbitmq-tutorials
- http://www.rabbitmq.com/getstarted.html
