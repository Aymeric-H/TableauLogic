package Model;

import Controler.Controler;
import View.BinaryTreeView;
import View.TableauView;

public class Main {

	public static void main(String[] args){
		/*
		//TreeMaker treeMaker = new TreeMaker("A v ~ ( A )");
		//TreeMaker treeMaker = new TreeMaker("( A <> B ) & ( C & D )");
		TreeMaker treeMaker = new TreeMaker("( A <> B ) & ( A & D )");
		//TreeMaker treeMaker = new TreeMaker("( A <> B ) > ( A & ~ D )");
		//TreeMaker treeMaker = new TreeMaker("~ A");
		try {
			Formula formula = treeMaker.syntaxAnalysis();
			formula.printTree(400, 400, 0, 0);
			formula = formula.applyRule();
			formula.printTree(400, 400, 400, 0);
			System.out.println(formula.getLiterals());
			TreeAnalyzer analyzer = new TreeAnalyzer(formula);
			System.out.println(analyzer.valid());
		} catch (Exception e) {
			e.printStackTrace();
		}*//*
		try {
			//Tableau tab = new Tableau(new Set("T [ ~ A > B ], F [ C v B ], T [ C ]"));
			//Tableau tab = new Tableau(new Set("T [ ~ A ], T [ A & B ]"));
			//Tableau tab = new Tableau(new Set("T [ A > B ], T [ A ]"));
			//Test 15
			//Tableau tab = new Tableau(new Set("~ ( ~ ( ~ ( P > Q ) v ( R > S ) ) v ( ~ ( ~ P v ( T > S ) ) v ( R > ( T > S ) ) ) )"));
			//Test 18
			//Tableau tab = new Tableau(new Set("~ ( ~ ( ~ ( P > Q ) v ( R > S ) ) v ( ~ ( P > ( T > S ) ) v ( R > ( T > S ) ) ) )"));
			//Test 20
			//Tableau tab = new Tableau(new Set("~ ( ~ ( ~ ( P > Q ) v ( R > S ) ) v ( ~ ( ~ P v ( T > S ) ) v ( ~ R v ( T > S ) ) ) )"));
			//Test 27
			//Tableau tab = new Tableau(new Set("~ ( ( ( ( R > P ) > ( S > P ) ) > ( ( ( P > Q ) > R ) > T ) ) > ( U > ( ( ( P > Q ) > R ) > T ) ) )"));
			//Test 29
			//Tableau tab = new Tableau(new Set("~ ( ( ~ R > ( P > ~ S ) ) > ( ( R > ( P > Q ) ) > ( ( S > P ) > ( S > Q ) ) ) )"));
			//Test 30
			//Tableau tab = new Tableau(new Set("~ ( ~ ( ~ R v ( P v ~ S ) ) v ( ~ ( ~ R v ( P > Q ) ) v ( ( S > P ) > ( S > Q ) ) ) )"));
			//Test 31
			//Tableau tab = new Tableau(new Set("~ ( ~ ( R v ( P > ~ S ) ) v ( ( R > ( P > Q ) ) > ( ( S > P ) > ( S > Q ) ) ) )"));
			//Test 32
			//Tableau tab = new Tableau(new Set("~ ( ( ( T > ( U > T ) ) > ( ( ( ~ R > ( P > ~ S ) ) > ( ( R > ( P > Q ) ) > ( ( S > P ) > ( S > Q ) ) ) ) > V ) ) > ( W > V ) )"));
			//Test 33
			//Tableau tab = new Tableau(new Set("~ ( ( ( P > ( ( ~ Q > ( R > S ) ) > ( ( S > Q ) > ( T > ( R > Q ) ) ) ) ) > ( ( ~ U > ( U > V ) ) > W ) ) > W )"));
			//Test 34
			//Tableau tab = new Tableau(new Set("~ ( ( ( T > ( ( ( P > Q ) > S ) > U )  ) > ( T > ( ( S > V ) > ( ~ P > ~ R ) ) ) ) > ( V > ( ( S > P ) > ( R > P ) ) ) )"));
			//Test 35
			//Tableau tab = new Tableau(new Set("~ ( ( ( P > Q ) > ( ( P > ( P & Q ) ) & ( ( P & Q  ) > P ) ) ) & ( ( ( P > ( P & Q ) ) & ( ( P & Q ) > P ) ) > ( P > Q ) ) )"));
			//Test 36
			//Tableau tab = new Tableau(new Set("~ ( ( P & ( ~ Q v ~ R ) ) v ~ ( ( P & ( ~ R v ~ P ) ) v ~ ( ( S & Q ) v ~ ~ ( ~ P v ~ S ) ) ) )"));
			//Test 37
			//Tableau tab = new Tableau(new Set("~ ( ~ ( P & ( Q > ~ R ) ) > ~ ( ~ ( P & ( R > ~ P ) ) > ~ ( ( S & Q ) V ( P > ~ S ) ) ) )"));
			//Test 38
			//Tableau tab = new Tableau(new Set("~ ( ( P & ( ~ Q v ~ R ) ) v ~ ( ~ ( ( S & R ) v ( ~ P v ~ S ) ) v ( P & ( ~ P v ~ Q ) ) ) )"));
			//Test 39
			//Tableau tab = new Tableau(new Set("~ ( ( P > ~ ( Q > ~ R ) ) > ( ( ( S > ~ R ) > ( P > ~ S ) ) & ( P > ~ ( P > ~ Q ) ) ) )"));	
			//Test 40
			//Tableau tab = new Tableau(new Set("~ ( ( P > ~ ( Q > ~ R ) ) > ~ ( ( ( S > ~ R ) > ( P > ~ S ) ) > ~ ( P > ~ ( P > ~ Q ) ) ) )"));
			//Test 41
			//Tableau tab = new Tableau(new Set("~ ( ~ ( P & ( ~ Q v ~ R ) ) > ~ ( ( ( S & R ) v ( ~ P v ~ S ) ) > ( P & ( ~ P v ~ Q ) ) ) )"));
			//Test 42
			//Tableau tab = new Tableau(new Set("~ ( ~ ( P & ( ~ Q v ~ R ) ) > ~ ( ~ ( ( S & R ) v ( ~ P v ~ S ) ) v ( P & ( ~ P v ~ Q ) ) ) )"));
			//Test 43
			//Tableau tab = new Tableau(new Set("~ ( ~ ( P & ( ~ Q v ~ R ) ) > ~ ( ( ~ ( S & R ) > ( ~ P v ~ S ) ) > ( P & ( ~ P v ~ Q ) ) ) )"));
			//Test 44
			//Tableau tab = new Tableau(new Set("~ ( ( P > ~ ( Q > ~ R ) ) > ~ ( ( T > T ) > ~ ( ( S > ~ Q ) > ( P > ~ S ) ) ) )"));
			//Test 45
			//Tableau tab = new Tableau(new Set("~ ( ( P & ( ~ Q v ~ R ) ) v ( ( ~ T v T ) & ( ( S & Q ) v ( ~ P v ~ S ) ) ) )"));
			//Test 46
			//Tableau tab = new Tableau(new Set("~ ( ( P & ( ~ Q v ~ R ) ) v ( ( ~ T v ~ ~ T ) & ( ( S & Q ) v ( ~ P v ~ S ) ) ) )"));
			//Test 47
			//Tableau tab = new Tableau(new Set("~ ( ( ( ( R > P ) > ( S > P ) ) > ( ( ( P > Q ) > R ) > T ) ) > ( ( T v U ) > ( ( ( P > Q ) > R ) > V ) ) )"));
			//Test 49
			//Tableau tab = new Tableau(new Set("~ ( ~ ( ~ P > ( ~ Q > R ) ) > ~ ( ~ ( ~ P > Q ) > R ) )"));
			//Test 50
			//Tableau tab = new Tableau(new Set("~ ( ~ ( ( P v ( R v Q ) ) > ( S v T ) ) v ( ~ ( S v T ) > ( ~ ( P & R ) & ~ ( P & Q ) ) ) )"));
			
			//Tableau tab = new Tableau(new Set("~ ( ( P > Q ) > ( R > S ) ) v ( ~ ( P > ( T > S ) ) v ( R > ( T > S ) ) )"));
			//Tableau tab = new Tableau(new Set("( P & ( ~ Q v ~ R ) )"));
			//Tableau tab = new Tableau(new Set("( ( P > R ) > R ) > ( ~ P > R )"));
			//Tableau tab = new Tableau(new Set("~ ( ( P > R ) > R ) v ( ~ P > R )"));
			//Tableau tab = new Tableau(new Set("( ( A > ( B > A ) ) > ( ( ( ~ R > ( P > ~ S ) ) > ( ( R > ( P > Q ) ) > ( ( S > P ) > ( S > Q ) ) ) ) > C ) ) > ( D > C )"));
			
			
			Tableau tab = new Tableau(new Set("~ ( ~ ( P v Q ) v ( ~ ( Q v ( R v S ) ) > P ) )"));
			tab.printTableau(400, 400, 0, 0);
			tab.applyRules();
			System.out.println(tab.validity());
		} catch (Exception e) {
			e.printStackTrace();
		}
		*/
		//Tests of the MVC
		try {
			/*
			 * Good example to show most of the cases :
			 * T [ ( ~ A v ~ B ) v ( C & ( ~ C v ( C & ~ C ) ) ) v ( ~ C v ~ ~ C ) ]
			 * T [ A v B & ~ B ]
			 * T [ ( ~ A v ~ B ) v ( C & ( ~ C v ( C & ~ C ) ) ) ], T [ A v B ]
			 */
			Tableau tab = new Tableau();
			Controler controler = new Controler(tab);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
