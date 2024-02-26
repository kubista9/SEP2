import serverMediator.RemoteCoreModel;
import serverMediator.Server;
import serverModel.CoreModel;
import serverModel.StoreModelManager;

import java.net.MalformedURLException;
import java.rmi.RemoteException;

public class ServerMain {
	public static void main(String[] args) throws MalformedURLException, RemoteException {
		CoreModel model = new StoreModelManager();
		RemoteCoreModel server = new Server(model);

	}
}
