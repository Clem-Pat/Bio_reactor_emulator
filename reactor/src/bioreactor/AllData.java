package bioreactor;

import java.beans.PropertyChangeSupport;

public class AllData {
    private final PropertyChangeSupport pcSupport;

    public AllData() {
        pcSupport = new PropertyChangeSupport(this);
    }

    public PropertyChangeSupport getPropertyChangeSupport() {
        return pcSupport;
    }
}

