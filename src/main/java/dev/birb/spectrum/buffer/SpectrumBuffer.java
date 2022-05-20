package dev.birb.spectrum.buffer;

import dev.birb.spectrum.SpectrumRenderer;

import java.nio.ByteBuffer;
import java.util.HashSet;

/**
 * Represents a contiguous section of memory on the GPU which can be used for various purposes
 */
public interface SpectrumBuffer {

    int getSize();

    /**
     * {@link SpectrumRenderer#getCapabilities()} must specify that SSBOs are supported for this to succeed.
     * If it doesn't, an error will be thrown.
     *
     * @return {@link SpectrumBindableBuffer}
     * @throws SpectrumRenderer.CapabilityError
     */
    SpectrumBindableBuffer createBindable() throws SpectrumRenderer.CapabilityError;

    HashSet<Usage> getUsages();

    /**
     * Copies the data from the ByteBuffer onto the already allocated buffer on the GPU.
     * @param buffer
     * @throws BufferCopyError when the provided ByteBuffer is larger than the allocated buffer on the GPU, or if the buffer doesn't have the usage {@link Usage#Write} specified
     */
    void upload(ByteBuffer buffer) throws BufferCopyError;

    public static enum Usage {

        SSBO,
        VertexBuffer,
        InstanceBuffer,
        Write,
        Read

    }

    class BufferCopyError extends Throwable {}

}
