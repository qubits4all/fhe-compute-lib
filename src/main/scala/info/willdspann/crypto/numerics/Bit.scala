package info.willdspann.crypto.numerics

trait Bit[B <: Bit[B]] {

    def xor(bit: B): B

    def and(bit: B): B

    def complement(): B
}
