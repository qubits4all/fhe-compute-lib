package info.willdspann.crypto.fhe.clear

import java.security.PublicKey

import info.willdspann.crypto.fhe.WrappedCiphertext

/**
 * Simple implementation of `WrappedCiphertext` for wrapping a `ClearBit`'s cleartext value, which is "encrypted" with
 * a `NoOpFhePublicKey`.
 */
class WrappedCleartext(val clearBit: Boolean) <: WrappedCiphertext {

    override def ciphertextBytes: Array[Byte] = Array(WrappedCleartext.byteFromBit(clearBit))

    override val publicKey: PublicKey = NoOpFhePublicKey

}

object WrappedCleartext {

    def apply(clearBit: Boolean): WrappedCleartext = {
        new WrappedCleartext(clearBit)
    }

    def apply(clearBytes: Array[Byte]): WrappedCleartext = {
        if (clearBytes != null && clearBytes.length >= 1) {
            WrappedCleartext(bitFromByte(clearBytes(0)))
        }
        else {
            throw new IllegalArgumentException("Non-empty byte array required.")
        }
    }

    /**
     * Creates a zero-padded bit from the given boolean value, mapping `true` to one and `false` to zero.
     *
     * @param clearBit boolean value to encode as a zero-padded bit.
     * @return a zero-padded bit encoding of the given boolean value.
     */
    def byteFromBit(clearBit: Boolean): Byte = {
        clearBit match {
            case true => 1
            case false => 0
        }
    }

    /**
     * Extracts the least significant bit (LSb) from the given byte, returning whether it's equal to one.
     *
     * @param clearByte a byte from which to extract the LSb as a boolean.
     * @return whether the given byte's LSb is one.
     */
    def bitFromByte(clearByte: Byte): Boolean = {
        (clearByte & 0x01) == 1
    }
}
