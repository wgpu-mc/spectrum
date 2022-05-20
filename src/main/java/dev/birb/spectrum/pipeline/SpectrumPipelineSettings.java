package dev.birb.spectrum.pipeline;

import dev.birb.spectrum.SpectrumBindable;
import dev.birb.spectrum.SpectrumLayout;
import dev.birb.spectrum.SpectrumTexture;

import javax.annotation.Nullable;
import java.util.List;

public class SpectrumPipelineSettings {

    @Nullable
    private final SpectrumTexture depth;
    private final SpectrumTexture output;

    @Nullable
    private final SpectrumLayout<SpectrumBindable.Type> bindableTypes;
    private final List<SpectrumLayout<SpectrumPipeline.VertexAttributeType>> vertexLayout;

    private final GeometryType geometryType;

    /**
     * @param depth If this pipeline should use a depth buffer. If this is set to null, fragments from this pipeline will always render.
     * @param output Where the result of this pipelines fragment shader will be rendered to
     * @param bindableTypes A layout describing what type of {@link SpectrumBindable}s will be used during draw calls
     * @param vertexLayout A layout describing the vertex attributes that will be passed into the vertex shader
     * @param geometryType What kind of geometry this pipeline will be rendering
     */
    public SpectrumPipelineSettings(@Nullable SpectrumTexture depth, SpectrumTexture output, @Nullable SpectrumLayout<SpectrumBindable.Type> bindableTypes, List<SpectrumLayout<SpectrumPipeline.VertexAttributeType>> vertexLayout, GeometryType geometryType) {
        this.depth = depth;
        this.output = output;
        this.bindableTypes = bindableTypes;
        this.vertexLayout = List.copyOf(vertexLayout);
        this.geometryType = geometryType;
    }

    @Nullable
    public SpectrumTexture getDepth() {
        return depth;
    }

    public SpectrumTexture getOutput() {
        return output;
    }

    @Nullable
    public SpectrumLayout<SpectrumBindable.Type> getBindableTypes() {
        return bindableTypes;
    }

    public List<SpectrumLayout<SpectrumPipeline.VertexAttributeType>> getVertexLayout() {
        return vertexLayout;
    }

    public GeometryType getGeometryType() {
        return geometryType;
    }

    public enum GeometryType {
        Triangles,
        Lines
    }

}
