/*
 * Decompiled with CFR 0.150.
 */
package com.Blood.Ware.utils;

import com.Blood.Ware.BloodWare;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.util.ResourceLocation;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class GifEngine {
    private int current = 0;
    private Counter counter = new Counter();
    private DynamicTexture texture;
    private List<DynamicTexture> frames;
    private int x;
    private int y;
    private int width;
    private int height;
    private int tiktok;
    private int ticks;

    public GifEngine(ResourceLocation rs, int width, int height, int tt) {
        this.texture = new DynamicTexture(width, height);
        this.width = width;
        this.height = height;
        this.frames = this.images(rs);
        this.ticks = tt;
    }

    public void update() {
        if (tiktok > ticks) {
            //1 seconds = 20 ticks
            if (this.counter.hasReached(Minecraft.getMinecraft().getTickLength()) && this.frames.size() > 0) {
                if (this.current > this.frames.size() - 1) {
                    this.current = 0;
                }
                this.texture = this.frames.get(this.current);
                ++this.current;
                this.counter.reset();
            }
            tiktok = 0;
        }
        tiktok++;
    }

    public void bind(int x, int y) {
        this.x = x;
        this.y = y;
        UIRender.bind((float)this.x + 5.0f, this.y, this.width / BloodWare.scale.getScale(), this.height / BloodWare.scale.getScale(), 1.0f, this.getTexture().getGlTextureId());
    }

    public List<DynamicTexture> images(ResourceLocation rs) {
        ArrayList<DynamicTexture> images = new ArrayList<DynamicTexture>();
        try {
            ImageReader reader = ImageIO.getImageReadersByFormatName("gif").next();
            InputStream xui = Minecraft.getMinecraft().getResourceManager().getResource(rs).getInputStream();
            ImageInputStream stream = ImageIO.createImageInputStream(xui);
            reader.setInput(stream);
            int count = reader.getNumImages(true);
            for (int index = 0; index < count; ++index) {
                BufferedImage frame = reader.read(index);
                images.add(new DynamicTexture(frame));
            }
        }
        catch (IOException iOException) {
            // empty catch block
        }
        return images;
    }

    public DynamicTexture getTexture() {
        return this.texture;
    }

    public void setTexture(DynamicTexture texture) {
        this.texture = texture;
    }

    public List<DynamicTexture> getFrames() {
        return this.frames;
    }

    public void setFrames(List<DynamicTexture> frames) {
        this.frames = frames;
    }
}

