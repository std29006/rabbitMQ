package ex04.routing;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import util.Conexao;

public class EmitLogDirect {

    private static final String EXCHANGE_NAME = "direct_logs";

    public static void main(String[] args) throws Exception {

        // Informações sobre a conexão com o sistema de filas
        ConnectionFactory factory = Conexao.getConnectionFactory();

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        // Declara uma exchange do tipo 'direct'
        // Mensagens são roteadas com base na chave de roteamento (severity)
        // Possíveis valores de severity: "info", "warning", "error"
        channel.exchangeDeclare(EXCHANGE_NAME, "direct");

        String severity = getSeverity(args);
        String message = getMessage(args);

        channel.basicPublish(EXCHANGE_NAME, severity, null, message.getBytes("UTF-8"));
        System.out.println(" [x] Sent '" + severity + "':'" + message + "'");

        channel.close();
        connection.close();
    }

    private static String getSeverity(String[] strings) {
        if (strings.length < 1) {
            return "info";
        }
        return strings[0];
    }

    // Obtém a mensagem a ser enviada
    private static String getMessage(String[] strings) {
        return strings.length < 1 ? "Hello World!" : String.join(" ", strings);
    }
}
