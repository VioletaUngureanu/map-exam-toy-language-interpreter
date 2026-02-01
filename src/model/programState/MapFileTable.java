package model.programState;

import model.ADT.dictionary.Dictionary;
import model.ADT.dictionary.IDictionary;
import model.value.StringValue;

import java.io.BufferedReader;
import java.io.FileDescriptor;

public class MapFileTable implements IFileTable{
    private static final IDictionary<StringValue, BufferedReader> fileTable = new Dictionary<>();

    @Override
    public synchronized boolean isDefined(StringValue filename) {
        return fileTable.isDefined(filename);
    }

    @Override
    public synchronized void add(StringValue filename, BufferedReader descriptor) {
        fileTable.add(filename, descriptor);
    }

    @Override
    public synchronized BufferedReader getBufferedReader(StringValue stringValue) {
        return fileTable.getValueForKey(stringValue);
    }

    @Override
    public synchronized void removeEntry(StringValue stringValue) {
        fileTable.remove(stringValue);

    }

    @Override
    public synchronized boolean isOpened(String fileName) {
        return fileTable.isDefined(new StringValue(fileName));
    }

    @Override
    public synchronized BufferedReader getFile(String fileName) {
        return  fileTable.getValueForKey(new StringValue(fileName));
    }
    @Override
    public synchronized String toString(){
        return fileTable.toStringKeys();
    }
}
