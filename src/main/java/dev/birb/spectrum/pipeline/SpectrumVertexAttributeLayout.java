package dev.birb.spectrum.pipeline;

import java.util.List;

public class SpectrumVertexAttributeLayout {

    private final int stride;
    private final List<SpectrumPipeline.VertexAttributeType> layout;

    public SpectrumVertexAttributeLayout(List<SpectrumPipeline.VertexAttributeType> elements) {
        this.layout = List.copyOf(elements);

        int stride = 0;

        for(SpectrumPipeline.VertexAttributeType type : elements) {
            stride += switch(type) {
                case Float_x3 -> 3 * 4;
                case Float_x2 -> 2 * 4;
                case Float_x1, Int -> 4;
            };
        }

        this.stride = stride;
    }

    public List<SpectrumPipeline.VertexAttributeType> getElements() {
        return this.layout;
    }

    public int stride() {
        return this.stride;
    }

}
