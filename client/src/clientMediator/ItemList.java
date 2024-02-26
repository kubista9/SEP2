package clientMediator;

import shared.Item;

import java.util.ArrayList;

public class ItemList
{
    private ArrayList<Item> items;

    public ItemList()
    {
        items = new ArrayList<>();
    }

    public void add (Item item)
    {
        items.add(item);
    }

    public void remove (Item item)
    {
        for (Item i : items)
        {
            if (i.equals(item))
            {
                items.remove(i);
            }
        }
    }

    public void remove (String id)
    {
        for (Item i : items)
        {
            if (i.getId().equals(id))
            {
                items.remove(i);
            }
        }
    }

    public Item get (Item item)
    {
        for (Item i : items)
        {
            if (i.equals(item))
            {
                return i;
            }
        }
        return null;
    }

    public Item get (String id)
    {
        for (Item i : items)
        {
            if (i.getId().equals(id))
            {
                return i;
            }
        }
        return null;
    }

    public ArrayList<Item> getAll()
    {
        return items;
    }
}
