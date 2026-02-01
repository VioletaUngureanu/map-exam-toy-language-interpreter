package repository;

import exceptions.FileOperationException;
import exceptions.TextFileException;
import model.programState.ProgramState;
import exceptions.InvalidProgram;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ListRepository implements IRepository {
    private final java.util.List<ProgramState> programStates = new ArrayList<ProgramState>();
    private int currentProgramIndex = -1;
    String logFilePath;
    private final List<String> loggedLines = new ArrayList<>();

    public ListRepository(String logFilePath ) {
        this.logFilePath = logFilePath;
    }
    public ListRepository( ) {
        this.logFilePath = "log.txt";
    }


    @Override
    public void addProgram(ProgramState programState) {
        programStates.add(programState);
        currentProgramIndex++;
    }

    @Override
    public List<ProgramState> getPrograms() {
        return programStates;
    }



    @Override
    public synchronized void logProgramState(ProgramState programState) {
        if (this.logFilePath != null) {
            PrintWriter logFile = null;
            try
            {
                logFile = new PrintWriter(new BufferedWriter(
                        new FileWriter(logFilePath, true)));

            }
            catch (IOException e)
            {
                throw new FileOperationException("Could not open log file: " + logFilePath);
            }
            loggedLines.add(programState.toString());
            logFile.println(programState.toString());
            logFile.close();
        }
    }

    @Override
    public List<ProgramState> getProgramList() {
        return programStates;
    }

    @Override
    public void setProgramList(List<ProgramState> programList) {
        programStates.clear();
        programStates.addAll(programList);
    }

    @Override
    public List<String> getLoggedLines() {
        return loggedLines;
    }
}
