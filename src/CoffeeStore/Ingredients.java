/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CoffeeStore;

import java.io.Serializable;

/**
 *
 * @author ntphu
 */
public class Ingredients implements Serializable {

    private String codeIngredient, nameIngredient, quantityIngredient, unitIngredient;

    public Ingredients() {
    }

    public Ingredients(String codeIngredient, String nameIngredient, String quantityIngredient, String unitIngredient) {
        this.codeIngredient = codeIngredient;
        this.nameIngredient = nameIngredient;
        this.quantityIngredient = quantityIngredient;
        this.unitIngredient = unitIngredient;
    }

    public String getCodeIngredient() {
        return codeIngredient;
    }

    public void setCodeIngredient(String codeIngredient) {
        this.codeIngredient = codeIngredient;
    }

    public String getNameIngredient() {
        return nameIngredient;
    }

    public void setNameIngredient(String nameIngredient) {
        this.nameIngredient = nameIngredient;
    }

    public String getQuantityIngredient() {
        return quantityIngredient;
    }

    public void setQuantityIngredient(String quantityIngredient) {
        this.quantityIngredient = quantityIngredient;
    }

    public String getUnitIngredient() {
        return unitIngredient;
    }

    public void setUnitIngredient(String unitIngredient) {
        this.unitIngredient = unitIngredient;
    }

    @Override
    public String toString() {
        return String.format("|%5s|%15s|%8s|%5s|", this.codeIngredient, this.nameIngredient, this.quantityIngredient, this.unitIngredient);
    }

}
