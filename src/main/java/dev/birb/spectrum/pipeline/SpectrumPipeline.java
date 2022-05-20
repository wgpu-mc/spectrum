package dev.birb.spectrum.pipeline;

import dev.birb.spectrum.SpectrumBindable;
import dev.birb.spectrum.SpectrumLayout;
import dev.birb.spectrum.buffer.SpectrumBuffer;

import java.util.List;

public interface SpectrumPipeline {

    /**
     *
     * @param vertices The amount of vertices to be rendered. Must be 0 or greater.
     * @param instances The amount of instances to be rendered. Must be 0 or greater.
     * @param bindings An array of {@link SpectrumBindable}s which will be used in this draw call. The entries of this array must match the types specified in the {@link SpectrumPipelineSettings}
     * @param vertexBuffers An array of {@link SpectrumBuffer}s which contain the vertex data.
     * @throws DrawCallError This will be thrown if <br>
     * - the vertex or instance count is less than zero, <br>
     * - the bindings do not match the pipeline settings <br>
     * - there are no specified vertex buffers, or there are not enough vertex buffers in correspondence to the pipeline settings <br>
     * - this method is called outside of the game's render loop (there is no active render pass)
     */
    void draw(int vertices, int instances, SpectrumBindable[] bindings, SpectrumBuffer[] vertexBuffers) throws DrawCallError;

    /**
     * This should be considered an expensive operation and should only be done during resource (re-)loading
     */
    void setShader(SpectrumShader shader);

    SpectrumShader getShader();

    SpectrumPipelineSettings getSettings();

    enum VertexAttributeType {

        Float_x1,
        Float_x2,
        Float_x3,

        Int

    }

    class DrawCallError extends Throwable {
    }

}
