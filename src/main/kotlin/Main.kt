package org.example

import org.example.dresseur.Entraineur
import org.example.monstre.EspeceMonstre

//Entraineur
var joueur = Entraineur(1, "Sacha", 100)
var rival = Entraineur(2,"Regis",200)
//Especes
val especeSpringleaf = EspeceMonstre(
    id = 1,
    nom = "Springleaf",
    type = "Graine",
    baseAttaque = 9,
    baseDefense = 11,
    baseVitesse = 10,
    baseAttaqueSpe = 12,
    baseDefenseSpe = 14,
    basePv = 60,
    modAttaque = 6.5,
    modDefense = 9.0,
    modVitesse = 8.0,
    modAttaqueSpe = 7.0,
    modDefenseSpe = 10.0,
    modPv = 34.0,
    description = "Petit monstre espiègle rond comme une graine, adore le soleil."
)

val especeFlamkip = EspeceMonstre(
    id = 4,
    nom = "Flamkip",
    type = "Animal",
    baseAttaque = 12,
    baseDefense = 8,
    baseVitesse = 13,
    baseAttaqueSpe = 16,
    baseDefenseSpe = 7,
    basePv = 50,
    modAttaque = 10.0,
    modDefense = 5.5,
    modVitesse = 9.5,
    modAttaqueSpe = 9.5,
    modDefenseSpe = 6.5,
    modPv = 22.0,
    description = "Petit animal entouré de flammes, déteste le froid."
)

val especeAquamy = EspeceMonstre(
    id = 7,
    nom = "Aquamy",
    type = "Meteo",
    baseAttaque = 10,
    baseDefense = 11,
    baseVitesse = 9,
    baseAttaqueSpe = 14,
    baseDefenseSpe = 14,
    basePv = 55,
    modAttaque = 9.0,
    modDefense = 10.0,
    modVitesse = 7.5,
    modAttaqueSpe = 12.0,
    modDefenseSpe = 12.0,
    modPv = 27.0,
    description = "Créature vaporeuse semblable à un nuage, produit des gouttes pures."
)

val especeLaoumi = EspeceMonstre(
    id = 8,
    nom = "Laoumi",
    type = "Animal",
    baseAttaque = 11,
    baseDefense = 10,
    baseVitesse = 9,
    baseAttaqueSpe = 8,
    baseDefenseSpe = 11,
    basePv = 58,
    modAttaque = 11.0,
    modDefense = 8.0,
    modVitesse = 7.0,
    modAttaqueSpe = 6.0,
    modDefenseSpe = 11.5,
    modPv = 23.0,
    description = "Petit ourson au pelage soyeux, aime se tenir debout."
)

val especeBugsyface = EspeceMonstre(
    id = 10,
    nom = "Bugsyface",
    type = "Insecte",
    baseAttaque = 10,
    baseDefense = 13,
    baseVitesse = 8,
    baseAttaqueSpe = 7,
    baseDefenseSpe = 13,
    basePv = 45,
    modAttaque = 7.0,
    modDefense = 11.0,
    modVitesse = 8.0,
    modAttaqueSpe = 6.5,
    modDefenseSpe = 11.5,
    modPv = 21.0,
    description = "Insecte à carapace luisante, se déplace par bonds et vibre des antennes."
)

val especeGalum = EspeceMonstre(
    id = 13,
    nom = "Galum",
    type = "Minéral",
    baseAttaque = 12,
    baseDefense = 15,
    baseVitesse = 6,
    baseAttaqueSpe = 8,
    baseDefenseSpe = 12,
    basePv = 55,
    modAttaque = 9.0,
    modDefense = 13.0,
    modVitesse = 4.0,
    modAttaqueSpe = 6.5,
    modDefenseSpe = 10.5,
    modPv = 13.0,
    description = "Golem ancien de pierre, yeux lumineux en garde."
)
fun main() {

    joueur.afficheDetail()
    rival.afficheDetail()
    joueur.argents+=50
    joueur.afficheDetail()
    /*
    println(changeCouleur("Hello","rouge"))
    println(changeCouleur("World","bleu"))
    println("Hello ${changeCouleur("my","jaune")} World")
    println(changeCouleur("Truc","marron"))
*/
}
/**
 * Change la couleur du message donné selon le nom de la couleur spécifié.
 * Cette fonction utilise les codes d'échappement ANSI pour appliquer une couleur à la sortie console. Si un nom de couleur
 * non reconnu ou une chaîne vide est fourni, aucune couleur n'est appliquée.
 *
 * @param message Le message auquel la couleur sera appliquée.
 * @param couleur Le nom de la couleur à appliquer (ex: "rouge", "vert", "bleu"). Par défaut c'est une chaîne vide, ce qui n'applique aucune couleur.
 * @return Le message coloré sous forme de chaîne, ou le même message si aucune couleur n'est appliquée.
 */
fun changeCouleur(message: String, couleur:String=""): String {
    val reset = "\u001B[0m"
    val codeCouleur = when (couleur.lowercase()) {
        "rouge" -> "\u001B[31m"
        "vert" -> "\u001B[32m"
        "jaune" -> "\u001B[33m"
        "bleu" -> "\u001B[34m"
        "magenta" -> "\u001B[35m"
        "cyan" -> "\u001B[36m"
        "blanc" -> "\u001B[37m"
        else -> "" // pas de couleur si non reconnu
    }
    return "$codeCouleur$message$reset"
}

