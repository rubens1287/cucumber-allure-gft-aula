package pages;

import driver.DriverManagerFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Cadastro extends DriverManagerFactory{

    @FindBy(css="#nome")
    private WebElement txtNome;

    @FindBy(css="#email")
    private WebElement txtEmail;

    @FindBy(css="#senha")
    private WebElement txtSenha;

    @FindBy(css="input[type='submit']")
    private WebElement btnCadastrar;

    public void preencheFormulario(String nome, String email, String senha) {

        WebDriverWait wait = new WebDriverWait(getDriver(),10);
        wait.until(ExpectedConditions.elementToBeClickable(txtNome));

        txtNome.sendKeys(nome);
        txtEmail.sendKeys(email);
        txtSenha.sendKeys(senha);
    }

    public void salvaCadastro() {
        btnCadastrar.click();
    }

}
