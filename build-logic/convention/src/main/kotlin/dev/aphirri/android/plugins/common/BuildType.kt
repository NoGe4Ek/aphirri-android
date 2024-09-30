package dev.aphirri.android.plugins.common

import dev.aphirri.android.Config.APPLICATION_ID_SUFFIX_SEPARATOR
import dev.aphirri.android.Config.VERSION_NAME_SUFFIX_SEPARATOR
import com.android.build.api.dsl.ApplicationBuildType
import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.BuildType
import com.android.build.api.dsl.CommonExtension
import com.android.build.api.dsl.ProductFlavor

@Suppress("EnumEntryName")
internal enum class AphirriBuildType(val suffix: String? = null) {
    debug(suffix = "debug"),
    release,
}

internal inline fun <reified T : BuildType, reified E : CommonExtension<*, *, *, *, *, *>> E.configureBuildTypes(
    crossinline buildTypeConfigurationBlock: T.(buildType: AphirriBuildType) -> Unit = {}
) {
    buildTypes {
        AphirriBuildType.values().forEach {
            val buildType = maybeCreate(it.name) as? T ?: error("Unspecified build type class")
            buildType.apply {
                buildTypeConfigurationBlock(it)
                if (E::class == ApplicationExtension::class && buildType is ApplicationBuildType) {
                    it.suffix?.let { suffix ->
                        buildType.configureSuffixes(suffix)
                    }
                }
            }
        }
    }
}

internal fun ApplicationBuildType.configureSuffixes(suffix: String) {
    applicationIdSuffix = "${APPLICATION_ID_SUFFIX_SEPARATOR}$suffix"
    versionNameSuffix = "$VERSION_NAME_SUFFIX_SEPARATOR$suffix}"
}