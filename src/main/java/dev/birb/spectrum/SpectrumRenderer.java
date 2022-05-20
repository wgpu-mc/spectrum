package dev.birb.spectrum;

import dev.birb.spectrum.buffer.SpectrumBuffer;
import dev.birb.spectrum.pipeline.SpectrumPipeline;
import dev.birb.spectrum.pipeline.SpectrumPipelineSettings;
import dev.birb.spectrum.pipeline.SpectrumShader;

import net.minecraft.client.texture.NativeImage;

import java.nio.ByteBuffer;
import java.util.HashSet;

public interface SpectrumRenderer {

    /**
     * Pipelines should always be re-used. Creating a pipeline should be considered an expensive operation and should only be
     * done during resource loading, and not during rendering. The actual performance impact depends on the implementation of {@link SpectrumRenderer}
     * and could be completely negligible, or not.
     *
     * @param name The name of the pipeline, for debugging purposes.
     * @param settings The pipeline settings
     * @return The pipeline itself
     */
    SpectrumPipeline createPipeline(
            String name,
            SpectrumPipelineSettings settings,
            SpectrumShader shader
    );

    /**
     * @return A string containing the name of the Spectrum implementation, e.g. "Electrum"
     */
    String brand();

    Capabilities getCapabilities();

    SpectrumTexture createTexture(NativeImage image);

    /**
     * Allocates a contiguous section of memory onto the GPU and copies the contents of the ByteBuffer onto it.
     * Do keep in mind that changes to the provided ByteBuffer are not reflected on the GPU and a manual call to re-upload data to the buffer is necessary.
     *
     * @param bytes The buffer to copy onto the GPU
     * @param usages An array describing how this buffer will be used
     * @return An opaque handle to the buffer on the GPU
     */
    SpectrumBuffer createBuffer(ByteBuffer bytes, HashSet<SpectrumBuffer.Usage> usages);

    /**
     * This is used when you want to manually create a pipeline that will draw straight to the framebuffer, such as the end of a compositor.
     * @return The color texture of the default FBO. This may be marked immutable.
     */
    SpectrumTexture getFramebuffer();

    /**
     * @return The depth buffer of the default FBO. This may be marked immutable.
     */
    SpectrumTexture getDefaultDepthBuffer();

    public static interface Capabilities {

        boolean computeShaders();

        boolean SSBOs();

        SpectrumTexture.Format[] supportedTextureFormats();

    }

    /**
     * {@link SpectrumRenderer} implementations *must* support at least GLSL for shaders. Support for anything else is optional.
     *
     * @param vertexSource A resource that when resolved will be a file containg the vertex shader source
     * @param fragmentSource A resource that when resolved will be a file containg the vertex shader source
     * @return {@link SpectrumShader} The shader itself, ready to be used in a pipeline
     */
    SpectrumShader createShader(String vertexSource, String fragmentSource);

    /**
     * This is thrown by methods that use functionality that the {@link SpectrumRenderer} did not specify in it's {@link Capabilities}
     */
    class CapabilityError extends Exception {

    }

}
