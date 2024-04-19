package main;

import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import model.ConfigApp;

import model.Oferta;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

import dao.OfertaDao;

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
		DataSource dataSource = (DataSource) context.getBean("dataSource");
		logger.info("Proveedor: " + configApp.getProveedor());
	    
	    
	    Navigate nav = new SwipcarNav(configApp.getPathDriverFirefox(), configApp.getPathFirefox()
	    		, configApp.getProveedor());
	    
	    for(String key : configApp.getFlota().keySet()) {
	    	List<Oferta> ofertas = nav.start(configApp.getFlota().get(key), key);
	    	
	    	// registrar ofertas en la BD
	    	OfertaDao db = new OfertaDao(dataSource);
	    	db.updateDb(ofertas);
	    }
	    
	    
	    
	}
	
	
	
	

}
