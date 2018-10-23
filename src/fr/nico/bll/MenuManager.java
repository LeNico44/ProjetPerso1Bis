package fr.nico.bll;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import fr.nico.bol.Menu;
import fr.nico.bol.Recette;
import fr.nico.dao.MenuDAO;

public class MenuManager {
	private Menu menu;
	private Scanner sc = new Scanner(System.in).useLocale(Locale.US);
	
	public MenuManager() {}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}
	
	// LES VRAIS MÉTHODES DE PONEY !!!
	
	public void creationMenu() throws SQLException {
		Menu menu = new Menu();
		Date dateDebut = null;
		Date dateFin = null;
		int reponse = 1;
		List<Recette> recettes = new ArrayList<>();
		SimpleDateFormat sdf = new SimpleDateFormat ("dd/MM/yyyy");
		RecetteManager recetteManager = new RecetteManager();
		MenuDAO menuDAO = new MenuDAO();
		
		//date de débuut
		System.out.println("Votre menu commence le : ");
		System.out.println("exemple : 22/05/2018");
		String str = sc.nextLine();
		
		try {
			dateDebut = sdf.parse(str);
			
			sdf = new SimpleDateFormat("EEE, d MMM yyyy");
			System.out.println("Date de début : " + sdf.format(dateDebut));
		} catch (ParseException e) {
			System.out.println("Parse Exception !!! " + e.getMessage());
		}
		
		//Date de fin
		System.out.println("Votre menu se termine le : ");
		System.out.println("exemple : 22/05/2018");
		String str2 = sc.nextLine();
		
		try {
			sdf = new SimpleDateFormat ("dd/MM/yyyy");
			dateFin = sdf.parse(str2);
			
			sdf = new SimpleDateFormat("EEE, d MMM yyyy");
			System.out.println("Date de fin : " + sdf.format(dateFin));
		} catch (ParseException e) {
			System.out.println("Parse Exception !!! " + e.getMessage());
		}
		
		System.out.println("Combien de personne mangeront sur ce menu ?");
		int nbPersonne = sc.nextInt();
		
		System.out.println("Choisir une recette : ");
		do {
			try {
				recettes.add(recetteManager.choixRecette(recetteManager.listeRecettes()));
				for(Recette recette : recettes) {
					System.out.println("- " + recette.getTitre());
				}
				
				
				} catch (SQLException e) {
					System.out.println("C'est la que ça merde !!!");
					e.printStackTrace();
				}catch ( NullPointerException e1) {
					System.out.println("Bon ça marche pas encore des masses " + e1.getMessage());
				}
			System.out.println("Voulez-vous ajouter une recette au menu ?");
			System.out.println("1- oui / 2- non");
			reponse = sc.nextInt();
		}while(reponse != 2);
		
		//Calcule du prix 
		Double prixUniteToltal = 0.0;
		Double prixMenu = 0.0;
		for(Recette recette : recettes) {
			Double prixUnite = recetteManager.prixRecette(recette) / recette.getNbPersonne();
			prixUniteToltal = prixUniteToltal + prixUnite;
			prixMenu = prixUniteToltal * nbPersonne;
			System.out.println("Le menu coûte environ " + prixMenu + " €.");
		} 
		
		try {
			menu.setDateDebut(dateDebut);
			menu.setDateFin(dateFin);
			menu.setNbPersonne(nbPersonne);
			menu.setRecettes(recettes);
			menu.setPrixMenu(prixMenu);
			
			menuDAO.create(menu);

		}catch(NullPointerException e) {
			System.out.println("C'est le settage ! " + e.getMessage());
		}
		
		
		System.out.println("Menu du " + sdf.format(menu.getDateDebut()) + " au " + sdf.format(menu.getDateFin()) + " pour " + menu.getNbPersonne() + ".");
		
		
	}
}
