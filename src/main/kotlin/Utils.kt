import java.math.BigInteger
import java.security.MessageDigest

/**
 * Converts this [String] to `MD5` hash.
 */
fun String.md5(): String = MessageDigest.getInstance("MD5")
    .digest(toByteArray())
    .let { BigInteger(1, it) }
    .toString(16)

/**
 * Extracts all integer numbers from this [String] and converts them to a list of [Int].
 */
fun String.toIntegers() = extractNumbers()
    .map { it.value.toInt() }
    .toList()

/**
 * Extracts all long numbers from this [String] and converts them to a list of [Long].
 */
fun String.toLongs() = extractNumbers()
    .map { it.value.toLong() }
    .toList()

private fun String.extractNumbers() = """-?\d+""".toRegex().findAll(this)
