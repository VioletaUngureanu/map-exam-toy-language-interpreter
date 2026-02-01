package controller;


import model.programState.ProgramState;

import java.util.List;

public interface IController {

    void allStepsExecution();


    List<ProgramState> getPrograms();

    List<ProgramState> removeCompletedPrograms(List<ProgramState> inProgramList);
    void oneStepForAllPrograms(List<ProgramState> programList) throws InterruptedException;
    void oneStepGui();
    List<String> getExecutionLog();
}
