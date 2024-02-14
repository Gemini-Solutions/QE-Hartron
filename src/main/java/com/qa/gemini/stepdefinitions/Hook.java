package com.qa.gemini.stepdefinitions;

import com.gemini.generic.exception.GemException;
import com.gemini.generic.ui.utils.DriverManager;
import io.cucumber.java.Before;

public class Hook {
 //   static WebDriver driver;
    @Before
    public static void start() throws GemException {
        DriverManager.setUpBrowser();
    }
}

