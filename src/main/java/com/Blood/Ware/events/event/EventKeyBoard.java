package com.Blood.Ware.events.event;

public class EventKeyBoard extends Event
{
    int key;
    
    public EventKeyBoard(final int key) {
        this.key = key;
    }
    
    public int getKey() {
        return this.key;
    }
}
