import java.io.FileNotFoundException
import scala.io.Source
import scala.io.StdIn

/**
 * Créer une classe qui s'appelle "FichierEntree"
 * Le contructeur "repertoire" égale le répertoire du fichier d'entrée
 * La méthode nbTondeuse afin de calculer nombre de tondeuses déployées
 * La méthode pelouse permet de récupérer la surface de la pelouse
 */
class FichierEntree(val repertoire: String) {
  //Lire le fichier d'entrée
  private val fichier = Source.fromFile(repertoire)
  //Récupérer toutes les lignes et mettre dans une liste
  val  ligne: List[String] = fichier.getLines().toList
  //Enlever l'espace de la 1ere ligne, transformer en Int et mettre dans une liste
  val dimensionPelouse: List[Int] = ligne(0).split(" ").map(_.toInt).toList

  //Calculer nombre de tondeuses qui égale nombre de lignes moins 1 et divisé par 2
  def nbTondeuse: Int = (ligne.length - 1) / 2
  //Renvoyer la surface de la pelouse
  def pelouse(): Unit = println(s"La surface de la pelouse est ${dimensionPelouse(0)} ${dimensionPelouse(1)}")
}

/**
 * Créer une classe "Tondeuse" avec le constructeur "n" égale numéro de la tondeuse
 * Le constructeur "repertoire" permet la classe "Tondeuse" hériter la classe "FichierEntree"
 * La classe contient les attributs et les méthode d'une tondeuse
 * La classe "Tondeuse" hérite la classe "fichierEntree"
 */
class Tondeuse(n: Int, repertoire: String) extends FichierEntree(repertoire) {
  //Récupérer la ligne contient la position initiale de la tondeuse n° n, enlever l'espace et mettre dans une liste
  private val positionInitiale: List[String] = ligne(n * 2 - 1).split(" ").toList
  //Récupérer les coordonnées (x,y) de la tondeuse, transformer en Int
  private var x: Int = positionInitiale(0).toInt
  private var y: Int = positionInitiale(1).toInt
  //Récupérer l'orientation de la tondeuse, transformer en majuscule
  private var orientation: String = positionInitiale(2).toUpperCase
  //Récupérer la ligne de commande liée à la tondeuse, transformer en majuscule
  private val ligneDeCommande: String = ligne(n * 2).toUpperCase
  //Créer deux maps afin de renvoyer l'orientation de la tondeuse après avoir tourné à gauche ou à droite
  private val tournerAGauche = Map("N" -> "W", "E" -> "N", "S" -> "E", "W" -> "S")
  private val tournerADroite = Map("N" -> "E", "E" -> "S", "S" -> "W", "W" -> "N")

  //Renvoyer le numéro de la tondeuse en cours de déplacement
  def nom(): Unit = println(s"La tondeuse n° $n")
  //Renvoyer la ligne de la commande liée à la tondeuse en cours de déplacement
  def commande(): Unit = println(s"La commande de la tondeuse$n est: $ligneDeCommande")
  //Renvoyer la position actuelle de la tondeuse
  def position(): Unit = println(s"La position actuelle de la tondeuse$n est: $x $y $orientation")

  //Déplacer la tondeuse
  def deplacement(): Unit = {
    //Créer une boucle permetant d'éxecuter toutes les opérations de la tondeuse déployé
    for (i <- 0 until ligneDeCommande.length) {
      //Utiliser "Pattern matching" pour chaque lettre dans la ligne de commande
      ligneDeCommande.charAt(i) match {
        //Si la commande est "D", trouver l'orientation dans le map tournerADroite
        case 'D' => orientation = tournerADroite(orientation)
        //Si la commande est "G", trouver l'orientation dans le map tournerAGauche
        case 'G' => orientation = tournerAGauche(orientation)
        //Si la commande est "A", Utiliser deuxième "Pattern Matching" pour chaque orientation
        case 'A' =>
          orientation match {
            //Si la tondeuse est orientée Nord et si le déplacement ne dépasse pas la surface de la pelouse => y + 1
            case "N" => if (y < dimensionPelouse(1)) y += 1
            //Si la tondeuse est orientée Est et si le déplacement ne dépasse pas la surface de la pelouse => x + 1
            case "E" => if (x < dimensionPelouse(0)) x += 1
            //Si la tondeuse est orientée Sud et si le déplacement ne dépasse pas la surface de la pelouse => y - 1
            case "S" => if (y > 0) y -= 1
            //Si la tondeuse est orientée Ouest et si le déplacement ne dépasse pas la surface de la pelouse => x - 1
            case "W" => if (x > 0) x -= 1
          }
        //Si aucun cas est trouvé, Imprimer l'annonce
        case _ => println(s"${ligneDeCommande.charAt(i)} n'est pas une opération valabe")
      }
    }
  }
}

object ApplicationMowItNow extends App {
  //Entrer le répertoire du fichier d'entrée
  val repertoire: String = StdIn.readLine("Entrer le répertoire du fichier d'entrée:\n")

  /**
   * Utiliser "Try" et "Catch" pour attraper les erreurs
   */
  try {
    //Créer une instance de la classe fichierEntree et renvoyer la surface de la pelouse
    val entree = new FichierEntree(repertoire)
    entree.pelouse()
    //Créer une boucle afin de exécuter toutes les tondeuses déployées
    for (n: Int <- 1 to entree.nbTondeuse) {
      //Dans la boucle, creér une instance de la classe Tondeuse avec la constructeur égale le n° de la tondeuse
      val tondeuse = new Tondeuse(n, repertoire)
      //Renvoyer le n° de la tondeuse en cours de déplacement
      tondeuse.nom()
      //Renvoyer la position de la tondeuse en cours de déplacement
      tondeuse.position()
      //Renvoyer les commandes liées à la tondeuse en cours de déplacement
      tondeuse.commande()
      //Déplacer la tondeuse
      tondeuse.deplacement()
      //Renvoyer la position actuelle de la tondeuse
      tondeuse.position()
    }
  } catch {
    //Si le fichier d'entrée est introuvable, renvoyer l'erreur
    case fileNotFoundException: FileNotFoundException => println("Le fichier est introuvable")
    //Si le fichier d'entrée est mal construit, renvoyer l'erreur
    case numberFormatException: NumberFormatException => println("Le format d'entrée n'est pas valable")
    //Si l'orientation initiale n'est pas N, E, S ou W, renvoyer l'erreur
    case matchError: MatchError => println("L'orientation initiale n'est pas valable")
    case noSuchElementException: NoSuchElementException => println("L'orientation initiale n'est pas valable")
    //S'il n'y a pas d'espace entre les dimension de la pelouse ou entre les coordonnées de la tondeuse, renvoyer l'erreur
    case indexOutOfBoundsException: IndexOutOfBoundsException => println("L'espace entre dimensions est obligatoire")
  }
}

