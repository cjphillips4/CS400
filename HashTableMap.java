// --== CS400 File Header Information ==--
// Name: Connor Phillips
// Email: cjphilips4@wisc.edu
// Team: Red
// Group: HF
// TA: Hang
// Lecturer: Florian
// Notes to Grader: NA
import java.util.LinkedList;
import java.util.NoSuchElementException;
public class HashTableMap<KeyType, ValueType> implements MapADT<KeyType, ValueType>
{
    private int capacity;
    private int size;
    private LinkedList[] list;

    public HashTableMap(int capacity)
    {
        this.capacity=capacity;
        this.size=0;
        list=new LinkedList[capacity];
    }

    public HashTableMap()
    {
        this.capacity=30;
        this.size=0;
        list=new LinkedList[capacity];
    }

    public boolean put(KeyType key,ValueType value)
    {
        if(key==null || containsKey(key))
            return false;
        int code=key.hashCode();
        if(code<0)
            code*=-1;
        code=code%capacity;
        //If this key value is the first to start a link list
        if(list[code]==null)
        {
            list[code]=new LinkedList<KeyValuePair>();
            list[code].add(new KeyValuePair(key,value));
            size++;
            //Checks resize
            if (resizeNeeded())
                resize();

            return true;
        }
        //If a link list has already been started
        else if(list[code]!=null)
        {
            list[code].addLast(new KeyValuePair(key,value));
            size++;
            //Checks resize
            if (resizeNeeded())
                resize();

            return true;
        }
        return false;
    }

    public int size()
    {
        return size;   
    }

    public boolean containsKey(KeyType key)
    {
        int code=key.hashCode();
        if(code<0)
            code*=-1;
        code=code%capacity;
        if(list[code]!=null)
        {
            KeyValuePair x=null;
            for(int i=0;i<list[code].size();i++)
            {
                x=(KeyValuePair)list[code].get(i);
                if(x.getKey().equals(key))
                    return true;
            }
        }
        return false;
    }

    public ValueType get(KeyType key) 
    {
        //Finds hash index
        int code=key.hashCode();
        if(code<0)
            code*=-1;
        code%=capacity;
        //Checks this linked list isnt null
        if(list[code]!=null)
        {
            KeyValuePair x=null;
            //Searches for key matches
            for(int i=0;i<list[code].size();i++)
            {
                x=(KeyValuePair)list[code].get(i);
                if(x.getKey().equals(key))
                    return (ValueType)x.getValue();
            }
        }
        throw new NoSuchElementException("The element does no exist");
    }

    public ValueType remove(KeyType key)
    {
        //Finds hash index
        int code=key.hashCode();
        if(code<0)
            code*=-1;
        code%=capacity;
        //Check index Linked List isnt null
        if(list[code]!=null)
        {
            KeyValuePair x=null;
            //Looks for matches
            for(int i=0;i<list[code].size();i++)
            {
                x=(KeyValuePair)list[code].get(i);
                if(x.getKey().equals(key))
                {
                    ValueType end=(ValueType)list[code].get(i); 
                    list[code].remove(i);
                    size--;
                    return end;
                }
            }
        }
        return null;
    }

    public void clear()
    {
        size=0;
        list=new LinkedList[capacity];
    }

    private boolean resizeNeeded()
    {
        if(((double)size/capacity)>=0.85)
            return true;
        return false;
    }


    private void resize()
    {
        //Creates new list with double capacity and stores old list in shallow copy
        LinkedList[] oldList=list;
        capacity*=2;
        list=new LinkedList[capacity];
        size=0;
        //Goes through the list rehashing every item into the new list
        for(int i=0;i<oldList.length;i++)
        {
            if(oldList[i]!=null)
            {
                for(int j=0;j<oldList[i].size();j++)
                {
                    KeyValuePair insert=(KeyValuePair)oldList[i].get(j);
                    put((KeyType)insert.getKey(),(ValueType)insert.getValue());
                }
            }
        }
    }
}