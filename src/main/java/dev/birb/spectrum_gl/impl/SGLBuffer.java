package dev.birb.spectrum_gl.impl;

import com.mojang.blaze3d.platform.GlStateManager;
import dev.birb.spectrum.SpectrumRenderer;
import dev.birb.spectrum.buffer.SpectrumBindableBuffer;
import dev.birb.spectrum.buffer.SpectrumBuffer;
import org.lwjgl.opengl.GL40;

import java.nio.ByteBuffer;
import java.util.HashSet;

public class SGLBuffer implements SpectrumBuffer {

    private final int size;
    private final int glId;
    private final HashSet<Usage> usages;

    public SGLBuffer(ByteBuffer buffer, HashSet<Usage> usages) {
        this.size = buffer.capacity();
        this.usages = usages;

        this.glId = GlStateManager._glGenBuffers();
        GlStateManager._glBindBuffer(GL40.GL_COPY_WRITE_BUFFER, this.glId);
        GlStateManager._glBufferData(GL40.GL_COPY_WRITE_BUFFER, buffer, 0);
    }

    @Override
    public int getSize() {
        return this.size;
    }

    @Override
    public SpectrumBindableBuffer createBindable() throws SpectrumRenderer.CapabilityError {
        throw new SpectrumRenderer.CapabilityError();
    }

    @Override
    public HashSet<Usage> getUsages() {
        return this.usages;
    }

    @Override
    public void upload(ByteBuffer buffer) throws BufferCopyError {
        if(buffer.capacity() > this.getSize()) throw new BufferCopyError();
        if(!this.getUsages().contains(Usage.Write)) throw new BufferCopyError();

        GlStateManager._glBindBuffer(GL40.GL_COPY_WRITE_BUFFER, this.glId);
        GlStateManager._glBufferData(GL40.GL_COPY_WRITE_BUFFER, buffer, 0);
    }

    public int getGlId() {
        return this.glId;
    }

}
