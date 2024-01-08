package com.example;

import net.serenitybdd.annotations.Managed;
import net.serenitybdd.annotations.Steps;
import net.serenitybdd.annotations.Title;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.junit.annotations.Qualifier;
import net.thucydides.junit.annotations.TestData;
import net.thucydides.junit.annotations.UseTestDataFrom;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SerenityParameterizedRunner.class)
@UseTestDataFrom(value = "testdata/credentials.csv")
public class ParameterizedTests {

    private String userName;
    private String passWord;
    private String errorMessage;

    @Managed(options = "--start-maximized")
     WebDriver driver;

    @Steps
    NavigateActions navigate;

    @Steps
    StepLoginPage loginPage;


    @TestData(columnNames = "Username, Password, ErrorMessage")

    @Qualifier
    public String qualifier(){return " - " + " Username = " + userName + " and " + " Password = " + passWord + " should display " + errorMessage;}

    @Test
    @Title("Login to application with invalid credential generates error message")
    public void unsuccessfulLogin() {

        // Given
        navigate.toTheHomePage();

        // When
        loginPage.inputUserName(userName);
        loginPage.inputPassword(passWord);
        loginPage.clickLogin();

        // Then
        Serenity.reportThat("Passing invalid credentials generates error message",
                () -> assertThat(assertThat(loginPage.errorMessage()).isEqualToIgnoringCase(errorMessage)));

    }

}