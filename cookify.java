// #Team AMAL | Alice - Mourad - Alexandre - Loic
import java.util.Scanner;
import java.io.*;

public class cookify
{
	static class recette implements Serializable
	{
		int id;
		String nom; //Nom de la recette
		int complexite; //code de complexite 1=facile 2= moyen 3=difficile
		int temps; //Temps de préparation
		double cout_total; 
		int nbr_ingredient; //nombre d'ingredient dans la recette
		String[] ingredient; //liste des ingredients
		int[] ingredient_quant; //liste des quantité
		double[] ingredient_prix; //liste des prix
		String[] etapes; //liste contenant les étapes
	}

	public static void Recipe_storage()
	{
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Sorry this fonction isn't implemented yet.");
		System.out.println("Type something to return to main menu:");
		keyboard.next();
	}

	public static void Recipe_creator()
	{
		String on;
		int i = 0;
		int b = 1;
		Scanner keyboard = new Scanner(System.in);
		recette plat = new recette();
		
		plat.id = 1;
		plat.ingredient = new String[50];
		plat.ingredient_quant = new int[50];
		plat.ingredient_prix = new double[50];
		plat.etapes = new String[50];

		System.out.println("Recipe Creator\n\n-----------------");
		System.out.print("Nom de la recette: ");
		plat.nom = keyboard.next();
		System.out.print("\nTemps de préparation (en minutes): ");
		plat.temps = keyboard.nextInt();
		do {
			//boucle servant à insérer les ingredients, quantité et prix un par un
			keyboard.nextLine();
			System.out.print("-ingredient "+b+"\nnom: ");
			plat.ingredient[i] = keyboard.nextLine();
			System.out.print("\nquantite: ");
			plat.ingredient_quant[i] = keyboard.nextInt();
			System.out.print("\nprix: ");
			plat.ingredient_prix[i] = keyboard.nextDouble();
			do {
				//boucle de sécurité pour savoit si l'utilisiateur veut passer à l'étapes suivante ou non
					System.out.println("\nAjouter un ingredient ? (o/n)");
					on = keyboard.next();
				}while ((on.equals("o") == false && on.equals("n") == false));
			plat.nbr_ingredient++;
			i++;
			b++;
		}while (on.equals("n") == false && i < 50); //limite à 50 cases
		for (int c = 0; c < plat.ingredient.length; c++)
			plat.cout_total = plat.cout_total + plat.ingredient_prix[c]; //calcul du cout total de la recette
		if (plat.temps <= 30 || plat.ingredient.length <= 5) //définition de la complexite du plat
			plat.complexite = 1;
		else if ((plat.temps > 30 && plat.temps <= 60) || plat.ingredient.length <= 10)
			plat.complexite = 2;
		else
			plat.complexite = 3;
		i = 0;
		b = 1;
		do { //boucle de mise en place des etapes de préparation de la recette
			keyboard.nextLine();
			on = "d";
			System.out.print("-Step "+b+": ");
			plat.etapes[i] = keyboard.nextLine();
			System.out.println("\nAjouter une etapes ? (o/n)");
			while (on.equals("o") == false && on.equals("n") == false)
				on = keyboard.next();
			i++;
			b++;
		}while (on.equals("n") == false && i < 50); //limite à 50 cases
		try { //enregistrement de la recette dans un fichier
		FileOutputStream fileOut = new FileOutputStream("recette.ser");
    	ObjectOutputStream out = new ObjectOutputStream(fileOut);
    	out.writeObject(plat);
    	out.close();
    	fileOut.close();
    	System.out.println("Serialized data is saved in recette.ser");
		}catch(IOException ex) 
		{
    	ex.printStackTrace();
    	}
		System.out.print("Votre recette à été sauvegardé avec succès !\nType something to return to main menu:");
		keyboard.next();
	}

