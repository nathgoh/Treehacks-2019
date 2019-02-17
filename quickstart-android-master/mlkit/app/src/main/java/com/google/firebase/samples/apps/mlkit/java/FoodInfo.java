package com.google.firebase.samples.apps.mlkit.java;
import com.microsoft.azure.storage.table.*;
public class FoodInfo extends TableServiceEntity {
    public FoodInfo(String pKey, String rKey) {
        this.partitionKey = pKey;
        this.rowKey = rKey;
    }
    public FoodInfo(){}
    String ServingSize;
    double Calories;
    double TotalFat;
    double SatFat;
    double TransFat;
    double Cholesterol;
    double Sodium;
    double TotalCarbs;
    double DietaryFiber;
    double TotalSugars;
    double Protein;
    public void setCalories(double calories) {
        this.Calories = calories;
    }
    public void setCholesterol(double cholesterol) {
        this.Cholesterol = cholesterol;
    }
    public void setDietaryFiber(double dietaryFiber) {
        this.DietaryFiber = dietaryFiber;
    }
    public void setSatFat(double satFat) {
        this.SatFat = satFat;
    }
    public void setServingSize(String servingSize) {
        this.ServingSize = servingSize;
    }
    public void setTotalFat(double totalFat) {
        this.TotalFat = totalFat;
    }
    public void setSodium(double sodium) {
        this.Sodium = sodium;
    }
    public void setTransFat(double transFat) {
        this.TransFat = transFat;
    }
    public void setTotalCarbs(double totalCarbs) {
        this.TotalCarbs = totalCarbs;
    }
    public void setProtein(double protein) {
        this.Protein = protein;
    }
    public void setTotalSugars(double totalSugars) {
        this.TotalSugars = totalSugars;
    }
    public String getServingSize() {
        return this.ServingSize;
    }
    public double getCalories() {
        return this.Calories;
    }
    public double getTotalFat() {
        return this.TotalFat;
    }
    public double getSatFat() {
        return this.SatFat;
    }
    public double getTransFat() {
        return this.TransFat;
    }
    public double getCholesterol() {
        return this.Cholesterol;
    }
    public double getTotalSugars() {
        return this.TotalSugars;
    }
    public double getDietaryFiber() {
        return this.DietaryFiber;
    }
    public double getProtein() {
        return this.Protein;
    }
    public double getTotalCarbs() {
        return this.TotalCarbs;
    }
    public double getSodium() {
        return this.Sodium;
    }
}