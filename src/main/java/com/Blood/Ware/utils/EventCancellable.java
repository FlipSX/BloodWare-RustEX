//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "D:\deobf\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package com.Blood.Ware.utils;

import akka.actor.*;
import com.Blood.Ware.events.event.Event;

public abstract class EventCancellable extends Event implements Cancellable
{
    private boolean cancelled;
    private boolean canceled;
    public boolean isCancelled() {
        return this.cancelled;
    }
    public void setCanceled(boolean canceled) {
        this.canceled = canceled;
    }
    public void setCancelled(final boolean cancelled) {
        this.cancelled = cancelled;
    }
}
