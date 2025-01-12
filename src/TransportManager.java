package src;

import java.util.*;

public class TransportManager {
    private List<TransportMode> transportModes;

    public TransportManager() {
        transportModes = new ArrayList<>();
        transportModes.add(new TransportMode("Car", 0.21));   
        transportModes.add(new TransportMode("Bus", 0.10));
        transportModes.add(new TransportMode("Bike", 0.0));
        transportModes.add(new TransportMode("Train", 0.05));
    }

    public List<TransportMode> getTransportModes() {
        return transportModes;
    }

    public void displayTransportModes() {
        System.out.println("\nAvailable Transport Modes:");
        for (int i = 0; i < transportModes.size(); i++) {
            TransportMode mode = transportModes.get(i);
            System.out.printf("%d. %s\n", i + 1, mode.getMode());
        }
    }

    public TransportMode getTransportMode(String modeName) {
        for (TransportMode mode : transportModes) {
            if (mode.getMode().equalsIgnoreCase(modeName)) {
                return mode;
            }
        }
        return null; 
    }
}
