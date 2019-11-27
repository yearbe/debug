package com.debug.configuration.util

import java.security.MessageDigest

/**
 *
 * @author Lyb
 * @since 2019-11-27
 */
fun String.toMD5(): String {
    val bytes = MessageDigest.getInstance("MD5").digest(this.toByteArray())
    return bytes.toHex()
}

fun ByteArray.toHex(): String {
    return joinToString("") { "%02x".format(it) }
}