package info.willdspann.crypto.fhe

import java.security.{PrivateKey, PublicKey}

import info.willdspann.crypto.fhe.providers.{ClearFheOperationsProvider, FheOperationsProvider}

trait FheOperations {

    def encrypt(bit: Boolean)(implicit publicKey: PublicKey): WrappedCiphertext

    def decrypt(cipherBit: WrappedCiphertext, privateKey: PrivateKey): Boolean

    def fheXor(cipherBit1: WrappedCiphertext, cipherBit2: WrappedCiphertext): WrappedCiphertext

    def fheAnd(cipherBit1: WrappedCiphertext, cipherBit2: WrappedCiphertext): WrappedCiphertext
}

object FheOperations {

    def getFheOperationsProvider: FheOperationsProvider = ClearFheOperationsProvider

}
