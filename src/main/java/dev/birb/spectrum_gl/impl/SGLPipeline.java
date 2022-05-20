package dev.birb.spectrum_gl.impl;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import dev.birb.spectrum.SpectrumBindable;
import dev.birb.spectrum.buffer.SpectrumBuffer;
import dev.birb.spectrum.pipeline.SpectrumPipeline;
import dev.birb.spectrum.pipeline.SpectrumPipelineSettings;
import dev.birb.spectrum.pipeline.SpectrumShader;
import org.lwjgl.opengl.GL30;
import org.lwjgl.opengl.GL33;
import org.lwjgl.opengl.GL40;

public class SGLPipeline implements SpectrumPipeline {

    private SGLShader shader;
    private final SpectrumPipelineSettings settings;

    public SGLPipeline(SpectrumPipelineSettings settings) {
        this.settings = settings;
    }

    @Override
    public void draw(int vertices, int instances, SpectrumBindable[] bindings, SpectrumBuffer[] vertexBuffers) throws DrawCallError {
        if(RenderSystem.isOnRenderThread()) {
            //Bind the shader
            shader.useProgram();

            //Use the Bindables
            int textureBindIndex = 0;

            for(int i=0; i<bindings.length;i++) {
                SpectrumBindable bindable = bindings[i];

                if(bindable == null) throw new DrawCallError();

                if(bindable.getType() != this.settings.getBindableTypes().getElements().get(i)) {
                    throw new DrawCallError();
                }

                if(bindable instanceof SGLTexture || bindable instanceof SGLFramebufferTexture) {
                    ((SGLBindableInternal) bindable).sglBindTexture(i, textureBindIndex);

                    textureBindIndex++;
                } else {
                    ((SGLBindableInternal) bindable).sglBind(i);
                }
            }

            GL40.glDrawArraysInstanced(switch(this.settings.getGeometryType()) {
                case Triangles -> GL30.GL_TRIANGLES;
                case Lines -> GL30.GL_LINES;
            }, 0, vertices, instances);
        } else {
            RenderSystem.recordRenderCall(() -> {

            });
        }
    }

    @Override
    public void setShader(SpectrumShader shader) {
        this.shader = (SGLShader) shader;
    }

    @Override
    public SpectrumShader getShader() {
        return this.shader;
    }

    @Override
    public SpectrumPipelineSettings getSettings() {
        return this.settings;
    }

}
