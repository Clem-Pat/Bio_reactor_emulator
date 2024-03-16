package bioreactor.variableManager;

public class Temperature extends Variable{

    public Temperature(double t){
        type = "T";
        setTime(t);
    }
    public Temperature(double t, double value){
        this(t);
        setValue(value);
    }
}
