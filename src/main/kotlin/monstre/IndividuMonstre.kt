package org.example.monstre

import org.example.dresseur.Entraineur
import kotlin.math.pow

/**
 * Représente un monstre individuel dans le jeu.
 */
class IndividuMonstre(
    var id: Int,
    var nom: String,
    expDepart: Double,
    val espece: EspeceMonstre,
    var entraineur: Entraineur? = null
) {
    var niveau: Int = 1
    var exp: Double = expDepart
        set(value) {
            field = value
            levelUp()
        }

    var pvMax: Int = 0
    var pv: Int = 0
        set(value) {
            field = value.coerceIn(0, pvMax)
        }

    var attaque: Int = 0
    var defense: Int = 0
    var vitesse: Int = 0
    var attaqueSpe: Int = 0
    var defenseSpe: Int = 0

    init {
        // Applique l'expérience de départ pour calculer le niveau et les stats
        this.exp = expDepart
        this.pv = this.pvMax
    }

    /**
     * Calcule le palier d'expérience nécessaire pour un niveau donné.
     */
    fun palierExp(niv: Int): Double {
        return 10.0 * niv.toDouble().pow(3) / 4.0
    }

    /**
     * Recalcule les statistiques du monstre en fonction de son niveau.
     */
    fun recalculerStats() {
        pvMax = espece.basePv + ((niveau - 1) * espece.modPv).toInt()
        attaque = espece.baseAttaque + ((niveau - 1) * espece.modAttaque).toInt()
        defense = espece.baseDefense + ((niveau - 1) * espece.modDefense).toInt()
        vitesse = espece.baseVitesse + ((niveau - 1) * espece.modVitesse).toInt()
        attaqueSpe = espece.baseAttaqueSpe + ((niveau - 1) * espece.modAttaqueSpe).toInt()
        defenseSpe = espece.baseDefenseSpe + ((niveau - 1) * espece.modDefenseSpe).toInt()
    }

    /**
     * Fait monter le monstre en niveau tant qu'il a assez d'expérience.
     */
    private fun levelUp() {
        while (exp >= palierExp(niveau + 1)) {
            niveau++
            println("$nom monte au niveau $niveau !")
        }
        val anciensPvMax = pvMax
        recalculerStats()
        if (pvMax > anciensPvMax && anciensPvMax > 0) {
            pv += (pvMax - anciensPvMax)
        }
    }

    /**
     * Ajoute de l'expérience au monstre.
     */
    fun ajouterExp(gainExp: Double) {
        this.exp += gainExp
    }

    /**
     * Effectue une attaque simple contre un monstre cible.
     */
    fun attaquer(cible: IndividuMonstre) {
        val degats = (this.attaque - cible.defense / 2).coerceAtLeast(1)
        println("${this.nom} attaque ${cible.nom} et inflige $degats dégâts !")
        cible.pv -= degats
    }

    /**
     * Permet de renommer le monstre.
     */
    fun renommer(nouveauNom: String) {
        if (nouveauNom.isNotBlank()) {
            println("$nom est renommé en $nouveauNom !")
            this.nom = nouveauNom
        }
    }

    /**
     * Affiche les détails et statistiques du monstre.
     */
    fun afficheDetail() {
        println("=== MONSTRE : $nom (${espece.nom}) ===")
        println("Niveau : $niveau | EXP : $exp / ${palierExp(niveau + 1)}")
        println("PV : $pv / $pvMax")
        println("Attaque : $attaque | Défense : $defense")
        println("Vitesse : $vitesse")
        println("Attaque Spé : $attaqueSpe | Défense Spé : $defenseSpe")
        println("======================================")
    }
}