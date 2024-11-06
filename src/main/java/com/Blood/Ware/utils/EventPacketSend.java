package com.Blood.Ware.utils;

import net.minecraft.network.Packet;

public class EventPacketSend extends EventCancellable {

    private Packet packet;

    public EventPacketSend(Packet packet) {
        this.packet = packet;
    }

    public void setPacket(Packet packet) {
        this.packet = packet;
    }

    public Packet getPacket() {
        return this.packet;
    }

    @Override
    public boolean cancel() {
        return false;
    }
}
