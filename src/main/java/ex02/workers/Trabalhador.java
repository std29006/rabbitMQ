package ex02.workers;

import com.rabbitmq.client.*;
import util.Conexao;

public class Trabalhador {

    private static final String TASK_QUEUE_NAME = "task_queue";

    public static void main(String[] args) throws Exception {
        // Informações sobre a conexão com o sistema de filas
        ConnectionFactory factory = Conexao.getConnectionFactory();

        final Connection connection = factory.newConnection();
        final Channel channel = connection.createChannel();

        channel.queueDeclare(TASK_QUEUE_NAME, true, false, false, null);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        channel.basicQos(1);

        // Cria um consumidor usando lambda para processar as mensagens recebidas
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            // Processa a mensagem recebida
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println(" [x] Received '" + message + "'");
            try {
                doWork(message); // Simula trabalho
            } finally {
                System.out.println(" [x] Done");
                // Confirma o processamento da mensagem
                channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
            }
        };
        channel.basicConsume(TASK_QUEUE_NAME, false, deliverCallback, consumerTag -> {
        });
    }

    private static void doWork(String task) {
        int seconds = 1;
        for (char ch : task.toCharArray()) {
            if (ch == '.') {
                try {
                    Thread.sleep(1000);
                    System.out.println(" working...done " + seconds + "s");
                    seconds++;
                } catch (InterruptedException _ignored) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}
