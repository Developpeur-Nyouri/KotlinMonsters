package org.example.dresseur

/**
 * Représente un entraîneur dans le contexte du jeu.
 *
 * Un entraîneur est responsable de gérer une équipe de monstres, une boîte pour stocker des monstres supplémentaires
 * et un sac contenant des objets appelés MonsterKubes. L'entraîneur a également une somme d'argent associée.
 *
 * @property id L'identifiant unique de l'entraîneur.
 * @property nom Le nom de l'entraîneur.
 * @property argents La quantité d'argent en possession de l'entraîneur.
 */
class Entraineur(
    var id: Int,
    var nom: String,
    var argents: Int,

    // TODO : liste des monstres actuellement dans l'équipe active de l'entraîneur (limitée, probablement 6 max comme dans les jeux du genre)
    //TODO equipeMonstre

    // TODO : liste des monstres "en réserve", stockés mais pas dans l'équipe active
    //TODO boiteMonstre

    // TODO : sac contenant les objets "MonsterKubes" (probablement les objets pour capturer les monstres)
    //TODO sacAKube
) {
    /**
     * Affiche les détails de l'entraîneur, y compris son nom et la quantité d'argent en sa possession.
     *
     * Cette méthode affiche les informations de l'entraîneur sous la forme de deux lignes :
     * 1. Le nom de l'entraîneur.
     * 2. La somme d'argent qu'il possède.
     */
    fun afficheDetail() {
        // Affiche le nom de l'entraîneur
        println("Dresseur : ${this.nom}")
        // Affiche la somme d'argent qu'il possède
        println("Argents: ${this.argents} ")
    }
}