package com.Blood.Ware.utils;

import net.minecraftforge.fml.common.eventhandler.Event;

public class CameraEwent extends Event
{
    public double distance;

    public CameraEwent( double distance )
    {
        this.distance = distance;
    }
}