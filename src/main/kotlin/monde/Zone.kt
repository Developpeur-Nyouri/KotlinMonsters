package org.example.monde

import org.example.monstre.EspeceMonstre

class Zone(
    var id: Int,
    var nom: String,
    var expZone: Int,
    var especesMonstres: MutableList<EspeceMonstre> = mutableListOf(),
    var zoneSuivante: Zone? = null,
    var zonePrecedante: Zone? = null
) {
    // TODO genereMonstre()
    // TODO rencontreMonstre()
}