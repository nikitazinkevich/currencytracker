package com.example.currencytracker.feature.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.preference.CheckBoxPreference
import androidx.preference.PreferenceFragmentCompat
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.currencytracker.MainActivity
import com.example.currencytracker.R
import com.example.currencytracker.databinding.FragmentSortSettingsBinding


class SortSettingsFragment : PreferenceFragmentCompat() {

    private val toolbarBinding by viewBinding(FragmentSortSettingsBinding::bind)
    private var preferences: List<CheckBoxPreference> = listOf()
    private val mainActivity by lazy {
        (requireActivity() as MainActivity)
    }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(R.xml.sort_settings_fragment)
        initPreferencesList()
        checkFirstInit()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mainActivity.hideBottomNavigation()
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initSettingsToolbar()
    }

    override fun onDestroy() {
        super.onDestroy()
        mainActivity.showBottomNavigation()
    }

    private fun initSettingsToolbar() {
        toolbarBinding.backButton.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun initPreferencesList() {
        val preferencesKeysArray =  SortSettings.getPreferencesKeys()
        preferences = preferencesKeysArray.mapNotNull { keyId ->
            findCheckBoxPreference(keyId)
        }
    }

    private fun findCheckBoxPreference(keyId: String): CheckBoxPreference? {
        val checkBoxPreference = findPreference<CheckBoxPreference>(keyId)
            ?: return null
        checkBoxPreference.setOnPreferenceClickListener {
            if (checkBoxPreference.isChecked) {
                preferences
                    .filter { it.key != checkBoxPreference.key }
                    .map { it.isChecked = false }
                SortSettings.updateCurrentSortSettingsKey(checkBoxPreference.key)
            }
            false
        }
        return checkBoxPreference
    }


    private fun checkFirstInit() {
        if (isFirstInit()) {
            preferences.first().isChecked = true
        }
    }

    private fun isFirstInit(): Boolean {
        return preferences.all { !it.isChecked }
    }
}

