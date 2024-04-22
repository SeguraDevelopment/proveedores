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

public class CochesComNav extends Navigate {

	public CochesComNav(String pathGeckoDriver, String pathFirefox) {
		super(pathGeckoDriver, pathFirefox);
		
	}

	@Override
	protected List<Oferta> getOfertas(Proveedor proveedor, WebDriver driver) {
		// Cargar toda la pagina
		ArrayList<Oferta> ofertas = new ArrayList<Oferta>();

		Actions action = new Actions(driver);
		
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.id("onetrust-accept-btn-handler")).click();
		boolean next = false;
		do {
			
			
			
			// el proceso se tiene que habilitar mientras "siguiente" este habilitado
			// Cargamos toda la pagina presionando el SPACE
			for(int i = 0; i < 10; i++) {
				action.sendKeys(Keys.SPACE).build().perform();
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			// localizar los elementos de la pagina en curso y saltamos a la siguiente pagina
			List<WebElement> items = driver.findElements(By.className("Card-module_card__Cb1o4"));
			for (WebElement webElement : items) {
				
				Oferta oferta = new Oferta();
				String url = webElement.findElement(By.tagName("a")).getAttribute("href");
				String price= webElement.findElement(By.className("Heading-module_heading__2pnHW")).getText();
				String tipo= webElement.findElement(By.className("FeatureText-module_wrapper__nGlYu")).getText();
				String vehiculo = webElement.findElement(By.className("text-overflow-ellipsis")).getText();
				String urlFoto = webElement.findElement(By.className("Image-module_image__Zq9Zs")).getAttribute("src");
			   
			    		
			    oferta.setPrecio(Float.parseFloat(price.replaceAll("\\D+","")));
			    oferta.setTipo(tipo);
			    oferta.setUrlAnuncio(url);
			    oferta.setVehiculo(vehiculo);
			    oferta.setUrlFotoVehiculo(urlFoto);
			    oferta.setProveedor(proveedor);
			 
			    
				logger.info(oferta);
				
				
				ofertas.add(oferta);
			}
			for (WebElement nextButtom 
					: driver.findElements(By.className("Pagination-module_pagination-item__oMUrp"))){
				String valor = nextButtom.getText();
				if(valor.contains("Siguiente")) {
					String nextValue = nextButtom.getAttribute("aria-disabled");
					next = nextValue == null ? true : false;
					if(next) 
						driver.get(nextButtom.getAttribute("href"));
					
					break;
				}
			}
		}  while(next); // vamos a la siguiente pagina
		
		
		 
		return ofertas;
	}

}
