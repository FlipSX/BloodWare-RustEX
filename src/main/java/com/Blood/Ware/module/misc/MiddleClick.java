package  com.Blood.Ware.module.misc;

import com.Blood.Ware.manager.FriendManager;
import com.Blood.Ware.module.Category;
import com.Blood.Ware.module.Module;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import org.lwjgl.input.Mouse;
import com.Blood.Ware.notifications.*;

public class MiddleClick extends Module {

	public MiddleClick() {
		super("MCF", Category.OUTHER);
	}

	@SubscribeEvent
	public void onMiddleClick(InputEvent.MouseInputEvent event) {
		if (mc.player == null || mc.world == null) return;

		if (Mouse.getEventButtonState() && Mouse.getEventButton() == 2) {

			if (mc.objectMouseOver.entityHit instanceof EntityPlayer) clickFriend();


		}
	}

	private void clickFriend() {
		String name = mc.objectMouseOver.entityHit.getName();

		if (FriendManager.isFriend(name)) {
			FriendManager.FRIENDS.remove(name);
			notifications.add(name,  "" +"Removed!" ,Type.Red);


		} else {
			FriendManager.FRIENDS.add(name);
			notifications.add(name,   "" +"Added!" ,Type.Green);
		}
	}
}




