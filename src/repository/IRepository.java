package repository;

import model.programState.ProgramState;

import java.util.List;

public interface IRepository {

    void addProgram(ProgramState programState);
    List<ProgramState> getPrograms();

    void logProgramState(ProgramState programState);
    List<ProgramState> getProgramList();
    void setProgramList(List<ProgramState> programList);
    List<String> getLoggedLines();

}