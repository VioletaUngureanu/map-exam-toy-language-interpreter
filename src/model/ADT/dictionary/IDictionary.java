package model.ADT.dictionary;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

public interface IDictionary<TKey, TValue> {
    void add(TKey key, TValue value);
    TValue getValueForKey(TKey key);
    boolean isDefined(TKey key);
    void remove(TKey key);
    IDictionary<TKey, TValue> deepCopy();
    String toStringKeys();
    boolean containsKey(TKey key);

    void clear();

    Map<TKey,TValue> getContent();

    Collection<TValue> getAllValues();

    Set<TKey> getAllKeys();
}


