package dev.aphirri.android.plugins.common

import dev.aphirri.android.Config.APPLICATION_ID_SUFFIX_SEPARATOR
import dev.aphirri.android.Config.APPLICATION_LABEL
import dev.aphirri.android.Config.APPLICATION_LABEL_KEY
import dev.aphirri.android.Config.VERSION_NAME_SUFFIX_SEPARATOR
import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.ApplicationProductFlavor
import com.android.build.api.dsl.CommonExtension
import com.android.build.api.dsl.ProductFlavor

@Suppress("EnumEntryName")
enum class FlavorDimension {
    version
}

@Suppress("EnumEntryName")
enum class AphirriFlavor(val dimension: FlavorDimension, val suffix: String? = null) {
    stage(FlavorDimension.version, suffix = "stage"),
    prod(FlavorDimension.version)
}

internal inline fun <reified T : ProductFlavor> CommonExtension<*, *, *, *, *, *>.configureFlavors(
    crossinline flavorConfigurationBlock: T.(flavor: AphirriFlavor) -> Unit = {}
) {
    // Register flavor dimensions
    FlavorDimension.values().forEach {
        flavorDimensions += it.name
    }

    productFlavors {
        AphirriFlavor.values().forEach {
            val productFlavor = maybeCreate(it.name) as? T ?: error("Unspecified flavor class")
            productFlavor.apply {
                flavorConfigurationBlock(this, it)
                dimension = it.dimension.name
                if (this is ApplicationExtension && this is ApplicationProductFlavor) {
                    manifestPlaceholders += APPLICATION_LABEL_KEY to "$APPLICATION_LABEL ${it.suffix}"
                    it.suffix?.let { suffix ->
                        configureSuffixes(suffix)
                    }
                }
            }
        }
    }
}

internal fun ApplicationProductFlavor.configureSuffixes(suffix: String) {
    applicationIdSuffix = "${APPLICATION_ID_SUFFIX_SEPARATOR}$suffix"
    versionNameSuffix = "$VERSION_NAME_SUFFIX_SEPARATOR$suffix}"
}