package ex05.topics;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import util.Conexao;

public class EmitLogTopic {

    private static final String EXCHANGE_NAME = "topic_logs";

    public static void main(String[] args) {
        ConnectionFactory factory = Conexao.getConnectionFactory();

        try (Connection connection = factory.newConnection();
                Channel channel = connection.createChannel();) {

            // Declara uma exchange do tipo 'topic'
            // A chave de roteamento pode ser uma frase com várias palavras separadas por
            // pontos (.)
            channel.exchangeDeclare(EXCHANGE_NAME, "topic");

            String routingKey = getRouting(args);
            String message = getMessage(args);

            channel.basicPublish(EXCHANGE_NAME, routingKey, null, message.getBytes("UTF-8"));
            System.out.println(" [x] Sent '" + routingKey + "':'" + message + "'");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static String getRouting(String[] strings) {
        return (strings.length < 1) ? "anonymous.info" : strings[0];
    }

    // Obtém a mensagem a ser enviada
    private static String getMessage(String[] strings) {
        return strings.length < 1 ? "Hello World!" : String.join(" ", strings);
    }
}
