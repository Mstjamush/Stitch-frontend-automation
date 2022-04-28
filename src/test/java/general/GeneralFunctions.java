package general;

import io.percy.selenium.Percy;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.*;
import java.util.List;

public class GeneralFunctions
{

    public static By byDate = By.id("create_project_startDate");
    //public static String first_letter = "Test_";

    public static String generateRandomString(int length,String name)
    {
        String other_letters = generateRandomNumber(3);
        String RandomName = name + other_letters;
        return RandomName;
    }
    public static String generateRandomEmail(int length,String firstName,String lastName)
    {
        String name = RandomStringUtils.randomAlphabetic(length);
        String other_letters = generateRandomNumber(3);
        String RandomName = firstName + "_"+lastName+other_letters +"@yopmail.com";
        return RandomName;
    }

    public static String generateRandomNumber(int length)
    {
        String number = RandomStringUtils.randomNumeric(length);
        return number;
    }

    public void selectRandomDate(String date) throws InterruptedException
    {
        Thread.sleep(3000);
        Actions action = new Actions(WebDriverFactory.getDriver());
        action.sendKeys(WebDriverFactory.getDriver().findElement(byDate), date).build().perform();
        Thread.sleep(2000);
    }

    public String getCurrentDay()
    {
        //Create a Calendar Object
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());

        //Get Current Day as a number
        int todayInt = calendar.get(Calendar.DAY_OF_MONTH);
        System.out.println("Today Int: " + todayInt +"\n");

        //Integer to String Conversion
        String todayStr = Integer.toString(todayInt);
        System.out.println("Today Str: " + todayStr + "\n");

        return todayStr;
    }

    public static String replaceString(String s, String replaceFrom, String replaceTo) {
        String replacedString = s.replace(replaceFrom, replaceTo);
        return replacedString;
    }

    public static Date getTime() {
        Calendar calendar = Calendar.getInstance();
        return calendar.getTime();
    }

    public static List<Integer> generateRandomNumSystem(int length, int sysCount)
    {
        List<Integer> RawRandomNumber = new ArrayList<>();
        for (int i=0 ; i<sysCount ; i++) {
            RawRandomNumber.add(Integer.valueOf(RandomStringUtils.randomNumeric(length)));
        }
        return RawRandomNumber;
    }



    public static void fileSelectionForLinux(String FileName) throws AWTException, InterruptedException {

        StringSelection StringSelection;
        String dir = System.getProperty("user.dir");
        System.out.println(dir);
        StringSelection = new StringSelection(dir + "/files/" + FileName);
        System.out.println(dir+"/files/"+FileName);
        System.out.println(StringSelection);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(StringSelection, null);

        Robot robot = new Robot();

        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);

        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);

        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);

    }


    public static void  fileSelectionforWindows(String FileName) throws AWTException, InterruptedException {

    }

    public static void PercyCapture(String Name) throws InterruptedException {
        Percy percy = new Percy(WebDriverFactory.getDriver());
        Thread.sleep(2000);
        percy.snapshot(Name);
        Thread.sleep(2000);
    }




}