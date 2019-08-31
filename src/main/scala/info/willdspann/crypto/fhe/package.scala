package info.willdspann.crypto

import java.security.PublicKey

import info.willdspann.crypto.fhe.clear.NoOpFhePublicKey

package object fhe {
    /** Default `FheOperations` implementation. */
    implicit val defaultFheOps: FheOperations = FheOperations.getFheOperationsProvider.getFheOperations

    /** Default `PublicKey` for use with the `FheOperations`. */
    implicit val defaultPubKey: PublicKey = NoOpFhePublicKey
}
