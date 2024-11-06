package com.Blood.Ware.module.render;

import net.minecraft.util.math.*;

import net.minecraft.util.math.*;

class Circle {
    private final Vec3d vec;
    private final Vec3d color;
    byte existed;

    Vec3d position() {
        return this.vec;
    }

    Circle(final Vec3d vec, final Vec3d color) {
        this.vec = vec;
        this.color = color;
    }

    Vec3d color() {
        return this.color;
    }

   public boolean update() {
        final byte existed = (byte) (this.existed + 1);
        this.existed = existed;
        return existed > 20;
    }

}

