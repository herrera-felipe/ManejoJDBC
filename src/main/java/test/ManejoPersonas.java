package test;

import datos.Conexion;
import datos.PersonaDao;
import datos.PersonaDaoJDBC;
import domain.PersonaDTO;
import java.sql.*;
import java.util.List;

public class ManejoPersonas {

	public static void main(String[] args) {

		// Conexion Transaccional
		Connection conexion = null;

		try {
			conexion = Conexion.getConnection();

			if (conexion.getAutoCommit()) { // si AutoCommit = true
				conexion.setAutoCommit(false);
			}

			PersonaDao personaDao = new PersonaDaoJDBC(conexion); // Establecer conexion Transaccional

			List<PersonaDTO> listaPersonas = personaDao.select();

			for (PersonaDTO persona : listaPersonas) {
				System.out.println("Persona DTO:" + persona);
			}

			conexion.commit(); // Commit de la Transaccion

			System.out.println("Se ha hecho commit de la transaccion.");
		} 
		catch (SQLException ex) {
			ex.printStackTrace(System.out);
			System.out.println("Entramos al rollback");

			try {
				conexion.rollback();
			} 
			catch (SQLException ex1) {
				ex1.printStackTrace(System.out);
			}
		}

	}

}
