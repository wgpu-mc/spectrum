package dev.birb.spectrum_gl.impl;

import dev.birb.spectrum.SpectrumRenderer;
import dev.birb.spectrum.SpectrumTexture;
import dev.birb.spectrum.buffer.SpectrumBuffer;
import dev.birb.spectrum.pipeline.SpectrumPipeline;
import dev.birb.spectrum.pipeline.SpectrumPipelineSettings;
import dev.birb.spectrum.pipeline.SpectrumShader;
import net.minecraft.client.texture.NativeImage;

import java.nio.ByteBuffer;

public class SGLRenderer implements SpectrumRenderer {

    private SGLTexture framebuffer;
    private SGLTexture depthBuffer;

    public SGLRenderer() {
//        this.framebuffer = new SGLTexture();
    }

    @Override
    public SpectrumPipeline createPipeline(String name, SpectrumPipelineSettings settings) {
        return null;
    }

    @Override
    public String brand() {
        return "SpectrumGL";
    }

    @Override
    public Capabilities getCapabilities() {
        return new Capabilities() {
            @Override
            public boolean computeShaders() {
                return false;
            }

            @Override
            public boolean SSBOs() {
                return false;
            }

            @Override
            public SpectrumTexture.Format[] supportedTextureFormats() {
                SpectrumTexture.Format[] formats = new SpectrumTexture.Format[2];
                formats[0] = SpectrumTexture.Format.Depth;
                formats[1] = SpectrumTexture.Format.Rgba8;

                return formats;
            }
        };
    }

    @Override
    public SpectrumTexture createTexture(NativeImage image) {
        return null;
    }

    @Override
    public SpectrumBuffer createBuffer(ByteBuffer bytes) {
        return null;
    }

    @Override
    public SpectrumTexture getFramebuffer() {
        return this.framebuffer;
    }

    @Override
    public SpectrumTexture getDefaultDepthBuffer() {
        return this.depthBuffer;
    }

    @Override
    public SpectrumShader createShader(String vertexSource, String fragmentSource) {
        return null;
    }

}
