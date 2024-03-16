package bioreactor.variableManager;

public class Ph  extends Variable{
    public Ph(double t) {
        setTime(t);
        type = "Ph";
    }
    public Ph(double t, double value){
        this(t);
        setValue(value);
    }
}
