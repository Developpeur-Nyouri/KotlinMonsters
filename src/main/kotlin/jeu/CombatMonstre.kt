package org.example.jeu

import org.example.item.Utilisable
import org.example.monstre.IndividuMonstre

/**
 * Représente un combat entre un monstre du joueur et un monstre sauvage.
 */
class CombatMonstre(
    var monstreJoueur: IndividuMonstre,
    val monstreSauvage: IndividuMonstre
) {
    var round: Int = 1

    /**
     * Vérifie si le joueur a perdu le combat.
     * Condition de défaite : aucun monstre de l'équipe du joueur n'a de PV > 0.
     */
    fun gameOver(): Boolean {
        val entraineur = monstreJoueur.entraineur
        if (entraineur != null) {
            return entraineur.equipeMonstre.none { it.pv > 0 }
        }
        return monstreJoueur.pv <= 0
    }

    /**
     * Indique si le joueur a gagné le combat et attribue l'expérience.
     */
    fun joueurGagne(): Boolean {
        if (monstreSauvage.pv > 0) {
            if (monstreSauvage.entraineur != null) {
                println("${monstreJoueur.nom} a gagné !")
                return true
            }
            return false
        }

        println("${monstreJoueur.nom} a gagné !")
        val gainExp = monstreSauvage.exp * 0.20
        monstreJoueur.ajouterExp(gainExp)
        println("${monstreJoueur.nom} gagne ${gainExp} exp !")
        return true
    }

    /**
     * L'adversaire attaque si ses PV sont supérieurs à 0.
     */
    fun actionAdversaire() {
        if (monstreSauvage.pv > 0) {
            monstreSauvage.attaquer(monstreJoueur)
        }
    }

    /**
     * Propose le menu d'actions au joueur pendant le combat.
     * @return true si le combat continue, false sinon.
     */
    fun actionJoueur(): Boolean {
        val dresseur = monstreJoueur.entraineur

        println("\n--- Que souhaitez-vous faire ? ---")
        println("1. Attaquer")
        println("2. Utiliser un objet")
        println("3. Changer de monstre")
        print("Votre choix : ")

        when (readlnOrNull()?.trim()) {
            "1" -> {
                monstreJoueur.attaquer(monstreSauvage)
                return true
            }
            "2" -> {
                if (dresseur == null || dresseur.sacAItems.isEmpty()) {
                    println("Vous n'avez aucun objet utilisable dans votre sac !")
                    return true
                }

                println("--- Sac à objets ---")
                dresseur.sacAItems.forEachIndexed { index, item ->
                    println("${index + 1}. ${item.nom} - ${item.description}")
                }
                print("Choisissez un objet à utiliser (ou 0 pour annuler) : ")
                val choix = readlnOrNull()?.toIntOrNull()

                if (choix != null && choix in 1..dresseur.sacAItems.size) {
                    val itemChoisi = dresseur.sacAItems[choix - 1]
                    if (itemChoisi is Utilisable) {
                        val reussi = itemChoisi.utiliser(monstreSauvage)
                        if (reussi) {
                            dresseur.sacAItems.removeAt(choix - 1)
                        }
                    } else {
                        println("Cet objet ne peut pas être utilisé en combat.")
                    }
                }
                return true
            }
            "3" -> {
                if (dresseur == null || dresseur.equipeMonstre.size <= 1) {
                    println("Vous n'avez pas d'autre monstre dans votre équipe !")
                    return true
                }

                println("--- Choisir un nouveau monstre ---")
                dresseur.equipeMonstre.forEachIndexed { index, m ->
                    println("${index + 1}. ${m.nom} (PV: ${m.pv}/${m.pvMax})")
                }
                print("Sélectionnez le numéro du monstre : ")
                val choix = readlnOrNull()?.toIntOrNull()

                if (choix != null && choix in 1..dresseur.equipeMonstre.size) {
                    val nouveauMonstre = dresseur.equipeMonstre[choix - 1]
                    if (nouveauMonstre.pv > 0) {
                        monstreJoueur = nouveauMonstre
                        println("Vous envoyez ${monstreJoueur.nom} au combat !")
                    } else {
                        println("Ce monstre est KO, vous ne pouvez pas l'envoyer !")
                    }
                }
                return true
            }
            else -> {
                println("Choix invalide. Vous passez votre tour.")
                return true
            }
        }
    }

    /**
     * Affiche l'état du combat selon l'organigramme.
     */
    fun afficheCombat() {
        println("========== Debut Round : $round ==========")
        println("Niveau : ${monstreSauvage.niveau}")
        println("PV : ${monstreSauvage.pv} / ${monstreSauvage.pvMax}")
        println(monstreSauvage.espece.description)
        println(monstreJoueur.espece.description)
        println("Niveau : ${monstreJoueur.niveau}")
        println("PV : ${monstreJoueur.pv} / ${monstreJoueur.pvMax}")
    }

    /**
     * Gère le déroulement d'un tour selon la vitesse des monstres.
     */
    fun jouer() {
        val joueurPlusRapide = monstreJoueur.vitesse >= monstreSauvage.vitesse

        this.afficheCombat()

        if (joueurPlusRapide) {
            val continuer = this.actionJoueur()
            if (!continuer) return

            if (!this.joueurGagne()) {
                this.actionAdversaire()
            }
        } else {
            this.actionAdversaire()
            if (!this.gameOver()) {
                val continuer = this.actionJoueur()
                if (!continuer) return
            }
        }
    }

    /**
     * Lance le combat et gère les rounds jusqu'à la victoire ou la défaite.
     */
    fun lancerCombat() {
        while (!gameOver() && !joueurGagne()) {
            this.jouer()
            round++
        }

        if (gameOver()) {
            println("Vous avez perdu le combat...")
            val entraineur = monstreJoueur.entraineur
            if (entraineur != null) {
                // Restaure les PV de tous les monstres de l'équipe
                for (monstre in entraineur.equipeMonstre) {
                    monstre.pv = monstre.pvMax
                }
                println("Les PV de tous vos monstres ont été restaurés.")
            } else {
                monstreJoueur.pv = monstreJoueur.pvMax
            }
        }
    }
}