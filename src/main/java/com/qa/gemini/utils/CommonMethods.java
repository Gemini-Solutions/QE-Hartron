package com.qa.gemini.utils;

import com.gemini.generic.reporting.GemTestReporter;
import com.gemini.generic.reporting.STATUS;
import com.gemini.generic.ui.utils.DriverAction;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CommonMethods {
    public static void verifyCountOfDropDown(List<WebElement> elements, By loc, int count, String dropDownName) {
        try {
            elements = DriverAction.getElements(loc);
            int sizeofDropDown = elements.size();
            if (sizeofDropDown == count) {
                GemTestReporter.addTestStep("" + dropDownName + " Dropdown count status", "Expected :" + count + " Actual : " + sizeofDropDown, STATUS.PASS);
            } else {
                GemTestReporter.addTestStep("" + dropDownName + " Dropdown count status", "Expected :" + count + " Actual : " + sizeofDropDown, STATUS.FAIL);

            }
        } catch (Exception e) {
            GemTestReporter.addTestStep("FAIL!!", "Element is not interactable", STATUS.FAIL);
        }
    }

    public static void selectDesiredTypeFromDropdown(List<WebElement> elements, By loc, String optionName, Boolean flag, String dropDownName) {
        elements = DriverAction.getElements(loc);

        for (WebElement element : elements) {
            try {
                if (element.getText().contains(optionName.trim())) {
                    element.click();
                    DriverAction.waitSec(2);
                    flag = true;
                    break;

                } else {
                    flag = false;
                }

            } catch (StaleElementReferenceException e) {
                elements = DriverAction.getElements(loc);
                element = elements.get(elements.indexOf(element));
                element.click();
            }
        }

        if (flag) {
            GemTestReporter.addTestStep("User should be able to select the desired " + dropDownName + " type", "Option  selected successfully", STATUS.PASS);
        } else {
            GemTestReporter.addTestStep("User should be able to select the desired " + dropDownName + " type", "Option not selected", STATUS.FAIL);
        }
    }
}
