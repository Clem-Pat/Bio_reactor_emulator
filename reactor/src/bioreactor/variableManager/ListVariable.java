package bioreactor.variableManager;

import java.util.ArrayList;
import java.util.List;

public class ListVariable implements IVariable{
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
    public double findVariableValueAtTime(double t){
        for (Variable variable : variables){
            if (variable.getTime() == t){
                return variable.getValue();
            }
        }
        return Double.parseDouble(null);
    }
}
