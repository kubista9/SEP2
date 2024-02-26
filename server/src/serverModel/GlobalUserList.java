package serverModel;

import java.util.ArrayList;

public class GlobalUserList
{
    private static GlobalUserList instance;
    private static Object lock = new Object();
    private ArrayList<User> list;

    private GlobalUserList()
    {
        list = new ArrayList<>();
    }

    public static GlobalUserList getInstance()
    {
        if (instance == null)
        {
            synchronized (lock)
            {
                if (instance == null)
                {
                    instance = new GlobalUserList();
                }
            }
        }
        return instance;
    }

    public ArrayList<User> getList()
    {
        return list;
    }

    public void addUser (User user)
    {
        for (User u : list)
        {
            if (u.getUsername().equals(user.getUsername()))
            {
                throw new IllegalArgumentException("Server.src.serverModel.User with this username already exists");
            }
        }
        list.add(user);
    }

    public User getUser (String username)
    {
        if (username.isBlank())
        {
            throw new IllegalArgumentException("Username cannot be empty");
        }
        for (User u : list)
        {
            if (u.getUsername().equals(username))
            {
                return u;
            }
        }
        return null;
    }

    public void removeUser (User user)
    {
        boolean removed = false;
        if (user == null)
        {
            throw new IllegalArgumentException("Server.src.serverModel.User cannot be null");
        }
        for (User u : list)
        {
            if (u.equals(user))
            {
                list.remove(u);
                removed = true;
                break;
            }
        }
        if (!removed)
        {
            throw new IllegalArgumentException("Server.src.serverModel.User does not exist");
        }
    }

    public void removeUser (String username)
    {
        boolean removed = false;
        if (username.isBlank())
        {
            throw new IllegalArgumentException("Username cannot be empty");
        }
        for (User u : list)
        {
            if (u.getUsername().equals(username))
            {
                list.remove(u);
                removed = true;
                break;
            }
        }
        if (removed)
        {
            throw new IllegalArgumentException("Server.src.serverModel.User does not exist");
        }
    }
}
