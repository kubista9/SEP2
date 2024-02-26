import clientMediator.Client;

public class ClientMain {
	public static void main(String[] args) {
		String host = "localhost";
		if (args.length > 0) {
			host = args[0];
		}
		Client client = new Client(host);
		client.start();
	}
}
