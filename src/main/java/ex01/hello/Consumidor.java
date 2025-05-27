package ex01.hello;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import util.Conexao;

import java.io.IOException;
import java.util.concurrent.TimeoutException;


public class Consumidor {

    private final static String QUEUE_NAME = "hello";

    public static void main(String[] argv) throws java.io.IOException, java.lang.InterruptedException, TimeoutException {

        // Informações sobre a conexão com o sistema de filas
        ConnectionFactory factory = Conexao.getConnectionFactory();

        // Estabelece a conexão com o sistema de filas
        Connection connection = factory.newConnection();
        // Cria um canal de comunicação com o sistema de filas
        Channel channel = connection.createChannel();

        // Declara a fila onde as mensagens serão recebidas
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        // Cria um consumidor usando lambda para processar as mensagens recebidas
        channel.basicConsume(QUEUE_NAME, true, (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println(" [x] Received '" + message + "'");
        }, consumerTag -> {});

    }

}
