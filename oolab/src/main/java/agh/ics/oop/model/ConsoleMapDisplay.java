package agh.ics.oop.model;

public class ConsoleMapDisplay implements MapChangeListener{
    private int count = 0;
    public void mapChanged(WorldMap worldMap, String message){
        synchronized (System.out){
            count++;
            System.out.println("Operation: "+message);
            System.out.println("Map ID: "+worldMap.getId());
            System.out.println("World map:\n"+worldMap);
            System.out.println("Number of updates: "+count+"\n\n");
        }
    }
}
