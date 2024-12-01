package agh.ics.oop;
import   java.util.List;
import java.util.concurrent.*;


public class SimulationEngine {
    private final List<Simulation> simulations;
    private final ExecutorService executorService;

    public SimulationEngine(List<Simulation> simulations) {
        this.simulations = simulations;
        this.executorService = Executors.newFixedThreadPool(4);
    }

    public void runSync(){
        for (Simulation simulation: simulations) {
            simulation.run();
        }
    }

    public void runAsync(){
        for (Simulation simulation: simulations){
            Thread thread = new Thread(() -> simulation.run());
            thread.start();
        }
        awaitSimulationsEnd();
    }

    public void awaitSimulationsEnd(){
        try{
            executorService.shutdown();
            if (!executorService.awaitTermination(10, TimeUnit.SECONDS)){
                executorService.shutdownNow();
            }
        } catch (InterruptedException e){
            executorService.shutdownNow();
        }
    }

    public void runAsyncInThreadPool(){
        for(Simulation simulation: simulations){
            executorService.submit(() -> simulation.run());
        }
    }
}
