package model.ADT.list;
import java.util.concurrent.CopyOnWriteArrayList;
public class List<T> implements IList<T> {
    private CopyOnWriteArrayList<T> list;

    public List() {
        this.list = new CopyOnWriteArrayList<T>();
    }

    @Override
    public void add(T item) {
        list.add(item);
    }

    @Override
    public T get(int index) {
        return list.get(index);
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public void remove(int index) {
        list.remove(index);
    }

    @Override
    public IList<T> deepCopy() {
       List<T> newList = new List<T>();
         for (T item : this.list) {
                newList.add(item);
         }
            return newList;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (T item : list) {
            sb.append(item.toString()).append("\n");
        }
        return sb.toString();
    }
}
