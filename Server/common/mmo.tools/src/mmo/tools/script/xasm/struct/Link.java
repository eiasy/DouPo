package mmo.tools.script.xasm.struct;
import java.util.Vector;

/**
 * Author: cuirongzhou
 * Date: 2007-10-29
 * Time: 15:22:23
 */
public class Link
{
    private Vector vec;
    private int size;
    public Link()
    {
        vec = new Vector();
    }

    public int addNode(Object obj)
    {
        vec.add(obj);
        size++;
        return size - 1;
    }

    public Object getNode(int index)
    {
        return vec.get(index);
    }

    public int getSize()
    {
        return size;
    }
}
