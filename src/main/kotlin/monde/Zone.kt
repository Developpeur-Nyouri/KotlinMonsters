package org.example.monde

import org.example.dresseur.Entraineur
import org.example.jeu.CombatMonstre
import org.example.monstre.EspeceMonstre
import org.example.monstre.IndividuMonstre
import kotlin.random.Random

// Représente un lieu du monde du jeu (route, grotte, forêt...)
// où le joueur peut se déplacer et rencontrer des monstres sauvages
class Zone(
    var id: Int,
    var nom: String,
    var expZone: Int, // expérience associée à cette zone

    // Liste des espèces de monstres qu'on peut rencontrer dans cette zone
    var especesMonstres: MutableList<EspeceMonstre> = mutableListOf(),

    // Zone suivante et zone précédente : permettent de se déplacer dans le monde
    var zoneSuivante: Zone? = null,
    var zonePrecedante: Zone? = null
) {

    /**
     * Génère un monstre sauvage appartenant à la zone.
     */
    fun genereMonstre(): IndividuMonstre {
        val especeChoisie = especesMonstres.random()

        // Expérience de la zone +/- 20%
        val variation = expZone * 0.20
        val expGagnee = Random.nextDouble(expZone - variation, expZone + variation)

        return IndividuMonstre(
            id = Random.nextInt(1, 10000),
            nom = especeChoisie.nom,
            expDepart = expGagnee,
            espece = especeChoisie
        )
    }

    /**
     * Simule une rencontre et lance le combat avec un monstre sauvage.
     */
    fun rencontreMonstre(joueur: Entraineur) {
        val monstreSauvage = genereMonstre()

        // Trouve le premier monstre valide du joueur (PV > 0)
        val premierPokemon = joueur.equipeMonstre.firstOrNull { it.pv > 0 }

        if (premierPokemon != null) {
            val combat = CombatMonstre(premierPokemon, monstreSauvage)
            combat.lancerCombat()
        } else {
            println("Tous vos monstres sont KO ! Impossible de combattre.")
        }
    }
}