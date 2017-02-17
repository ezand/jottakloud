package org.ezand.jottakloud

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule
import com.fasterxml.jackson.dataformat.xml.XmlMapper
import com.fasterxml.jackson.datatype.joda.JodaModule
import com.fasterxml.jackson.module.kotlin.KotlinModule
import khttp.get
import khttp.responses.Response
import khttp.structures.authorization.BasicAuthorization
import mu.KLogging
import org.ezand.jottakloud.data.*
import org.ezand.jottakloud.deserializers.JottacloudDateTimeDeserializer
import org.joda.time.DateTime
import java.io.InputStream
import java.net.URL
import java.net.URLEncoder

class Jottacloud(val auth: JottacloudAuthentication, val baseUrl: URL = URL("https://www.jottacloud.com/jfs")) {
    companion object : KLogging()

    private val xmlMapper = XmlMapper()
            .registerModule(KotlinModule())
            .registerModule(JacksonXmlModule())
            .registerModule(JodaModule().addDeserializer(DateTime::class.java, JottacloudDateTimeDeserializer()))
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .setPropertyNamingStrategy(PropertyNamingStrategy.KEBAB_CASE)

    fun getUser(): User? {
        val url = buildUrl(auth.username)
        logger.debug { "Getting user: $url" }

        val response = get(url, auth = auth.basicAuth())
        return objectOrNull(response)
    }

    fun getDevice(deviceName: String): Device? {
        val url = buildUrl("${auth.username}/$deviceName")
        logger.debug { "Getting device '$deviceName': $url" }

        val response = get(url, auth = auth.basicAuth())
        return objectOrNull(response)
    }

    fun getMountPoint(deviceName: String, mountPointName: String): MountPoint? {
        val url = buildUrl("${auth.username}/$deviceName/$mountPointName")
        logger.debug { "Getting mount point: '$deviceName' -> '$mountPointName': $url" }

        val response = get(url, auth = auth.basicAuth())
        return objectOrNull(response)
    }

    fun getFolder(deviceName: String, mountPointName: String, path: String): FolderDetails? {
        val url = buildUrl("${auth.username}/$deviceName/$mountPointName/$path")
        logger.debug { "Getting folder: '$deviceName' -> '$mountPointName' -> '$path': $url" }

        val response = get(url, auth = auth.basicAuth())
        return objectOrNull(response)
    }

    fun getFile(deviceName: String, mountPointName: String, path: String): FileDetails? {
        val url = buildUrl("${auth.username}/$deviceName/$mountPointName/$path")
        logger.debug { "Getting file: '$deviceName' -> '$mountPointName' -> '$path': $url" }

        val response = get(url, auth = auth.basicAuth())
        return objectOrNull(response)
    }

    fun getFiles(deviceName: String, mountPointName: String, path: String, recursive: Boolean = false): List<FileDetails> {
        return getFiles(deviceName, mountPointName, path, emptyList(), recursive)
    }

    fun downloadFile(deviceName: String, mountPointName: String, path: String): InputStream? {
        val url = buildUrl("${auth.username}/$deviceName/$mountPointName/$path?mode=bin")
        logger.debug { "Getting file: '$deviceName' -> '$mountPointName' -> '$path': $url" }

        val response = get(url, auth = auth.basicAuth())
        return streamOrNull(response)
    }

    private fun getFiles(deviceName: String, mountPointName: String, path: String, files: List<FileDetails>, recursive: Boolean): List<FileDetails> {
        val folder = getFolder(deviceName, mountPointName, path)
        if (folder != null) {
            val filesInFolder = folder.files.map { getFile(deviceName, mountPointName, it.name) }.filterNotNull()

            if (recursive) {
                return folder.folders.fold(filesInFolder) { f, subFolder ->
                    return getFiles(deviceName, mountPointName, subFolder.name, f, recursive)
                }
            } else {
                return filesInFolder
            }
        } else {
            return files
        }
    }

    private fun JottacloudAuthentication.basicAuth(): BasicAuthorization {
        return BasicAuthorization(this.username, this.password)
    }

    private fun buildUrl(url: String): String {
        val encodedUrl = url.split("/")
                .map { URLEncoder.encode(it, "utf-8") }
                .joinToString("/")

        return "$baseUrl/$encodedUrl"
    }

    inline private fun <reified T : Any> objectOrNull(response: Response): T? {
        if (response.statusCode == 200) {
            return xmlMapper.readValue(response.text, T::class.java)
        }
        else {
            logger.debug("Got status code ${response.statusCode}: ${response.text}")
            return null
        }
    }

    private fun streamOrNull(response: Response): InputStream? {
        if (response.statusCode == 200) return response.raw else return null
    }
}
