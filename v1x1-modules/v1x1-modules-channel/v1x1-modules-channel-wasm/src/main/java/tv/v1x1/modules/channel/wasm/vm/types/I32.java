package tv.v1x1.modules.channel.wasm.vm.types;

import com.google.common.primitives.Ints;

public final class I32 extends IN {
    public static final I32 ZERO = new I32(0);
    public static final I32 ONE = new I32(1);

    public static I32 decode(final byte[] bits) {
        return new I32(Ints.fromByteArray(bits));
    }

    private final int val;

    public I32(final int val) {
        this.val = val;
    }

    @Override
    public byte[] bits() {
        return Ints.toByteArray(val);
    }

    @Override
    public I64 extendU() {
        return new I64(Integer.toUnsignedLong(val));
    }

    @Override
    public I64 extendS() {
        return new I64(val);
    }

    @Override
    public I32 wrap() {
        return this;
    }

    @Override
    public I32 add(final IN other) {
        return new I32(val + other.wrap().val);
    }

    @Override
    public I32 sub(final IN other) {
        return new I32(val - other.wrap().val);
    }

    @Override
    public I32 mul(final IN other) {
        return new I32(val * other.wrap().val);
    }

    @Override
    public I32 divU(final IN other) {
        return new I32(Integer.divideUnsigned(val, other.wrap().val));
    }

    @Override
    public I32 divS(final IN other) {
        return new I32(val / other.wrap().val);
    }

    @Override
    public I32 remU(final IN other) {
        return new I32(Integer.remainderUnsigned(val, other.wrap().val));
    }

    @Override
    public I32 remS(final IN other) {
        return new I32(val % other.wrap().val);
    }

    @Override
    public I32 and(final IN other) {
        return new I32(val & other.wrap().val);
    }

    @Override
    public I32 or(final IN other) {
        return new I32(val | other.wrap().val);
    }

    @Override
    public I32 xor(final IN other) {
        return new I32(val ^ other.wrap().val);
    }

    @Override
    public I32 shl(final IN other) {
        return new I32(val << other.wrap().val);
    }

    @Override
    public I32 shrU(final IN other) {
        return new I32(val >>> other.wrap().val);
    }

    @Override
    public I32 shrS(final IN other) {
        return new I32(val >> other.wrap().val);
    }

    @Override
    public I32 rotl(final IN other) {
        return new I32(Integer.rotateLeft(val, (int) (other.wrap().val % 64)));
    }

    @Override
    public I32 rotr(final IN other) {
        return new I32(Integer.rotateRight(val, (int) (other.wrap().val % 64)));
    }

    @Override
    public I32 clz() {
        return new I32(Integer.numberOfLeadingZeros(val));
    }

    @Override
    public I32 ctz() {
        return new I32(Integer.numberOfTrailingZeros(val));
    }

    @Override
    public I32 popcnt() {
        return new I32(Integer.bitCount(val));
    }

    @Override
    public I32 eqz() {
        return bool(val == 0);
    }

    @Override
    public I32 eq(final IN other) {
        return bool(val == other.wrap().val);
    }

    @Override
    public I32 ne(final IN other) {
        return bool(val != other.wrap().val);
    }

    @Override
    public I32 ltU(final IN other) {
        return bool(Integer.compareUnsigned(val, other.wrap().val) < 0);
    }

    @Override
    public I32 ltS(final IN other) {
        return bool(val < other.wrap().val);
    }

    @Override
    public I32 gtU(final IN other) {
        return bool(Integer.compareUnsigned(val, other.wrap().val) > 0);
    }

    @Override
    public I32 gtS(final IN other) {
        return bool(val > other.wrap().val);
    }

    @Override
    public I32 leU(final IN other) {
        return bool(Integer.compareUnsigned(val, other.wrap().val) <= 0);
    }

    @Override
    public I32 leS(final IN other) {
        return bool(val <= other.wrap().val);
    }

    @Override
    public I32 geU(final IN other) {
        return bool(Integer.compareUnsigned(val, other.wrap().val) >= 0);
    }

    @Override
    public I32 geS(final IN other) {
        return bool(val >= other.wrap().val);
    }

    @Override
    public FN reinterpret() {
        return F32.decode(bits());
    }

    private I32 bool(final boolean bool) {
        return bool ? I32.ONE : I32.ZERO;
    }
}
