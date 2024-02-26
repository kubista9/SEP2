package serverModel;

import shared.Item;

public class Log
{
    private static Log instance;
    private static Object lock = new Object();

    private Log (){}

    public static Log getInstance()
    {
        if (instance == null)
        {
            synchronized (lock)
            {
                if (instance == null)
                {
                    instance = new Log();
                }
            }
        }
        return instance;
    }

    public void logUserCreation (User user)
    {
        System.out.println("New user created: " + user);
    }

    public void logUserRegistration (User user)
    {
        System.out.println(user.getUsername() + " registered");
    }

    public void logNewItem (User user, Item item)
    {
        System.out.println(user.getUsername() + " added new item: " + item);
    }

    public void logItemPosting (User user, Item item)
    {
        System.out.println(user.getUsername() + " posted item '" + item.getName() + "'");
    }

    public void logQuantityAddition (User user, Item item, int quantity)
    {
        System.out.println(user.getUsername() + " added " + quantity + " of item '" + item.getName() + "'");
    }

    public void logBasketAddition (User user, Item item)
    {
        System.out.println(user.getUsername() + " added item '" + item.getName() + "' to basket");
    }

    public void logPurchase (User user, Item item)
    {
        System.out.println(user.getUsername() + " bought item '" + item.getName() + "', quantity: " + item.getQuantity());
    }

    public void logEmailChange (User user, String newEmail)
    {
        System.out.println(user.getUsername() + " changed email to '" + newEmail + "'");
    }

    public void logPasswordChange (User user, String newPassword)
    {
        System.out.println(user.getUsername() + " changed password to '" + newPassword + "'");
    }
}
