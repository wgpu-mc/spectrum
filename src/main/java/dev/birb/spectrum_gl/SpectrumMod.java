package dev.birb.spectrum_gl;

import dev.birb.spectrum.Spectrum;
import dev.birb.spectrum.SpectrumLayout;
import dev.birb.spectrum.SpectrumRenderer;
import dev.birb.spectrum.SpectrumTexture;
import dev.birb.spectrum.buffer.SpectrumBuffer;
import dev.birb.spectrum.pipeline.SpectrumPipeline;
import dev.birb.spectrum.pipeline.SpectrumPipelineSettings;
import dev.birb.spectrum.pipeline.SpectrumShader;
import dev.birb.spectrum.pipeline.SpectrumVertexAttributeLayout;
import dev.birb.spectrum_gl.impl.SGLRenderer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.resource.IdentifiableResourceReloadListener;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.minecraft.resource.ResourceManager;
import net.minecraft.resource.ResourceType;
import net.minecraft.util.Identifier;
import net.minecraft.util.profiler.Profiler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

public class SpectrumMod implements ModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("spectrum");

	@Override
	public void onInitialize() {
		Spectrum.registerRenderer(new SGLRenderer());

		ResourceManagerHelper.get(ResourceType.CLIENT_RESOURCES).registerReloadListener(new IdentifiableResourceReloadListener() {
			@Override
			public Identifier getFabricId() {
				return null;
			}

			@Override
			public CompletableFuture<Void> reload(Synchronizer synchronizer, ResourceManager manager, Profiler prepareProfiler, Profiler applyProfiler, Executor prepareExecutor, Executor applyExecutor) {
				SpectrumRenderer renderer = Spectrum.getRenderer();

				SpectrumTexture fbo = renderer.getFramebuffer();
				SpectrumTexture depth = renderer.getDefaultDepthBuffer();

				List<SpectrumPipeline.VertexAttributeType> vertElements = new ArrayList<>();

				vertElements.add(SpectrumPipeline.VertexAttributeType.Float_x3);

				SpectrumVertexAttributeLayout vertexLayout = new SpectrumVertexAttributeLayout(
						vertElements
				);

				List<SpectrumVertexAttributeLayout> layouts = new ArrayList<>();

				layouts.add(vertexLayout);

				SpectrumPipelineSettings settings = new SpectrumPipelineSettings(
						depth,
						fbo,
						new SpectrumLayout<>(new ArrayList<>()),
						layouts,
						SpectrumPipelineSettings.GeometryType.Triangles
				);

				SpectrumShader shader = null;

				SpectrumPipeline pipeline = renderer.createPipeline("test pipeline", settings, shader);

				return CompletableFuture.completedFuture(null);
			}
		});
	}
}
