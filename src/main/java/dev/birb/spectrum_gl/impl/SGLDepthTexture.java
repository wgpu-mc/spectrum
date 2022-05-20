package dev.birb.spectrum_gl.impl;

import com.mojang.blaze3d.systems.RenderSystem;
import dev.birb.spectrum.SpectrumTexture;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.texture.NativeImage;

public class SGLDepthTexture implements SpectrumTexture, SGLBindableInternal {

    @Override
    public Type getType() {
        return Type.Texture;
    }

    @Override
    public int getWidth() {
        return MinecraftClient.getInstance().getFramebuffer().textureWidth;
    }

    @Override
    public int getHeight() {
        return MinecraftClient.getInstance().getFramebuffer().textureHeight;
    }

    @Override
    public Format getFormat() {
        return Format.Depth;
    }

    @Override
    public void upload(NativeImage image) throws TextureUploadError {
        throw new TextureUploadError();
    }

    @Override
    public boolean immutable() {
        return true;
    }

    @Override
    public void sglBindTexture(int slot, int textureBindableIndex) {
        RenderSystem.activeTexture(textureBindableIndex);
        RenderSystem.bindTexture(MinecraftClient.getInstance().getFramebuffer().fbo);
        RenderSystem.glUniform1i(slot, textureBindableIndex);
    }

    @Override
    public void sglBind(int slot) {
    }
}
