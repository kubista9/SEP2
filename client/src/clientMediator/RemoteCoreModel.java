package clientMediator;

import serverModel.User;
import shared.Item;
import shared.ItemList;
import shared.Password;
import utility.observer.subject.RemoteSubject;

import java.rmi.RemoteException;
import java.util.ArrayList;

public interface RemoteCoreModel extends RemoteSubject<Item, Item>
{
    void registerUser (User user) throws RemoteException;
    void addItemToSell (User user, Item item) throws RemoteException;
    void postItem (User user, Item item) throws RemoteException;
    void addQuantity (User user, String itemName, int quantity) throws RemoteException;
    void addToBasket (User user, Item item) throws RemoteException;
    Item buyItem (User user, String itemName, int quantity) throws RemoteException;
    shared.ItemList buyItems (User user, shared.ItemList items) throws RemoteException;
    ItemList buyBasket (User user) throws RemoteException;
    void setUserPassword (User user, Password password) throws RemoteException;
    void setUserEmail (User user, String email) throws RemoteException;
    ArrayList<Item> getItemList() throws RemoteException;
	ArrayList<User> getUserList() throws RemoteException;
    Item getItem(String itemName) throws RemoteException;
    User getUser(String username) throws RemoteException;
}
