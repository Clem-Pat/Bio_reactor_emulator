package bioreactor.variableManager;

import java.util.ArrayList;
import java.util.List;

public class CollectorVariable implements IVariable{
    public List<Variable> variables = new ArrayList<>();
    @Override
    public ArrayList<Double> getValue() {
        ArrayList<Double> res = new ArrayList<>();
        for (Variable variable : variables) {
            res.add(variable.getValue());
        }
        return res;
    }
    @Override
    public ArrayList<Double> getTime() {
        ArrayList<Double> res = new ArrayList<>();
        for (Variable variable : variables) {
            res.add(variable.getTime());
        }
        return res;
    }
    public void add(Variable V) {
        variables.add(V);
    }
    public Variable get(double t) {
        for (Variable variable : variables){
            if (variable.getTime() == t){
                return variable;
            }
        }
        return null;
    }
    public Double getValueAtTime(double t){
        for (Variable variable : variables){
            if (variable.getTime() == t){
                return variable.getValue();
            }
        }
        return null;
    }
    public Integer size(){
        return variables.size();
    }
    public Variable getLastVariable(){
        return variables.get(variables.size() - 1);
    }
}
