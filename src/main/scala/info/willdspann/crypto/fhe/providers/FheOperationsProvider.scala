package info.willdspann.crypto.fhe.providers

import info.willdspann.crypto.fhe.FheOperations

trait FheOperationsProvider {

    def getFheOperations: FheOperations
}
