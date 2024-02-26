package serverMediator;

import serverModel.CoreModel;
import serverModel.User;
import shared.Item;
import shared.ItemList;
import shared.Password;
import utility.observer.listener.GeneralListener;
import utility.observer.subject.PropertyChangeHandler;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class Server implements RemoteCoreModel {
	private RemoteCoreModel model;
	private PropertyChangeHandler<Item, Item> property;
	private CoreModel itemList;

	public Server(CoreModel model) throws MalformedURLException, RemoteException {
		this.property = new PropertyChangeHandler<>(this);
		this.itemList = model;
		startRegistry();
		startServer();
	}

	private void startRegistry() throws RemoteException {
		try {
			Registry registry = LocateRegistry.createRegistry(1099);
			System.out.println("Registry started...");
		} catch (java.rmi.server.ExportException e) {
			System.out.println("Registry already started? " + e.getMessage());
		}
	}

	private void startServer() throws RemoteException, MalformedURLException {
		UnicastRemoteObject.exportObject(this, 0);
		Naming.rebind("Message", this);
		System.out.println("Server started...");
	}

	@Override
	public void registerUser(User user) throws RemoteException {

	}

	@Override
	public void addItemToSell(User user, Item item) throws RemoteException {
		itemList.addItemToSell(new User("testUserForServerClass", new Password("testPassword"), "testEmail", false), item);
		property.firePropertyChange("ADD", null, item);
	}

	@Override
	public void postItem(User user, Item item) throws RemoteException {
		itemList.postItem(user, item);
	}

	@Override
	public void addQuantity(User user, String itemName, int quantity) throws RemoteException {
		itemList.addQuantity(user, itemName, quantity);
	}

	@Override
	public void addToBasket(User user, Item item) throws RemoteException {
		itemList.addToBasket(user, item);
	}

	@Override
	public Item buyItem(User user, String itemName, int quantity) throws RemoteException {
		return itemList.buyItem(user, itemName, quantity);
	}

	@Override
	public ItemList buyItems(User user, ItemList items) throws RemoteException {
		return itemList.buyItems(user, items);
	}

	@Override
	public ItemList buyBasket(User user) throws RemoteException {
		return itemList.buyBasket(user);
	}

	@Override
	public void setUserPassword(User user, Password password) throws RemoteException {
		user.setPassword(password);
	}

	@Override
	public void setUserEmail(User user, String email) throws RemoteException {
		user.setEmail(email);
	}

	@Override
	public ArrayList<Item> getItemList() throws RemoteException {
		return itemList.getItemList();
	}

	@Override
	public ArrayList<User> getUserList() throws RemoteException {
		return itemList.getUserList();
	}

	@Override
	public Item getItem(String itemName) throws RemoteException {
		return itemList.getItem(itemName);
	}

	@Override
	public User getUser(String username) throws RemoteException {
		return itemList.getUser(username);
	}


	@Override
	public boolean addListener(GeneralListener listener, String... propertyNames) throws RemoteException {
		return property.addListener(listener, propertyNames);
	}

	@Override
	public boolean removeListener(GeneralListener listener, String... propertyNames) throws RemoteException {
		return property.removeListener(listener, propertyNames);
	}
}
