package ru.vitkt.androidanimal;

import ru.vitkt.androidanimalwallpaper.R;

import android.os.Build;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.widget.Toast;

public class AnimalPreference extends PreferenceActivity {

	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
			addPreferencesFromResource(R.xml.pref);
		} else {
			getFragmentManager()
					.beginTransaction()
					.replace(android.R.id.content,
							new AnimalPreferenceFragment()).commit();
		}
	}

	private class AnimalPreferenceFragment extends PreferenceFragment {

		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			addPreferencesFromResource(R.xml.pref);
			Preference columnsPreference = getPreferenceScreen()
					.findPreference("columns");
			Preference rowsPreference = getPreferenceScreen().findPreference(
					"rows");
			rowsPreference.setOnPreferenceChangeListener(validator);
			columnsPreference.setOnPreferenceChangeListener(validator);
		}

		Preference.OnPreferenceChangeListener validator = new OnPreferenceChangeListener() {

			@Override
			public boolean onPreferenceChange(Preference preference,
					Object newValue) {
				// check that the string is an integer

				if (newValue != null && newValue.toString().length() > 0
						&& newValue.toString().matches("\\d*")) {
					int value = Integer.valueOf(newValue.toString());
					if (value < 1 || value > 100)
					{
						Toast.makeText(AnimalPreference.this,R.string.big_number,
								Toast.LENGTH_LONG).show();
						return false;
					}

					return true;
				}

				Toast.makeText(AnimalPreference.this,R.string.num_parse_error,
						Toast.LENGTH_LONG).show();
				return false;
			}
		};

	}

}
