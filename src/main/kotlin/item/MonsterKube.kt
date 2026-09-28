package org.example.item

import org.example.dresseur.Entraineur
import org.example.monstre.IndividuMonstre
import kotlin.random.Random

/**
 * Représente un MonsterKube permettant de capturer des monstres.
 */
class MonsterKube(
    id: Int,
    nom: String,
    description: String,
    val chanceCapture: Double
) : Item(id, nom, description), Utilisable {

    override fun utiliser(cible: IndividuMonstre): Boolean {
        println("Vous lancez le MonsterKube !")

        // 1. Un monstre qui a déjà un entraîneur ne peut pas être capturé
        if (cible.entraineur != null) {
            println("Ce monstre ne peut pas être capturé !")
            return false
        }

        // 2. Calcul du ratio de vie et de la chance effective de capture
        val ratioVie = cible.pv.toDouble() / cible.pvMax.toDouble()
        var chanceEffective = chanceCapture * (1.5 - ratioVie)
        chanceEffective = chanceEffective.coerceAtLeast(5.0)

        // 3. Tirage aléatoire entre 0.0 et 100.0
        val tirageAlea = Random.nextDouble(0.0, 100.0)

        return if (tirageAlea < chanceEffective) {
            println("Le monstre ${cible.nom} est capturé !")

            // Demander un nouveau nom à l'utilisateur
            print("Entrez un nouveau nom pour le monstre (ou appuyez sur Entrée pour ignorer) : ")
            val nouveauNom = readlnOrNull()
            if (!nouveauNom.isNullOrBlank()) {
                cible.nom = nouveauNom
            }

            true
        } else {
            println("Presque ! Le Kube n'a pas pu capturer le monstre !")
            false
        }
    }
}