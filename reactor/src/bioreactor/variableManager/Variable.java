package bioreactor.variableManager;

public class Variable implements IVariable{
    public String type = null;
    private double time;
    private double value;
    @Override
    public Double getValue() {return value;}
    public void setValue(double val){value = val;}
    @Override
    public Double getTime(){ return time;}
    public void setTime(double t){ time = t;}
}
