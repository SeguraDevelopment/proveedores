package main;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import model.Oferta;
import model.Proveedor;

public class SwipcarNav extends Navigate {

	public SwipcarNav(String pathGeckoDriver, String pathFirefox) {
		super(pathGeckoDriver, pathFirefox);
		
	}

	@Override
	protected List<Oferta> getOfertas(Proveedor proveedor, WebDriver driver) {
		// Cargar toda la pagina
		ArrayList<Oferta> ofertas = new ArrayList<Oferta>();
		//((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
		Actions action = new Actions(driver);
		// Cargamos toda la pagina presionando el SPACE
		for(int i = 0; i < 50; i++) {
			action.sendKeys(Keys.SPACE).build().perform();
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		List<WebElement> items = driver.findElements(By.className("CarItem__Item-sc-12tr62h-1"));
		for (WebElement webElement : items) {
			
			Oferta oferta = new Oferta();
			
			String url = webElement.getAttribute("href");
			String price= webElement.findElement(By.className("CarItem__Price-sc-12tr62h-7")).getText();
		    String tipo= webElement.findElement(By.className("HighlightedFeature__Small-sc-1j16t60-1")).getText();
		    String vehiculo = webElement.findElement(By.className("CarItem__Title-sc-12tr62h-2")).getText();
		    String urlFoto = "";
		    WebElement imagenCoche = webElement.findElement(By.className("CarItem__ImageWrapper-sc-12tr62h-3"));
		    for(WebElement img : imagenCoche.findElements(By.tagName("img"))){
		    	String hidden = img.getAttribute("aria-hidden");
		    	if(hidden!=null && hidden.contentEquals("true"))
		    		continue;
		    	String alt = img.getAttribute("alt");
		    	urlFoto = alt.isEmpty() ? "": img.getAttribute("src");
		    }
		    		
		    oferta.setPrecio(Float.parseFloat(price.replaceAll("\\D+","")));
		    oferta.setTipo(tipo);
		    oferta.setUrlAnuncio(url);
		    oferta.setVehiculo(vehiculo);
		    oferta.setUrlFotoVehiculo(urlFoto);
		    oferta.setProveedor(proveedor);
		 
		    
			logger.info(oferta);
			
			
			ofertas.add(oferta);
		}
		
		return ofertas;
	}

}
