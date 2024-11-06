package com.Blood.Ware.settings;

import com.Blood.Ware.module.Module;

import java.util.ArrayList;



public class SettingsManager {
	
	private ArrayList<Setting> settings;
	
	public SettingsManager(){
		this.settings = new ArrayList<Setting>();
	}
	
	public void rSetting(Setting in){
		this.settings.add(in);
	}
	public void fSetting(Setting in){
		this.settings.add(in);
	}
	
	public ArrayList<Setting> getSettings(){
		return this.settings;
	}
	
	public ArrayList<Setting> getSettingsByMod(Module mod){
		ArrayList<Setting> out = new ArrayList<Setting>();
		for(Setting s : getSettings()){
			if(s.getParentMod().equals(mod)){
				out.add(s);
			}
		}
		if(out.isEmpty()){
			return null;
		}
		return out;
	}

	public Setting getSettingByName(String name, String range)
	{
		for (Setting setting : this.getSettings())
		{
			if (setting.getName().equalsIgnoreCase(name))
			{
				return setting;
			}
		}

		System.out.println("Setting not found! (" + name + ").");
		return null;
	}

	public Setting getSettingByName(Module mod, String name){
		for(Setting set : getSettings()){
			if(set.getName().equalsIgnoreCase(name) && set.getParentMod() == mod){
				return set;
			}
		}
		System.err.println("[Tutorial] Error Setting NOT found: '" + name +"'!");
		return null;




	}
}