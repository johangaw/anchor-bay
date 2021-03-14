package com.example.anchorbay.data

enum class LabelCategory {
    Facilities,
    Nature
}

data class Label(val text: String, val category: LabelCategory)

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