package main;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import model.Oferta;

public abstract class Navigate {
	protected static final Logger logger = LogManager.getLogger();
	private String pathDriver;
	private String pathFirefox;
	public Navigate(String pathGeckoDriver, String pathFirefox) {
		logger.info("GeckDriver->"+pathGeckoDriver);
		logger.info("Firefox->"+pathFirefox);
		pathDriver= pathGeckoDriver;
		this.pathFirefox=pathFirefox;
		System.setProperty("webdriver.gecko.driver",pathDriver);
	}
	
	protected abstract List<Oferta> getOfertas(WebDriver driver);
	
	public List<Oferta> start(String url) {
		logger.info("Navegacion->"+url);
		FirefoxOptions options = new FirefoxOptions();
		options.setBinary(pathFirefox);
		
		WebDriver driver = new FirefoxDriver(options);
		
		
		driver.get(url);
		
		List<Oferta> res = getOfertas(driver);
		
		
		driver.close();
		
		return res;
	}

}
