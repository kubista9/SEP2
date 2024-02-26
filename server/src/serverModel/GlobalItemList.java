package serverModel;

import shared.Item;

import java.util.ArrayList;

public class GlobalItemList
{
    private static GlobalItemList instance;
    private static Object lock = new Object();
    private ArrayList<Item> list;

    private GlobalItemList()
    {
        list = new ArrayList<>();
    }

    public static GlobalItemList getInstance()
    {
        if (instance == null)
        {
            synchronized (lock)
            {
                if (instance == null)
                {
                    instance = new GlobalItemList();
                }
            }
        }
        return instance;
    }

    public ArrayList<Item> getList()
    {
        return list;
    }

    public void addItem (Item item)
    {
        for (Item i : list)
        {
            if (i.getName().equals(item.getName()))
            {
                throw new IllegalArgumentException("shared.Item with this name already exists");
            }
        }
        list.add(item);
    }

    public Item getItem (String itemName)
    {
        if (itemName.isBlank())
        {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        for (Item i : list)
        {
            if (i.getName().equals(itemName))
            {
                return i;
            }
        }
        return null;
    }

    public void removeItem (Item item)
    {
        boolean removed = false;
        if (item == null)
        {
            throw new IllegalArgumentException("shared.Item cannot be null");
        }
        for (Item i : list)
        {
            if (i.equals(item))
            {
                list.remove(i);
                removed = true;
                break;
            }
        }
        if (!removed)
        {
            throw new IllegalArgumentException("shared.Item does not exist");
        }
    }

    public void removeItem (String itemName)
    {
        boolean removed = false;
        if (itemName.isBlank())
        {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        for (Item i : list)
        {
            if (i.getName().equals(itemName))
            {
                list.remove(i);
                removed = true;
                break;
            }
        }
        if (!removed)
        {
            throw new IllegalArgumentException("shared.Item does not exist");
        }
    }

    public void increaseQuantity (String itemName, int quantity)
    {
        if (itemName.isBlank())
        {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        else if (quantity <= 0)
        {
            throw new IllegalArgumentException("Quantity has to be positive");
        }
        getItem(itemName).addQuantity(quantity);
    }

    public void decreaseQuantity (String itemName, int quantity)
    {
        if (itemName.isBlank())
        {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        else if (quantity <= 0)
        {
            throw new IllegalArgumentException("Quantity has to be positive");
        }
        Item item = getItem(itemName);
        item.removeQuantity(quantity);
        if (item.getQuantity() <= 0)
        {
            list.remove(item);
        }
    }
}
