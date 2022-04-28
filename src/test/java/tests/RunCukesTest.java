package tests;


import config.ConfigProperties;
import general.*;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import java.io.File;
import java.util.ArrayList;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import java.sql.SQLException;
import java.util.Date;

import static config.ConfigProperties.sendEmail;

@RunWith(Cucumber.class)
@CucumberOptions(features = { "src/test/resources/features" },
        glue = {"stepdefs"},
        plugin = { "pretty", "html:target/cucumber" },
        tags={"addToDo"})

public  class RunCukesTest {
    static Date startTime = null;
    static Date endTime = null;
    static Integer passedCount = 0;
    static Integer failedCount = 0;
    static Integer skippedCount = 0;

    public static ArrayList<String> automationSteps;
    public static Integer beforeAddingStepsLength;
    public static Integer afterAddingStepsLength;
    public static Integer beforeAddingExpectedResultLength;
    public static Integer afterAddingExpectedResultLength;
    public static ArrayList<String> expectedResults;
    public static ArrayList<File> screenShotCollection = new ArrayList<File>();

    public static Integer getPassCount()
    {
        return passedCount;
    }
    public static Integer getFailCount()
    {
        return failedCount;
    }
    public static Integer getSkippedCount()
    {
        return skippedCount;
    }

    public static void setPassCount(Integer passCount)
    {
        passedCount=passCount;
    }
    public static void setFailCount(Integer failCount)
    {
        failedCount = failCount;
    }
    public static void setSkippedCount(Integer skipCount)
    {
        skippedCount = skipCount;
    }

    @BeforeClass
    public static void  beforeClass() throws SQLException {

        if (ConfigProperties.isReportingEnable.equals("true")) {
            MainCall.startReport();
        }

        startTime = GeneralFunctions.getTime();
        automationSteps = new ArrayList<String>();
        expectedResults=new ArrayList<String>();
    }
    @AfterClass
    public static void AfterClass() {
        try {

            WebDriverFactory.finishDriver();

            if (ConfigProperties.isReportingEnable.equals("true")) {
                MainCall.getExtentReport().flush();
                MainCall.getExtentReport().close();
            }
            endTime = GeneralFunctions.getTime();
            if (sendEmail.equals("true")) {
                System.out.println("sendEmail");
                SendEmailAfterExecution.sendReportAfterExecution(passedCount, failedCount, skippedCount);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
