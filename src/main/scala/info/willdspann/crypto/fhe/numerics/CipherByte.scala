package info.willdspann.crypto.fhe.numerics

import java.security.PublicKey

import info.willdspann.crypto.fhe.FheOperations
import info.willdspann.crypto.numerics.IntegerLike

class CipherByte(private val bits: Array[CipherBit] = new Array(CipherByte.SIZE))
                (implicit val publicKey: PublicKey, implicit val fheOps: FheOperations) <: IntegerLike[CipherByte]
{
    override def xor(integer: CipherByte): CipherByte = ???

    override def and(integer: CipherByte): CipherByte = ???

    override def add(integer: CipherByte): CipherByte = ???

    override def subtract(integer: CipherByte): CipherByte = ???

    override def negate(): CipherByte = ???

    override def complement(): CipherByte = ???
}

object CipherByte {
    val SIZE = 8
}
