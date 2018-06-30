package tv.v1x1.modules.channel.wasm.vm.instructions.numeric.f64;

import tv.v1x1.modules.channel.wasm.vm.Context;
import tv.v1x1.modules.channel.wasm.vm.Instruction;
import tv.v1x1.modules.channel.wasm.vm.TrapException;
import tv.v1x1.modules.channel.wasm.vm.WebAssemblyVirtualMachine;
import tv.v1x1.modules.channel.wasm.vm.types.F64;
import tv.v1x1.modules.channel.wasm.vm.types.I32;

import java.io.DataInputStream;
import java.io.IOException;

public abstract class F64RelOpInstruction extends Instruction {
    @Override
    public void decode(final DataInputStream dataInputStream) throws IOException {
        /* No action */
    }

    @Override
    public boolean validate(final Context context) {
        return true;
    }

    @Override
    public void execute(final WebAssemblyVirtualMachine virtualMachine) throws TrapException {
        final F64 val2 = virtualMachine.getStack().pop(F64.class);
        final F64 val1 = virtualMachine.getStack().pop(F64.class);
        virtualMachine.getStack().push(op(val1, val2));
    }

    public abstract I32 op(final F64 val1, final F64 val2) throws TrapException;
}
