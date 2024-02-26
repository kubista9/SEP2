package clientModel;

import serverModel.User;
import shared.Item;
import shared.ItemList;
import shared.Password;
import utility.observer.javaobserver.UnnamedPropertyChangeSubject;

import java.util.ArrayList;

public interface CoreModel extends UnnamedPropertyChangeSubject {
    void registerUser (User user);
    void addItemToSell (User user, Item item);
    void postItem (User user, Item item);
    void addQuantity (User user, String itemName, int quantity);
    void addToBasket (User user, Item item);
    Item buyItem (User user, String itemName, int quantity);
    ItemList buyItems (User user, ItemList items);
    shared.ItemList buyBasket (User user);
    void setUserPassword (User user, Password password);
    void setUserEmail (User user, String email);
    ArrayList<Item> getItemList();
    ArrayList<User> getUserList();
    Item getItem(String itemName);
    User getUser(String username);
}
