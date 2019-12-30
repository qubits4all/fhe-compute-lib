package info.willdspann.crypto.fhe.clear.numerics

import info.willdspann.crypto.fhe.FheOperations
import info.willdspann.crypto.numerics.IntegerLike

// TODO: Also implement Iterable[ClearBit].
class ClearByte(private val bits: Array[ClearBit])(implicit val fheOps: FheOperations) extends IntegerLike[ClearByte] {

    override def xor(integer: ClearByte): ClearByte = ???

    override def and(integer: ClearByte): ClearByte = ???

    override def add(integer: ClearByte): ClearByte = ???

    override def subtract(integer: ClearByte): ClearByte = ???

    override def negate(): ClearByte = ???

    override def complement(): ClearByte = ???

    def toArray: Array[ClearBit] = {
        Array.copyOf(bits, ClearByte.SIZE)
    }

    def iterator: Iterator[ClearBit] = {
        toArray.iterator
    }
}

object ClearByte {
    val SIZE = 8

    def apply(cleartextByte: Byte)(implicit fheOps: FheOperations): ClearByte = ???

}
