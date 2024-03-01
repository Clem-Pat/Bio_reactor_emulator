package bioreactor;

import java.io.IOException;

public class Temperature extends Variable{
    public Temperature(double t) throws IOException {
        type = "T";
        time = t;
    }
}
