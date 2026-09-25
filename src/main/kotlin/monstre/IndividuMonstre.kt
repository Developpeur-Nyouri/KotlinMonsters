package org.example.monstre

import org.example.dresseur.Entraineur
import kotlin.random.Random

class IndividuMonstre(
    var id: Int,
    var nom: String,
    expInit: Double,
    var espece: EspeceMonstre,
    var entraineur: Entraineur? = null
) {
    var niveau: Int = 1
    var attaque: Int = espece.baseAttaque + Random.nextInt(-2, 3)
    var defense: Int = espece.baseDefense + Random.nextInt(-2, 3)
    var vitesse: Int = espece.baseVitesse + Random.nextInt(-2, 3)
    var attaqueSpe: Int = espece.baseAttaqueSpe + Random.nextInt(-2, 3)
    var defenseSpe: Int = espece.baseDefenseSpe + Random.nextInt(-2, 3)
    var pvMax: Int = espece.basePv + Random.nextInt(-5, 6)
    var potentiel: Double = Random.nextDouble(0.5, 2.0)
    var exp: Double = 0.0
        get() = field
        set(value) {
            field = value
            val estNiveau1 = niveau == 1
            while (field >= palierExp(niveau)) {
                levelUp()
            }
        }
    var pv: Int = pvMax
        get() = field
        set(nouveauPv) {
            field = nouveauPv.coerceIn(0, pvMax)
        }
    /**
     * Calcule l'expérience totale nécessaire pour atteindre un niveau donné.
     *
     * @param niveau Niveau cible.
     * @return Expérience cumulée nécessaire pour atteindre ce niveau.
     */
    fun palierExp(niveau: Int): Double {
        return 100 * Math.pow((niveau - 1).toDouble(), 2.0)
    }
    fun levelUp() {
        niveau++
        attaque += (Math.round(espece.modAttaque * potentiel) + Random.nextInt(-2, 3)).toInt()
        defense += (Math.round(espece.modDefense * potentiel) + Random.nextInt(-2, 3)).toInt()
        vitesse += (Math.round(espece.modVitesse * potentiel) + Random.nextInt(-2, 3)).toInt()
        attaqueSpe += (Math.round(espece.modAttaqueSpe * potentiel) + Random.nextInt(-2, 3)).toInt()
        defenseSpe += (Math.round(espece.modDefenseSpe * potentiel) + Random.nextInt(-2, 3)).toInt()
        pvMax += (Math.round(espece.modPv * potentiel) + Random.nextInt(-5, 6)).toInt()
        println("Le monstre $nom est maintenant niveau $niveau !")
    }
    init {
        this.exp = expInit // applique le setter et déclenche un éventuel level-up
    }
}