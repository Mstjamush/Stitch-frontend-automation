package objects;

import com.mysql.cj.exceptions.AssertionFailedException;
import general.CommonAssertions;
import general.MainCall;
import org.openqa.selenium.By;

public class LandingPage {

    public LandingPage() {}
    public static By stitch_todolist = By.xpath("/html/body/div/div/div/h1");
    public static By todo_textfield = By.xpath("/html/body/div/div/div/form/div/input");
    public static By submit_Button = By.xpath("/html/body/div/div/div/form/button");


    public static void arriveToLandingPage()
    {
        try {
            MainCall.webDriverFactory.getInstance();
        } catch (Exception exception) {
            throw new AssertionFailedException(exception.getMessage());
        }
    }

    public static void enterToDo(String userName)
    {
        MainCall.seleniumFunctions.IhaveGivenInput(todo_textfield, userName);
    }

    public static void clickSubmit()
    {
        MainCall.seleniumFunctions.PressButton(submit_Button);
    }

    public static  void verifySubmit()
    {
        MainCall.seleniumFunctions.PressButton(stitch_todolist);
    }

    public static void verifyLandingPage()
    {
        MainCall.seleniumFunctions.isExist(stitch_todolist);
    }

}
