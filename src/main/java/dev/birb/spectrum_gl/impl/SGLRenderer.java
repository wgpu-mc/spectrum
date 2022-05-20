package dev.birb.spectrum_gl.impl;

import dev.birb.spectrum.SpectrumRenderer;
import dev.birb.spectrum.SpectrumTexture;
import dev.birb.spectrum.buffer.SpectrumBuffer;
import dev.birb.spectrum.pipeline.SpectrumPipeline;
import dev.birb.spectrum.pipeline.SpectrumPipelineSettings;
import dev.birb.spectrum.pipeline.SpectrumShader;
import net.minecraft.client.texture.NativeImage;

import java.nio.ByteBuffer;
import java.util.HashSet;

public class SGLRenderer implements SpectrumRenderer {

    private final SpectrumTexture framebuffer;
    private SpectrumTexture depthBuffer;

    public SGLRenderer() {
        this.framebuffer = new SGLFramebufferTexture();
        this.depthBuffer = new SGLDepthTexture();
    }

    @Override
    public SpectrumPipeline createPipeline(String name, SpectrumPipelineSettings settings, SpectrumShader shader) {
        return new SGLPipeline(settings, (SGLShader) shader);
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
                return true;
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
    public SpectrumBuffer createBuffer(ByteBuffer bytes, HashSet<SpectrumBuffer.Usage> usages) {
        return new SGLBuffer(bytes, usages);
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
