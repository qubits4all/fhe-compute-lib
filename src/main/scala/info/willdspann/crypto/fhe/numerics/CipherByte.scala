package info.willdspann.crypto.fhe.numerics

import java.security.PublicKey

import info.willdspann.crypto.fhe.FheOperations
import info.willdspann.crypto.fhe.clear.numerics.ClearByte
import info.willdspann.crypto.numerics.IntegerLike

class CipherByte(private val bits: Array[CipherBit] = new Array(CipherByte.SIZE))
                (implicit val publicKey: PublicKey, implicit val fheOps: FheOperations)
    extends IntegerLike[CipherByte]
    with Iterable[CipherBit]
{
    override def xor(integer: CipherByte): CipherByte = {
        val xored: Array[CipherBit] = bits.zipWithIndex.map[CipherBit] { (cbit: CipherBit, i: Int) =>
            cbit.xor(integer.bits(i))
        }

        new CipherByte(xored)
    }

    override def and(integer: CipherByte): CipherByte = {
        val anded = bits.zipWithIndex.map[CipherBit] { (cbit: CipherBit, i: Int) =>
            cbit.and(integer.bits(i))
        }

        new CipherByte(anded)
    }

    override def add(integer: CipherByte): CipherByte = ???

    override def subtract(integer: CipherByte): CipherByte = {
        add(integer.negate())
    }

    override def negate(): CipherByte = {
        complement().add(CipherByte.one())
    }

    override def complement(): CipherByte = {
        val complemented: Array[CipherBit] = bits.map {
            _.complement()
        }

        new CipherByte(complemented)
    }

    def toArray: Array[CipherBit] = {
        Array.copyOf(bits, CipherByte.SIZE)
    }

    override def iterator: Iterator[CipherBit] = {
        toArray.iterator
    }
}

object CipherByte {
    val SIZE = 8

    def apply(bits: Array[CipherBit])(implicit publicKey: PublicKey, fheOps: FheOperations): CipherByte = {
        new CipherByte(bits)
    }

    def apply(cleartextByte: Byte)(implicit publicKey: PublicKey, fheOps: FheOperations): CipherByte = {
        val clearByte = ClearByte.apply(cleartextByte)
        // TODO...
        ???
    }

    def zero()(implicit publicKey: PublicKey, fheOps: FheOperations): CipherByte = {
        val zero = CipherBit.zero()(publicKey, fheOps)
        val zeros = Array.fill(SIZE)(zero)

        new CipherByte(zeros)
    }

    def one()(implicit publicKey: PublicKey, fheOps: FheOperations): CipherByte = {
        val zero = CipherBit.zero()(publicKey, fheOps)
        val cbits = Array.fill(SIZE)(zero)
        cbits(0) = CipherBit.one()(publicKey, fheOps)

        new CipherByte(cbits)
    }
}
