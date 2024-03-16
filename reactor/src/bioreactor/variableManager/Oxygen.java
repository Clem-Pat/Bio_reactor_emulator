package bioreactor.variableManager;

public class Oxygen  extends Variable{
    public Oxygen(double t) {
        setTime(t);
        type = "O2";
    }
    public Oxygen(double t, double value){
        this(t);
        setValue(value);
    }
}
