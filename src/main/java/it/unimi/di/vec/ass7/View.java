package it.unimi.di.vec.ass7;

public class View {
    private double model;
    private String tempLabelText = "";

    public void setModel(Model model) {
        this.model = model.getTemp();
    }

    public void update(double model) {
        this.model = model;
        tempLabelText = Double.toString(model);
    }

    public String getTempLabelText() {
        return tempLabelText;
    }
}
