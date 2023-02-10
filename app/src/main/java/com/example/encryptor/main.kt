import android.os.Build
import androidx.annotation.RequiresApi
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec
import java.util.*

@RequiresApi(Build.VERSION_CODES.O)
fun encryptAES256(plainText: String, secretKey: String): String {
    val key = SecretKeySpec(secretKey.toByteArray(Charsets.UTF_8), "AES")
    val cipher = Cipher.getInstance("AES/ECB/PKCS5Padding")
    cipher.init(Cipher.ENCRYPT_MODE, key)
    val encryptedBytes = cipher.doFinal(plainText.toByteArray(Charsets.UTF_8))
    return Base64.getEncoder().encodeToString(encryptedBytes)
}

@RequiresApi(Build.VERSION_CODES.O)
fun decryptAES256(encryptedText: String, secretKey: String): String {
    val encryptedBytes = Base64.getDecoder().decode(encryptedText)
    val key = SecretKeySpec(secretKey.toByteArray(Charsets.UTF_8), "AES")
    val cipher = Cipher.getInstance("AES/ECB/PKCS5Padding")
    cipher.init(Cipher.DECRYPT_MODE, key)
    val decryptedBytes = cipher.doFinal(encryptedBytes)
    return String(decryptedBytes, Charsets.UTF_8)
}

@RequiresApi(Build.VERSION_CODES.O)
fun main() {
    val secretKey = "secretkey123456secretkey"
    val plainText = "hello world"
    val encryptedText = encryptAES256(plainText, secretKey)
    println("Encrypted text: $encryptedText")
    val decryptedText = decryptAES256(encryptedText, secretKey)
    println("Decrypted text: $decryptedText")
}
