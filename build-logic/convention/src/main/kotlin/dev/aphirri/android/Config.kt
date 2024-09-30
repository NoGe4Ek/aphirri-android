package dev.aphirri.android

object Config {
    const val APPLICATION_LABEL_KEY = "appName"
    const val VERSION_NAME_SUFFIX_SEPARATOR = "-"
    const val APPLICATION_ID_SUFFIX_SEPARATOR = "."
    const val NAME_SPACE_SEPARATOR = "."
    const val RESOURCES_PREFIX_SEPARATOR = "_"

    const val APPLICATION_LABEL = "Aphirri"
    const val APPLICATION_ID = "dev.aphirri.android"

    const val MIN_SDK = 29
    const val COMPILE_SDK = 35
    const val TARGET_SDK = 35
    const val JDK_VERSION = 21

    const val VERSION_CODE = 1
    private const val MAJOR = 1
    private const val MINOR = 0
    private const val PATCH = 0
    const val VERSION_NAME = "$MAJOR.$MINOR.$PATCH"
}
