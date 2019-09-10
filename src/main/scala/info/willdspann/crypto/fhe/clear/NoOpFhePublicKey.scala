package info.willdspann.crypto.fhe.clear

import java.security.PublicKey

/**
 * A no-op `PublicKey` for use with `ClearFheOperations`.
 */
case object NoOpFhePublicKey extends PublicKey {

    override val getAlgorithm: String = "NoOp"

    override val getFormat: String = ""

    override val getEncoded: Array[Byte] = null
}
