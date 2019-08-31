package info.willdspann.crypto.fhe.providers

import info.willdspann.crypto.fhe.FheOperations
import info.willdspann.crypto.fhe.clear.ClearFheOperations

/**
 * Provides an `FheOperations` implementation that performs all operations in the clear, instead of actually doing
 * any fully-homomorphic encryption (FHE).
 *
 * @see ClearFheOperations
 */
object ClearFheOperationsProvider <: FheOperationsProvider {

    override lazy val getFheOperations: FheOperations = ClearFheOperations()
}
