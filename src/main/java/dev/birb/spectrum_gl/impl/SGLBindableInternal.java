package dev.birb.spectrum_gl.impl;

public interface SGLBindableInternal {

    void sglBindTexture(int slot, int textureBindableIndex);

    void sglBind(int slot);

}
