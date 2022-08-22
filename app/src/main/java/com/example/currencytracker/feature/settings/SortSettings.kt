package com.example.currencytracker.feature.settings

import androidx.preference.PreferenceManager
import com.example.currencytracker.App
import com.example.currencytracker.R

object SortSettings {

    var currentSortSettingsKey: SortSettingsKey
        private set
    private var stringArrayResourceId: Int = R.array.sort_settings_preferences_keys
    private val preferencesKeysArray: Array<String> by lazy { obtainPreferencesKeys() }

    init {
        currentSortSettingsKey = setDefaultSortingKey()
    }

    fun getPreferencesKeys(): Array<String> {
        return preferencesKeysArray
    }

    fun updateCurrentSortSettingsKey(key: String) {
        currentSortSettingsKey = createSortSettingsKey(key)
    }

    private fun createSortSettingsKey(key: String): SortSettingsKey {
        return when (key) {
            preferencesKeysArray[0] -> SortSettingsKey.Alphabetical.also {
                SortSettingsKey.Alphabetical.isAscending = true
            }
            preferencesKeysArray[1] -> SortSettingsKey.Alphabetical.also {
                SortSettingsKey.Alphabetical.isAscending = false
            }
            preferencesKeysArray[2] -> SortSettingsKey.Value.also {
                SortSettingsKey.Value.isAscending = true
            }
            else -> SortSettingsKey.Value.also { SortSettingsKey.Value.isAscending = false }
        }
    }

    private fun setDefaultSortingKey(): SortSettingsKey {
        val preferencesMap = PreferenceManager.getDefaultSharedPreferences(App.instance).all
        val activePreferencePair = preferencesMap.toList().find { it.second == true }
        return if (activePreferencePair != null) {
            createSortSettingsKey(activePreferencePair.first)
        } else SortSettingsKey.Alphabetical.also { SortSettingsKey.Alphabetical.isAscending = true }
    }

    private fun obtainPreferencesKeys(): Array<String> {
        return App.instance.resources.getStringArray(stringArrayResourceId)
    }

}

sealed class SortSettingsKey {
    abstract var isAscending: Boolean

    object Alphabetical : SortSettingsKey() {
        override var isAscending: Boolean = false
    }

    object Value : SortSettingsKey() {
        override var isAscending: Boolean = false
    }
}

