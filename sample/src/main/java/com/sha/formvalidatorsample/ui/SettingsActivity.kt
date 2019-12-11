package com.sha.formvalidatorsample.ui

import android.annotation.TargetApi
import android.content.Context
import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.preference.ListPreference
import android.preference.Preference
import android.preference.PreferenceActivity
import android.preference.PreferenceCategory
import android.preference.PreferenceFragment
import android.preference.PreferenceManager

import com.sha.formvalidatorsample.R

/**
 * A [PreferenceActivity] that presents a set of application settings. On handset devices, settings are presented
 * as a single list. On tablets, settings are split by category, with category headers shown to the left of the list of
 * settings.
 *
 *
 * See [ Android Design: Settings](http://developer.android.com/design/patterns/settings.html) for design
 * guidelines and the [Settings API Guide](http://developer.android.com/guide/topics/ui/settings.html) for
 * more information on developing a Settings UI.
 */
class SettingsActivity : PreferenceActivity() {

    /**
     * {@inheritDoc}
     */
    override fun onIsMultiPane(): Boolean {
        return SettingsActivity.isXLargeTablet(this) && !SettingsActivity.isSimplePreferences(this)
    }

    /**
     * Set up the [android.app.ActionBar], if the API is available.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private fun setupActionBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            // Show the Up button in the action bar.
            actionBar!!.setDisplayHomeAsUpEnabled(true)
        }
    }

    /**
     * Shows the simplified settings UI if the device configuration if the device configuration dictates that a
     * simplified, single-pane UI should be shown.
     */
    private fun setupSimplePreferencesScreen() {
        if (!SettingsActivity.isSimplePreferences(this)) {
            return
        }

        // Class<ContactPreferenceFragment> a = ContactPreferenceFragment.class;

        // In the simplified UI, fragments are not used at all and we instead
        // use the older PreferenceActivity APIs.

        // Add 'general' preferences.
        val fakeHeader = PreferenceCategory(this)
        addPreferencesFromResource(R.xml.pref_contact)

        SettingsActivity.bindPreferenceSummaryToValue(findPreference(SettingsActivity.PREF_KEY_FULL_NAME))
        SettingsActivity.bindPreferenceSummaryToValue(findPreference(SettingsActivity.PREF_KEY_ADDRESS))
        SettingsActivity.bindPreferenceSummaryToValue(findPreference(SettingsActivity.PREF_KEY_PHONE_NUMBER))
        SettingsActivity.bindPreferenceSummaryToValue(findPreference(SettingsActivity.PREF_KEY_EMAIL))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)

        setupSimplePreferencesScreen()
    }

    /**
     * This fragment shows general preferences only. It is used when the activity is showing a two-pane settings UI.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    class ContactPreferenceFragment : PreferenceFragment() {

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            addPreferencesFromResource(R.xml.pref_contact)

            // Bind the summaries of EditText/List/Dialog/Ringtone preferences
            // to their values. When their values change, their summaries are
            // updated to reflect the new value, per the Android Design
            // guidelines.
            SettingsActivity.bindPreferenceSummaryToValue(findPreference(SettingsActivity.PREF_KEY_FULL_NAME))
            SettingsActivity.bindPreferenceSummaryToValue(findPreference(SettingsActivity.PREF_KEY_ADDRESS))
            SettingsActivity.bindPreferenceSummaryToValue(findPreference(SettingsActivity.PREF_KEY_PHONE_NUMBER))
            SettingsActivity.bindPreferenceSummaryToValue(findPreference(SettingsActivity.PREF_KEY_EMAIL))

        }
    }

    companion object {
        /**
         * Binds a preference's summary to its value. More specifically, when the preference's value is changed, its summary
         * (line of text below the preference title) is updated to reflect the value. The summary is also immediately
         * updated upon calling this method. The exact display format is dependent on the type of preference.
         *
         * @see .sBindPreferenceSummaryToValueListener
         */
        private fun bindPreferenceSummaryToValue(preference: Preference) {
            // Set the listener to watch for value changes.
            preference.onPreferenceChangeListener = SettingsActivity.sBindPreferenceSummaryToValueListener

            // Trigger the listener immediately with the preference's current value.
            val newValue = PreferenceManager.getDefaultSharedPreferences(preference.context).getString(preference.key, "")
            SettingsActivity.sBindPreferenceSummaryToValueListener.onPreferenceChange(preference, newValue)
        }

        /**
         * Determines whether the simplified settings UI should be shown. This is true if this is forced via
         * [.ALWAYS_SIMPLE_PREFS], or the device doesn't have newer APIs like [PreferenceFragment], or the
         * device doesn't have an extra-large screen. In these cases, a single-pane "simplified" settings UI should be
         * shown.
         */
        private fun isSimplePreferences(context: Context): Boolean {
            return (SettingsActivity.ALWAYS_SIMPLE_PREFS || Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB
                    || !SettingsActivity.isXLargeTablet(context))
        }

        /**
         * Helper method to determine if the device has an extra-large screen. For example, 10" tablets are extra-large.
         */
        private fun isXLargeTablet(context: Context): Boolean {
            return context.resources.configuration.screenLayout and Configuration.SCREENLAYOUT_SIZE_MASK >= Configuration.SCREENLAYOUT_SIZE_XLARGE
        }

        val PREF_KEY_EMAIL = "email"

        val PREF_KEY_PHONE_NUMBER = "phone_number"

        val PREF_KEY_ADDRESS = "address"

        val PREF_KEY_FULL_NAME = "full_name"

        /**
         * Determines whether to always show the simplified settings UI, where settings are presented in a single list. When
         * false, settings are shown as a master/detail two-pane view on tablets. When true, a single pane is shown on
         * tablets.
         */
        private val ALWAYS_SIMPLE_PREFS = false

        /**
         * A preference value change listener that updates the preference's summary to reflect its new value.
         */
        private val sBindPreferenceSummaryToValueListener = Preference.OnPreferenceChangeListener { preference, value ->
            val stringValue = value.toString()

            if (preference is ListPreference) {
                // For list preferences, look up the correct display value in
                // the preference's 'entries' list.
                val index = preference.findIndexOfValue(stringValue)

                // Set the summary to reflect the new value.
                preference.setSummary(if (index >= 0) preference.entries[index] else null)

            } else {
                // For all other preferences, set the summary to the value's
                // simple string representation.
                preference.summary = stringValue
            }
            true
        }
    }
}
