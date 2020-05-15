package steps;

import com.github.javafaker.Faker;
import cucumber.api.PendingException;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.Entao;
import cucumber.api.java.pt.Quando;
import driver.DriverManagerFactory;
import hooks.Hook;
import org.openqa.selenium.support.PageFactory;
import pages.*;

import static org.junit.Assert.assertTrue;

public class MovimentacaoSteps extends DriverManagerFactory {

    private Home home = PageFactory.initElements(getDriver(), Home.class);
    private NavBar navBar = PageFactory.initElements(getDriver(), NavBar.class);
    private Contas contas = PageFactory.initElements(getDriver(), Contas.class);
    private Login login = PageFactory.initElements(getDriver(), Login.class);
    private Cadastro cadastro = PageFactory.initElements(getDriver(), Cadastro.class);
    private Movimentacao movimentacao = PageFactory.initElements(getDriver(), Movimentacao.class);
    private Faker faker = new Faker();

    @Dado("^eu tenha acessado a pagina de movimentacao$")
    public void euTenhaAcessadoAPaginaDeMovimentacao() throws Throwable {
        login.acessaAplicacao();
        navBar.criaNovoUsuario();
        cadastro.preencheFormulario("Teste",faker.name().firstName()+"@gft.com","123456");
        cadastro.salvaCadastro();
        assertTrue(login.validaCadastro("Usuário inserido com sucesso"));
        login.entra("teste2000@gft.com","123456");
        assertTrue(home.validaEntrada("Bem vindo, Teste!"));
        navBar.criaConta();
        contas.preencheFormulario("Teste");
        contas.salvaConta();
        assertTrue(contas.validaConta("Conta adicionada com sucesso!"));
    }

    @Quando("^eu criar a movimentacao$")
    public void euCriarAMovimentacao() throws Throwable {
        navBar.criaMovimentacao();
        movimentacao.preencheFormulario("Despesa","14/05/2020","30/05/2020"
                ,"Teste","Teste","13.00","Teste","pago");
        movimentacao.salvaMovimentacao();
    }

    @Entao("^devo ver o valor$")
    public void devoVerOValor() throws Throwable {
        assertTrue(movimentacao.validaMovimentacao("Movimentação adicionada com sucesso!"));
        navBar.sai();
    }
}
