package info.willdspann.crypto.numerics

trait IntegerLike {

    def xor(integer: IntegerLike): IntegerLike

    def and(integer: IntegerLike): IntegerLike

    def add(integer: IntegerLike): IntegerLike

    def subtract(integer: IntegerLike): IntegerLike

    def negate(): IntegerLike

    def complement(): IntegerLike
}
