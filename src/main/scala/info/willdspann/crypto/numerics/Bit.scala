package info.willdspann.crypto.numerics

trait Bit {

    def xor(bit: Bit): Bit

    def and(bit: Bit): Bit
}
