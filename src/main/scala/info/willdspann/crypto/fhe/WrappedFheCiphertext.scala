package info.willdspann.crypto.fhe

import java.security.PublicKey

/**
 * Immutable value object for containing an FHE ciphertext corresponding to an encrypted bit, along with the FHE
 * public key under which it was encrypted.
 *
 * @param ciphertext byte array containing the ciphertext for an encrypted bit.
 * @param publicKey the FHE public key under which the encrypted bit was encrypted.
 */
class WrappedFheCiphertext(ciphertext: Array[Byte], override val publicKey: PublicKey) extends WrappedCiphertext {
    private val encBytes: Array[Byte] = Array.copyOf(ciphertext, ciphertext.length)

    override def ciphertextBytes: Array[Byte] = Array.copyOf(encBytes, encBytes.length)
}
