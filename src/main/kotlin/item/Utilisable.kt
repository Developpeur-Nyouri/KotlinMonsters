package org.example.item

import org.example.monstre.IndividuMonstre

/**
 * Interface définissant le comportement d'un objet pouvant être utilisé sur un IndividuMonstre.
 */
interface Utilisable {
    /**
     * Applique l'effet de l'objet sur le monstre cible.
     *
     * @param cible Le monstre sur lequel l'objet est utilisé.
     * @return true si l'action a eu un effet, false sinon.
     */
    fun utiliser(cible: IndividuMonstre): Boolean
}