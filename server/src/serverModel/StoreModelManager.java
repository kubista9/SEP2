package serverModel;
import shared.Item;
import shared.ItemList;
import shared.Password;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class StoreModelManager implements CoreModel
{

	@Override public void registerUser (User user)
    {
        user.register();
    }

    @Override public void addItemToSell (User user, Item item)
    {
        user.addItemToSell(item);
    }

    @Override public void postItem (User user, Item item)
    {
        user.postItem(item);
    }

    @Override public void addQuantity (User user, String itemName, int quantity)
    {
        user.addQuantity(itemName, quantity);
    }

    @Override public void addToBasket (User user, Item item)
    {
        user.addToBasket(item);
    }

    @Override public Item buyItem (User user, String itemName, int quantity)
    {
        return user.buyItem(itemName, quantity);
    }

    @Override public ItemList buyItems (User user, ItemList items)
    {
        return user.buyItems(items);
    }

    @Override public ItemList buyBasket (User user)
    {
        return user.buyBasket();
    }

    @Override public void setUserPassword (User user, Password password)
    {
        user.setPassword(password);
    }

    @Override public void setUserEmail (User user, String email)
    {
        user.setEmail(email);
    }

    @Override public ArrayList<Item> getItemList()
    {
        return GlobalItemList.getInstance().getList();
    }

    @Override public ArrayList<User> getUserList()
    {
        return GlobalUserList.getInstance().getList();
    }

    @Override public Item getItem (String itemName)
    {
        return GlobalItemList.getInstance().getItem(itemName);
    }

    @Override public User getUser (String username)
    {
        return GlobalUserList.getInstance().getUser(username);
    }

	@Override
	public void addListener(PropertyChangeListener listener) {

	}

	@Override
	public void removeListener(PropertyChangeListener listener) {

	}
}
