package org.example.monstre

import java.io.File

// Représente une espèce de monstre : le "modèle générique" (ex: Flamkip, Aquamy...)
// avec ses statistiques de base, communes à tous les individus de cette espèce
class EspeceMonstre(
    var id: Int,
    var nom: String,
    var type: String, // type élémentaire de l'espèce (ex: Animal, Meteo, Insecte...)

    // Statistiques de base : point de départ utilisé par chaque IndividuMonstre
    // pour calculer ses propres stats (avec un aléatoire en plus)
    val baseAttaque: Int,
    val baseDefense: Int,
    val baseVitesse: Int,
    val baseAttaqueSpe: Int,
    val baseDefenseSpe: Int,
    val basePv: Int,

    // Modificateurs utilisés lors des montées de niveau (levelUp() dans IndividuMonstre)
    // pour calculer le gain de statistiques à chaque niveau
    val modAttaque: Double,
    val modDefense: Double,
    val modVitesse: Double,
    val modAttaqueSpe: Double,
    val modDefenseSpe: Double,
    val modPv: Double,

    // Informations textuelles optionnelles sur l'espèce (valeurs par défaut vides)
    val description: String = "",
    val particularites: String = "",
    val caractères: String = "",
) {
    /**
     * Affiche la représentation artistique ASCII du monstre.
     *
     * @param deFace Détermine si l'art affiché est de face (true) ou de dos (false).
     *               La valeur par défaut est true.
     * @return Une chaîne de caractères contenant l'art ASCII du monstre avec les codes couleur ANSI.
     *         L'art est lu à partir d'un fichier texte dans le dossier resources/art.
     */
    fun afficheArt(deFace: Boolean = true): String {
        // Choisit le nom du fichier selon qu'on veut l'art de face ou de dos
        val nomFichier = if (deFace) "front" else "back"

        // Lit le contenu du fichier texte correspondant, dans un dossier nommé d'après l'espèce
        // (ex: resources/art/flamkip/front.txt)
        val art = File("src/main/resources/art/${this.nom.lowercase()}/$nomFichier.txt").readText()

        // Remplace les "/" par un caractère visuellement proche pour éviter des conflits
        // (probablement des soucis liés aux séparateurs de chemin ou à l'affichage ASCII)
        val safeArt = art.replace("/", "∕")

        // Remplace le texte littéral "\u001B" par le vrai caractère d'échappement ANSI (Escape)
        // nécessaire pour que les codes couleur s'affichent correctement dans la console
        return safeArt.replace("\\u001B", "\u001B")
    }
}