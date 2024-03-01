package bioreactor;

public class Variable implements IVariable{
    public String type = null;
    public double time;
    private double value;

    public double getValue() {
        return value;
    }
    public void setValue(double val){
        value = val;
    }

    public void setTimeAndValue(double currentTime, double val) {
        time = currentTime;
        setValue(val);
    }
}
