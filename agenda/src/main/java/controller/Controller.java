package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import model.DAO;
import model.JavaBeans;

// TODO: Auto-generated Javadoc
/**
 * The Class Controller.
 */
@WebServlet(urlPatterns = { "/Controller", "/main", "/insert", "/select", "/update", "/delete", "/report" })
public class Controller extends HttpServlet {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The dao. */
	DAO dao = new DAO();
	
	/** The contato. */
	JavaBeans contato = new JavaBeans();

	/**
	 * Instantiates a new controller.
	 */
	public Controller() {
		super();
	}

	/**
	 * Do get.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		// Teste de Conexão
		// dao.testeConexao();
		String action = request.getServletPath();
		System.out.println(action);

		// comparação de strings no java é equals
		if (action.equals("/main")) {
			contatos(request, response);
		} else if (action.equals("/insert")) {
			adicionarContato(request, response);
		} else if (action.equals("/select")) {
			listarContato(request, response);
		} else if (action.equals("/update")) {
			atualizarContato(request, response);
		} else if (action.equals("/delete")) {
			deletarContato(request, response);
		} else if (action.equals("/report")) {
			gerarRelatorio(request, response);
		} else {
			response.sendRedirect("index.html");
		}
	}

	/**
	 * Contatos.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	// Listar contatos
	protected void contatos(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// response.sendRedirect("agenda.jsp");
		// Criando um objeto para receber os dados JavaBeans
		ArrayList<JavaBeans> lista = dao.listarContatos();

		// Adiciona a lista no request da pagina
		request.setAttribute("contatos", lista);

		// Direciona o request com o novo atributo para a página agenda
		RequestDispatcher rd = request.getRequestDispatcher("agenda.jsp");
		rd.forward(request, response);

		// validação
		/*
		 * for (int i = 0; i < lista.size(); i++) {
		 * System.out.println(lista.get(i).getIdcontato());
		 * System.out.println(lista.get(i).getNome());
		 * System.out.println(lista.get(i).getTelefone());
		 * System.out.println(lista.get(i).getEmail()); }
		 */

	}

	/**
	 * Adicionar contato.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	// Novo Contato
	protected void adicionarContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Teste recebimento dos dados do formulário
		/*
		 * System.out.println(request.getParameter("nome"));
		 * System.out.println(request.getParameter("telefone"));
		 * System.out.println(request.getParameter("email"));
		 */

		// Setvar objeto contato
		contato.setNome(request.getParameter("nome"));
		contato.setTelefone(request.getParameter("telefone"));
		contato.setEmail(request.getParameter("email"));

		// Inserir contato no DAO
		dao.inserirContato(contato);

		// Redirect para agenda.jsp
		response.sendRedirect("main");

	}

	/**
	 * Listar contato.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	// Listar Contato
	protected void listarContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		contato.setIdContato(request.getParameter("idcontato"));
		dao.selecionarContato(contato);
		
		// Setar os atributos dos formulários com o conteúdo do JavaBeans
		request.setAttribute("idcontato", contato.getIdContato());
		request.setAttribute("nome", contato.getNome());
		request.setAttribute("telefone", contato.getTelefone());
		request.setAttribute("email", contato.getEmail());

		// Ecaminhar ao documentjo editar.jsp
		RequestDispatcher rd = request.getRequestDispatcher("editar.jsp");
		rd.forward(request, response);
	}

	/**
	 * Atualizar contato.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	// Editar Contato
	protected void atualizarContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Teste recebimento
		/*
		 * System.out.println(request.getParameter("idcontato"));
		 * System.out.println(request.getParameter("nome"));
		 * System.out.println(request.getParameter("telefone"));
		 * System.out.println(request.getParameter("email"));
		 */
		contato.setIdContato(request.getParameter("idcontato"));
		contato.setNome(request.getParameter("nome"));
		contato.setTelefone(request.getParameter("telefone"));
		contato.setEmail(request.getParameter("email"));

		// Alterar Contato
		dao.alterarContato(contato);

		// Redirecionar para o documento agenda.jsp
		response.sendRedirect("main");
	}

	/**
	 * Deletar contato.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	// Deletar Contato
	protected void deletarContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		contato.setIdContato(request.getParameter("idContato"));
		// Excluir contato do banco
		dao.deletarContato(contato);
		// Redirecionar para agenda.jsp
		response.sendRedirect("main");
	}

	/**
	 * Gerar relatorio.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	// Gerar relatório
	protected void gerarRelatorio(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Document documento = new Document();
		try {
			String filename = "contatos.pdf";
			
			//Tipo de Conteúdo
			response.setContentType("application/pdf");

			//Nome do Documento
			response.addHeader("Content-Disposition", "inline; filename=" + filename );
			
			//Criar o documento
			PdfWriter.getInstance(documento, response.getOutputStream());
			
			//Abrir o documento -> Conteúdo
			documento.open();
			documento.add(new Paragraph("Lista de Contatos:"));
			documento.add(new Paragraph(" "));
			
			//Criar Tabela
			PdfPTable tabela = new PdfPTable(3);
			
			//Cabeçalho
			PdfPCell colunaNome = new PdfPCell(new Paragraph("Nome"));
			PdfPCell colunaTelefone = new PdfPCell(new Paragraph("Telefone"));
			PdfPCell colunaEmail = new PdfPCell(new Paragraph("E-mail"));
			
			//Nome do Cabeçalho
			tabela.addCell(colunaNome);
			tabela.addCell(colunaTelefone);
			tabela.addCell(colunaEmail);
			
			//Popular tabela com os contatos
			ArrayList<JavaBeans> lista = dao.listarContatos();
			for (JavaBeans contato : lista) {
				tabela.addCell(contato.getNome());
				tabela.addCell(contato.getTelefone());
				tabela.addCell(contato.getEmail());				
			}
			
			//Adicionar tabela no documento
			documento.add(tabela);			
			//Fechar Documento
			documento.close();
			
		} catch (Exception e) {
			System.out.println(e);
			documento.close();
		}
		// Redirecionar para agenda.jsp
		//response.sendRedirect("main");
	}
}
