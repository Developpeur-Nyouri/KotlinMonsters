package org.example.monstre

import org.example.dresseur.Entraineur
import kotlin.random.Random

// Représente un monstre individuel et unique possédé par un joueur ou rencontré en jeu
// (contrairement à EspeceMonstre qui ne décrit que le "modèle" générique de l'espèce)
class IndividuMonstre(
    var id: Int,
    var nom: String,
    expInit: Double, // paramètre simple (pas val/var) : sert uniquement à l'initialisation, n'est pas stocké comme propriété
    var espece: EspeceMonstre, // l'espèce à laquelle appartient ce monstre (ex: Flamkip)
    var entraineur: Entraineur? = null // le dresseur qui possède ce monstre, null si sauvage
) {
    // Niveau de départ du monstre
    var niveau: Int = 1

    // Chaque statistique part de la stat de base de l'espèce, avec un bonus/malus aléatoire entre -2 et +2
    // pour que deux monstres de la même espèce ne soient jamais parfaitement identiques
    var attaque: Int = espece.baseAttaque + Random.nextInt(-2, 3)
    var defense: Int = espece.baseDefense + Random.nextInt(-2, 3)
    var vitesse: Int = espece.baseVitesse + Random.nextInt(-2, 3)
    var attaqueSpe: Int = espece.baseAttaqueSpe + Random.nextInt(-2, 3)
    var defenseSpe: Int = espece.baseDefenseSpe + Random.nextInt(-2, 3)

    // Les PV max suivent la même logique, mais avec un aléatoire plus large (-5 à +5)
    var pvMax: Int = espece.basePv + Random.nextInt(-5, 6)

    // "Potentiel" propre à cet individu : multiplicateur aléatoire utilisé plus tard dans levelUp()
    // pour rendre chaque monstre unique dans sa progression
    var potentiel: Double = Random.nextDouble(0.5, 2.0)

    // Expérience actuelle du monstre, avec un comportement personnalisé (setter custom)
    var exp: Double = 0.0
        get() = field // renvoie simplement la valeur stockée, comportement par défaut
        set(value) {
            field = value // on stocke d'abord la nouvelle valeur d'expérience
            val estNiveau1 = niveau == 1 // vérifie si le monstre est encore niveau 1 (info non utilisée pour l'instant)
            // tant que l'exp actuelle dépasse le palier requis pour le niveau en cours,
            // on fait monter le monstre de niveau (boucle utile si plusieurs niveaux d'un coup)
            while (field >= palierExp(niveau)) {
                levelUp()
            }
        }

    // Points de vie actuels, avec un setter personnalisé
    var pv: Int = pvMax
        get() = field // comportement par défaut pour la lecture
        set(nouveauPv) {
            // on "bride" la valeur entre 0 et pvMax pour éviter des PV négatifs ou supérieurs au max
            field = nouveauPv.coerceIn(0, pvMax)
        }

    /**
     * Calcule l'expérience totale nécessaire pour atteindre un niveau donné.
     *
     * @param niveau Niveau cible.
     * @return Expérience cumulée nécessaire pour atteindre ce niveau.
     */
    fun palierExp(niveau: Int): Double {
        // Formule : 100 * (niveau - 1)^2
        return 100 * Math.pow((niveau - 1).toDouble(), 2.0)
    }

    // Fait monter le monstre d'un niveau : augmente le niveau et toutes ses statistiques
    fun levelUp() {
        niveau++ // incrémente le niveau

        // Pour chaque statistique : on ajoute (mod de l'espèce * potentiel de l'individu, arrondi)
        // plus un petit bonus/malus aléatoire entre -2 et +2 (ou -5/+5 pour les PV)
        attaque += (Math.round(espece.modAttaque * potentiel) + Random.nextInt(-2, 3)).toInt()
        defense += (Math.round(espece.modDefense * potentiel) + Random.nextInt(-2, 3)).toInt()
        vitesse += (Math.round(espece.modVitesse * potentiel) + Random.nextInt(-2, 3)).toInt()
        attaqueSpe += (Math.round(espece.modAttaqueSpe * potentiel) + Random.nextInt(-2, 3)).toInt()
        defenseSpe += (Math.round(espece.modDefenseSpe * potentiel) + Random.nextInt(-2, 3)).toInt()
        pvMax += (Math.round(espece.modPv * potentiel) + Random.nextInt(-5, 6)).toInt()

        // Message affiché à chaque montée de niveau
        println("Le monstre $nom est maintenant niveau $niveau !")
    }

    // Bloc exécuté automatiquement à la création de l'objet
    init {
        // Applique expInit via le setter de exp, ce qui déclenche automatiquement
        // un ou plusieurs levelUp() si l'expérience de départ est suffisante
        this.exp = expInit
    }
}