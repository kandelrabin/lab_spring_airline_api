package com.example.airline_api.models;

public enum MealPreference {
    AVML("Asian Vegetarian Meal – A flavourful vegetarian alternative, with likely standards being curry dishes.  Expect rice, noodles, fruit, vegetables and milk products –  but no meat, fish or eggs."),
    BBML("Baby Meal"),
    CAKE("Birthday Cake – airlines vary in their offering of birthday cakes. Some charge and some are complimentary when pre-ordered."),
    BLML("Bland Meal– some critics say all airline meals are bland but in this case, the meal is designed for passengers who may have ulcers, heartburn, nausea, vomiting, diarrhea or have undergone stomach or intestinal surgery. Includes foods that are soft, not very spicy, and low in fiber."),
    DBML("Diabetic Meal- Sugar reduced items only"),
    HNML("Hindu Non-Vegetarian Meal- Traditionally flavoured meals that will likely contain lamb, poultry, fish or milk but absent of beef, veal or pork."),
    KSML("Kosher Meal- Confirmed to be prepared to meet strict Jewish Kosher cooking rules.KSMLS – Kosher Meal (Snack size)"),
    LCML("Low-Calorie Meal"),
    VGML("Vegetarian Vegan Meal – No animal products, including meat, fish, dairy, eggs, honey."),
    SDML("Standard meal");

    private String description;

    MealPreference(String description){
        this.description = description;
    }

    public String getDescription(){
        return this.description;
    }

}
