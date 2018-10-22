package fr.nico.bll;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import fr.nico.bol.Menu;
import fr.nico.bol.Recette;

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
	
	public void creationMenu() {
		Menu menu = new Menu();
		Date dateDebut = null;
		Date dateFin = null;
		Recette recette = null;
		SimpleDateFormat sdf = new SimpleDateFormat ("dd/MM/yyyy");
		RecetteManager recetteManager = new RecetteManager();
		
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
		try {
			recette = recetteManager.choixRecette(recetteManager.listeRecettes());
			System.out.println("Test de renvoi de données " + recette.getTitre());
			
		} catch (SQLException e) {
			System.out.println("C'est la que ça merde !!!");
			e.printStackTrace();
		}catch ( NullPointerException e1) {
			System.out.println("Bon ça marche pas encore des masses " + e1.getMessage());
		}
		
		try {
			menu.setDateDebut(dateDebut);
			menu.setDateFin(dateFin);
			menu.setNbPersonne(nbPersonne);
			menu.setRecette(recette);

		}catch(NullPointerException e) {
			System.out.println("C'est le settage ! " + e.getMessage());
		}
		
		
		System.out.println("Menu du " + sdf.format(menu.getDateDebut()) + " au " + sdf.format(menu.getDateFin()) + " pour " + menu.getNbPersonne() + " personnes possède la recette : '" + menu.getRecette().getTitre() + "'.");
		
		
	}
}
