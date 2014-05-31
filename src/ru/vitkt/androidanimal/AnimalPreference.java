package ru.vitkt.androidanimal;

import ru.vitkt.androidanimalwallpaper.R;
import android.app.Activity;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.widget.Toast;

public class AnimalPreference extends PreferenceActivity{
	// TODO Исправить баг с Preference
	PreferenceManager manager;
	
	 @Override
	  protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    
	    addPreferencesFromResource(R.xml.pref);
	    //manager = getPreferenceManager();

	    Preference columnsPreference = getPreferenceScreen().findPreference("columns");
	    Preference rowsPreference = getPreferenceScreen().findPreference("rows");

	    rowsPreference.setOnPreferenceChangeListener(validator);
	    columnsPreference.setOnPreferenceChangeListener(validator);
	    // add the validator
	   // circlePreference.setOnPreferenceChangeListener(numberCheckListener);
	  }

	  
	/**
	   * Checks that a preference is a valid numerical value
	   */

	  Preference.OnPreferenceChangeListener validator = new OnPreferenceChangeListener() {

	    @Override
	    public boolean onPreferenceChange(Preference preference, Object newValue) {
	      // check that the string is an integer
	    	
	      if (newValue != null && newValue.toString().length() > 0
	          && newValue.toString().matches("\\d*")) {
	    	  int value = Integer.valueOf(newValue.toString());
	    	  if (value<1 || value>100)
	    		  return false;
	        return true;
	      }
	      // If now create a message to the user
	      Toast.makeText(AnimalPreference.this, "Invalid Input",
	          Toast.LENGTH_SHORT).show();
	      return false;
	    }
	  };
	  
	  class MainFragment extends PreferenceFragment
	  {
		  @Override
		public void onCreate(Bundle savedInstanceState) {

			super.onCreate(savedInstanceState);
		    addPreferencesFromResource(R.xml.pref);

		}
	  }
}