	public static void Prepare_a_recipe()
	{
		Scanner keyboard = new Scanner(System.in);
		int i = 0;
		String oui_non;
		recette charge = new recette();
		//Initialisation de la recette
		charge.ingredient = new String[50];
		charge.ingredient_quant = new int [50];
		charge.ingredient_prix = new double[50];
		charge.etapes = new String[50];

		charge.nom = "omelette";
		charge.complexite = 1;
		charge.temps = 10;
		charge.nbr_ingredient = 3;
		charge.ingredient[0] = "oeufs";
		charge.ingredient[1] = "huile 100ml";
		charge.ingredient[2] = "sel";
		charge.ingredient_quant[0] = 4;
		charge.ingredient_quant[1] = 1;
		charge.ingredient_quant[2] = 1;
		charge.ingredient_prix[0] = 4;
		charge.ingredient_prix[1] = 2;
		charge.ingredient_prix[2] = 2;
		charge.cout_total = 8;
		charge.etapes[0] = "Mettre l'huile à chauffer.";
		charge.etapes[1] = "Rajouter les oeufs et assaisonner.";
		charge.etapes[2] = "Laisser sur le feu pendant 3min.";
		System.out.println("+---------------------------------------------------+"); //affichage
		System.out.print("\nCOOKIFY\n\n");
		System.out.println("Recette: "+charge.nom+"\n");
		System.out.println("Temps: "+charge.temps+"min");
		if (charge.complexite == 1)
			System.out.println("Complexite: facile");
		else if (charge.complexite == 2)
			System.out.println("Complexite: moyen");
		else
			System.out.println("Complexite: difficile");
		System.out.println("-----------------------------------------------------\n");
		System.out.println("Shopping list (total ingredients : "+charge.nbr_ingredient+")");
		System.out.println("-----------------------------------------------------");
		for(int f = 0; f < charge.nbr_ingredient; f++)
			System.out.println(" * "+charge.ingredient[f]+"\t\t\tx"+Math.round(charge.ingredient_quant[f])+"\t("+charge.ingredient_prix[f]+"€)");
		System.out.println("-----------------------------------------------------\n");
		System.out.println("Total Cost\t\t\t\t"+charge.cout_total+"€");
		System.out.println("\nVoulez-vous préparer la recette ? (o/n)"); 
		oui_non = keyboard.next();
		if (oui_non.equals("oui") || oui_non.equals("Oui") || oui_non.equals("o") || oui_non.equals("O"))
			{
				System.out.println("\t\tPreparation");
				System.out.println("--------------------------------------------------------");
				while (charge.etapes[i] != null) //boucle d'affichage des etapes de la recette
				{
					System.out.println(charge.etapes[i]);
					System.out.println("next? (n)");
					if (i > 0)
						System.out.println("back ? (b)");
					oui_non = keyboard.next();
					if (oui_non.equals("n") == true || oui_non.equals("N") == true)
						i++;
					if ((oui_non.equals("b") == true || oui_non.equals("B")) && i > 0)
						i--;
				}
				System.out.println("\"Congratulation Ratatouille, you are a petit Chef!! :)\"");
				System.out.println("\"You have won 10 cookie points!!\"");
				System.out.print("\n\nType something to return to main menu:");
				keyboard.nextLine();
				keyboard.next();
			}
	}

	public static void My_Healt() //IMC
	{
		float meters;
		float kg;
		float bmi;

		Scanner keyboard = new Scanner(System.in);
		System.out.println("Veuillez indiquer votre taille en mètre:");
		meters = keyboard.nextFloat();
		System.out.println("Veuillez maintenant indiquer votre poid en kilogramme:");
		kg = keyboard.nextFloat();
		bmi = kg / (meters * meters);
		System.out.println("Votre IMC est de " + Math.round(bmi) + ", vous êtes en ");
		if (bmi > 40)
			System.out.println("obésité morbide");
		else if (bmi > 35)
			System.out.println("obésité sévère");
		else if (bmi > 30)
			System.out.println("obésité modéré");
		else if (bmi > 25)
			System.out.println("surpoid");
		else if (bmi > 18.5)
			System.out.println("corpulence normal");
		else if (bmi > 16.5)
			System.out.println("mince");
		else
			System.out.println("sous nutrition");	
		System.out.print("Type something to return to main menu:");
		keyboard.next();
	}

	public static void main(String args[])
	{
		String choix = "0";

		Scanner keyboard = new Scanner(System.in);
		while (choix.equals("esc") != true) //boucle de fonctionnement du menu
		{
			System.out.println("+---------------------------------------------------+\n");
			System.out.println("\t\tCOOKIFY your personal chef\n");
			System.out.println("-----------------------------------------------------\n");
			System.out.print("1. Prepare a recipe\n2. My health\n3. Recipe Creator\n4. Recipe storage\n");
			System.out.println("-----------------------------------------------------\n");
			System.out.print("Select an option or \"esc\" for exit:");
			choix = keyboard.next();
			if (choix.equals("1") == true)
				Prepare_a_recipe();
			else if (choix.equals("2") == true)
				My_Healt();
			else if (choix.equals("3") == true)
				Recipe_creator();
			else if (choix.equals("4") == true)
				Recipe_storage();
		}
		System.out.println("Au revoir");
		//Modification	
	}
}