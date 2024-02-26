package clientMediator;

import serverMediator.Server;
import serverModel.User;
import shared.Item;
import shared.ItemList;
import shared.MyDate;
import shared.Password;
import utility.observer.event.ObserverEvent;
import utility.observer.listener.GeneralListener;
import utility.observer.listener.RemoteListener;
import utility.observer.subject.PropertyChangeHandler;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class Client implements RemoteCoreModel, RemoteListener<Item, Integer> {
	private RemoteCoreModel itemList;
	private PropertyChangeHandler<Item, Item> property;
	String host;

	public Client(String host) {
		this.host = host;
		try {
			itemList = (RemoteCoreModel) Naming.lookup("rmi://" + host + ":1099/Message");
			UnicastRemoteObject.exportObject(this, 0);
			System.out.println("Client started...");
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}

	public void start(){
			try {
				Password testPassword = new Password("TestPassword1");
				User testUser = new User("Test Person", testPassword, "testUserTestEmail@testDomain.com", new MyDate(), false );
				Item testItem = new Item("Test item", 11.0, 1, "Lorem ipsum dolor sit amet",testUser );
				itemList.addItemToSell(testUser, testItem);
				System.out.println(itemList.getItemList());

			} catch (RemoteException e) {
				throw new RuntimeException(e);
			}
	}

	@Override public void registerUser(User user) throws RemoteException {
		itemList.registerUser(user);
	}

	@Override public void addItemToSell(User user, Item item) throws RemoteException {
		itemList.addItemToSell(user, item);
	}

	@Override public void postItem(User user, Item item) throws RemoteException {
		itemList.postItem(user, item);
	}

	@Override public void addQuantity(User user, String itemName, int quantity) throws RemoteException {
		itemList.addQuantity(user, itemName, quantity);
	}

	@Override public void addToBasket(User user, Item item) throws RemoteException {
		itemList.addToBasket(user, item);
	}

	@Override public Item buyItem(User user, String itemName, int quantity) throws RemoteException {
		return itemList.buyItem(user, itemName, quantity);
	}

	@Override public shared.ItemList buyItems(User user, shared.ItemList items) throws RemoteException {
		return itemList.buyItems(user, items);
	}

	@Override public ItemList buyBasket(User user) throws RemoteException {
		return itemList.buyBasket(user);
	}

	@Override public void setUserPassword(User user, Password password) throws RemoteException {
		itemList.setUserPassword(user, password);
	}

	@Override public void setUserEmail(User user, String email) throws RemoteException {
		itemList.setUserEmail(user, email);
	}

	@Override public ArrayList<Item> getItemList() throws RemoteException {
		return itemList.getItemList();
	}

	@Override public ArrayList<User> getUserList() throws RemoteException {
		return itemList.getUserList();
	}

	@Override public Item getItem(String itemName) throws RemoteException {
		return itemList.getItem(itemName);
	}

	@Override public User getUser(String username) throws RemoteException {
		return itemList.getUser(username);
	}

	@Override
	public void propertyChange(ObserverEvent<Item, Integer> event) throws RemoteException {
		System.out.println(event.getValue2());
	}

	@Override
	public boolean addListener(GeneralListener<Item, Item> listener, String... propertyNames) throws RemoteException {
		return property.addListener(listener, propertyNames);
	}

	@Override
	public boolean removeListener(GeneralListener<Item, Item> listener, String... propertyNames) throws RemoteException {
		return property.removeListener(listener, propertyNames);
	}
}
