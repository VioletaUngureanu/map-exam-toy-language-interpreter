package model.ADT.dictionary;

import java.util.Collection;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class Dictionary<TKey, TValue> implements IDictionary<TKey, TValue> {

    private final ConcurrentHashMap<TKey, TValue> map;

    public Dictionary() {
        this.map = new ConcurrentHashMap<>();
    }

    @Override
    public void add(TKey tKey, TValue tValue) {
        map.put(tKey, tValue);
    }

    @Override
    public TValue getValueForKey(TKey tKey) {
        return map.get(tKey);
    }

    @Override
    public boolean isDefined(TKey tKey) {
        return map.containsKey(tKey);
    }

    @Override
    public void remove(TKey tKey) {
        map.remove(tKey);
    }

    @Override
    public IDictionary<TKey, TValue> deepCopy() {
        Dictionary<TKey, TValue> newDictionary = new Dictionary<>();
        for (TKey key : map.keySet()) {
            newDictionary.add(key, map.get(key));
    }
        return newDictionary;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (TKey key : map.keySet()) {
            sb.append(key.toString()).append("->").append(map.get(key).toString()).append("\n");
        }

        return sb.toString();
    }
    @Override
    public String toStringKeys()
    {
        StringBuilder sb = new StringBuilder();

        for (TKey key : map.keySet()) {
            sb.append(key.toString()).append("\n");
        }

        return sb.toString();
    }

    @Override
    public boolean containsKey(TKey tKey) {
        return map.containsKey(tKey);
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public ConcurrentHashMap<TKey, TValue> getContent() {
        return map;
    }

    @Override
    public Collection<TValue> getAllValues() {
        return map.values();
    }

    @Override
    public Set<TKey> getAllKeys() {
        return map.keySet();
    }

}
