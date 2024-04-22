package dao;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import model.Oferta;
import model.Proveedor;

import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

public class OfertaDao {
	DataSource ds;
	JdbcTemplate jdbcTemplate;
	public void updateDb(Proveedor p, List<Oferta> items) {
		int idProveedor= queryProveedor(p);
		p.setIdProveedor(idProveedor);
		for(Oferta o: items) {
			
			updateOferta(p.getIdProveedor(), o);
			
		}
		bajaOfertas(p);
	}
	
	private void bajaOfertas(Proveedor p) {
		// un anuncio que no ha sido actualizado entre dos ejecuciones 
		// del proceso se tiene que dar de baja
		// y esta dato lo contiene la fecha de LAST_UPDATE del registro de la tabla de 
		// proveedores
		
		String STMT = "update ofertas "
				+ "set f_baja = sysdatetime() "
				+ "from ofertas o "
				+ "join proveedores p on o.ID_PROVEEDOR= p.ID "
				+ "where p.ID = ? and p.f_last_update > o.F_ACTZ";
		
		jdbcTemplate.update(connection -> {
			PreparedStatement ps = connection
			 .prepareStatement(STMT);
			  ps.setInt(1, p.getIdProveedor());
			  return ps;
		});
	}

	private void updateOferta(int idProveedor, Oferta o) {
		try {
		int id = jdbcTemplate.queryForObject(
			    "SELECT ID FROM OFERTAS WHERE ID_PROVEEDOR= ? AND TIPO = ? AND VEHICULO = ?",
			    Integer.class,
			    idProveedor,
			    o.getTipo(),
			    o.getVehiculo()
			    );
		// si ya existe se actualiza el precio
		 jdbcTemplate.update(connection -> {
		        PreparedStatement ps = connection
		          .prepareStatement(
		        		  "UPDATE OFERTAS SET PRECIO = ?, F_ACTZ=SYSDATETIME(), F_BAJA=NULL WHERE ID = ? ");
		          ps.setFloat(1, o.getPrecio());
		          ps.setInt(2, id);
		          	          
		          return ps;
		        });
		} catch (EmptyResultDataAccessException e) { // si no existe se crea el valor
			jdbcTemplate.update(connection -> {
				PreparedStatement ps = connection
						.prepareStatement(
						"INSERT INTO OFERTAS(ID_PROVEEDOR, TIPO, PRECIO, URL_ANUNCIO, URL_VEHICULO, VEHICULO, F_ALTA, F_ACTZ) VALUES"
						+ "(  ?,?,?,?,?,?   , SYSDATETIME(), SYSDATETIME())"
								);
						ps.setInt(1, idProveedor);
						ps.setString(2,o.getTipo());
						ps.setFloat(3, o.getPrecio());
						ps.setString(4,o.getUrlAnuncio());
						ps.setString(5,o.getUrlFotoVehiculo());
						ps.setString(6,o.getVehiculo());
					          	          
				          return ps;
			  });
		}
	}
	
	private int queryProveedor(Proveedor p) {
		int  idProveedor;
		String INSERT_MESSAGE_SQL= "insert into proveedores (proveedor, flota, url, F_ALTA, F_ACTZ, F_LAST_UPDATE) values(?, ?, ?,SYSDATETIME(),SYSDATETIME(), SYSDATETIME() ) ";
		try {	
			idProveedor = jdbcTemplate.queryForObject(
				    "SELECT ID FROM PROVEEDORES WHERE PROVEEDOR= ? AND FLOTA = ? AND URL = ?",
				    Integer.class,
				    p.getProveedor(),
				    p.getFlota(),
				    p.getUrlFlota()
				    );
			
					
		} 
		catch (EmptyResultDataAccessException e) {
		   // Crear ID de proveedor
				KeyHolder keyHolder = new GeneratedKeyHolder();

			    jdbcTemplate.update(connection -> {
			        PreparedStatement ps = connection
			          .prepareStatement(INSERT_MESSAGE_SQL, Statement.RETURN_GENERATED_KEYS);
			          ps.setString(1, p.getProveedor());
			          ps.setString(2, p.getFlota());
			          ps.setString(3, p.getUrlFlota());
			          
			          return ps;
			        }, keyHolder);

			    idProveedor= keyHolder.getKey().intValue();
		}
		p.setIdProveedor(idProveedor);
		jdbcTemplate.update( connection -> {
			PreparedStatement ps = connection
			 .prepareStatement(
			 "update PROVEEDORES set F_LAST_UPDATE = SYSDATETIME() WHERE ID = ?");
			  ps.setInt(1, p.getIdProveedor());
			return ps;
		});
		
		return idProveedor;
	}
	

	public OfertaDao (DataSource source) {
		ds = source;
		jdbcTemplate  = new JdbcTemplate(ds);
	}
}
