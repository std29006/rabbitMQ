package util;

public class App {

    
    public static void main(String[] args) {
        System.out.println("\n..:: Bem-vindo ao projeto de exemplo! ::..\n");
        System.out.println("  Este projeto apresenta vários exemplos para uso do RabbitMQ.");
        System.out.println("  Para mais informações, consulte o arquivo Readme.md na raiz do repositório.");
        
        
        System.out.println(args.length > 0 ? "  Argumentos recebidos: " + String.join(", ", args) : "Nenhum argumento recebido.");

        System.out.println("..........................................");
    }
}
