package com.Blood.Ware.utils;

import net.minecraft.network.Packet;
import net.minecraftforge.fml.common.eventhandler.Cancelable;
import net.minecraftforge.fml.common.eventhandler.Event;

/**
 *
 */

@Cancelable
public class PacketEvent extends Event {
    private static Packet<?> packet;

    public PacketEvent(Packet<?> packet) {

        this.packet = packet;
    }

    public static Packet getPacket() {
        return PacketEvent.packet;
    }


    public final Packet<?> setPacket(Packet<?> packet) {

        return this.packet = packet;
    }

    public static class Send extends PacketEvent {

        public Send(Packet<?> packet) {

            super(packet);
        }

        public static class Post extends Send {
            public Post(Packet<?> packet) {

                super(packet);
            }
        }
    }

    public static class Receive extends PacketEvent {

        public Receive(Packet<?> packet) {

            super(packet);
        }
    }
}
