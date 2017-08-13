package std.ex01.hello;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import java.util.concurrent.TimeoutException;

public class Produtor {

    private final static String QUEUE_NAME = "hello";

    public static void main(String args[]) throws java.io.IOException, TimeoutException {

        // Informações sobre a conexão com o sistema de filas
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection()) {
            Channel channel = connection.createChannel();
            
            // Enviando mensagem
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            String message = "Hello World!";
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            System.out.println(" [x] Sent '" + message + "'");
            
            
            // Fechando canal e conexão
            channel.close();
        }catch (Exception e){
            System.err.println("Não foi poss;ível criar conexão\n");
        }
    }

}
