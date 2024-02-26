package clientModel;

import clientMediator.Client;
import serverModel.User;
import shared.Item;
import shared.ItemList;
import shared.Password;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class StoreModelManager implements CoreModel, PropertyChangeListener
{
    private Client client;
    private PropertyChangeSupport support;

    public StoreModelManager (Client client)
    {
        this.client = client;
    }

    @Override public void registerUser (User user)
    {
		try {
			client.registerUser(user);
		} catch (RemoteException e) {
			throw new RuntimeException(e);
		}
	}

    @Override public void addItemToSell (User user, Item item)
    {
		try {
			client.addItemToSell(user, item);
		} catch (RemoteException e) {
			throw new RuntimeException(e);
		}
	}

    @Override public void postItem (User user, Item item)
    {
		try {
			client.postItem(user, item);
		} catch (RemoteException e) {
			throw new RuntimeException(e);
		}
	}

    @Override public void addQuantity (User user, String itemName, int quantity)
    {
		try {
			client.addQuantity(user, itemName, quantity);
		} catch (RemoteException e) {
			throw new RuntimeException(e);
		}
	}

    @Override public void addToBasket (User user, Item item)
    {
		try {
			client.addToBasket(user, item);
		} catch (RemoteException e) {
			throw new RuntimeException(e);
		}
	}

    @Override public Item buyItem (User user, String itemName, int quantity)
    {
		try {
			return client.buyItem(user, itemName, quantity);
		} catch (RemoteException e) {
			throw new RuntimeException(e);
		}
	}

    @Override public ItemList buyItems (User user, ItemList items)
    {
		try {
			return client.buyItems(user, items);
		} catch (RemoteException e) {
			throw new RuntimeException(e);
		}
	}

    @Override public shared.ItemList buyBasket (User user)
    {
		try {
			return client.buyBasket(user);
		} catch (RemoteException e) {
			throw new RuntimeException(e);
		}
	}

    @Override public void setUserPassword (User user, Password password)
    {
		try {
			client.setUserPassword(user, password);
		} catch (RemoteException e) {
			throw new RuntimeException(e);
		}
	}

    @Override public void setUserEmail (User user, String email)
    {
		try {
			client.setUserEmail(user, email);
		} catch (RemoteException e) {
			throw new RuntimeException(e);
		}
	}

    @Override public ArrayList<Item> getItemList()
    {
		try {
			return client.getItemList();
		} catch (RemoteException e) {
			throw new RuntimeException(e);
		}
	}

    @Override public ArrayList<User> getUserList()
    {
		try {
			return client.getUserList();
		} catch (RemoteException e) {
			throw new RuntimeException(e);
		}
	}

    @Override public Item getItem (String itemName)
    {
		try {
			return client.getItem(itemName);
		} catch (RemoteException e) {
			throw new RuntimeException(e);
		}
	}

    @Override public User getUser (String username)
    {
		try {
			return client.getUser(username);
		} catch (RemoteException e) {
			throw new RuntimeException(e);
		}
	}

    @Override public void propertyChange(PropertyChangeEvent evt) {
        // Temporary
        System.out.println("<StoreModelManager> Event: " + evt.getPropertyName());
    }

    @Override public void addListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    @Override public void removeListener(PropertyChangeListener listener) {
        support.removePropertyChangeListener(listener);
    }
}
