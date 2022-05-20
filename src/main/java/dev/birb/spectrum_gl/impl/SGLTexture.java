package dev.birb.spectrum_gl.impl;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import dev.birb.spectrum.SpectrumTexture;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.texture.NativeImage;
import net.minecraft.client.texture.NativeImageBackedTexture;

public class SGLTexture implements SpectrumTexture, SGLBindableInternal {

    private NativeImageBackedTexture texture;

    public SGLTexture(NativeImageBackedTexture texture) {
        this.texture = texture;
    }

    @Override
    public Type getType() {
        return Type.Texture;
    }

    @Override
    public int getWidth() {
        return this.texture.getImage().getWidth();
    }

    @Override
    public int getHeight() {
        return this.texture.getImage().getHeight();
    }

    @Override
    public Format getFormat() {
        assert this.texture.getImage().getFormat().toGl() == 0x5085;
        return Format.Rgba8;
    }

    @Override
    public void upload(NativeImage image) throws TextureUploadError {
        if(image.getWidth() != this.texture.getImage().getWidth() || image.getHeight() != this.texture.getImage().getHeight() || image.getFormat() != this.texture.getImage().getFormat()) {
            throw new TextureUploadError();
        }
        this.texture = new NativeImageBackedTexture(image);
    }

    @Override
    public boolean immutable() {
        return false;
    }

    @Override
    public void sglBindTexture(int slot, int textureBindableIndex) {
        RenderSystem.activeTexture(textureBindableIndex);
        RenderSystem.bindTexture(this.texture.getGlId());
        RenderSystem.glUniform1i(slot, textureBindableIndex);
    }

    @Override
    public void sglBind(int slot) {

    }
}
