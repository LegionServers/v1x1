package tv.v1x1.modules.channel.wasm.vm.instructions.store.memory.load;

import com.google.common.primitives.Shorts;
import tv.v1x1.modules.channel.wasm.vm.ValType;
import tv.v1x1.modules.channel.wasm.vm.instructions.store.memory.LoadInstruction;
import tv.v1x1.modules.channel.wasm.vm.types.I64;
import tv.v1x1.modules.channel.wasm.vm.types.WebAssemblyType;

public class I64Load16SInstruction extends LoadInstruction {
    @Override
    protected WebAssemblyType convert(final byte[] bytes) {
        return new I64(Shorts.fromByteArray(I64.swapEndian(bytes)));
    }

    @Override
    protected int getWidth() {
        return 16;
    }

    @Override
    protected ValType getType() {
        return ValType.I64;
    }
}
