package com.example.anchorbay.data

enum class LabelCategory {
    Facilities,
    Nature
}

data class Label(val text: String, val category: LabelCategory)

enum class Direction {
    N, NE, E, SE, S, SW, W, NW
}

data class Localisation(val location: String, val direction: Direction?)

val baseLabels = listOf(
    Label("Toalett", LabelCategory.Facilities),
    Label("Dass", LabelCategory.Facilities),
    Label("Sopor", LabelCategory.Facilities),
    Label("Mataffär", LabelCategory.Facilities),
    Label("Resturang", LabelCategory.Facilities),
    Label("El", LabelCategory.Facilities),
    Label("Färskvatten", LabelCategory.Facilities),
    Label("Grillklippa", LabelCategory.Nature),
    Label("Promenadstigar", LabelCategory.Nature),
    Label("Möjlighet-att-går-runt-ön", LabelCategory.Nature),
    Label("Sandstrand", LabelCategory.Nature),
    Label("Hoppklippor", LabelCategory.Nature),
    Label("Utsiktstopp", LabelCategory.Nature),
)

data class Boat(val name: String)

val availableBoats = listOf(
    Boat("Katla"),
    Boat("Legolas III"),
    Boat("Kraken"),
    Boat("Talassa"),
    Boat("Sö gull"),
)

data class Bay(
    val rating: Int = 0,
    val nickname: String = "",
    val labels: List<Label> = emptyList(),
    val localisation: Localisation = Localisation("", null),
    val boat: Boat? = null,
    val comments: String = "",
)