package io.github.itliwei.vboot.vtrend.vo;

/**
 * @author itliwei
 *
 */
public class Result {
    private int index;
    private double value;
    private double predict;
    private double[] multiValue;
    private Boolean isMuti;

    public Result(int index, double[] multiValue) {
        this.index = index;
        this.multiValue = multiValue;
        this.isMuti = true;
    }

    public Result(int index, double value) {
        this.index = index;
        this.value = value;
        this.isMuti = false;
    }

    public Result(int index, double value, double predict) {
        this.index = index;
        this.value = value;
        this.predict = predict;
        this.isMuti = false;
    }

    @Override
    public String toString() {
        String result = "Index is " + index + ",  value is ";
        if (isMuti) {
            for (double d : multiValue) {
                result += "[ " + d + " ],";
            }
            return result;
        } else {
            return "Index is " + index + ",  value is " + value+ ",  predict is " + predict;
        }
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public Boolean getMuti() {
        return isMuti;
    }

    public void setMuti(Boolean muti) {
        isMuti = muti;
    }

    public double getPredict() {
        return predict;
    }

    public void setPredict(double predict) {
        this.predict = predict;
    }
}
