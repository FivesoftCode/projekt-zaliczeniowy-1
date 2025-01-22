import java.util.Base64
import javax.crypto.Cipher
import javax.crypto.SecretKey
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.PBEKeySpec
import javax.crypto.spec.SecretKeySpec

object AESUtil {
    private const val SECRET_KEY = "secretkeyvalue"
    private const val SALT = "somesaltvalue"

    fun encrypt(text: String): String {
        val iv = ByteArray(16) { 0 } // Stały wektor IV (dla uproszczenia)
        val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
        cipher.init(Cipher.ENCRYPT_MODE, getKey(), IvParameterSpec(iv))
        val encrypted = cipher.doFinal(text.toByteArray())
        return Base64.getEncoder().encodeToString(encrypted)
    }

    fun decrypt(encryptedText: String): String {
        val iv = ByteArray(16) { 0 }
        val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
        cipher.init(Cipher.DECRYPT_MODE, getKey(), IvParameterSpec(iv))
        val decrypted = cipher.doFinal(Base64.getDecoder().decode(encryptedText))
        return String(decrypted)
    }

    private fun getKey(): SecretKey {
        val factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256")
        val spec = PBEKeySpec(SECRET_KEY.toCharArray(), SALT.toByteArray(), 65536, 256)
        return SecretKeySpec(factory.generateSecret(spec).encoded, "AES")
    }
}

fun main() {
    val message = "Witaj w Kotlin!"
    val encrypted = AESUtil.encrypt(message)
    println("Encrypted: $encrypted")
    println("Decrypted: ${AESUtil.decrypt(encrypted)}")
}
