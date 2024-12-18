package tests;

import dto.Board;
import dto.User;
import manager.AppManager;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.BoardsPage;
import pages.HomePage;
import pages.LoginPage;

import static utils.RandomUtils.*;

public class BoardsTests extends AppManager {

    @BeforeMethod
    public void login(){
        User user = User.builder().email("svetamedqwerty@gmail.com").password("Medqwerty12345!").build();
        new HomePage(getDriver()).clickBtnLogin();
        new LoginPage(getDriver()).login(user);
    }

    @Test
    public void createNewBoardPositiveTest(){
        Board board = Board.builder().boardTitle(generateString(5)).build();
        new BoardsPage(getDriver()).createNewBoard(board);
    }
}
