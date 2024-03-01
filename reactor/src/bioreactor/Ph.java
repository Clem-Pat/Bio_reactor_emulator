package bioreactor;

import java.io.IOException;

public class Ph extends Variable{
    public Ph(double t) throws IOException {
        time = t;
        type = "Ph";
    }
}
