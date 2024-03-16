package bioreactor.variableManager;

import java.io.IOException;

public class Ph extends Variable{
    public Ph(double t) {
        setTime(t);
        type = "Ph";
    }
    public Ph(double t, double value) throws IOException {
        this(t);
        setValue(value);
    }
}
