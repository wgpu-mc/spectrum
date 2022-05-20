# Spectrum Rendering API

Minecraft runs on OpenGL, we know this. It has served a purpose and OpenGL was essentially the only option to get good cross-platform support for rendering. However, Minecraft's first versions were created in 2009 and it was officially released in 2011 or so. Graphics programming has progressed a lot since then, and Minecraft should have a rendering pipeline which reflects it. Mojang has shown repeatedly they have 0 interest in radically changing how rendering works.

I think that this should change, and in a productive way that everyone can benefit from. People making optimization mods (like Sodium), QoL mods/shader mods (like Iris) and general modders that are doing "fancy" rendering tricks using raw OpenGL need something new.

Recently, my project Electrum (which utilizes my rendering engine wgpu-mc) has gotten me thinking about how this could look like.

Firstly, the primary requirement would be stateless rendering. 
You define groupings of shaders + their respective vertex attribute layouts in "pipelines", which you can then use to make actual draw calls. 

The benefits here are:

- this mimics how modern rendering APIs work (Vulkan, Metal, D3D12)
- much simpler to use as you don't have literally hundreds to thousands of obscure properties and settings working in an archaic stateful system which interact in arcane ways (cough OpenGL)
- makes it possible for alternative rendering implementations (such as Blaze4D or Electrum) to entirely change which backend the game runs on (to something other than OpenGL) and yet still have mod rendering compatibility

The second requirement would be ease-of-use. We're not trying to re-write Vulkan here, that would be stupid. We want something that is powerful enough to cover what anyone doing Minecraft modding would ever need, while still not being absurdly complex. Obviously, a lot of implementation detail would be hidden from the "end-user" (a modder who isn't an expert in graphics programming).

The third requirement is compatibility. This must be able to run on everything. 
It must be able to be implemented on top of OpenGL, for obvious reasons, while still not succumbing to GL's shortcomings. 
Thankfully, it's way way way way way way way way simpler to adapt a stateless system to run on a stateful system than vice-versa, so this is essentially a non-issue.