package org.example.item

import org.example.dresseur.Entraineur

/**
 * Représente un Badge, qui hérite de la classe Item.
 */
class Badge(
    id: Int,
    nom: String,
    description: String,
    var champion: Entraineur? = null
) : Item(id, nom, description)