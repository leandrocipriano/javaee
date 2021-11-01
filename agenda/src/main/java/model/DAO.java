package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * The Class DAO.
 */
public class DAO {
	
	/**  Módulo de Conexão *. */
	// Parâmetros de Conexão
	private String driver = "com.mysql.cj.jdbc.Driver";
	
	/** The url. */
	private String url = "jdbc:mysql://127.0.0.1:3306/dbagenda?useTimezone=true&serverTimezone=UTC";
	
	/** The user. */
	private String user = "root";
	
	/** The password. */
	private String password = "@2904SamLe";

	/**
	 * Conectar.
	 *
	 * @return the connection
	 */
	// Método de Conexão
	private Connection conectar() {
		Connection con = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			return con;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}

	}

	/**
	 * Teste conexao.
	 */
	// Teste de Conexão
	public void testeConexao() {
		try {
			Connection con = conectar();
			System.out.println(con);
			con.close();
		} catch (Exception e) {
			System.out.println(e);

		}
	}

	/**
	 *  CRUD CREATE *.
	 *
	 * @param contato the contato
	 */
	public void inserirContato(JavaBeans contato) {
		String create = "INSERT INTO contatos (nome, telefone, email) values (?, ?, ?)";
		try {
			// Open Connection
			Connection conn = conectar();

			// Preparação da Query
			PreparedStatement pst = conn.prepareStatement(create);

			// Alteração dos parâmetros
			pst.setString(1, contato.getNome());
			pst.setString(2, contato.getTelefone());
			pst.setString(3, contato.getEmail());

			// Executar a query
			pst.executeUpdate();

			// Encerrar a conexão com o banco
			conn.close();

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
	}

	/**
	 *  CRUD - READ *.
	 *
	 * @return the array list
	 */
	public ArrayList<JavaBeans> listarContatos() {
		// Objeto JavaBeans
		ArrayList<JavaBeans> contatos = new ArrayList<>();
		String read = "SELECT * FROM contatos ORDER BY nome";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(read);
			ResultSet rs = pst.executeQuery();
			// Execução enquanto houver contatos
			while (rs.next()) {
				// Variáveis de apoio
				String idcontato = rs.getString(1);
				String nome = rs.getString(2);
				String telefone = rs.getString(3);
				String email = rs.getString(4);

				// Armazenar no array
				contatos.add(new JavaBeans(idcontato, nome, telefone, email));
			}

			con.close();
			return contatos;

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			return null;
		}
	}
	
	/**
	 *  CRUD - READ *.
	 *
	 * @param contato the contato
	 */
	public void selecionarContato(JavaBeans contato) {
		String readOnly = "SELECT * FROM contatos WHERE idcontato = ?";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(readOnly);
			pst.setString(1, contato.getIdContato());
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				contato.setIdContato(rs.getString(1));
				contato.setNome(rs.getString(2));
				contato.setTelefone(rs.getString(3));
				contato.setEmail(rs.getString(4));				
			}
			con.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
	}
	
	/**
	 *  CRUD - UPDATE *.
	 *
	 * @param contato the contato
	 */
	public void alterarContato(JavaBeans contato) {
		String update = "UPDATE contatos set nome=?, telefone=?, email=? WHERE idcontato=?";
		try {
			Connection conn = conectar();
			PreparedStatement pst = conn.prepareStatement(update);
			pst.setString(1, contato.getNome());
			pst.setString(2, contato.getTelefone());
			pst.setString(3, contato.getEmail());
			pst.setString(4, contato.getIdContato());
			
			pst.executeUpdate();
			conn.close();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	/**
	 *  CRUD - DELETE *.
	 *
	 * @param contato the contato
	 */
	public void deletarContato(JavaBeans contato) {
		String delete  = "DELETE FROM contatos WHERE idcontato=?";
		try {
			Connection conn = conectar();
			PreparedStatement pst = conn.prepareStatement(delete);
			pst.setString(1, contato.getIdContato());
			pst.executeUpdate();
			
			conn.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
		
	}
}
