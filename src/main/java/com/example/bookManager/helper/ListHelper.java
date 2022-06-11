package com.example.bookManager.helper;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ListHelper
{
    public static volatile ListHelper _instance;

    /**
     * Return a singleton instance of CredentialsHelper.
     */
    public static ListHelper getInstance()
    {
        // Double lock for thread safety.
        if (_instance == null)
        {
            synchronized (ListHelper.class)
            {
                if (_instance == null)
                {
                    _instance = new ListHelper();
                }
            }
        }
        return _instance;
    }

    public <T> List<T>
    getListFromIterator(Iterator<T> iterator)
    {

        // Create an empty list
        List<T> list = new ArrayList<>();

        // Add each element of iterator to the List
        iterator.forEachRemaining(list::add);

        // Return the List
        return list;
    }
}
