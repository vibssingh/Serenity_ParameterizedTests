package com.example;

import net.serenitybdd.annotations.Step;
import net.serenitybdd.core.steps.UIInteractionSteps;

public class NavigateActions extends UIInteractionSteps {

    @Step
    public void toTheHomePage() {
        openPageNamed("loginForm");
    }
}