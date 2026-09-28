package org.example.jeu

import org.example.dresseur.Entraineur
import org.example.monde.Zone
import org.example.monstre.IndividuMonstre

class Partie(
    val id: Int,
    val joueur: Entraineur,
    var zone: Zone
) {

    /**
     * Propose au joueur de choisir son starter selon l'organigramme.
     */
    fun choixStarter() {
        val monstre1 = zone.genereMonstre()
        val monstre2 = zone.genereMonstre()
        val monstre3 = zone.genereMonstre()

        var starter: IndividuMonstre? = null

        while (starter == null) {
            println("=== CHOIX DU STARTER ===")
            println("1. ${monstre1.nom} (PV: ${monstre1.pvMax}, Att: ${monstre1.attaque})")
            println("2. ${monstre2.nom} (PV: ${monstre2.pvMax}, Att: ${monstre2.attaque})")
            println("3. ${monstre3.nom} (PV: ${monstre3.pvMax}, Att: ${monstre3.attaque})")
            print("Faites votre choix (1..3) : ")

            val choixSelection = readlnOrNull()?.trim()

            when (choixSelection) {
                "1" -> starter = monstre1
                "2" -> starter = monstre2
                "3" -> starter = monstre3
                else -> println("Choix invalide. Veuillez saisir 1, 2 ou 3.\n")
            }
        }

        val monstreDefinitif = starter

        print("Voulez-vous donner un nom à votre monstre ? (Laissez vide pour garder ${monstreDefinitif.nom}) : ")
        val nouveauNom = readlnOrNull()?.trim()
        if (!nouveauNom.isNullOrEmpty()) {
            monstreDefinitif.renommer(nouveauNom)
        }

        joueur.equipeMonstre.add(monstreDefinitif)
        monstreDefinitif.entraineur = joueur

        println("Félicitations ! ${monstreDefinitif.nom} a rejoint votre équipe.")
    }

    /**
     * Inverse la position de deux monstres dans l'équipe du joueur.
     */
    fun modifierOrdreEquipe() {
        val equipe = joueur.equipeMonstre
        if (equipe.size <= 1) {
            println("Vous n'avez pas assez de monstres pour changer l'ordre.")
            return
        }

        println("--- Équipe actuelle ---")
        equipe.forEachIndexed { index, m ->
            println("${index + 1}. ${m.nom} (PV: ${m.pv}/${m.pvMax})")
        }

        print("Choisissez le numéro du premier monstre : ")
        val pos1 = (readlnOrNull()?.toIntOrNull() ?: 0) - 1

        print("Choisissez le numéro du deuxième monstre : ")
        val pos2 = (readlnOrNull()?.toIntOrNull() ?: 0) - 1

        if (pos1 in equipe.indices && pos2 in equipe.indices && pos1 != pos2) {
            val temp = equipe[pos1]
            equipe[pos1] = equipe[pos2]
            equipe[pos2] = temp
            println("Ordre de l'équipe modifié avec succès !")
        } else {
            println("Saisie invalide ou mêmes positions choisies.")
        }
    }

    /**
     * Affiche les informations sur les monstres de l'équipe.
     */
    fun examineEquipe() {
        var enCours = true
        while (enCours) {
            println("\n=== ÉQUIPE DE ${joueur.nom.uppercase()} ===")
            joueur.equipeMonstre.forEachIndexed { index, m ->
                println("${index + 1}. ${m.nom} - Niv. ${m.niveau} (PV: ${m.pv}/${m.pvMax})")
            }
            println("Tapez un numéro pour voir le détail d'un monstre.")
            println("Tapez 'm' pour modifier l'ordre.")
            println("Tapez 'q' pour quitter le menu principal.")
            print("Votre choix : ")

            val saisie = readlnOrNull()?.trim()?.lowercase()
            when {
                saisie == "q" -> enCours = false
                saisie == "m" -> modifierOrdreEquipe()
                saisie != null && saisie.toIntOrNull() != null -> {
                    val index = saisie.toInt() - 1
                    if (index in joueur.equipeMonstre.indices) {
                        val monstre = joueur.equipeMonstre[index]
                        println("\n--- Détail de ${monstre.nom} ---")
                        println("Espèce : ${monstre.espece.nom}")
                        println("Niveau : ${monstre.niveau}")
                        println("PV : ${monstre.pv}/${monstre.pvMax}")
                        println("Attaque : ${monstre.attaque}")
                        println("Défense : ${monstre.defense}")
                        println("Vitesse : ${monstre.vitesse}")
                        println("Expérience : ${monstre.exp}")
                    } else {
                        println("Numéro invalide.")
                    }
                }
                else -> println("Choix invalide.")
            }
        }
    }

    /**
     * Gère les actions possibles du joueur dans sa zone.
     */
    fun jouer() {
        var enJeu = true
        while (enJeu) {
            println("\n==========================================")
            println("Vous êtes actuellement dans la zone : ${zone.nom}")
            println("1. Rencontrer un monstre sauvage")
            println("2. Examiner l'équipe de monstres")
            println("3. Aller à la zone suivante")
            println("4. Aller à la zone précédente")
            println("5. Quitter le jeu")
            print("Votre choix : ")

            when (readlnOrNull()?.trim()) {
                "1" -> zone.rencontreMonstre(joueur)
                "2" -> examineEquipe()
                "3" -> {
                    val suivante = zone.zoneSuivante
                    if (suivante != null) {
                        zone = suivante
                        println("Vous vous déplacez vers ${zone.nom}.")
                    } else {
                        println("Il n'y a pas de zone suivante !")
                    }
                }
                "4" -> {
                    val precedente = zone.zonePrecedante
                    if (precedente != null) {
                        zone = precedente
                        println("Vous vous déplacez vers ${zone.nom}.")
                    } else {
                        println("Il n'y a pas de zone précédente !")
                    }
                }
                "5" -> {
                    println("Fin de la partie. À bientôt !")
                    enJeu = false
                }
                else -> println("Choix invalide.")
            }
        }
    }
}