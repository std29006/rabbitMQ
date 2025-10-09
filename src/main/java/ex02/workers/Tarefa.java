package ex02.workers;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;
import util.Conexao;

public class Tarefa {

    private static final String TASK_QUEUE_NAME = "task_queue";

    public static void main(String[] args) {
        // Informações sobre a conexão com o sistema de filas
        ConnectionFactory factory = Conexao.getConnectionFactory();

        try (Connection connection = factory.newConnection();
                Channel channel = connection.createChannel()) {

            channel.queueDeclare(TASK_QUEUE_NAME, true, false, false, null);

            String message = getMessage(args);

            channel.basicPublish("", TASK_QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN,
                    message.getBytes("UTF-8"));
            System.out.println(" [x] Sent '" + message + "'");

        } catch (IOException | TimeoutException e) {

            e.printStackTrace();
        }
    }

    // Obtém a mensagem a ser enviada
    private static String getMessage(String[] strings) {
        return strings.length < 1 ? "Hello World!" : String.join(" ", strings);
    }
}
