package info.willdspann.crypto.fhe.clear

import java.security.PublicKey

import info.willdspann.crypto.fhe.WrappedCiphertext

/**
 * Simple implementation of `WrappedCiphertext` for wrapping a `ClearBit`'s cleartext value, which is "encrypted" with
 * a `NoOpFhePublicKey`.
 */
class WrappedCleartext(cleartext: Array[Byte]) <: WrappedCiphertext {
    private val clearBytes = Array.copyOf(cleartext, cleartext.length)

    override def ciphertextBytes: Array[Byte] = Array.copyOf(clearBytes, clearBytes.length)

    override val publicKey: PublicKey = NoOpFhePublicKey
}
