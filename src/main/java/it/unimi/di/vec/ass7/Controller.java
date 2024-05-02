package it.unimi.di.vec.ass7;

public class Controller {

    private View view;
    private Model model;

    public Controller(View view, Model model) {
        this.view = view;
        this.model = model;
    }

    public void handle(double value) {
        model.setTemp(value);
        view.update(model.getTemp());
    }
}
