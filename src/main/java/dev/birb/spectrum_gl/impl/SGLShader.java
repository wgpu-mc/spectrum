package dev.birb.spectrum_gl.impl;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import dev.birb.spectrum.pipeline.SpectrumShader;
import net.minecraft.client.gl.GlProgramManager;
import org.lwjgl.opengl.GL33;

import java.io.IOException;
import java.util.Collections;

public class SGLShader implements SpectrumShader {

    private int programId;
    private int vertShaderGlId;
    private int fragShaderGlId;

    private String vertSrc;
    private String fragSrc;

    public SGLShader(String vertSrc, String fragSrc) throws IOException {
        this.vertSrc = vertSrc;
        this.fragSrc = fragSrc;

        this.programId = GlProgramManager.createProgram();
        this.vertShaderGlId = GlStateManager.glCreateShader(GL33.GL_VERTEX_SHADER);
        this.fragShaderGlId = GlStateManager.glCreateShader(GL33.GL_FRAGMENT_SHADER);
    }

    public void createProgram() {
        GlStateManager.glShaderSource(this.vertShaderGlId, Collections.singletonList(this.vertSrc));
        GlStateManager.glCompileShader(this.vertShaderGlId);

        GlStateManager.glShaderSource(this.fragShaderGlId, Collections.singletonList(this.fragSrc));
        GlStateManager.glCompileShader(this.fragShaderGlId);

        if (GlStateManager.glGetShaderi(this.vertShaderGlId, 35713) == 0) {
            //error
        }

        if (GlStateManager.glGetShaderi(this.fragShaderGlId, 35713) == 0) {
            //error
        }

        GlStateManager.glAttachShader(this.programId, this.vertShaderGlId);
        GlStateManager.glAttachShader(this.programId, this.fragShaderGlId);
    }

    public void useProgram() {
        GlStateManager._glUseProgram(this.programId);
    }

}
