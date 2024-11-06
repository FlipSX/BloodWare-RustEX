package com.Blood.Ware.events.event;

import com.Blood.Ware.BloodWare;

import java.lang.reflect.InvocationTargetException;

public abstract class Event
{
    private boolean cancelled;
    
    public Event call() {
        this.cancelled = false;
        call(this);
        return this;
    }
    
    public boolean isCancelled() {
        return this.cancelled;
    }
    
    public void setCancelled(final boolean cancelled) {
        this.cancelled = cancelled;
    }
    
    private static void call(final Event event) {
        final EventManager eventManager = BloodWare.eventManager;
        final ArrayHelper<Data> dataList = EventManager.get(event.getClass());
        if (dataList != null) {
            for (final Data data : dataList) {
                try {
                    data.target.invoke(data.source, event);
                }
                catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                catch (InvocationTargetException e2) {
                    e2.printStackTrace();
                }
            }
        }
    }
}
