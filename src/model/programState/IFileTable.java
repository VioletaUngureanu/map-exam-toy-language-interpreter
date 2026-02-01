package model.programState;

import model.value.StringValue;

import java.io.BufferedReader;

public interface IFileTable {
    boolean isDefined(StringValue filename);
    void add(StringValue filename, BufferedReader descriptor);

    BufferedReader getBufferedReader(StringValue stringValue);

    void removeEntry(StringValue stringValue);

    boolean isOpened(String fileName);

    BufferedReader getFile(String fileName);
}


