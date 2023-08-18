package site.nomoreparties.stellarburgers.model;

import java.util.ArrayList;

public class MakeOrderModel {
    private ArrayList<String> ingredients;

    public MakeOrderModel(ArrayList<String> ingredients) {
        this.ingredients = ingredients;
    }

    public MakeOrderModel() {
    }

    public ArrayList<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<String> ingredients) {
        this.ingredients = ingredients;
    }

}
