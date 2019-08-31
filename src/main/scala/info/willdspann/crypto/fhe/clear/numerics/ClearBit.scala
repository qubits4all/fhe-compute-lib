package info.willdspann.crypto.fhe.clear.numerics

import java.security.PublicKey

import info.willdspann.crypto.fhe.clear.NoOpFhePublicKey
import info.willdspann.crypto.fhe.providers.ClearFheOperationsProvider
import info.willdspann.crypto.fhe.{FheOperations, WrappedCiphertext}
import info.willdspann.crypto.numerics.Bit

class ClearBit(cleartextBit: WrappedCiphertext)(implicit val fheOps: FheOperations) <: Bit[ClearBit]
{
    import ClearBit.defaultPubKey

    private val clrBit: WrappedCiphertext = cleartextBit

    def this(clearBit: Boolean = false)(implicit fheOps: FheOperations) {
        this(fheOps.encrypt(clearBit))
    }

    override def xor(bit: ClearBit): ClearBit = {
        val clearBytes: WrappedCiphertext = fheOps.fheXor(this.clrBit, bit.clrBit)
        ClearBit(clearBytes)
    }

    override def and(bit: ClearBit): ClearBit = {
        val clearBytes: WrappedCiphertext = fheOps.fheAnd(this.clrBit, bit.clrBit)
        ClearBit(clearBytes)
    }

    override def complement(): ClearBit = {
        this.xor(ClearBit.ONE)
    }
}

object ClearBit {
    private implicit val defaultPubKey: PublicKey = NoOpFhePublicKey
    private implicit val fheOps: FheOperations = ClearFheOperationsProvider.getFheOperations

    lazy val ZERO: ClearBit = new ClearBit()
    lazy val ONE: ClearBit = new ClearBit(true)

    def apply(clearBit: Boolean = false): ClearBit = {
        new ClearBit(clearBit)
    }

    def apply(cleartextBit: WrappedCiphertext): ClearBit = {
        new ClearBit(cleartextBit)
    }
}
