package info.willdspann.crypto.fhe

import java.security.PublicKey

/**
 * Immutable value object for containing an FHE ciphertext corresponding to an encrypted bit, along with the FHE
 * public key under which it was encrypted.
 */
trait WrappedCiphertext {

    def ciphertextBytes: Array[Byte]

    val publicKey: PublicKey
}
