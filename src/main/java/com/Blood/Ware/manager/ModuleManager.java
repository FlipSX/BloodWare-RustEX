package com.Blood.Ware.manager;

import com.Blood.Ware.font.FontUtils;
import com.Blood.Ware.module.Category;
import com.Blood.Ware.module.Module;
import com.Blood.Ware.module.combat.*;
import com.Blood.Ware.module.hud.*;
import com.Blood.Ware.module.misc.*;
import com.Blood.Ware.module.movement.*;
import com.Blood.Ware.module.render.*;
import com.google.common.reflect.ClassPath;

import javafx.scene.Camera;

import java.util.ArrayList;
import java.util.Iterator;

public class ModuleManager {

	public static ArrayList<Module> modules;

	public ModuleManager() {
		(modules = new ArrayList<Module>()).clear();
		modules.add(new BlockEsp());
		modules.add(new BAim());
		modules.add(new AimBot());
		modules.add(new ClickGUI());
		modules.add(new ESP());
		modules.add(new AntiZoomSmooth());
		modules.add(new MicoBot());
		modules.add(new ClickPosInfo());
		modules.add(new ChestStealer());
		modules.add(new DiscordRPC());
		modules.add(new ChatCalc());
		modules.add(new HouseEsp());
		modules.add(new Zoom());
		modules.add(new PenisESP());
		modules.add(new ItemESP());
		modules.add(new Tracers());
		modules.add(new Crosshair());
		modules.add(new GuiWalk());
		modules.add(new NoClip());
		modules.add(new FogColor());
		modules.add(new ArmorHUD());
		modules.add(new Keystrokes());
		modules.add(new Freecam());
		modules.add(new FullBright());
		modules.add(new InvViewer());
		modules.add(new NameTags());
		modules.add(new WallHackPlayer());
		modules.add(new NoHurtCum());
		modules.add(new ModuleList());
		modules.add(new ViewModel());
		modules.add(new Notifications());
		modules.add(new FakePlayer());
		modules.add(new Africa());
		modules.add(new MiddleClick());
		modules.add(new ChinaHat());
		modules.add(new Sprint());
		modules.add(new NoPush());
		modules.add(new AimAssist());
		modules.add(new AntiBot());
		modules.add(new WallHack());
		modules.add(new Watermark());

		FontUtils.bootstrap();
		for (Module m : this.modules) {
		}
	}

	public static ArrayList<Class<?>> getClasses(final String packageName) {
		final ArrayList<Class<?>> classes = new ArrayList<Class<?>>();
		try {
			final ClassLoader loader = Thread.currentThread().getContextClassLoader();
			for (final ClassPath.ClassInfo info : ClassPath.from(loader).getAllClasses()) {
				if (info.getName().startsWith(packageName)) {
					classes.add(info.load());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return classes;
	}



	public Module getModule(String name) {
		for (Module m : this.modules) {
			if (m.getName().equalsIgnoreCase(name)) {
				return m;
			}
		}
		return null;
	}
	public <T extends Module> T getModuleByClass(Class<T> clazz) {
		for (Module module : this.modules) {
			if (!clazz.isInstance(module)) continue;
			return (T) module;
		}
		return null;
	}
	public ArrayList<Module> getModuleList() {
		return this.modules;
	}

	public ArrayList<Module> getModulesInCategory(Category c) {
		ArrayList<Module> mods = new ArrayList<Module>();
		for (Module m : this.modules) {
			if (m.getCategory() == c) {
				mods.add(m);
			}
		}
		return mods;
	}


	public ArrayList<Module> getModules(Category category) {
		ArrayList<Module> modules2 = new ArrayList();
		Iterator var3 = this.modules.iterator();

		while(var3.hasNext()) {
			Module module = (Module)var3.next();
			if (module.getCategory() == category) {
				modules2.add(module);
			}
		}

		return modules2;
	}
}
