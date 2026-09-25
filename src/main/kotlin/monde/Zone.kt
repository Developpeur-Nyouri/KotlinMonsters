package org.example.monde

import org.example.monstre.EspeceMonstre

// Représente un lieu du monde du jeu (route, grotte, forêt...)
// où le joueur peut se déplacer et rencontrer des monstres sauvages
class Zone(
    var id: Int,
    var nom: String,
    var expZone: Int, // expérience associée à cette zone (probablement gagnée en combattant ici)

    // Liste des espèces de monstres qu'on peut rencontrer dans cette zone
    // Mutable car elle peut évoluer (ex: ajout/suppression d'espèces), vide par défaut
    var especesMonstres: MutableList<EspeceMonstre> = mutableListOf(),

    // Zone suivante et zone précédente : permettent de se déplacer dans le monde
    // Nullable (?) car une zone peut ne pas avoir de voisine (ex: début ou fin du parcours)
    var zoneSuivante: Zone? = null,
    var zonePrecedante: Zone? = null
) {
    // TODO : méthode qui génère un monstre aléatoire parmi les espèces de la zone
    // TODO genereMonstre()

    // TODO : méthode qui simule une rencontre avec un monstre sauvage dans la zone
    // TODO rencontreMonstre()
}