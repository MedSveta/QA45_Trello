package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import java.io.File;

public class AtlassianProfilePage extends BasePage{
    public AtlassianProfilePage(WebDriver driver) {
        setDriver(driver);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
    }

    @FindBy(xpath = "//div[@data-test-selector='profile-hover-info']")
    WebElement btnProfilePhoto;
    @FindBy(xpath = "//button[@data-testid='change-avatar']")
    WebElement btnChangeAvatar;
    @FindBy(xpath = "//input[@data-testid='image-navigator-input-file']")
    WebElement inputUploadPhoto;
    @FindBy(xpath = "//button[@class='css-1jk3zmn']")
    WebElement btnUpload;
    @FindBy(xpath = "//div[@class='css-1748k3u']")
    WebElement popUpMessage;
    @FindBy(xpath = "//h2[@class='css-1tqa3gm']")
    WebElement popUpMessageWrongFormat;


    public void changeMyProfilePhoto(String photoPath){
        //clickWait(btnProfilePhoto, 5);

        Actions actions = new Actions(driver);
        actions.moveToElement(btnProfilePhoto).pause(5000).click().perform();
        clickWait(btnChangeAvatar, 5);

        File photo = new File(photoPath);
        System.out.println(photo.getAbsolutePath());
        inputUploadPhoto.sendKeys(photo.getAbsolutePath());
        clickWait(btnUpload, 3);
    }

    public boolean validateMessage(String text) {
      return validateTextInElementWait(popUpMessage, text, 5);
    }
    public boolean validateMessageWrongFormatMessage(String text) {
        return validateTextInElementWait(popUpMessageWrongFormat, text, 5);
    }
}
