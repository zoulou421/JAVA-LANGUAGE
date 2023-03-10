package com.formationkilo.projetfilter.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.formationkilo.projetfilter.dao.CategorieImpl;
import com.formationkilo.projetfilter.dao.ICategorie;
import com.formationkilo.projetfilter.dao.IProduit;
import com.formationkilo.projetfilter.dao.ProduitImpl;
import com.formationkilo.projetfilter.entities.Categorie;
import com.formationkilo.projetfilter.entities.Produit;

/**
 * Servlet implementation class AccueilServlet
 */
@WebServlet("/Accueil")
public class AccueilServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IProduit produitdao; 
	private ICategorie catdao;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccueilServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		produitdao = new ProduitImpl();
		catdao = new CategorieImpl();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("usersession") == null) {
			response.sendRedirect("Login");
		} else {
			List<Produit> produits = produitdao.listerTout();
			request.setAttribute("list_produits", produits);
			
			List<Categorie> categories = catdao.listerTout();
			request.setAttribute("list_categories", categories);
			
			
			request.getRequestDispatcher("accueil.jsp").forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
