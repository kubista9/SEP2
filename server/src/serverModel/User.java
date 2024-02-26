package serverModel;

import shared.Item;
import shared.ItemList;
import shared.MyDate;
import shared.Password;

/*
    GUEST VS REGISTERED USER
    Guest - user that does not figure on the UserList
    Registered user - user that figures on the UserList
<<<<<<<< HEAD:SEP2-Project/Server/src/serverModel/User.java
    - If a Server.src.serverModel.User is created as a guest, he can be later elevated to registered user by calling register().
========
    - If a shared.User is created as a guest, he can be later elevated to registered user by calling register().
>>>>>>>> 5186b801f55481fe004ca0ef22409a80839d2ebd:SEP2-Project/Shared/src/shared/User.java
    - A guest cannot post or buy items. If he wants to buy them, he has to register first.
    - A registered user has full rights, and he can buy/sell items.
 */
public class User
{
    private String username;
    private Password password;
    private String email;
    private MyDate registrationDate;
    private ItemList itemsToSell;
    private ItemList basket;
    private ItemList boughtItems;
    private boolean isGuest;

    public User (String username, Password password, String email, MyDate registrationDate, boolean isGuest)
    {
        if (username == null || password == null)
        {
            throw new IllegalArgumentException("Username or password is null");
        }
        else if (username.length() < 3)
        {
            throw new IllegalArgumentException("Username must have at least 3 characters");
        }
        this.username = username;
        this.password = password;
        this.email = email;
        this.registrationDate = registrationDate;
        this.itemsToSell = new ItemList();
        this.basket = new ItemList();
        this.boughtItems = new ItemList();
        this.isGuest = isGuest;
        if (!isGuest)
        {
            GlobalUserList.getInstance().addUser(this);
        }
        Log.getInstance().logUserCreation(this);
    }

    public User (String username, Password password, String email, boolean isGuest)
    {
        this (username, password, email, new MyDate(), isGuest);
    }

    public User (String username, Password password, String email)
    {
        this (username, password, email, new MyDate(), false);
    }

    public User (String username, Password password, boolean isGuest)
    {
        this (username, password, null, new MyDate(), isGuest);
    }

    public User (String username, Password password)
    {
        this (username, password, null, new MyDate(), false);
    }

    public void register()
    {
        if (isGuest)
        {
            GlobalUserList.getInstance().addUser(this);
            Log.getInstance().logUserRegistration(this);
        }
        else
        {
            throw new IllegalStateException("Server.src.serverModel.User already registered");
        }
    }

    public void addItemToSell (Item item)
    {
        if (!isGuest)
        {
            itemsToSell.add(item);
            Log.getInstance().logNewItem(this, item);
        }
        else
        {
            throw new IllegalStateException("Guest cannot add items to sell");
        }
    }

    public void postItem (Item item)
    {
        if (!isGuest)
        {
            for (Item i : itemsToSell.getAll())
            {
                if (i.getName().equals(item.getName()) && i.getQuantity() > 0)
                {
                    GlobalItemList.getInstance().addItem(i);
                    Log.getInstance().logItemPosting(this, item);
                }
            }
        }
        else
        {
            throw new IllegalStateException("Guest cannot post items");
        }
    }

    public void addQuantity (String itemName, int quantity)
    {
        if (!isGuest)
        {
            Item i = GlobalItemList.getInstance().getItem(itemName);
            i.addQuantity(quantity);
            Log.getInstance().logQuantityAddition(this, i, quantity);
        }
        else
        {
            throw new IllegalStateException("Guest cannot perform this operation");
        }
    }

    public void addToBasket (Item item)
    {
        if (!isGuest)
        {
            Item i = GlobalItemList.getInstance().getItem(item.getName());
            basket.add(i);
            Log.getInstance().logBasketAddition(this, i);
        }
        else
        {
            throw new IllegalStateException("Guest cannot add to basket");
        }
    }

    public synchronized Item buyItem (String itemName, int quantity)
    {
        if (!isGuest)
        {
            Item i = GlobalItemList.getInstance().getItem(itemName);
            int resultQuantity = i.getQuantity() - quantity;
            if (resultQuantity < 0)
            {
                throw new IllegalStateException("Attempt to purchase more item copies than exist");
            }
            else
            {
                GlobalItemList.getInstance().decreaseQuantity(itemName, quantity);
            }
            i.changeOwner(this);
            Item boughtItem = new Item(i.getName(), i.getPrice(), quantity, i.getDescription(), i.getOwner());
            Log.getInstance().logPurchase(this, boughtItem);
            boughtItems.add(boughtItem);
            return boughtItem;
        }
        else
        {
            throw new IllegalStateException("Guest cannot buy items");
        }
    }

    public ItemList buyItems (ItemList items)
    {
        if (!isGuest)
        {
            ItemList boughtNowList = new ItemList();
            for (Item i : items.getAll())
            {
                Item boughtNowItem = buyItem(i.getName(), i.getQuantity());
                boughtNowList.add(boughtNowItem);
            }
            return boughtNowList;
        }
        else
        {
            throw new IllegalStateException("Guest cannot buy items");
        }
    }

    public ItemList buyBasket()
    {
        return buyItems(basket);
    }

    public String getUsername()
    {
        return username;
    }

    public Password getPassword()
    {
        return password;
    }

    public String getEmail()
    {
        return email;
    }

    public MyDate getRegistrationDate()
    {
        return registrationDate;
    }

    public ItemList getItemsToSell()
    {
        return itemsToSell;
    }

    public ItemList getBasket()
    {
        return basket;
    }

    public ItemList getBoughtItems()
    {
        return boughtItems;
    }

    public boolean isGuest()
    {
        return isGuest;
    }

    public void setPassword (Password password)
    {
        this.password = password;
        Log.getInstance().logPasswordChange(this, password.getPassword());
    }

    public void setEmail (String email)
    {
        this.email = email;
        Log.getInstance().logEmailChange(this, email);
    }

    public String toString()
    {
        return "Username: " + username + ", shared.Password: " + password + ", Email: " + email + ", RegistrationDate: " + registrationDate + ", ItemsToSell: " + itemsToSell + ", Basket: " + basket + ", BoughtItems: " + boughtItems;
    }

    public boolean equals (Object obj)
    {
        if (!(obj instanceof User))
        {
            return false;
        }
       	User user = (User)obj;
        return username.equals(user.username) && password.equals(user.password) && email.equals(user.email) && registrationDate.equals(user.registrationDate) && itemsToSell.equals(user.itemsToSell) && basket.equals(user.basket) && boughtItems.equals(user.boughtItems);
    }
}
