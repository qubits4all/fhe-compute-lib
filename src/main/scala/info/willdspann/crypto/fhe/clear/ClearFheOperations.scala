package info.willdspann.crypto.fhe.clear

import java.security.{PrivateKey, PublicKey}

import info.willdspann.crypto.fhe.{FheOperations, WrappedCiphertext}
import info.willdspann.crypto.fhe.clear.WrappedCleartext.byteFromBit

/**
 * <p>An `FheOperations` implementation that performs all operations in the clear, instead of actually doing any fully-
 * homomorphic encryption (FHE).</p>
 * <p>
 * This implementation is useful for testing programs using this FHE library for correctness, as well as measuring a
 * baseline for performance testing of various FHE schemes. </p>
 */
class ClearFheOperations() <: FheOperations {

    override def encrypt(bit: Boolean)(implicit publicKey: PublicKey): WrappedCiphertext = {
        WrappedCleartext(bit)
    }

    override def decrypt(cipherBit: WrappedCiphertext, privateKey: PrivateKey = null): Boolean = {
        cipherBit match {
            case cleartext: WrappedCleartext => cleartext.clearBit
            case ciphertext: WrappedCiphertext => throw new IllegalArgumentException(
                s"WrappedCleartext required but another WrappedCiphertext was provided: ${ciphertext.getClass}"
            )
        }
    }

    override def fheXor(cipherBit1: WrappedCiphertext, cipherBit2: WrappedCiphertext): WrappedCiphertext = {
        val clearBit1: Byte = byteFromBit(decrypt(cipherBit1))
        val clearBit2: Byte = byteFromBit(decrypt(cipherBit2))

        // XOR the two zero-padded bits (as Ints), then clear all but the LSb using an 0x01 mask, and fetch the LSB.
        val xorByte: Byte = ((clearBit1 ^ clearBit2) & 0x01).byteValue

        WrappedCleartext(Array(xorByte))
    }

    override def fheAnd(cipherBit1: WrappedCiphertext, cipherBit2: WrappedCiphertext): WrappedCiphertext = {
        val clearBit1: Byte = byteFromBit(decrypt(cipherBit1))
        val clearBit2: Byte = byteFromBit(decrypt(cipherBit2))

        val andByte: Byte = ((clearBit1 & clearBit2) & 0x01).byteValue

        WrappedCleartext(Array(andByte))
    }
}

object ClearFheOperations {

    def apply(): ClearFheOperations = {
        new ClearFheOperations()
    }
}
