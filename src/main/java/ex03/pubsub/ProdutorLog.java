package ex03.pubsub;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import util.Conexao;

public class ProdutorLog {

    // Nome da exchange onde as mensagens serão publicadas
    private static final String EXCHANGE_NAME = "logs";

    public static void main(String[] args) throws Exception {
        // Informações sobre a conexão com o sistema de filas
        ConnectionFactory factory = Conexao.getConnectionFactory();

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        // Declara uma exchange do tipo 'fanout'
        // As mensagens são enviadas para todas as filas ligadas à exchange
        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");

        String message = getMessage(args);

        // Publica a mensagem na exchange
        channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes("UTF-8"));
        System.out.println(" [x] Sent '" + message + "'");

        channel.close();
        connection.close();
    }

    // Obtém a mensagem a ser enviada
    private static String getMessage(String[] strings) {
        return strings.length < 1 ? "info: Hello World!" : String.join(" ", strings);
    }
}
