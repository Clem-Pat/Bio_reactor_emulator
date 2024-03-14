package bioreactor;

import java.io.IOException;

public class Oxygen  extends Variable{
    public Oxygen(double t) throws IOException {
        time = t;
        type = "O2";
    }
}
