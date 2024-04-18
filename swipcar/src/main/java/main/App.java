package main;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import model.ConfigApp;
import model.Oferta;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

	protected static final Logger logger = LogManager.getLogger();
	private ConfigApp configApp;
	
	public static void main(String[] args) {
		logger.info("Arranque aplicacion");
		
		App app = new App();
		app.start();

	}
	
	public void start() {
		logger.info("");
		
		ApplicationContext context = new ClassPathXmlApplicationContext("config.xml");
		configApp = (ConfigApp) context.getBean("configApp");
		logger.info("Proveedor: " + configApp.getProveedor());
	    
	    
	    Navigate nav = new SwipcarNav(configApp.getPathDriverFirefox(), configApp.getPathFirefox()
	    		, configApp.getProveedor());
	    
	    for(String key : configApp.getFlota().keySet()) {
	    	List<Oferta> ofertas = nav.start(configApp.getFlota().get(key), key);
	    	
	    	// registrar ofertas en la BD
	    }
	    
	    
	}
	
	

}
