package tests;
import io.qameta.allure.*;
import org.testng.annotations.*;
import tests.BaseUI_Test;

public class Test_KeyDev extends BaseUI_Test {


  @Test(priority = 1, description = "KeyDev | Verify VCCEdge source filter functionality | VCCEdge",groups = {"smoke", "regression"})
  public void testVccEdgeSource() {
    Allure.step("Given I am logged in and on the dashboard", () -> {
      lp.givenIamAlreadyOnLoginPageAndOnDashboard(Domain, "username", "newPassword");
    });

    Allure.step("Then I navigate to the Key Developments page");
    kda.navigateToKeyDevelopments();

    Allure.step("When I apply source filter as VCCEdge");
    kda.applySourceFilter("VCCEdge");

    Allure.step("Then I verify VCCEdge source is visible");
    kda.verifyVccEdgeSourceVisible();
  }

  @Test(priority = 2, description = "KeyDev | Verify non-VCCEdge source filter functionality | Live Mint",groups = {"regression", "smoke"})
  public void testNonVccEdgeSource() {

    Allure.step("Given I am logged in and on the dashboard", () -> {
      lp.givenIamAlreadyOnLoginPageAndOnDashboard(Domain, "username", "newPassword");
    });


    Allure.step("Then I navigate to the Key Developments page");
    kda.navigateToKeyDevelopments();
    Allure.step("When I apply source filter as Live Mint");
    kda.applySourceFilter("Live Mint");

    Allure.step("Then I verify source is not VCCEdge");
    kda.verifyNonVccEdgeSourceVisible("Live Mint");
  }

  @Test(priority = 3, description = "KeyDev | Verify adding key development item to Reading List",groups = {"smoke", "regression"})
  public void testReadingListFunctionality() {
    Allure.step("Given I am logged in and on the dashboard", () -> {
      lp.givenIamAlreadyOnLoginPageAndOnDashboard(Domain, "username", "newPassword");
    });

    Allure.step("Then I navigate to the Key Developments page");
    kda.navigateToKeyDevelopments();
    Allure.step("When I add a Key Development item to Reading List");
    kda.addToReadingListAndVerify();
  }

  @Test(priority = 4, description = "KeyDev | Verify date filter functionality",groups = {"smoke", "regression"})
  public void testDateFilterFunctionality() {
    Allure.step("Given I am logged in and on the dashboard", () -> {
      lp.givenIamAlreadyOnLoginPageAndOnDashboard(Domain, "username", "newPassword");
    });

    Allure.step("Then I navigate to the Key Developments page");
    kda.navigateToKeyDevelopments();
    Allure.step("When I apply date filter for Last Week");
    kda.applyDateFilter();
  }
}
