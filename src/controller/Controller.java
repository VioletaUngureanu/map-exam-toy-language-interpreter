package controller;


import exceptions.*;
import model.programState.ProgramState;
import model.value.ReferenceValue;
import repository.IRepository;
import model.value.IValue;

import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class Controller implements IController {
    private final IRepository repository;

    private ExecutorService executorService;

    public Controller(IRepository repository) {
        this.repository = repository;


    }

    private void loadProcedures()
    {

    }
    public Map<Integer, IValue> unsafeGarbageCollector(
            List<Integer> symTableAddresses,
            Map<Integer, IValue> heap)  {
        /*
         * acest garbage collector nu e sigur, fiindca pastreaza doar adresele care apar in Symbol table
         * dar in reap pot exista referinte catre alte adrese, "ascunse" in interiorul referintelor din heap
         * daca v este o referinta care pointeaza la adresa 1 si a pointeaza v, in cazul in care
         * modificam v, astfel incat sa pointeze la adresa 3, adresa 1 devine inaccesibila,
         * desi a inca pointeaza la ea
         * ex: Ref int v;new(v,20);Ref Ref int a; new(a,v); new(v,30);print(rH(rH(a)))
         *
         */
        return heap.entrySet().stream()
                .filter(e -> symTableAddresses.contains(e.getKey()))
                .collect(java.util.stream.Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public Map<Integer, IValue> safeGarbageCollector(
            List<Integer> symTableAddresses,
            Map<Integer, IValue> heap
    )
    {
        Set<Integer> reachableAddresses = new HashSet<>(symTableAddresses);
        boolean changed;
        do {
            changed = false;
            Set<Integer> current = new HashSet<>(reachableAddresses);
            for (Integer address : current) {
                IValue value = heap.get(address);
                if (value instanceof ReferenceValue refValue) {
                    int innerAddress = refValue.heapAddress();
                    if (!reachableAddresses.contains(innerAddress)) {
                        reachableAddresses.add(innerAddress);
                        changed = true;
                    }
                }
            }
        }while (changed);
        return heap.entrySet().stream()
                .filter(e -> reachableAddresses.contains(e.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
    public List<Integer> getAddressesFromSymbolTable(Collection<IValue> symbolTableValues) {
        return symbolTableValues.stream()
                .filter(v -> v instanceof model.value.ReferenceValue)
                .map(v -> {
                    ReferenceValue v1 = (ReferenceValue) v;
                    return v1.heapAddress();
                })
                .collect(Collectors.toList());
    }



    @Override
    public void allStepsExecution() throws StackEmptyException {

        executorService = Executors.newFixedThreadPool(2);
        List<ProgramState> programList = removeCompletedPrograms(repository.getPrograms());
        while (!programList.isEmpty()) {

            try {
                oneStepForAllPrograms(programList);
            } catch (InterruptedException e) {
                throw new MyExecutionException("Execution interrupted: " + e.getMessage());
            }
            programList = removeCompletedPrograms(repository.getPrograms());
            if (!programList.isEmpty()) {
                ProgramState firstPrg = programList.get(0);
                var heap = firstPrg.heap();

                List<Integer> addresses = getAddressesFromAllSymbolTables(programList);

                heap.setContent(safeGarbageCollector(
                        addresses,
                        heap.getContent()
                ));
            }
        }

        executorService.shutdownNow();
        repository.setProgramList(programList);

    }


    @Override
    public List<ProgramState> getPrograms() {
        return repository.getPrograms();
    }



    @Override
    public List<ProgramState> removeCompletedPrograms(List<ProgramState> inProgramList) {
        return inProgramList.stream()
                .filter(ProgramState::isNotCompleted)
                .collect(Collectors.toList());
    }

    @Override
    public void oneStepForAllPrograms(List<ProgramState> programList) throws InterruptedException {
        programList.forEach(repository::logProgramState);
        List<Callable<ProgramState>> callList = programList.stream()
                .map((ProgramState programState) -> (Callable<ProgramState>)(programState::oneStep))
                .collect(Collectors.toList());
        List<ProgramState> newProgramList = executorService.invokeAll(callList).stream()
                .map(future -> {try {return future.get();}
                catch (InterruptedException | ExecutionException
                        e) {throw new MyExecutionException(e.getMessage());
                }
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        programList.addAll(newProgramList);

        programList.forEach(repository::logProgramState);
        repository.setProgramList(programList);
    }

    private List<Integer> getAddressesFromAllSymbolTables(List<ProgramState> programs) {
        return programs.stream()
                .map(programState -> programState.symbolTable().getAllValues())
                .map(this::getAddressesFromSymbolTable)
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }


    public void oneStepGui() {

        if (executorService == null || executorService.isShutdown()) {
            executorService = Executors.newFixedThreadPool(2);
        }

        List<ProgramState> programList = removeCompletedPrograms(repository.getPrograms());
        if (programList.isEmpty()) {
            repository.setProgramList(programList);
            return;
        }

        try {
            oneStepForAllPrograms(programList);
        } catch (InterruptedException e) {
            throw new MyExecutionException("Execution interrupted: " + e.getMessage());
        }


        programList = removeCompletedPrograms(repository.getPrograms());


        if (!programList.isEmpty()) {
            ProgramState first = programList.get(0);
            var heap = first.heap();
            List<Integer> addresses = getAddressesFromAllSymbolTables(programList);
            heap.setContent(safeGarbageCollector(addresses, heap.getContent()));
        }

        repository.setProgramList(programList);

        if (programList.isEmpty()) {
            executorService.shutdownNow();
        }
    }

    @Override
    public List<String> getExecutionLog() {
        return repository.getLoggedLines();
    }

}
