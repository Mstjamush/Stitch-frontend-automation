package stepdefs;

import tests.RunCukesTest;
import config.ConfigProperties;
import general.*;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import cucumber.api.Result;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.runtime.ScenarioImpl;
import org.apache.commons.lang3.reflect.FieldUtils;


import java.lang.reflect.Field;
import java.util.*;




public class Hooks extends RunCukesTest {

    static ExtentTest logger;
    @Before
    public static void testStart(Scenario scenario) throws Throwable {

        beforeAddingStepsLength=automationSteps.size();
        beforeAddingExpectedResultLength=expectedResults.size();
        MainCall.caseID = "";
        if (ConfigProperties.isReportingEnable.equals("true")) {
            logger = MainCall.getExtentReport().startTest(scenario.getName(),"");
            logger.setStartedTime(GeneralFunctions.getTime());
        }
    }
    @After
    public static void testEnd(Scenario scenario) throws Throwable
    {
        afterAddingStepsLength=automationSteps.size();
        afterAddingExpectedResultLength=expectedResults.size();
            if ( scenario.getStatus() == Result.Type.FAILED ) {
                String screenShot  = Screenshots.takeScreenshot(scenario.getName());
                screenShotCollection.add(Screenshots.screenShot);
                Field field = FieldUtils.getField(((ScenarioImpl) scenario).getClass(), "stepResults", true);
                field.setAccessible(true);
                ArrayList<Result> results = (ArrayList<Result>) field.get(scenario);
                RunCukesTest.setFailCount(RunCukesTest.getFailCount() + 1);
                if (ConfigProperties.isReportingEnable.equals("true")) {
                    logger.log(LogStatus.FAIL, logger.addScreenCapture(screenShot));
                    for (Result result : results) {
                        if (result.getError() != null)
                            logger.log(LogStatus.FAIL, "Test Case Failed reason is: " + result.getError());
                    }
                }
            }
            else if (scenario.getStatus() == Result.Type.SKIPPED ) {
                RunCukesTest.setSkippedCount(RunCukesTest.getSkippedCount() + 1);
                if (ConfigProperties.isReportingEnable.equals("true")) {
                    logger.log(LogStatus.SKIP, "Test Case Skipped is: ");
                }
            }
            else {
                RunCukesTest.setPassCount((RunCukesTest.getPassCount()+1));
                if (ConfigProperties.isReportingEnable.equals("true")) {
                    logger.log(LogStatus.PASS, scenario.getName() + " is Passed");
                }

            }
        if (ConfigProperties.isReportingEnable.equals("true")) {
            logger.setEndedTime(GeneralFunctions.getTime());
            MainCall.getExtentReport().endTest(logger);
        }
    }


}