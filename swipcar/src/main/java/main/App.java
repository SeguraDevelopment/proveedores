package main;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import model.ConfigApp;
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
	    logger.info("URL Proveedor: " + configApp.getUrlProveedor());
	    
	    Navigate nav = new Navigate(configApp.getPathDriverFirefox(), configApp.getPathFirefox());
	    nav.start(configApp.getUrlProveedor());
	    
	    
	}
	
	

}
