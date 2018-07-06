package tv.v1x1.modules.channel.wasm.vm.store;

import tv.v1x1.modules.channel.wasm.vm.types.WebAssemblyType;

public class GlobalInstance {
    private WebAssemblyType value;
    private final boolean mutable;

    public GlobalInstance(final WebAssemblyType value, final boolean mutable) {
        this.value = value;
        this.mutable = mutable;
    }

    public WebAssemblyType getValue() {
        return value;
    }

    public void setValue(final WebAssemblyType value) {
        this.value = value;
    }

    public boolean isMutable() {
        return mutable;
    }
}
