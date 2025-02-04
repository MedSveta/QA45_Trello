package tests;

import data_provider.DataProviderBoards;
import dto.Board;
import dto.User;
import manager.AppManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.BoardsPage;
import pages.HomePage;
import pages.LoginPage;
import pages.MyBoardPage;
import utils.TestNGListener;

import static utils.RandomUtils.*;

@Listeners(TestNGListener.class)

public class BoardsTests extends AppManager {

    @BeforeMethod
    public void login() {
        User user = User.builder().email("svetamedqwerty@gmail.com").password("Medqwerty12345!").build();
        new HomePage(getDriver()).clickBtnLogin();
        new LoginPage(getDriver()).login(user);
    }

    @Test
    public void createNewBoardPositiveTest() {
        Board board = Board.builder().boardTitle(generateString(5)).build();
        new BoardsPage(getDriver()).createNewBoard(board);
        Assert.assertTrue(new MyBoardPage(getDriver()).validateBoardName(board.getBoardTitle(), 5));
        //Assert.assertTrue(new MyBoardPage(getDriver()).validateBoardName("drt4j", 5));
    }

    @Test
    public void createNewBoardNegativeTest() {
        Board board = Board.builder().boardTitle("").build();
        new BoardsPage(getDriver()).createNewBoardNegative(board);
        Assert.assertTrue(new BoardsPage(getDriver()).buttonCreateIsNotClickable());

    }

    @Test(dataProvider ="newBoardDP", dataProviderClass = DataProviderBoards.class)
    public void createNewBoardPositiveTestWithDP(Board board) {
        new BoardsPage(getDriver()).createNewBoard(board);
        Assert.assertTrue(new MyBoardPage(getDriver()).validateBoardName(board.getBoardTitle(), 5));

    }

    @Test(dataProvider ="newBoardDPFile", dataProviderClass = DataProviderBoards.class)
    public void createNewBoardPositiveTestWithDPFile(Board board) {
        new BoardsPage(getDriver()).createNewBoard(board);
        Assert.assertTrue(new MyBoardPage(getDriver()).validateBoardName(board.getBoardTitle(), 5));

    }
}
