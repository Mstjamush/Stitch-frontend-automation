package general;

import config.ApplicationConfigReader;
import com.mysql.cj.exceptions.AssertionFailedException;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.util.ArrayList;
import java.util.List;

import static general.WebDriverFactory.driver;

public class SeleniumFunctions {

    String device = ApplicationConfigReader.getConfig().getDevice();

    public void IhaveGivenInput(By Locator, String textboxvalue) {
        try {
            WebDriverWait wait = new WebDriverWait(WebDriverFactory.getDriver(), 20);
            wait.until(ExpectedConditions.visibilityOfElementLocated(Locator));
            {
                WebElement input = WebDriverFactory.getDriver().findElement(Locator);
                Thread.sleep(2000);
                while (!input.getAttribute("value").equals("")) {
                    input.sendKeys(Keys.BACK_SPACE);
                }
                input.clear();
                input.sendKeys(textboxvalue);
            }
        } catch (ElementNotVisibleException e)
        {
            throw new AssertionFailedException(String.format("The element provided {0} is not on screen", Locator));
        }
        catch (StaleElementReferenceException e)
        {
            throw new AssertionFailedException(String.format("The element provided {0} is Stale", Locator));
        }
        catch (InvalidElementStateException e)
        {
            throw new AssertionFailedException(String.format("The element provided {0} is not in desired state", Locator));
        }
        catch (Exception e)
        {
            throw new AssertionFailedException(String.format("The element provided {0} is invalid", Locator));
        }
    }


    public void PressButton(By Locator) {
        try {

            WebDriverWait wait = new WebDriverWait(WebDriverFactory.getDriver(), 20);
            wait.until(ExpectedConditions.visibilityOfElementLocated(Locator));
            {
                WebElement button = wait.until(ExpectedConditions.elementToBeClickable(Locator));
                {
                    button.click();
                }
            }
        }catch (ElementNotVisibleException e)
        {
            throw new AssertionFailedException(String.format("The element provided {0} is not on screen", Locator));
        }
        catch (StaleElementReferenceException e)
        {
            throw new AssertionFailedException(String.format("The element provided {0} is Stale", Locator));
        }
        catch (InvalidElementStateException e)
        {
            throw new AssertionFailedException(String.format("The element provided {0} is not in desired state", Locator));
        }
        catch (Exception e)
        {
            throw new AssertionFailedException(String.format("The element provided {0} is invalid", Locator));
        }
    }


