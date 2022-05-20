package dev.birb.spectrum;

import static dev.birb.spectrum_gl.Spectrum.LOGGER;

public class Spectrum {

    private static SpectrumRenderer RENDERER = null;

    public static void registerRenderer(SpectrumRenderer renderer) {
        if(RENDERER != null) {
            throw new RuntimeException("Conflicting Spectrum renderers were attempted to be registered.");
        }

        RENDERER = renderer;

        SpectrumRenderer.Capabilities capabilities = RENDERER.getCapabilities();
        LOGGER.info(
                "Registered Spectrum implementation + " + RENDERER.brand() +
                "\nCapabilities:\n  SSBOs: " + (capabilities.SSBOs() ? "yes" : "no") +
                "\n  Compute Shaders: " + (capabilities.computeShaders() ? "yes" : "no") +
                "\n  Supported texture formats: " + capabilities.supportedTextureFormats());
    }

    public static SpectrumRenderer getRenderer() {
        return RENDERER;
    }

}
