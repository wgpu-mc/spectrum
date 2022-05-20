package dev.birb.spectrum;

import dev.birb.spectrum.pipeline.SpectrumPipeline;
import net.minecraft.client.texture.NativeImage;

/**
 * Represents a texture which has been uploaded to the GPU. All textures are automatically usable as an {@link SpectrumBindable} in shaders
 */
public interface SpectrumTexture extends SpectrumBindable {

    int getWidth();

    int getHeight();

    Format getFormat();

    void upload(NativeImage image) throws TextureUploadError;

    /**
     *
     * @return A boolean. If true, this means that this texture cannot be uploaded to.
     */
    boolean immutable();

    /**
     * Defines texture formats that a given {@link SpectrumTexture} can use. Any color format which has a per-color bit-depth higher than 8
     * may not be supported by a given {@link SpectrumRenderer}
     */
    enum Format {
        Depth,
        Rgba8,
        Bgra8,
        Rgba16,
        Rgba10,
        Rgba12
    }

    /**
     * {@link Usage} is a specifier for extra functionality that this {@link SpectrumTexture} can be used for.
     * {@link Usage#PipelineOutput} means that this texture can be used as the output texture of an {@link SpectrumPipeline}
     */
    enum Usage {
        PipelineOutput
    }

    /**
     * {@link TextureUploadError} be thrown in the following situations situations: <br>
     *     - The {@link SpectrumRenderer} does not support a texture of this size, OR <br>
     *     - An attempt was made to upload new data to an already existing texture, where there is a dimension and/or format mismatch between the upload attempt and the existing image on the GPU, OR <br>
     *     - This texture has been marked immutable
     */
    class TextureUploadError extends Error {

    }

}
