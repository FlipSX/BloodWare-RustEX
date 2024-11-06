package com.Blood.Ware.utils;

import net.minecraft.network.*;

public class EventSendPacket extends EventCancellable
{
    private final Packet packet;

    public boolean cancel() {
        return false;
    }

    public EventSendPacket(final Packet packet) {
        this.packet = packet;
    }

    public Packet getPacket() {
        return this.packet;
    }
}
