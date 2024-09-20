package org.company.app.presentation.ui.common

import eduapp.composeapp.generated.resources.Res
import eduapp.composeapp.generated.resources.art
import eduapp.composeapp.generated.resources.art_creativity
import eduapp.composeapp.generated.resources.children
import eduapp.composeapp.generated.resources.choreographic_creativity
import eduapp.composeapp.generated.resources.choreography
import eduapp.composeapp.generated.resources.classes_for_preschoolers
import eduapp.composeapp.generated.resources.decorative
import eduapp.composeapp.generated.resources.decorative_creativity
import eduapp.composeapp.generated.resources.ecology
import eduapp.composeapp.generated.resources.ecology_and_countries
import eduapp.composeapp.generated.resources.it
import eduapp.composeapp.generated.resources.it_technology
import eduapp.composeapp.generated.resources.music_creativity
import eduapp.composeapp.generated.resources.musik
import eduapp.composeapp.generated.resources.singing
import eduapp.composeapp.generated.resources.singing_direction
import eduapp.composeapp.generated.resources.sport
import eduapp.composeapp.generated.resources.sport_direction
import eduapp.composeapp.generated.resources.tech_art
import eduapp.composeapp.generated.resources.technical_creativity
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource


data class Category (
    val stringResource: StringResource,
    val string: String,
    val image: DrawableResource
)

object Categories {
    val list =  listOf(
        Category(Res.string.decorative_creativity,"decoration_creativity", Res.drawable.decorative),
        Category(Res.string.art_creativity,"art_creativity", Res.drawable.art),
        Category(Res.string.choreographic_creativity,"choreographic_creativity", Res.drawable.choreography),
        Category(Res.string.ecology_and_countries,"ecology_and_countries", Res.drawable.ecology),
        Category(Res.string.singing_direction,"singing_direction", Res.drawable.singing),
        Category(Res.string.music_creativity,"music_creativity", Res.drawable.musik),
        Category(Res.string.sport_direction,"sport_direction", Res.drawable.sport),
        Category(Res.string.technical_creativity,"technical_creativity", Res.drawable.tech_art),
        Category(Res.string.it,"it_technology", Res.drawable.it_technology),
        Category(Res.string.classes_for_preschoolers,"classes_for_preschoolers", Res.drawable.children),
        )
}
