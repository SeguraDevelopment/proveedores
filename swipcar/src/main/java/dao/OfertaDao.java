package dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import model.Oferta;

public class OfertaDao {
	DataSource ds;
	JdbcTemplate jdbcTemplate;
	public void updateDb(List<Oferta> items) {
		for(Oferta o: items) {
			int idProveedor;
			try {	
				idProveedor = jdbcTemplate.queryForObject(
					    "SELECT ID FROM PROVEEDORES WHERE PROVEEDOR= ? AND FLOTA = ? ",
					    Integer.class,
					    o.getProveedor(),
					    o.getFlota()
					    );
			} 
			catch (EmptyResultDataAccessException e) {
			   // Crear ID de proveedor
				
			}
			
		}
	}

	public OfertaDao (DataSource source) {
		ds = source;
		jdbcTemplate  = new JdbcTemplate(ds);
	}
}
