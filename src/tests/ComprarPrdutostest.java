package tests;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import cucumber.api.PendingException;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.Então;
import cucumber.api.java.pt.Quando;

public class ComprarPrdutostest {

	WebDriver driver;

	@Dado("^acessar a pagina principal da loja de livros$")
	public void acessar_a_pagina_principal_da_loja_de_livros() {
		// definindo o local onde esta o driver do google chrome
		System.setProperty("webdriver.chrome.driver", "c:\\teste\\chromedriver.exe");

		// Abrindo o google chrome
		driver = new ChromeDriver();

		// Maximizando a janela do navegador
		driver.manage().window().maximize();

		// Acessando a pagina de cadastro de funcionario
		driver.get("http://www.lojaexemplodelivros.com.br/");
	}

	@Dado("^Seleciona o livro \"([^\"]*)\" para compra$")
	public void seleciona_o_livro_para_compra(String titulo) {

		// verificar qual e o livro desejado
		switch (titulo) {

		case "Fortaleza Digital":
			driver.findElement(By.xpath("/html/body/div/div/div[3]/div/div[1]/div[2]/ul[1]/li[2]/h2/a")).click();
			break;

		case "O Código da Vinci":
			driver.findElement(By.xpath("/html/body/div/div/div[3]/div/div[1]/div[2]/ul[1]/li[3]/h2/a")).click();
			break;

		case "O Caçador de Pipas":
			driver.findElement(By.xpath("/html/body/div/div/div[3]/div/div[1]/div[2]/ul[1]/li[4]/h2/a")).click();
			break;
		}

	}

	@Dado("^informar a quantidade desejada (\\d+)$")
	public void informar_a_quantidade_desejada(int quantidade) {

		driver.findElement(By.xpath("//*[@id=\"qty\"]")).clear();
		driver.findElement(By.xpath("//*[@id=\"qty\"]")).sendKeys(String.valueOf(quantidade));

	}

	@Quando("^Solicitar realizacao da compra$")
	public void solicitar_realizacao_da_compra() {

		driver.findElement(By.cssSelector("#product_addtocart_form > div.product-shop > div.add-to-box > div > button"))
				.click();
	}

	@Então("^Sistema informa que o livro \"([^\"]*)\" foi adicionado com sucesso$")
	public void sistema_informa_que_o_livro_foi_adicionado_com_sucesso(String titulo) {

		String mensagem = "[PRODUTO DE EXEMPLO] - " + titulo + " foi adicionado ao carrinho de compras.";
		String resultado = driver.findElement(By.xpath("/html/body/div/div/div[3]/div/div/div/ul/li/ul/li/span"))
				.getText();

		assertEquals(resultado, mensagem);

		// Gerar a evidencia do teste
		File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		try {

			String dataAtual = new SimpleDateFormat("dd_MM_yyyy HH_mm_ss").format(new Date());

			FileUtils.copyFile(file, new File("c:\\evidencias\\Loja de livros " + dataAtual + ".png"));
		} catch (Exception e) {

		}

		driver.close();
		driver.quit();

	}

}
