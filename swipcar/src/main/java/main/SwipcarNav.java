package main;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import model.Oferta;

public class SwipcarNav extends Navigate {

	public SwipcarNav(String pathGeckoDriver, String pathFirefox) {
		super(pathGeckoDriver, pathFirefox);
		
	}

	@Override
	protected List<Oferta> getOfertas(WebDriver driver) {
		// Cargar toda la pagina
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
		List<WebElement> items = driver.findElements(By.className("CarItem__Item-sc-12tr62h-1"));
		for (WebElement webElement : items) {
			logger.info("\n"+webElement.getText());
		}
		
		return null;
	}

}
