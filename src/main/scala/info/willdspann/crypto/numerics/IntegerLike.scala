package info.willdspann.crypto.numerics

trait IntegerLike[I <: IntegerLike[I]] {

    def xor(integer: I): I

    def and(integer: I): I

    def add(integer: I): I

    def subtract(integer: I): I

    def negate(): I

    def complement(): I
}
