package shared;

import serverModel.User;

import java.io.Serializable;
import java.util.UUID;


public class Item implements Serializable
{
    private String id;
    private String name;
    private double price;
    private int quantity;
    private String description;
    private User owner;

    public Item(String name, double price, int quantity, String description, User owner)
    {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
        this.owner = owner;
        this.id = UUID.randomUUID().toString();
    }

    public String getName()
    {
        return name;
    }

    public double getPrice()
    {
        return price;
    }

    public int getQuantity()
    {
        return quantity;
    }

    public String getDescription()
    {
        return description;
    }

    public String getId()
    {
        return id;
    }

    public User getOwner()
    {
        return owner;
    }

    public void addQuantity (int quantity)
    {
        this.quantity += quantity;
    }

    public void removeQuantity (int quantity)
    {
        this.quantity -= quantity;
        if (this.quantity < 0)
        {
            this.quantity = 0;
        }
    }

    public void changeOwner (User owner)
    {
        this.owner = owner;
    }

    public String toString()
    {
        return "Id: " + id + ", Name: " + name + ", Price: " + price + ", Quantity: " + quantity + ", Description: " + description + ", Owner: " + owner;
    }

    public boolean equals (Object obj)
    {
        if (!(obj instanceof Item))
        {
            return false;
        }
        Item item = (Item)obj;
        return id.equals(item.id) && name.equals(item.name) && price == item.price && quantity == item.quantity && description.equals(item.description) && owner.equals(item.owner);
    }
}
