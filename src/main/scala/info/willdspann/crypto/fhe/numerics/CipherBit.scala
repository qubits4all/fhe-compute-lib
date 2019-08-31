package info.willdspann.crypto.fhe.numerics

import java.security.PublicKey

import info.willdspann.crypto.fhe.{FheOperations, WrappedCiphertext}
import info.willdspann.crypto.numerics.Bit

/**
 * Implementation of an encrypted `Bit` that supports fully-homomorphic encryption (FHE) operations, including the
 * boolean XOR and AND operations on the encrypted bit value.
 *
 * @param ciphertextBit wrapped
 * @param publicKey
 * @param fheOps
 */
class CipherBit(ciphertextBit: WrappedCiphertext)(implicit val publicKey: PublicKey,
                                                  implicit val fheOps: FheOperations) <: Bit[CipherBit]
{
    private val encBit: WrappedCiphertext = ciphertextBit

    lazy val ONE: CipherBit = CipherBit.one()

    def this(clearBit: Boolean = false)(implicit publicKey: PublicKey, fheOps: FheOperations) {
        this(fheOps.encrypt(clearBit))
    }

    override def xor(bit: CipherBit): CipherBit = {
        val encBytes = fheOps.fheXor(this.encBit, bit.encBit)
        CipherBit(encBytes)
    }

    override def and(bit: CipherBit): CipherBit = {
        val encBytes = fheOps.fheAnd(this.encBit, bit.encBit)
        CipherBit(encBytes)
    }

    override def complement(): CipherBit = {
        this.xor(ONE)
    }
}

object CipherBit {

    def apply(clearBit: Boolean = false)(implicit publicKey: PublicKey, fheOps: FheOperations): CipherBit = {
        new CipherBit(clearBit)
    }

    def apply(ciphertextBit: WrappedCiphertext)(implicit publicKey: PublicKey, fheOps: FheOperations): CipherBit = {
        new CipherBit(ciphertextBit)
    }

    def zero()(implicit publicKey: PublicKey, fheOps: FheOperations): CipherBit = {
        new CipherBit(false)(publicKey, fheOps)
    }

    def one()(implicit publicKey: PublicKey, fheOps: FheOperations): CipherBit = {
        new CipherBit(true)(publicKey, fheOps)
    }
}
