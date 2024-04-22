package main;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import model.Oferta;
import model.Proveedor;

public abstract class Navigate {
	protected static final Logger logger = LogManager.getLogger();
	private String pathDriver;
	private String pathFirefox;
	protected Proveedor proveedor;
	public Navigate(String pathGeckoDriver, String pathFirefox) {
		logger.info("GeckDriver->"+pathGeckoDriver);
		logger.info("Firefox->"+pathFirefox);
		pathDriver= pathGeckoDriver;
		this.pathFirefox=pathFirefox;
		System.setProperty("webdriver.gecko.driver",pathDriver);
		
	}
	
	protected abstract List<Oferta> getOfertas(Proveedor proveedor, WebDriver driver);
	
	public List<Oferta> start(Proveedor proveedor) {
		logger.info("Proveedor: " + proveedor);
		FirefoxOptions options = new FirefoxOptions();
		options.setBinary(pathFirefox);
		
		WebDriver driver = new FirefoxDriver(options);
		
		
		driver.get(proveedor.getUrlFlota());
		
		List<Oferta> res = getOfertas(proveedor, driver);
		
		
		driver.close();
		
		return res;
	}

}