    public void ClickingOn(By locator) {
        try {

            WebDriverWait wait = new WebDriverWait(WebDriverFactory.getDriver(), 20);

            wait.until(ExpectedConditions.elementToBeClickable(locator));
            {
                wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
                {
                    wait.until(ExpectedConditions.elementToBeClickable(locator));
                    {
                        WebElement link =  WebDriverFactory.getDriver().findElement(locator);
                        link.click();
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }



    public void UploadDocument(String FileName) {
        try {
            if (device.equals("Windows")) {
                GeneralFunctions.fileSelectionforWindows(FileName);
            }
            if (device.equals("Linux")) {
                GeneralFunctions.fileSelectionForLinux(FileName);
            }
            }
            catch (Exception e) {
            e.printStackTrace();
        }
    }



    public void VerificationMessageByMessage(By Locator, String message) {

        try {
            WebDriverWait wait = new WebDriverWait(WebDriverFactory.getDriver(), 20);
            wait.until(ExpectedConditions.visibilityOfElementLocated(Locator));
            {
                Assert.assertEquals(WebDriverFactory.getDriver().findElement(Locator).getText(), message);
            }
        } catch (ElementNotVisibleException e)
        {
            throw new AssertionFailedException(String.format("The element provided {0} is not on screen", Locator));
        }
        catch (StaleElementReferenceException e)
        {
            throw new AssertionFailedException(String.format("The element provided {0} is Stale", Locator));
        }
        catch (InvalidElementStateException e)
        {
            throw new AssertionFailedException(String.format("The element provided {0} is not in desired state", Locator));
        }
        catch (Exception e)
        {
            throw new AssertionFailedException(String.format("The element provided {0} is invalid", Locator));
        }

    }

    public  String getText(By Locator) {

        try {
            WebDriverWait wait = new WebDriverWait(WebDriverFactory.getDriver(), 20);
            wait.until(ExpectedConditions.visibilityOfElementLocated(Locator));
            {
                return WebDriverFactory.getDriver().findElement(Locator).getText();
            }
        } catch (ElementNotVisibleException e)
        {
            throw new AssertionFailedException(String.format("The element provided {0} is not on screen", Locator));
        }
        catch (StaleElementReferenceException e)
        {
            throw new AssertionFailedException(String.format("The element provided {0} is Stale", Locator));
        }
        catch (InvalidElementStateException e)
        {
            throw new AssertionFailedException(String.format("The element provided {0} is not in desired state", Locator));
        }
        catch (Exception e)
        {
            throw new AssertionFailedException(String.format("The element provided {0} is invalid", Locator));
        }

    }

    public void VerificationMessageByPartialText(By Locator, String message) {

        try {
            WebDriverWait wait = new WebDriverWait(WebDriverFactory.getDriver(), 20);
            wait.until(ExpectedConditions.visibilityOfElementLocated(Locator));
            {
                Assert.assertTrue(WebDriverFactory.getDriver().findElement(Locator).getText().contentEquals(message));
            }
        } catch (ElementNotVisibleException e)
        {
            throw new AssertionFailedException(String.format("The element provided {0} is not on screen", Locator));
        }
        catch (StaleElementReferenceException e)
        {
            throw new AssertionFailedException(String.format("The element provided {0} is Stale", Locator));
        }
        catch (InvalidElementStateException e)
        {
            throw new AssertionFailedException(String.format("The element provided {0} is not in desired state", Locator));
        }
        catch (Exception e)
        {
            throw new AssertionFailedException(String.format("The element provided {0} is invalid", Locator));
        }

    }

    public static void scrollDropDown(By Locator,String ListLocator)
    {
            WebDriverWait wait = new WebDriverWait(WebDriverFactory.getDriver(), 20);
            WebElement Combobox = wait.until(ExpectedConditions.elementToBeClickable(Locator));
            {
                    Combobox.click();
                    JavascriptExecutor je = (JavascriptExecutor) driver;
                    WebElement element = driver.findElement(By.xpath(ListLocator));
                    je.executeScript("window.scrollTo(100,0);",element);
                    element.click();
            }
    }

    public void SelectCombo(By Locator,By ListLocator, String value) {
        try {
            WebDriverWait wait = new WebDriverWait(WebDriverFactory.getDriver(), 20);
            WebElement Combobox = wait.until(ExpectedConditions.elementToBeClickable(Locator));
            {
                Combobox.click();
                Thread.sleep(3000);

                    wait.until(ExpectedConditions.visibilityOfElementLocated(ListLocator));
                    {
                        ArrayList<WebElement> comboValues = new ArrayList<>(Combobox.findElements(ListLocator));
                        {
                            String check = "";
                            for (WebElement element : comboValues) {

                                if (value.contentEquals(element.getText())) {
                                    check = element.getText();
                                    Thread.sleep(200);
                                    element.click();
                                    break;
                                }
                            }
                            if (check.isEmpty()) {
                                throw new Exception("No value found in Dropdown");
                            }
                        }
                    }

            }
        }
        catch (ElementNotVisibleException e)
        {
            throw new AssertionFailedException(String.format("The element provided "+Locator+" is not on screen"));
        }
        catch (StaleElementReferenceException e)
        {
            throw new AssertionFailedException(String.format("The element provided "+Locator+" is Stale"));
        }
        catch (InvalidElementStateException e)
        {
            throw new AssertionFailedException(String.format("The element provided "+Locator+" is not in desired state"));
        }
        catch (Exception ex)
        {
            throw new AssertionFailedException(String.format(ex.getMessage()));
        }

    }


    public void ClickCheckBox(By locator,String value) {

        WebDriver driver = WebDriverFactory.getDriver() ;
        JavascriptExecutor js = (JavascriptExecutor)driver;
        WebDriverWait wait = new WebDriverWait(WebDriverFactory.getDriver(), 20);
        try
        {
                wait.until((ExpectedConditions.presenceOfElementLocated(locator)));
                {
                    WebElement Checkbox = WebDriverFactory.getDriver().findElement(locator);
                    {
                            Checkbox.click();
                    }
                }
        }
        catch (ElementNotVisibleException e)
        {
            throw new AssertionFailedException(String.format("The element provided "+locator+" is not on screen"));
        }
        catch (StaleElementReferenceException e)
        {
            throw new AssertionFailedException(String.format("The element provided "+locator+" is Stale"));
        }
        catch (InvalidElementStateException e)
        {
            throw new AssertionFailedException(String.format("The element provided "+locator+" is not in desired state"));
        }
        catch (Exception ex)
        {
            throw new AssertionFailedException(String.format(ex.getMessage()));
        }

    }

    public void navigate(String url) {

     WebDriverFactory.getDriver().navigate().to(url);
    }


    public void isExist(By Locator) {

        try {
            WebDriverWait wait = new WebDriverWait(WebDriverFactory.getDriver(), 20);
            wait.until(ExpectedConditions.visibilityOfElementLocated(Locator));
            {

            }
        } catch (ElementNotVisibleException e)
        {
            throw new AssertionFailedException(String.format("The element provided {0} is not on screen", Locator));
        }
        catch (StaleElementReferenceException e)
        {
            throw new AssertionFailedException(String.format("The element provided {0} is Stale", Locator));
        }
        catch (InvalidElementStateException e)
        {
            throw new AssertionFailedException(String.format("The element provided {0} is not in desired state", Locator));
        }
        catch (Exception e)
        {
            throw new AssertionFailedException(String.format("The element provided {0} is invalid", Locator));
        }
    }

}
