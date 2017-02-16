package org.ezand.jottakloud.data

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty
import org.joda.time.DateTime
import java.util.*

data class FileDetails(
        @JacksonXmlProperty(isAttribute = true) val name: String,
        val uuid: UUID,
        val time: DateTime,
        val path: String,
        val abspath: String,
        @JacksonXmlProperty(localName = "currentRevision") val currentRevision: FileRevision,
        val revisions: List<FileRevision>?
)
